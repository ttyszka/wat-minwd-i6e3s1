# Otomoto.pl scraper

Application for scrapping otomoto.pl website
###Input
User gives category, brand, model, price (from - to) and type of fuel

###Output
User receive list of offers (title, subtitle, product price, year, mileage, engine capacity, offer url and image url)
Everything is presented in json.
Note: If you do not want json format, you can just uncomment all `Console.WriteLine()` and comment `Console.WriteLine(json);` in GetHtml.cs class

###Additives
- Image url is converted to Base 64 (+ json)
- Unfortunately it w

##Prerequisites
To run the program you need to have .NET Core 3.0 

##Running
You can run program from:
1) Visual Studio IDE 
Just download program and run it in IDE

2) Command line
Open command line and change directory to project solution. Then type: dotnet run --project OtoMotoScraper