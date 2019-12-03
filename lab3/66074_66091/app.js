const Twitter = require('./src/TwitterClient.js');
const client = new Twitter();
const express = require('express');
const cors = require('cors');
const app = express();
const port = 3000;
var tags = [];

app.use(cors());
app.listen(port, () => {
  console.log('Listen on port' + port);
})

app.get('/timeline', (req, res) => {
  client
    .get("statuses/user_timeline")
    .then(results => {
      let t = [];
      results.forEach(e => {
        let tweet = {};
        tweet.retweeted = e.retweeted;
        if (tweet.retweeted) {
          tweet.createTime = e.retweeted_status.created_at;
          tweet.hashtags = e.retweeted_status.entities.hashtags;
          tweet.source = e.retweeted_status.source;
          tweet.lang = e.retweeted_status.lang;
        } else {
          tweet.createTime = e.created_at;
          tweet.hashtags = e.entities.hashtags;
          tweet.source = e.source;
          tweet.lang = e.lang;
        }
        tweet.message = e.text;
        tweet.mentions = e.entities.user_mentions;
        t.push(tweet);
      });
      setTimeout(() => {
        res.json(t);
      }, 200);
    })
    .catch(console.error);
});

app.get('/user', (req, res) => {
  client
    .get("account/verify_credentials")
    .then(results => {
        res.json({
          'Uzytkownik': results.name,
          'Nazwa uzytkownika': results.screen_name,
          'Miejscowość': results.location,
          'Data założenia konta': results.created_sat,
          'Obserwujący': results.followers_count,
          'Obserwowani': results.friends_count,
          'Ilość postów': results.statuses_count
        })
      }

    )
});

app.get('/tags', (req, res) => {
  client
    .get("statuses/user_timeline")
    .then(results => {
      let hashtags;
      results.forEach(tweet => {
        if (tweet.retweeted) {
          hashtags = tweet.retweeted_status.entities.hashtags;
        } else {
          hashtags = tweet.entities.hashtags;
        }
        hashtags.forEach(tag => {
          tags.push(tag.text);
        });
      });
      let tagsArray = [];
      [...new Set(tags)].sort().forEach(tag => {
        let t = {}
        t.tag = tag;
        t.occurrences = tags.filter((v) => (v === tag)).length;
        tagsArray.push(t);
      });
      setTimeout(() => {
        res.json(tagsArray);
      }, 200);
    })
    .catch(console.error);
})