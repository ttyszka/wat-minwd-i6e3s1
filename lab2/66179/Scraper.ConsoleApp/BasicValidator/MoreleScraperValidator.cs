using Scraper.ConsoleApp.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
using static Scraper.ConsoleApp.Enums.ValidatorHelper;

namespace Scraper.ConsoleApp.BasicValidator
{
    public class MoreleScraperValidator : IValidator
    {
        private readonly string _httpRegexPattern = @"^(http|https|ftp|)\://|[a-zA-Z0-9\-\.]+\.[a-zA-Z](:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\-\._\?\,\'/\\\+&amp;%\$#\=~])*[^\.\,\)\(\s]$";
        public InputTypeEnum IsCorrectInput(string input)
        {
            if (ValidateIfCorrectUrl(input))
                return InputTypeEnum.Url;
            else if (input.ToLower() == "q")
                return InputTypeEnum.SearchEngineParam;
            else
                return InputTypeEnum.Incorrect;
        }
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
