using Scraper.ConsoleApp.Scraper;
using System;

namespace Scraper.ConsoleApp
{
    public class BasicConsoleApp
    {
        static void Main(string[] args)
        {
            string scrapingWebsiteUrl = "https://www.morele.net/laptopy/laptopy/notebooki-laptopy-ultrabooki-31/";
            // Create Object From Hello Scrape class
            var scrape = new MoreleScraper(scrapingWebsiteUrl);
            // Start Scraping
            scrape.Start();
        }
    }
}
