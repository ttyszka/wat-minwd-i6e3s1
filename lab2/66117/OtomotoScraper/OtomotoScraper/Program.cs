using System;

namespace OtomotoScraper
{
    public class Program
    {
        static void Main(string[] args)
        {
            var GetHtml = new GetHtml();
            GetHtml.GetHtmlAsync();
            Console.ReadLine();
        }
    }
}
