using Github.DAL.Models;
using GithubAPI.DAL;
using GithubAPI.DAL.Helpers;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Github.DAL.UserInfo
{
    public class UserInfo : IUserInfo
    {
        public async Task <UserInfoModel> GetUserInfo(string relativeUrl)
        {
            ApiHelper.InitializeClient();
            string baseUrl = "https://api.github.com/users/";
            string url = baseUrl + relativeUrl;

            using (HttpResponseMessage response = await ApiHelper.ApiClient.GetAsync(url))
            {
                if(response.IsSuccessStatusCode)
                {
                    // convert json to object
                    UserInfoModel user = await response.Content.ReadAsAsync<UserInfoModel>();
                    return user;
                } 
                else
                {
                    throw new Exception(response.ReasonPhrase);
                }
            }
        }
    }
}
