using Github.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Github.DAL.UserInfo
{
    public interface IUserInfo
    {
        Task<UserInfoModel> GetUserInfo(string relativeUrl);
    }
}
