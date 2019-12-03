using System.Collections.Generic;
using System.Threading.Tasks;
using TwitterApp.TwitterDataAccessLayer.Entities;

namespace TwitterApp.TwitterDataAccessLayer.Interfaces
{
    public interface IApiService
    {
        Task<UserProfile> GetUserProfileAsync(string userId);
        Task<List<string>> GetAllTweetsTextsAsync(string userId, int tweetsCount);
        Task<List<string>> GetAllTweetIdsAsync(string userId, int tweetsCount);
    }
}
