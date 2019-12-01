using System;
using System.Net.Http;
using System.Net.Http.Headers;

namespace GithubAPI.DAL.Helpers
{
    public static class ApiHelper
    {
        public static HttpClient ApiClient { get; set; } 

        public static void InitializeClient()
        {
            ApiClient = new HttpClient();
            // ApiClient.BaseAddress = new Uri("https://api.github.com/users/");
            ApiClient.DefaultRequestHeaders.Accept.Clear();
            ApiClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            ApiClient.DefaultRequestHeaders.Add("User-Agent","user-agent");

        }
    }
}
