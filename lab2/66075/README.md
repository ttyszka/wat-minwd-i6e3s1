Simple Twitter Scraper


Output Data
------------
```json
{
	"id": "123456789012345678",
	"permalink": "https://twitter.com/username/status/123456789012345678",
	"username": "username",
	"text": "@abcd @efgh #This is #tweet text",
	"datetime": "2019-12-03 11:11",
	"retweets": 30,
	"favorites": 20,
	"mentions": "@abcd @efgh",
	"hashtags": "#This #tweet",
	"geo": ""
}
```

Instrukcja obslugi
----------
`simple-twitter-scraper <keyword> <first date(YYYY-MM-DD)> <last date(YYYY-MM-DD)> <output directory>`
```sh
> npm install -g simple-twitter-scraper
> mkdir tmp_twitter
> cd tmp_twitter
> simple-twitter-scraper obama 2019-12-03 2019-12-04 ./
```
