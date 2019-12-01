namespace TwitterApp.TwitterDataAccessLayer.Entities
{
    public class BaseTimeline
    {
        public string Text { get; set; }
        public UserProfile User { get; set; }
        public string Id_str { get; set; }
    }
}
