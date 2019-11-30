import base64
import re
from io import BytesIO

import numpy as np
import tweepy
from PIL import Image
from django.contrib.auth.decorators import login_required
from django.contrib.staticfiles import finders
from django.http import HttpResponseRedirect
from django.shortcuts import render
from tweepy.auth import OAuthHandler
from wordcloud import WordCloud, STOPWORDS

from backend.forms import WordcloudForm
from twitter_api_sample.settings import SOCIAL_AUTH_TWITTER_KEY, SOCIAL_AUTH_TWITTER_SECRET, TWITTER_ACCESS_TOKEN_KEY, \
    TWITTER_ACCESS_TOKEN_SECRET

auth = OAuthHandler(SOCIAL_AUTH_TWITTER_KEY, SOCIAL_AUTH_TWITTER_SECRET)
auth.set_access_token(TWITTER_ACCESS_TOKEN_KEY, TWITTER_ACCESS_TOKEN_SECRET)
api = tweepy.API(auth)


@login_required
def home(request):
    home_timeline = api.home_timeline()

    if request.method == 'POST':
        submit_button_value = request.POST.get('submit_button')

        if submit_button_value == 'tweet':
            handle_new_tweet_action(request)
        elif submit_button_value == 'favorite':
            handle_favorite_tweet_action(request)

        return HttpResponseRedirect("/")

    home_content = {'home_timeline': home_timeline}

    return render(request, 'app/home.html', home_content)


@login_required
def profile(request):
    form = WordcloudForm(request.POST or None)
    if request.method == 'POST' and form.is_valid():
        tweets_count = int(form.cleaned_data['tweets_count'])
    else:
        tweets_count = 10

    current_user = api.me()
    home_timeline = api.home_timeline(count=tweets_count)

    wordcloud = create_wordcloud(get_tweets_words(home_timeline), 400)
    profile_image_url = get_full_quality_profile_image_url(current_user.profile_image_url)

    profile_content = {'current_user': current_user, 'wordcloud': wordcloud, 'profile_image_url': profile_image_url,
                       'wordcloud_form': form}

    return render(request, 'app/profile.html', profile_content)


def handle_new_tweet_action(request):
    tweet_text = request.POST.get('tweet_text')
    if tweet_text:
        api.update_status(tweet_text)


def handle_favorite_tweet_action(request):
    tweet_id = request.POST.get('tweet_id')
    tweet_favorited = request.POST.get('tweet_favorited')

    print tweet_id
    print tweet_favorited

    if tweet_id:
        if tweet_favorited == 'False':
            api.create_favorite(id=tweet_id)
        elif tweet_favorited == 'True':
            api.destroy_favorite(id=tweet_id)


def get_full_quality_profile_image_url(profile_image_url):
    return profile_image_url.replace('_normal', '')


def get_tweets_words(home_timeline):
    tweets_list = []
    for tweet in home_timeline:
        tweets_list.append(tweet.text)

    tweets_content = " ".join(tweets_list)

    return re.sub("[^\w]", " ", tweets_content)


def create_wordcloud(text, max_words):
    url = finders.find('img/cloud.png')

    with open(url, 'rb') as image_file:
        mask = np.array(Image.open(image_file))

    wc = WordCloud(background_color="white",
                   mask=mask,
                   max_words=max_words,
                   stopwords=STOPWORDS)

    wc.generate(text)

    return get_base64(wc.to_image())


def get_base64(image):
    buffered = BytesIO()
    image.save(buffered, format="JPEG")
    img_str = base64.b64encode(buffered.getvalue())

    return img_str.decode('utf-8')
