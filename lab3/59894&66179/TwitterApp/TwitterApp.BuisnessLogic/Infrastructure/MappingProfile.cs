using AutoMapper;
using TwitterApp.BuisnessLogic.LookupModels;
using TwitterApp.TwitterDataAccessLayer.Entities;

namespace TwitterApp.BuisnessLogic.Infrastructure
{
    public class MappingProfile : Profile
    {
        public MappingProfile()
        {
            CreateMap<UserProfile, UserProfileLookup>();
        }
    }
}
