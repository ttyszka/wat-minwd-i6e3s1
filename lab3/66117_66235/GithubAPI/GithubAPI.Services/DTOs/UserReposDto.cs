using System;
using System.Collections.Generic;
using System.Text;

namespace Github.Services.DTOs
{
    public class UserReposDto
    {
        public string Name { get; set; }
        public string Html_url { get; set; }
        public string Description { get; set; }
        public string Watchers { get; set; }
        public string Forks { get; set; }
        public string Created_at { get; set; }
    }
}
