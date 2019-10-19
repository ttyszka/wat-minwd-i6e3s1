# Scraping morele.net website

The application allows user to scrap morele.net website. By category or the search component param.

LIBRARY: IronWebScraper [link](https://ironsoftware.com/)

## Installation:

Make sure to install the .Net Core 3.0 SDK for the proper functioning.
You can find it [here](https://dotnet.microsoft.com/download/dotnet-core/3.0) .

To start the application from the command line, make sure you are inside the project with the solution. 

```
cd [YourDirectory]../wat-minwd-i6e3s1/lab2/66179
```

Then just simply run the command from the powershell/cmd: 
```
dotnet run --project Scraper.ConsoleApp
```

*There should be no worries to try compile it on Linux machine.*

## App usage with basic scenario:
The application allows you to search by a specific category or by a phrase entered into the search engine.

1. First you will be asked to enter the correct url or press the q button on your keyboard and then enter the parameter into the search engine.
2. Then the application will try to find the results for the entered query.
3. The result will be stored in the:

   `[currentDirectory]/Output/Products.json`

4. Scraped Product Example: 
```
"ProductName":"Laptop Lenovo V130-15IKB (81HN00N0PB) Bestseller",
"Price":"2 398 zł",
"Parameters":[
         "Chipset karty graficznej: Intel HD Graphics 620",
         "Pamięć RAM (zainstalowana): 8 GB",
         "Procesor: Intel Core i5-7200U",
         "Przekątna ekranu [cal]: 15.6",
         "System operacyjny: Windows 10 Pro"
      ],
"ProductImage": base 64 string
```

> Any errors should be logged into console.

Usage example:

![Basic Usage Example](https://imgur.com/a/rmeIxVA)

## Remarks
> When inserting an url, it must lead to a specific subcategory. For example, in case of computers it is necessary to specify subcategories.

Examples of incorrect URLs: 

[PCs](https://www.morele.net/komputery/)

[Price's Alerts](https://www.morele.net/alarmcenowy/)

Examples of correct URLs:

[Laptops/Notebooks](https://www.morele.net/laptopy/laptopy/notebooki-laptopy-ultrabooki-31/)
```
https://www.morele.net/laptopy/laptopy/notebooki-laptopy-ultrabooki-31/
```

[OLED Tvs](https://www.morele.net/rtv/tv-i-video/telewizory-412/,,,,,,,,0,,,,23155O1107159/1/)
```
https://www.morele.net/rtv/tv-i-video/telewizory-412/,,,,,,,,0,,,,23155O1107159/1/
```

>The entered search phrase should always return the correct result if it exists in the [morele.net] database.

Correct search phrase examples:
```
Asus,
Mi-Band, 
etc.
```
### Additional informations:
Autor: Wojciech Regulski I6E3S1, 66179

Przedmiot: Metody i narzędzia informatycznego wspomagania decyzji

Prowadzący: mgr inż. Kamil Banach