using AutoMapper;
using Github.DAL.Models;
using Github.Services.DTOs;
using System;
using System.Collections.Generic;
using System.Text;

namespace Github.Services.AutoMapper
{
    public class MapperProfile : Profile
    {
        public MapperProfile()
        {
            CreateMap<UserInfoModel, UserInfoDto>();
            CreateMap<UserInfoDto, UserInfoModel>();

            CreateMap<UserReposModel, UserReposDto>();
            CreateMap<UserReposDto, UserReposModel>();

            CreateMap<UserFollowersModel, UserFollowersDto>();
            CreateMap<UserFollowersDto, UserFollowersModel>();
        }
    }
}
