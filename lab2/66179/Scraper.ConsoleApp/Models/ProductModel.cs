using System;
using System.Collections.Generic;
using System.Text;

namespace Scraper.ConsoleApp.Models
{
    public class ProductModel
    {
        public string ProductName { get; set; }
        public string Price { get; set; }
        public IEnumerable<string> Parameters { get; set; }
        
    }
}
