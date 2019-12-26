using System;
using System.Collections.Generic;
using System.Text;

namespace FlashscoreScraper
{
    public class Match
    {
        public String time { get; set; }
        public String homeTeam { get; set; }
        public String awayTeam { get; set; }
        public List<String> result { get; set; }
    }
}
