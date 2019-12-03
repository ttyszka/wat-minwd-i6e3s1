using IronWebScraper;
using System;
using System.Collections.Generic;
using System.Text;

namespace Scraper.ConsoleApp.Scraper
{
    public class DataCounterScraper : WebScraper
    {
        #region Readonly String Path Declarations
        // Readonly div classes which are used in morele.net parsing.
        private string _url;
        private readonly string _numberOfPagesContainerClass = ".pagination-wrapper > ul";
        #endregion

        #region Public fields and properties
        public int NumberOfPages { get; set; }
        #endregion

        public DataCounterScraper(string? url)
        {
            _url = url;
        }
        public override void Init()
        {
            LoggingLevel = LogLevel.None;
            Request(_url, Parse);
        }

        public override void Parse(Response response)
        {
            // call of a method taking number of pages before the main function working
            if (response.CssExists(_numberOfPagesContainerClass))
                NumberOfPages = GetNumberOfPages(response.Css(_numberOfPagesContainerClass)[0]);
        }

        #region Private parsing helper methods
        /// <summary>
        ///  Method which takes the data-count attribute and parse it to the int value
        /// </summary>
        /// <param name="numberOfPagesUl"></param>
        /// <returns>number of Pages / 0 if there is no content to get attribute from</returns>
        private int GetNumberOfPages(HtmlNode numberOfPagesUl)
        {
            int numberOfPages;
            var dataCountAttribute = numberOfPagesUl.GetAttribute("data-count");
            int.TryParse(dataCountAttribute, out numberOfPages);
            return numberOfPages;
        }
        #endregion
    }
}
