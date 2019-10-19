using System;
using System.Collections.Generic;
using System.Text;

namespace Scraper.ConsoleApp.Enums
{
    /// <summary>
    /// Static helper class for the validator.
    /// </summary>
    public static class ValidatorHelper
    {
        // Morele.net search engine url
        private static readonly string _baseSearchEngineUrl = "https://www.morele.net/wyszukiwarka/0/0/,,1,,,,,,,,,,/1/?q=";
        /// <summary>
        /// Enumerator precising entered input.
        /// </summary>
        public enum InputTypeEnum
        { 
            Url,
            SearchEngineParam,
            Incorrect
        }
        /// <summary>
        /// Method which concats current Morele.net base search engine url with phrase entered by user.
        /// </summary>
        /// <param name="searchPhrase"></param>
        /// <returns>Concated morele.net search engine base url and entered search phrase.</returns>
        public static string SearchEngineUrlConverter(string searchPhrase)
        {
            return string.Concat(_baseSearchEngineUrl, searchPhrase);
        }
    }
}
