using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.LookupModels;
using System.Linq;
using TwitterApp.TwitterDataAccessLayer.Interfaces;

namespace TwitterApp.BuisnessLogic.TweetIdsService
{
    public class TweetIdsService : ITweetIdsService
    {
        private readonly string userName = "_mknbl";
        private readonly IApiService _apiService;

        public TweetIdsService(IApiService apiService)
        {
            _apiService = apiService;
        }

        public async Task<TweetIdsModel> GetFirstAndLastTweetIdAsync(int numberOfTweets)
        {
            var tweetIds = await _apiService.GetAllTweetIdsAsync(userName, numberOfTweets);

            var TweetIdsModel = new TweetIdsModel();

            TweetIdsModel.firstTweetId = GetFirstTweetId(tweetIds);
            TweetIdsModel.lastTweetId = GetLastTweetId(tweetIds);

            return TweetIdsModel;
        }

        private string GetLastTweetId(List<string> tweetIds)
        {
            return tweetIds.ElementAt(0);
        }

        private string GetFirstTweetId(List<string> tweetIds)
        {
            return tweetIds.ElementAt(tweetIds.Count-1);
        }
    }
}
