using System.Collections.Generic;

namespace Scraper.ConsoleApp.Models
{
    public class ProductModel
    {
        public string ProductName { get; set; }
        public string Price { get; set; }
        public IEnumerable<string> Parameters { get; set; }
        public string ProductImage { get; set; }
    }
}
