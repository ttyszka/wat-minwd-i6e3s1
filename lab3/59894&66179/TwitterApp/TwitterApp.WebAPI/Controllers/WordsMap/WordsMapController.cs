using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.UserService;
using TwitterApp.BuisnessLogic.WordsMapService;

namespace TwitterApp.WebAPI.Controllers.UserProfile
{
    [ApiController]
    [Route("[controller]")]
    public class WordsMapController : ControllerBase
    {
        private readonly IWordsMapService _wordsMapService;
        public WordsMapController(IWordsMapService wordsMapService)
        {
            _wordsMapService = wordsMapService;
        }
        [HttpGet]
        public async Task<IActionResult> Get(int tweets, int wordsNo, int ignoreUrls)
        {
            var tweetsMap = await _wordsMapService.GetWordsMapAsync(tweets, wordsNo, ignoreUrls);
            if (tweetsMap == null)
                return BadRequest();
            else
                return Ok(tweetsMap);
        }
    }
}
