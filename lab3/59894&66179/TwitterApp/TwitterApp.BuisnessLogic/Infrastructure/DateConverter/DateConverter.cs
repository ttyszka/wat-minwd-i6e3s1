using System;
using System.Globalization;

namespace TwitterApp.BuisnessLogic.Infrastructure.DateConverter
{
    public class DateConverter : IDateConverter
    {
        private const string twitterDateFormat = "ddd MMM dd HH:mm:ss +ffff yyyy";
        
        public DateTime ParseToNationalDateFormat(string date)
        {
            var currentCultureDate = DateTime
                .ParseExact(date, twitterDateFormat, CultureInfo.InvariantCulture, DateTimeStyles.AdjustToUniversal);
            return currentCultureDate;
        }
    }
}
