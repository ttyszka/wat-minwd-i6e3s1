using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.TweetIdsService;

namespace TwitterApp.WebAPI.Controllers.TweetIds
{
    [ApiController]
    [Route("[controller]")]
    public class TweetIdsController : ControllerBase
    {
        private readonly ITweetIdsService _tweetIdsService;

        public TweetIdsController(ITweetIdsService tweetIdsService)
        {
            _tweetIdsService = tweetIdsService;
        }
        [HttpGet]

        public async Task<IActionResult> Get(int tweets)
        {
            var tweetIds = await _tweetIdsService.GetFirstAndLastTweetIdAsync(tweets);
            if (tweetIds == null)
                return BadRequest();
            else
                return Ok(tweetIds);
        }
    }
}
