using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using TwitterApp.TwitterDataAccessLayer.Entities;
using TwitterApp.TwitterDataAccessLayer.Interfaces;

namespace TwitterApp.TwitterDataAccessLayer.TwitterApiServices
{
    public class ApiService : IApiService
    {
        #region Private readonly strings
        private readonly string _id = "user_id=";
        private readonly string _count = "count=";
        #endregion
        private readonly IOAuthService _oAuthService;
        public ApiService(IOAuthService oAuthService)
        {
            _oAuthService = oAuthService;
        }
        //generates full url
        private string GenerateFullUrl(string userId, string count, string baseUrl) => 
            string.Concat(baseUrl, _id, userId, "&", _count, count);

        private async Task<string> GetResponseJsonAsync(string userId, string count)
        {
            var baseUrl = _oAuthService.GetBaseUrl;
            var tinyOAuthHandler = _oAuthService.GetOAuthDataForRequest();

            var fullUrl = GenerateFullUrl(userId, count, baseUrl);

            using var httpClient = new HttpClient(tinyOAuthHandler);

            var response = await httpClient.GetAsync(fullUrl);
            if (!response.IsSuccessStatusCode)
                return "";
            return await response.Content.ReadAsStringAsync();
        }

        public async Task<UserProfile> GetUserProfileAsync(string userId)
        {
            // get only the first timeline post to retrive user information
            var wholeResponse = await GetResponseJsonAsync(userId, "1");
            // if unsuccessful status code  - return User with -1 Id
            if (wholeResponse == "")
                return new UserProfile() { Id = "-1"};
            // if unsuccessful deserialization - return User with -2 Id
            var deserializedResponse = JsonConvert.DeserializeObject<List<BaseTimeline>>(wholeResponse);
            if (!deserializedResponse.Any())
                return new UserProfile() { Id = "-2"};

            return deserializedResponse.First().User;
        }

        public async Task<List<string>> GetAllTweetsTextsAsync(string userId, int tweetsCount)
        {
            var wholeResponse = await GetResponseJsonAsync(userId, tweetsCount.ToString());
            if (wholeResponse == "")
                return new List<string>();
            var deserializedResponse = JsonConvert.DeserializeObject<List<BaseTimeline>>(wholeResponse);

            var textSentences = new List<string>();

            foreach(var entity in deserializedResponse)
            {
                textSentences.Add(entity.Text);
            }
            return textSentences;
        }

        public async Task<List<string>> GetAllTweetIdsAsync(string userId, int tweetsCount)
        {
            var wholeResponse = await GetResponseJsonAsync(userId, tweetsCount.ToString());
            if (wholeResponse == "")
                return new List<string>();
            var deserializedResponse = JsonConvert.DeserializeObject<List<BaseTimeline>>(wholeResponse);

            var tweetIds = new List<string>();

            foreach(var entity in deserializedResponse)
            {
                tweetIds.Add(entity.Id_str);
            }
            return tweetIds;
        }
    }
}
