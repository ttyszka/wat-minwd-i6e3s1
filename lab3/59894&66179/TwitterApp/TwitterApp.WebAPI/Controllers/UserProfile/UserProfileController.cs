using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.UserService;

namespace TwitterApp.WebAPI.Controllers.UserProfile
{
    [ApiController]
    [Route("[controller]")]
    public class UserProfileController : ControllerBase
    {
        private readonly IUserService _userService;
        public UserProfileController(IUserService userService)
        {
            _userService = userService;
        }
        [HttpGet]
        public async Task<IActionResult> Get()
        {
            var user = await _userService.GetUserProfileLookupAsync();
            if (user == null)
                return BadRequest();
            else
                return Ok(user);
        }
    }
}
