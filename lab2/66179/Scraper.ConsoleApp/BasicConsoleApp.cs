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
            bool isCorrectInput = false;
            IValidator moreleValidator = new MoreleScraperValidator();
            InputTypeEnum inputType;
            string inputPhrase = "";

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
                    inputPhrase = SearchEngineUrlConverter(inputPhrase);
                }
                else if (inputType != InputTypeEnum.Incorrect && inputType == InputTypeEnum.Url)
                {
                    isCorrectInput = true;
                    inputPhrase = input;
                }
                else if(inputType == InputTypeEnum.Incorrect)
                {
                    Console.WriteLine();
                    Console.WriteLine("The input is incorrect, try again.");
                }
            }
            // Create Object From Hello Scrape class
            var scrape = new MoreleScraper(inputPhrase);
            // Start Scraping
            scrape.Start();
            
            Console.ReadKey();
        }
    }
}
