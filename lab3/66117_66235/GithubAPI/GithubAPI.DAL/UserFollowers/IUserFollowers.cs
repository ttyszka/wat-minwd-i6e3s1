using Github.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Github.DAL.UserFollowers
{
    public interface IUserFollowers
    {
        Task<IEnumerable<UserFollowersModel>> GetUserFollowers(string relativeUrl);
    }
}
