using System;
using System.Collections.Generic;
using System.Text;

namespace Github.Services.DTOs
{
    public class UserInfoDto
    {
        public string Avatar_url { get; set; }
        public string Html_url { get; set; }
        public string Name { get; set; }
        public string Login { get; set; }
        public string Company { get; set; }
        public string Location { get; set; }
        public string Email { get; set; }
        public string Bio { get; set; }
        public string Blog { get; set; }
        public int Public_repos { get; set; }
        public int Public_gists { get; set; }
        public int Followers { get; set; }
        public int Following { get; set; }
        public string Followers_url { get; set; }
        public DateTime Created_at { get; set; }
    }
}
