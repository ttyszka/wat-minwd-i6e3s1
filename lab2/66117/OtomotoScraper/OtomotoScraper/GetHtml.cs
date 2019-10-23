using HtmlAgilityPack;
using Newtonsoft.Json;
using OtomotoScraper.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;

namespace OtomotoScraper
{
    public class GetHtml
    {
        public async void GetHtmlAsync()
        {
            var httpClient = new HttpClient();
            var urlGenerator = new UrlGenerator();

            var url = urlGenerator.CreateUrl();
            var html = await httpClient.GetStringAsync(url);

            var htmlDocument = new HtmlDocument();
            htmlDocument.LoadHtml(html);

            var productsHtml = htmlDocument.DocumentNode.Descendants("div")
                .Where(node => node.GetAttributeValue("class", "")
                .Equals("offers list")).ToList();

            var productOutput = new ProductOutput();
            var productListItems = new List<HtmlNode>();

            try
            {
                productListItems = productsHtml[0].Descendants("article")
                .Where(node => node.GetAttributeValue("data-ad-id", "") != null).ToList();
            }

            catch (Exception e)
            {
                Console.WriteLine("No results found matching your requirements");
            }

            if (productListItems != null)
            {
                foreach (var productListItem in productListItems)
                {
                    // Title
                    productOutput.Title = productListItem.Descendants("h2")
                        .Where(node => node.GetAttributeValue("class", "")
                        .Equals("offer-title ds-title")).FirstOrDefault().InnerText.Trim();
                    //Console.WriteLine("Name: " + product.Title);

                    // Subtitle
                    try
                    {
                        productOutput.Subtitle = productListItem.Descendants("h3")
                        .Where(node => node.GetAttributeValue("data-type", "")
                        .Equals("complement")).FirstOrDefault().InnerText.Trim();
                        //Console.WriteLine("Description: " + product.Subtitle);
                    }
                    catch (System.NullReferenceException ex)
                    {
                        //Console.WriteLine("Description: There is no given description for this product");
                    }

                    // Price
                    try
                    {
                        productOutput.Price = productListItem.Descendants("span")
                       .Where(node => node.GetAttributeValue("class", "")
                       .Equals("offer-price__number ds-price-number")).FirstOrDefault().InnerText.Trim();
                        //Console.WriteLine("Price: " + product.Price);
                    }
                    catch (System.NullReferenceException ex)
                    {
                        //Console.WriteLine("Price: There is no given price for this product");
                    }

                    // Year
                    try
                    {
                        productOutput.Year = productListItem.Descendants("li")
                        .Where(node => node.GetAttributeValue("data-code", "")
                        .Equals("year")).FirstOrDefault().InnerText.Trim();
                        //Console.WriteLine("Year: " + product.Year);
                    }
                    catch (System.NullReferenceException ex)
                    {
                        //Console.WriteLine("Year: There is no given year for this product");
                    }

                    // Mileage
                    try
                    {
                        productOutput.Mileage = productListItem.Descendants("li")
                        .Where(node => node.GetAttributeValue("data-code", "")
                        .Equals("mileage")).FirstOrDefault().InnerText.Trim();
                        //Console.WriteLine("Mileage: " + product.Mileage);
                    }
                    catch (System.NullReferenceException ex)
                    {
                        //Console.WriteLine("Mileage: There is no given mileage for this product");
                    }

                    try
                    {
                        productOutput.EngineCapacity = productListItem.Descendants("li")
                        .Where(node => node.GetAttributeValue("data-code", "")
                        .Equals("engine_capacity")).FirstOrDefault().InnerText.Trim();
                        //Console.WriteLine("Engine capacity: " + product.EngineCapacity);
                    }
                    catch (System.NullReferenceException ex)
                    {
                        //Console.WriteLine("Engine capacity: There is no given engine capacity for this product");
                    }

                    // Link to product
                    productOutput.OfferUrl = productListItem.Descendants("a").FirstOrDefault().GetAttributeValue("href", "");
                    //Console.WriteLine("Link to product: " + product.OfferUrl);

                    //Product image
                    productOutput.ImageUrl = productListItem.Descendants("img").FirstOrDefault().GetAttributeValue("data-src", "");

                    string json = JsonConvert.SerializeObject(productOutput);

                    // Final base64 json
                    var ImgToBase64 = new ImgToBase64();
                    ImgToBase64.ConvertImgToBase64(productOutput.ImageUrl);

                    Console.WriteLine(json);
                    Console.WriteLine("--------------------------------");
                }
            }
        }
    }
}
