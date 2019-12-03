using Scraper.ConsoleApp.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using static Scraper.ConsoleApp.Enums.ValidatorHelper;

namespace Scraper.ConsoleApp.BasicValidator
{
    /// <summary>
    /// Validator class which validates entered input.
    /// </summary>
    public class MoreleScraperValidator : IValidator
    {
        // Url regex pattern.
        private readonly string _httpRegexPattern = @"^(http|https|ftp|)\://|[a-zA-Z0-9\-\.]+\.[a-zA-Z](:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\-\._\?\,\'/\\\+&amp;%\$#\=~])*[^\.\,\)\(\s]$";
        /// <summary>
        /// Method which returns exact InputTypeEnum for entered input.
        /// </summary>
        /// <param name="input"></param>
        /// <returns>InputTypeEnum</returns>
        public InputTypeEnum IsCorrectInput(string input)
        {
            if (ValidateIfCorrectUrl(input))
                return InputTypeEnum.Url;
            else if (input.ToLower() == "q")
                return InputTypeEnum.SearchEngineParam;
            else
                return InputTypeEnum.Incorrect;
        }
        /// <summary>
        /// Method which uses regex to check if entered url is valid. 
        /// </summary>
        /// <param name="userInput"></param>
        /// <returns>Boolean value.</returns>
        private bool ValidateIfCorrectUrl(string userInput)
        {
            var regex = new Regex(_httpRegexPattern, RegexOptions.Compiled | RegexOptions.IgnoreCase);
            if (regex.IsMatch(userInput))
                return true;
            else
                return false;
        }
    }
}
