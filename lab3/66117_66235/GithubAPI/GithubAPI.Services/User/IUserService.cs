using Github.DAL.Models;
using Github.Services.DTOs;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Github.Services
{
    public interface IUserService
    {
        Task<UserInfoDto> DisplayUserInfo(string userName);
        Task<IEnumerable<UserReposDto>> DisplayUserRepos(string userName);
        Task<IEnumerable<UserFollowersDto>> DisplayUserFollowers(string userName);

    }
}
