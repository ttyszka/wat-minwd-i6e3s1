using IronWebScraper;
using Scraper.ConsoleApp.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;

namespace Scraper.ConsoleApp.Scraper
{
    public class MoreleCategoryScraper : WebScraper
    {
        #region Readonly String Path Declarations
        // Readonly div classes which are used in morele.net parsing.
        private string _url;
        private int _numberOfPages;
        private readonly string _wholeProductPath = ".cat-product";
        private readonly string _productInsideDivPath = ".cat-product-inside";
        private readonly string _imgPath = ".cat-product-left > .cat-product-image > .product-image";
        private readonly string _imgContainingAttribute = "data-cfsrc";
        private readonly string _productNamePath = ".cat-product-content > .cat-product-center > .cat-product-center-inside >.cat-product-name";
        private readonly string _featureDescriptionPath = ".cat-product-features > .cat-product-feature";
        private readonly string _pricePath = ".cat-product-right > .cat-product-price > .price-new";
        #endregion

        #region Public collections and variables
        //Final parsed collection, containing product model entities.
        public bool SearchEngineMode { get; set; }
        public string CurrentUrl { get; set; }
        public int CurrentPage { get; set; }
        public List<ProductModel> FinalList { get; set; }
        #endregion

        #region Ctor
        public MoreleCategoryScraper(string? categoryPathUrl, int numberOfPages, bool searchEngineMode)
        {
            _url = categoryPathUrl;
            _numberOfPages = numberOfPages;
            SearchEngineMode = searchEngineMode;
            CurrentUrl = UpdateUrlForCurrentPage(1, _url);
            CurrentPage = 1;
            FinalList = new List<ProductModel>();
        }
        #endregion


        public override void Init()
        {
            // Scraper initialization, precised: loglevel, and working directory.
            LoggingLevel = LogLevel.Critical;
            Request(CurrentUrl, Parse);
            WorkingDirectory = Directory.GetCurrentDirectory() + @"\Output\";
        }

        /// <summary>
        /// Overrided Parse class, the output is json file containing ProductModel collection.
        /// </summary>
        /// <param name="response"></param>
        public override void Parse(Response response)
        {
            var ProductList = new List<ProductModel>();
            foreach (var wholeProduct in response.Css(_wholeProductPath))
            {
                // get main product inside div to work with, and assign to variable
                var productInsideDivClass = wholeProduct.Css(_productInsideDivPath)[0];
                // create single product model, to fill it's properties and finally and to final collection
                var singleProduct = new ProductModel();

                singleProduct.ProductName = ParseProductName(productInsideDivClass);
                singleProduct.Parameters = ParseProductParameters(productInsideDivClass);
                singleProduct.Price = ParseProductPrice(wholeProduct);
                var productImgUrl = ParseProductImgUrl(wholeProduct);
                singleProduct.ProductImage = ConvertProductImgToBase64(productImgUrl);

                ProductList.Add(singleProduct);
            }
            FinalList.AddRange(ProductList);
            #region Another ugly logic to face pages problem :/
            CurrentPage += 1;
            CurrentUrl = UpdateUrlForCurrentPage(CurrentPage, _url);
            if (CurrentPage <= _numberOfPages)
                Request(CurrentUrl, Parse);
            else 
            {
                Scrape(FinalList, "Products.json");
                CurrentPage -= 1; 
                string path = string.Concat(Directory.GetCurrentDirectory(), @"\Output\Products.json");
                if (FinalList.Count == 0)
                    Log("*NO RESULTS FOR INSERTED INPUT*", LogLevel.Critical);
                else
                    Log("*SCRAPED DATA HAS BEEN SAVED IN: " + path + " || " + CurrentPage + " PAGES AND " +  FinalList.Count + " RECORDS HAS BEEN SCRAPED. *", LogLevel.Critical);
            }
            #endregion
        }
        // Methods which parse Product Model's properties.
        #region Product properties private parsing methods

        private string UpdateUrlForCurrentPage(int pageNumber, string baseUrl)
        {
            if(SearchEngineMode)
            {
                var splittedString = baseUrl.Split("/");
                splittedString[7] = pageNumber.ToString();
                string newUrl = string.Join("/", splittedString);
                return(newUrl);
            }
            string pageNumberPart = String.Format(",,,,,,,, 0,,,,/{0}/", pageNumber);
            return string.Concat(baseUrl, pageNumberPart);
        }
        private string ParseProductName(HtmlNode productInsideDivHtmlNode)
        {
            return productInsideDivHtmlNode.Css(_productNamePath)[0].TextContentClean;
        }
        private IEnumerable<string> ParseProductParameters(HtmlNode productInsideDivHtmlNode)
        {
            var parameterList = new List<string>();
            foreach (var featureDiv in productInsideDivHtmlNode.Css(_featureDescriptionPath))
            {
                parameterList.Add(featureDiv.TextContentClean);
            }
            return parameterList;
        }
        private string ParseProductPrice(HtmlNode wholeProductHtmlNode)
        {
            return wholeProductHtmlNode.Css(_pricePath)[0].TextContentClean;
        }
        private string ParseProductImgUrl(HtmlNode wholeProductHtmlNode)
        {
            HtmlNode imgPath = null;
            if(wholeProductHtmlNode.CssExists(_imgPath))
                 imgPath = wholeProductHtmlNode.Css(_imgPath)[0];
            if (imgPath == null)
                return null;
            return imgPath.GetAttribute(_imgContainingAttribute);
        }
        #endregion
        // Method returning base64 from img src html tag.
        #region Private method which takes base64 from img url
        private string ConvertProductImgToBase64 (string url)
        {
            if (url == null)
                return "Lack of image";
            using (var httpClient = new HttpClient())
            {
                var bytes = httpClient.GetByteArrayAsync(url).Result;
                return Convert.ToBase64String(bytes);
            }
        }

        #endregion
    }
}
