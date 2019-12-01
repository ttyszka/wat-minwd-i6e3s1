using Github.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace Github.DAL.UserRepos
{
    public interface IUserRepos
    {
        Task<IEnumerable<UserReposModel>> GetUserRepos(string relativeUrl);
    }
}
