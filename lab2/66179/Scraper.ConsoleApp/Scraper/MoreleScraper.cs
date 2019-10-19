using IronWebScraper;
using Scraper.ConsoleApp.Models;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Scraper.ConsoleApp.Scraper
{
    public class MoreleScraper : WebScraper
    {
        #region Readonly String Path Declarations
        private string _url;
        private readonly string _wholeProductPath = ".cat-product";
        private readonly string _productInsideDivPath = ".cat-product-inside";
        private readonly string _imgPath = ".cat-product-left > .cat-product-image > .product-image";
        private readonly string _imgContainingAttribute = "data-cfsrc";
        private readonly string _productNamePath = ".cat-product-content > .cat-product-center > .cat-product-center-inside >.cat-product-name";
        private readonly string _featureDescriptionPath = ".cat-product-features > .cat-product-feature";
        private readonly string _pricePath = ".cat-product-right > .cat-product-price > .price-new";
        #endregion
        public MoreleScraper(string? categoryPathUrl)
        {
            _url = categoryPathUrl;
        }

        public override void Init()
        {
            LoggingLevel = WebScraper.LogLevel.All;
            Request(_url, Parse);
            WorkingDirectory = Directory.GetCurrentDirectory() + @"\Output\";
        }

        public override void Parse(Response response)
        {
            var productList = new List<ProductModel>();
            foreach (var wholeProduct in response.Css(_wholeProductPath))
            {
                var productInsideDivClass = wholeProduct.Css(_productInsideDivPath)[0];
                var productImgClass = wholeProduct.Css(_imgPath)[0].GetAttribute(_imgContainingAttribute);
                var singleProduct = new ProductModel();
                
                singleProduct.ProductName = productInsideDivClass.Css(_productNamePath)[0].TextContentClean;
                var parameterList = new List<string>();

                foreach (var featureDiv in productInsideDivClass.Css(_featureDescriptionPath)) 
                {
                    parameterList.Add(featureDiv.TextContentClean);
                }
                singleProduct.Parameters = parameterList;
                singleProduct.Price = wholeProduct.Css(_pricePath)[0].TextContentClean;
                productList.Add(singleProduct);
            }
            Scrape(productList, "Products.json");
        }
    }
}
