using AutoMapper;
using Github.DAL;
using Github.DAL.Models;
using Github.DAL.UserFollowers;
using Github.DAL.UserInfo;
using Github.DAL.UserRepos;
using Github.Services;
using Github.Services.DTOs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GithubAPI.Services
{
    public class UserService : IUserService
    {
        private readonly IUserInfo _userInfo;
        private readonly IUserRepos _userRepos;
        private readonly IUserFollowers _userFollowers;
        private readonly IMapper _mapper;

        public UserService(IUserInfo userInfo, IUserRepos userRepos, IUserFollowers userFollowers, IMapper mapper)
        {
            _userInfo = userInfo;
            _userRepos = userRepos;
            _userFollowers = userFollowers;
            _mapper = mapper;
        }

        public async Task<UserInfoDto> DisplayUserInfo(string userName)
        {
            var userInfo = await _userInfo.GetUserInfo(userName);
            var userInfoDto = _mapper.Map<UserInfoDto>(userInfo);

            return userInfoDto;
        }

        public async Task<IEnumerable<UserReposDto>> DisplayUserRepos(string userName)
        {
            var userRepos = await _userRepos.GetUserRepos(userName);
            var userReposDto = _mapper.Map<IEnumerable<UserReposDto>>(userRepos);

            return userReposDto;    
        }

        public async Task<IEnumerable<UserFollowersDto>> DisplayUserFollowers(string userName)
        {
            var userFollowers = await _userFollowers.GetUserFollowers(userName);
            var userFollowersDto = _mapper.Map<IEnumerable<UserFollowersDto>>(userFollowers);

            //foreach (string login in userFollowersDto.Select(a => a.Login))
            //{
            //    // Get login followers
            //    var parentFollowers = await _userFollowers.GetUserFollowers(login);
            //    var parentFollowersDto = _mapper.Map<IEnumerable<UserFollowersDto>>(parentFollowers);

            //    var together = userFollowersDto.Concat(parentFollowersDto);

            //    return together;
            //}

            return userFollowersDto;
        }

    }
}
