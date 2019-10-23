using Newtonsoft.Json;
using OtomotoScraper.Models;
using System;
using System.Net.Http;

namespace OtomotoScraper
{
    public class ImgToBase64
    {
        public string ConvertImgToBase64(string url)
        {
            var httpClient = new HttpClient();

            var productBase64 = new ProductUrlBase64();
            var bytes = httpClient.GetByteArrayAsync(url).Result;
            productBase64.ImageBase64 = Convert.ToBase64String(bytes);

            return JsonConvert.SerializeObject(productBase64);
        }
    }
}
