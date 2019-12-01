using AutoMapper;
using System;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.Infrastructure.DateConverter;
using TwitterApp.BuisnessLogic.LookupModels;
using TwitterApp.TwitterDataAccessLayer.Interfaces;

namespace TwitterApp.BuisnessLogic.UserService
{
    public class UserService : IUserService
    {
        private readonly string _userName = "_mknbl";

        private readonly IApiService _apiService;
        private readonly IMapper _mapper;
        private readonly IDateConverter _dateConverter;
        public UserService(IApiService apiService, IMapper mapper, IDateConverter dateConverter)
        {
            _apiService = apiService;
            _mapper = mapper;
            _dateConverter = dateConverter;
        }

        public async Task<UserProfileLookup> GetUserProfileLookupAsync()
        {
            var userProfile = await _apiService.GetUserProfileAsync(_userName);
            var mappedUser = _mapper.Map<UserProfileLookup>(userProfile);
            mappedUser.ProfileImage = "";

            mappedUser.Url = SpecifyAnUrl(mappedUser.Url);


            if (userProfile.Profile_Image_Url == null)
                return mappedUser;

            string imgUrl = ExcludeImageSizeFromUrl(userProfile.Profile_Image_Url);
            var byteArray = await GetImageFromUrl(imgUrl);

            if (!byteArray.Any())
                return mappedUser;

            string convertedImg = ConvertToBase64String(byteArray);

            if (convertedImg != "")
                mappedUser.ProfileImage = convertedImg;
            var date = new DateTime();

            if (userProfile.Created_At != null)
                date = _dateConverter.ParseToNationalDateFormat(userProfile.Created_At);

            mappedUser.CreationTime = date.ToString();
            return mappedUser;
        }

        #region Private Helper Methods
        private string ExcludeImageSizeFromUrl(string imageUrl)
        {
            return imageUrl.Replace("_normal", "");
        }

        private async Task<byte[]> GetImageFromUrl(string imageUrl)
        {
            using var httpClient = new HttpClient();
            return await httpClient.GetByteArrayAsync(imageUrl);
        }

        private string ConvertToBase64String(byte[] img)
        {
            return Convert.ToBase64String(img);
        }

        private string SpecifyAnUrl(string Url)
        {
            if (Url == null) Url = "not specified";
            return Url;
        }
        #endregion
    }
}
