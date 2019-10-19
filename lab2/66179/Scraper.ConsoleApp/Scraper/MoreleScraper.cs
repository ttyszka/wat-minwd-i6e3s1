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
    public class MoreleScraper : WebScraper
    {
        #region Readonly String Path Declarations
        // Readonly div classes which are used in morele.net parsing.
        private string _url;
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
        List<ProductModel> ProductList;
        #endregion

        #region Ctor
        public MoreleScraper(string? categoryPathUrl)
        {
            _url = categoryPathUrl;
            // Final collection initialization
            ProductList = new List<ProductModel>();
        }
        #endregion

        public override void Init()
        {
            // Scraper initialization, precised: loglevel, and working directory.
            LoggingLevel = LogLevel.Critical;
            Request(_url, Parse);
            WorkingDirectory = Directory.GetCurrentDirectory() + @"\Output\";
        }

        /// <summary>
        /// Overrided Parse class, the output is json file containing ProductModel collection.
        /// </summary>
        /// <param name="response"></param>
        public override void Parse(Response response)
        {
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
            string path = string.Concat(Directory.GetCurrentDirectory(), @"\Output\Products.json");
            if (ProductList.Count == 0)
                Log("*NO RESULTS FOR INSERTED INPUT*", LogLevel.Critical);
            else
                Log("*SCRAPED DATA HAS BEEN SAVED IN*: " + path, LogLevel.Critical);
            Scrape(ProductList, "Products.json");
            
        }
        // Methods which parse Product Model's properties.
        #region Product properties private parsing methods
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
            var imgPath = wholeProductHtmlNode.Css(_imgPath)[0];
            return imgPath.GetAttribute(_imgContainingAttribute);
        }
        #endregion
        // Method returning base64 from img src html tag.
        #region Private method which takes base64 from img url
        private string ConvertProductImgToBase64 (string url)
        {
            using (var httpClient = new HttpClient())
            {
                var bytes = httpClient.GetByteArrayAsync(url).Result;
                return Convert.ToBase64String(bytes);
            }
        }
        #endregion
    }
}
