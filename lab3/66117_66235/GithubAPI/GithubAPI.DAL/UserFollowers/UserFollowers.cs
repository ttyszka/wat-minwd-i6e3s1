using Github.DAL.Models;
using GithubAPI.DAL.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Github.DAL.UserFollowers
{
    public class UserFollowers : IUserFollowers
    {
        public async Task<IEnumerable<UserFollowersModel>> GetUserFollowers(string relativeUrl)
        {
            ApiHelper.InitializeClient();
            string baseUrl = "https://api.github.com/users/";
            string suffix = "/followers";
            string url = baseUrl + relativeUrl + suffix;

            using (HttpResponseMessage response = await ApiHelper.ApiClient.GetAsync(url))
            {
                if (response.IsSuccessStatusCode)
                {
                    // get json
                    string responseBody = await response.Content.ReadAsStringAsync();

                    // convert json to object
                    var userFollowers = JsonConvert.DeserializeObject<List<UserFollowersModel>>(responseBody);

                    return userFollowers;
                }
                else
                {
                    throw new Exception(response.ReasonPhrase);
                }
            }
        }


    }
}
