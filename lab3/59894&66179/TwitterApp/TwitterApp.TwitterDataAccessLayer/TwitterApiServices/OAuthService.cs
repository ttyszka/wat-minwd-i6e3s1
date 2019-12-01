using TinyOAuth1;
using TwitterApp.TwitterDataAccessLayer.Interfaces;

namespace TwitterApp.TwitterDataAccessLayer.TwitterApiServices
{
    public class OAuthService : IOAuthService
    {
        #region Readonly Strings
        private readonly string _apiKey = "ll4lj1CKwjxjTRxcBSFBD3aux";
        private readonly string _apiSecretKey = "8G0U2YJtKj1RzliuCjetcMtpGT69XJ6gljclnzmJACyFPDZvEo";
        private readonly string _accessToken = "464509969-Fk9n6I2grsOVJD29YNOMGIISDjHPMwh1zxr2hptf";
        private readonly string _accessTokenSecret = "GNaPywtjAnCsDV3EBX7u6BC6SvM0zUTiZ363jZqG1c77W";
        #endregion

        // returnes base url
        public string GetBaseUrl { get; } = "https://api.twitter.com/1.1/statuses/user_timeline.json?";

        // generates tiny o auth config file to pass consumer and secret key via headers
        private TinyOAuthConfig GenerateTinyOAuthConfig()
        {
            return new TinyOAuthConfig
            {
                ConsumerKey = _apiKey,
                ConsumerSecret = _apiSecretKey
            };
        }
        // generates oauth data to fill get request with authorization stuff
        public TinyOAuthMessageHandler GetOAuthDataForRequest()
        {
            var configFile = GenerateTinyOAuthConfig();
            return new TinyOAuthMessageHandler(configFile, _accessToken, _accessTokenSecret);
        }
    }
}
