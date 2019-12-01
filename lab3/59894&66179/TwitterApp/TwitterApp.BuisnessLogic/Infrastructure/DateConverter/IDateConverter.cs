using System;

namespace TwitterApp.BuisnessLogic.Infrastructure.DateConverter
{
    public interface IDateConverter
    {
        DateTime ParseToNationalDateFormat(string date);
    }
}
