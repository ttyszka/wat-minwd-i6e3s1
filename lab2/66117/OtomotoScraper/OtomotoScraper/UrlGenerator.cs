using OtomotoScraper.Models;
using System;


namespace OtomotoScraper
{
    public class UrlGenerator
    {
        public string CreateUrl()
        {
            var productInput = new ProductInput();
            Console.WriteLine("Enter category (osobowe, dostawcze)");

            if (Enum.TryParse(Console.ReadLine(), true, out ProductInput.CategoryEnum category))
            {
                productInput.Category = category;
            }
            else
            {
                Console.WriteLine("Choose one of the categories listed above");
            }

            Console.WriteLine("Enter brand (e.g. Toyota)");
            productInput.Brand = Console.ReadLine();
            Console.WriteLine("Enter model (e.g. Corolla)");
            productInput.Model = Console.ReadLine();
            Console.WriteLine("Enter minimum price (e.g. 5000)");
            productInput.PriceMin = Console.ReadLine();
            Console.WriteLine("Enter maximum price (e.g. 30000)");
            productInput.PriceMax = Console.ReadLine();
            Console.WriteLine("Enter type of fuel (petrol, diesel)");

            if (Enum.TryParse(Console.ReadLine(), true, out ProductInput.FuelTypeEnum fuelType))
            {
                productInput.FuelType = fuelType;
            }
            else
            {
                Console.WriteLine("Choose one of the fuel type listed above");
            }

            Console.WriteLine("Results: ");
            var url = $"https://www.otomoto.pl/{productInput.Category}/{productInput.Brand}/{productInput.Model}/?search%5Bfilter_float_price%3Afrom%5D={productInput.PriceMin}&search%5Bfilter_float_price%3Ato%5D={productInput.PriceMax}&search%5Bfilter_enum_fuel_type%5D={productInput.FuelType}&search%5Bnew_used%5D=on";
            return url;
        }
    }
}
