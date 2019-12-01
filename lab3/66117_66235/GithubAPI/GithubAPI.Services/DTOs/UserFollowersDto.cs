using System;
using System.Collections.Generic;
using System.Text;

namespace Github.Services.DTOs
{
    public class UserFollowersDto
    {
        public string Login { get; set; }
        public string Avatar_url { get; set; }
        public string Html_url { get; set; }
    }
}
