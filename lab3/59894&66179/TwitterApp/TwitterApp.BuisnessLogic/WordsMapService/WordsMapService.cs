using AutoMapper;
using MoreLinq;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.LookupModels;
using TwitterApp.TwitterDataAccessLayer.Interfaces;

namespace TwitterApp.BuisnessLogic.WordsMapService
{
    public class WordsMapService : IWordsMapService
    {
        private readonly string _userName = "_mknbl";
        private readonly Regex regex;
        private readonly Regex url;

        private readonly IApiService _apiService;
        public WordsMapService(IApiService apiService)
        {
            _apiService = apiService;
            regex = new Regex(@"/(\w+)\.\1/g", RegexOptions.IgnoreCase);
            url = new Regex(@"(https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|www\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\.[^\s]{2,}|https?:\/\/(?:www\.|(?!www))[a-zA-Z0-9]+\.[^\s]{2,}|www\.[a-zA-Z0-9]+\.[^\s]{2,})");
        }

        public async Task<List<WordsMapKeyValue>> GetWordsMapAsync(int numberOfTweets, int numberOfWords, int ignoreUrls)
        {
            var tweetTexts = await _apiService.GetAllTweetsTextsAsync(_userName, numberOfTweets);
            var allWords = GetAllWordsFromTweets(tweetTexts, ignoreUrls);

            var occurences = FindForOccurences(allWords);

            var sortedAndLimitedOccurences = SortAndTrimTheList(occurences, numberOfWords);

            return sortedAndLimitedOccurences;
        }

        private List<string> GetAllWordsFromTweets(List<string> tweets, int ignoreUrls)
        {
            var allWordsList = new List<string>();
            foreach(var tweet in tweets)
            {
                string [] splittedArray = tweet.Split(' ');
                allWordsList.AddRange(splittedArray);
            }
            if (ignoreUrls == 1)
                return RemoveUrls(allWordsList);
            return allWordsList;
        }

        private List<WordsMapKeyValue> FindForOccurences(List<string> words)
        {
            var dictionaryModels = new List<WordsMapKeyValue>();
            int increment = 1;
            foreach(string word in words)
            {
                var matchWord = word;
                matchWord = matchWord.ToLower();
                var dictionaryModel = new WordsMapKeyValue();
                int counter = 1;
                for(int i = increment; i< words.Count; i++ )
                {
                    var currentWord = words[i].ToLower();
                    if (currentWord == matchWord)
                        counter++;
                }
                increment = increment + 1;
                dictionaryModel.Text = word;
                dictionaryModel.Weight = counter;
                counter = 1;
                dictionaryModels.Add(dictionaryModel);
            }
            dictionaryModels = dictionaryModels.DistinctBy(x => x.Text).ToList();
            return dictionaryModels;
        }

        private List<WordsMapKeyValue> SortAndTrimTheList(List<WordsMapKeyValue> wordsMapKeyValues, int numberOfWords)
        {
            return  wordsMapKeyValues
                .OrderByDescending(wm => wm.Weight)
                .Take(numberOfWords)
                .ToList();
        }
        private List<string> RemoveUrls(List<string> words)
        {
            var newList = new List<string>();
            foreach(string word in words)
            {
                if (!url.IsMatch(word))
                    newList.Add(word);
            }
            return newList;
        }
    }
}
