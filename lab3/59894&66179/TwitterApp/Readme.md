# Simple Web Application using data from Twitter API 

The application provides some statistical details about user activity together with posted content by authorized Twitter member. All of used data were obtained from Twitter API.


## Installation:

***Running API***

Make sure to install the .Net Core 3.0 SDK for the proper functioning.
You can find it [here](https://dotnet.microsoft.com/download/dotnet-core/3.0) .

To start the application from the command line, make sure you are inside the project with the solution. 

```
cd [YourDirectory]../lab3/59894&66179/TwitterApp
```

Then just simply run the command from the powershell/cmd: 
```
dotnet run --project TwitterApp.WebApi
```

*There should be no worries to try compile it on Linux machine.*


***Running Angular***: 

Navigate to:
```
cd [YourDirectory]../lab3/59894&66179/TwitterApp/Angular/twitter-web-app/src
```

Then run two commands: 
```
npm install
ng serve --o 
```
*--o as optional parameter to open browser.*

*If your port 4200 is already in use, feel free to use ng serve --port whaterveryouwant.*


## Technology stack:

***Used Technologies***
+ Angular 8
+ .Net Core 3.0

***Additional Informations:***
+ Twitter Authentication: OAuth 1.0

## App usage:
The application allows user to view his Twitter profile details and to visualize some user activity content such as most recent and latest post together with word cloud, which is generated from selected range of latest tweets.

After running an app, the main view shows a sidebar with Twitter profile details and two tabs. The first one allows user to generate and view a word cloud from his posts. You can adjust the range of tweets to generate the word cloud using a slider.
![Basic Usage Example-1](https://i.imgur.com/yAhKSqU.png)
The second tab allows user to view his first and most recent post on Twitter. There is also a possibility to like the specific post.
![Basic Usage Example-2](https://i.imgur.com/Qhwkd9X.png)

## Remarks
To get access to Twitter API you must apply for a Twitter developer account and be approved before you get access tokens. Even after approval, access to user timeline is limited to your own profile. For that reason app doesn't provide to switch users without changing the source code.

### Additional info:
Authors: Mateusz Książek I6E3S1, 59894
Wojciech Regulski I6E3S1, 66179

Przedmiot: Metody i narzędzia informatycznego wspomagania decyzji

Prowadzący: mgr inż. Kamil Banach