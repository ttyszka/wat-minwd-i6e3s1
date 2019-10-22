using Scraper.ConsoleApp.BasicValidator;
using Scraper.ConsoleApp.Interfaces;
using Scraper.ConsoleApp.Scraper;
using System;
using System.IO;
using static Scraper.ConsoleApp.Enums.ValidatorHelper;

namespace Scraper.ConsoleApp
{
    public class BasicConsoleApp
    {
        static void Main(string[] args)
        {
            // Basic variables initialization.
            bool isCorrectInput = false;
            bool isSearchEngineMode = false;
            InputTypeEnum inputType;
            string inputPhrase = "";
            // IValidator object creation.
            IValidator moreleValidator = new MoreleScraperValidator();

            //Basic menu to simplify usage.
            while (!isCorrectInput) 
            {
                Console.WriteLine("Please insert morele.net category url or press q button to insert search engine phrase, then press enter");
                var input = Console.ReadLine();
                inputType = moreleValidator.IsCorrectInput(input);
                if (inputType == InputTypeEnum.SearchEngineParam)
                {
                    Console.WriteLine();
                    Console.WriteLine("Please input search engine phrase, then press enter");
                    inputPhrase = Console.ReadLine();
                    isCorrectInput = true;
                    inputPhrase = inputPhrase.Replace(" ", "+");
                    inputPhrase = SearchEngineUrlConverter(inputPhrase);
                    isSearchEngineMode = true;
                }
                else if (inputType != InputTypeEnum.Incorrect && inputType == InputTypeEnum.Url)
                {
                    isCorrectInput = VerifyUrl(input);
                    inputPhrase = input;
                }
                else if(inputType == InputTypeEnum.Incorrect)
                {
                    Console.WriteLine();
                    Console.WriteLine("The input is incorrect, try again.");
                }
            }
            #region GetNumberOfPagesInUglyWay :/
            var numberOfPagesScrapper = new DataCounterScraper(inputPhrase);
            numberOfPagesScrapper.Start();
            int numberOfPages = numberOfPagesScrapper.NumberOfPages;
            #endregion
            // Create Object From Hello Scrape class
            var scrapeData = new MoreleCategoryScraper(inputPhrase, numberOfPages, isSearchEngineMode);
            // Start Scraping
            scrapeData.Start();

            
            Console.ReadKey();
        }
    }
}
