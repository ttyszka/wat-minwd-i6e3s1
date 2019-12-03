using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.LookupModels;

namespace TwitterApp.BuisnessLogic.TweetIdsService
{
    public interface ITweetIdsService
    {
        Task <TweetIdsModel> GetFirstAndLastTweetIdAsync(int numberOfTweets);
    }
}
