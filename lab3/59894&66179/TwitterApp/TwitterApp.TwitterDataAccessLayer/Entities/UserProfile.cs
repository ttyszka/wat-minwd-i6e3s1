using System;
using System.Collections.Generic;
using System.Text;

namespace TwitterApp.TwitterDataAccessLayer.Entities
{
    public class UserProfile
    {
        public string Id { get; set; }
        public string Name { get; set; }
        public string Screen_Name { get; set; }
        public string Location { get; set; }
        public string Description { get; set; }
        public string Url { get; set; }
        public string Followers_Count { get; set; }
        public string Friends_Count { get; set; }
        public string Created_At { get; set; }
        public string Favourites_Count { get; set; }
        public bool Verified { get; set; }
        public string Statuses_Count { get; set; }
        public string Profile_Image_Url { get; set; }
    }
}
