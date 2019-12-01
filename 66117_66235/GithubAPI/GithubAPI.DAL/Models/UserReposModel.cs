using System;
using System.Collections.Generic;
using System.Text;

namespace Github.DAL.Models
{
    public class UserReposModel
    {
        public int Id { get; set; }
        public string Node_id { get; set; }
        public string Name { get; set; }
        public string Full_name { get; set; }
        public bool Private { get; set; }
        public string Html_url { get; set; }
        public string Description { get; set; }
        public string Watchers { get; set; }
        public string Forks { get; set; }
        public string Created_at { get; set; }
    }
}
