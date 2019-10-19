using System;
using System.Collections.Generic;
using System.Text;

namespace Scraper.ConsoleApp.Enums
{
    public static class ValidatorHelper
    {
        private static readonly string _baseSearchEngineUrl = "https://www.morele.net/wyszukiwarka/0/0/,,1,,,,,,,,,,/1/?q=";
        public enum InputTypeEnum
        { 
            Url,
            SearchEngineParam,
            Incorrect
        }
        public static string SearchEngineUrlConverter(string searchPhrase)
        {
            return string.Concat(_baseSearchEngineUrl, searchPhrase);
        }
    }
}
