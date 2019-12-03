using System;
using System.Collections.Generic;
using System.Text;

namespace OtomotoScraper.Models
{
    public class ProductOutput
    {
        public string Title { get; set; }
        public string Subtitle { get; set; }
        public string Price { get; set; }
        public string OfferUrl { get; set; }
        public string ImageUrl { get; set; }
        public string Year { get; set; }
        public string Mileage { get; set; }
        public string EngineCapacity { get; set; }
    }
}
