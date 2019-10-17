using Scraper.ConsoleApp.Scraper;
using System;

namespace Scraper.ConsoleApp
{
    public class BasicConsoleApp
    {
        static void Main(string[] args)
        {
            // Create Object From Hello Scrape class
            var scrape = new MoreleScraper();
            // Start Scraping
            scrape.Start();
        }
    }
}
