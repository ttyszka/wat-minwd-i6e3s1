using System;
using System.Collections.Generic;
using System.Text;

namespace OtomotoScraper.Models
{
    public class ProductInput
    {
        public CategoryEnum Category { get; set; }
        public string Brand{ get; set; }
        public string Model { get; set; }
        public string PriceMin { get; set; }
        public string PriceMax { get; set; }
        public FuelTypeEnum FuelType { get; set; }

        public enum CategoryEnum
        {
            osobowe,
            dostawcze
        }

        public enum FuelTypeEnum
        {
            petrol,
            diesel,
        }
    }
}
