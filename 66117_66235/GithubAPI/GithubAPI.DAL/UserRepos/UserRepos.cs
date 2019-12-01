using Github.DAL.Models;
using GithubAPI.DAL;
using GithubAPI.DAL.Helpers;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Github.DAL.UserRepos
{
    public class UserRepos : IUserRepos
    {
        public async Task<IEnumerable<UserReposModel>> GetUserRepos(string relativeUrl)
        {
            ApiHelper.InitializeClient();
            string baseUrl = "https://api.github.com/users/";
            string suffix = "/repos";
            string url = baseUrl + relativeUrl + suffix;

            using (HttpResponseMessage response = await ApiHelper.ApiClient.GetAsync(url))
            {
                if (response.IsSuccessStatusCode)
                {
                    // get json
                    string responseBody = await response.Content.ReadAsStringAsync();

                    // convert json to object
                    var userRepos = JsonConvert.DeserializeObject<List<UserReposModel>>(responseBody);

                    return userRepos;
                }
                else
                {
                    throw new Exception(response.ReasonPhrase);
                }
            }
        }
    }
}
