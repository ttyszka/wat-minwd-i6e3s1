using TinyOAuth1;

namespace TwitterApp.TwitterDataAccessLayer.Interfaces
{
    public interface IOAuthService
    {
        string GetBaseUrl { get; }
        TinyOAuthMessageHandler GetOAuthDataForRequest();
    }
}
