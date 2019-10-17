using IronWebScraper;
using Scraper.ConsoleApp.Models;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Scraper.ConsoleApp.Scraper
{
    public class MoreleScraper : WebScraper
    {
        public override void Init()
        {
            LoggingLevel = WebScraper.LogLevel.All;
            Request("https://www.morele.net/laptopy/laptopy/notebooki-laptopy-ultrabooki-31/", Parse);
            WorkingDirectory = Directory.GetCurrentDirectory() + @"\Output\";
        }

        public override void Parse(Response response)
        {
            var productList = new List<ProductModel>();
            foreach (var wholeProduct in response.Css(".cat-product"))
                //foreach (var Div in response.Css(".cat-product > .cat-product-inside > .cat-product-content > .cat-product-center > .cat-product-center-inside"))
            {
                var productInsideDivClass = wholeProduct.Css(".cat-product-inside")[0];
                var singleProduct = new ProductModel();
                
                singleProduct.ProductName = productInsideDivClass.Css(".cat-product-content > .cat-product-center > .cat-product-center-inside >.cat-product-name")[0].TextContentClean;
                var parameterList = new List<string>();
                
                foreach(var featureDiv in productInsideDivClass.Css(".cat-product-features > .cat-product-feature"))
                {
                    parameterList.Add(featureDiv.TextContentClean);
                }
                singleProduct.Parameters = parameterList;
                singleProduct.Price = wholeProduct.Css(".cat-product-right > .cat-product-price > .price-new")[0].TextContentClean;
                productList.Add(singleProduct);
            }
            Scrape(productList, "Products.json");
        }
    }
}
