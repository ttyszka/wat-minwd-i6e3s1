using System.Collections.Generic;
using System.Threading.Tasks;
using TwitterApp.BuisnessLogic.LookupModels;

namespace TwitterApp.BuisnessLogic.WordsMapService
{
    public interface IWordsMapService
    {
        Task<List<WordsMapKeyValue>> GetWordsMapAsync(int numberOfTweets, int numberOfWords, int ignoreUrls);
    }
}
