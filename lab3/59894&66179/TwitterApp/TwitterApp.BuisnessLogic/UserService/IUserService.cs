using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.LookupModels;

namespace TwitterApp.BuisnessLogic.UserService
{
    public interface IUserService
    {
        Task<UserProfileLookup> GetUserProfileLookupAsync();
    }
}
