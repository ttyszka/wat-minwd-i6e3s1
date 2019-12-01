using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Github.Services;
using GithubAPI.Services;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace Github.WebAPI.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class UserController : ControllerBase
    {
        private readonly IUserService _userService;

        public UserController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpGet("{name}")]
        public async Task<IActionResult> GetUserInfo(string name)
        {
            var userInfo = await _userService.DisplayUserInfo(name);
            if (userInfo == null)
            {
                return NotFound();
            }
            else
            {
                return Ok(userInfo);
            }
        }

        [HttpGet("{name}/repos")]
        public async Task<IActionResult> GetUserRepos(string name)
        {
            var userRepos = await _userService.DisplayUserRepos(name);
            if (userRepos == null)
            {
                return NotFound();
            }
            else
            {
                return Ok(userRepos);
            }
        }

        [HttpGet("{name}/followers")]
        public async Task<IActionResult> GetUserFollowers(string name)
        {
            var userFollowers = await _userService.DisplayUserFollowers(name);
            if (userFollowers == null)
            {
                return NotFound();
            }
            else
            {
                return Ok(userFollowers);
            }
        }

    }
}