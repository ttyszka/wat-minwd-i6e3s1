# Github API project

## Authors
- Kamil Kowalczyk (66117)
- Mariusz Wlazło (66235)

## Architecture
Application consists of 2 projects: 
- Angular project (Frontend)
- .NET Core project (Backend)

Application allows user to entry user name and then we get information about this user from public Github API:
- Basic information about user 
- Repositories of user
- List of followers

## Prerequisites
To run this project you need to have:
- .NET Core 3.0 
- Node.js 

## Running project

You can run project through:
##### 1. Visual Studio and Visual studio Code
1. Open Visual Studio and just run the project
2. Open Visual studio code
3. If angular can't find modules, type `npm install` in terminal
4. Run the project typing `ng serve -o` in VS Code terminal

##### 2. Command line
1. Open command prompt and change directory to Github.WebAPI folder  
Then type `dotnet run --project Github.WebAPI.csproj`

2. Open second command prompt, change directory to GhFront folder  
Then type ` ng serve - o`
