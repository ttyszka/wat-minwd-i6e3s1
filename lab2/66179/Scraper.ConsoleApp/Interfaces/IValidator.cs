using System;
using System.Collections.Generic;
using System.Text;
using static Scraper.ConsoleApp.Enums.ValidatorHelper;

namespace Scraper.ConsoleApp.Interfaces
{
    public interface IValidator
    {
        InputTypeEnum IsCorrectInput(string input);
    }
}
