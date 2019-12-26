using Newtonsoft.Json;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.IO;
using System.Linq;

namespace FlashscoreScraper
{
    class Program
    {

        static void Main(string[] args)
        {
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("--headless");
            options.AddArguments("--disable-gpu");
            IWebDriver driver = new ChromeDriver(options);
 
            driver.Url = "https://www.flashscore.pl/pilka-nozna/anglia/premier-league/";
            List<Match> LatestMatches = new List<Match>();
            List<Match> TodayMatches = new List<Match>();
            List<Match> UpcomingMatches = new List<Match>();
            List<TableRow> FullTable = new List<TableRow>();
            int i = 0;



            
            ReadOnlyCollection<IWebElement> elements = driver.FindElements(By.XPath("//div[@class='sportName soccer']"));
            
            foreach (IWebElement element in elements)
            {
                ReadOnlyCollection<IWebElement> matches = element.FindElements(By.XPath(".//div[contains(@class,'event__match event__match')]"));

                foreach (IWebElement match in matches)
                {
                    Match singleMatch = new Match();
                    singleMatch.homeTeam = match.FindElement(By.XPath(".//div[contains(@class,'participant--home')]")).Text;
                    String fullResult = match.FindElement(By.XPath(".//div[contains(@class,'event__score')]")).Text;
                    singleMatch.awayTeam = match.FindElement(By.XPath(".//div[contains(@class,'participant--away')]")).Text;
                    singleMatch.time = match.FindElement(By.XPath(" .//div[@class='event__stage' or @class='event__time']")).Text;

                    fullResult = fullResult.Replace("\r", "");
                    singleMatch.result = fullResult.Split("\n").ToList();

                    if (i == 0) TodayMatches.Add(singleMatch);
                    else if (i == 1) LatestMatches.Add(singleMatch);
                    else if (i == 2) UpcomingMatches.Add(singleMatch);

                }
                i++;
                
            }

            
            
            //tabela ligowa
            ReadOnlyCollection<IWebElement> tableRow = driver.FindElements(By.XPath("//div[contains(@class,'table__row')]"));
            foreach (IWebElement row in tableRow)
            {
                TableRow singleRow = new TableRow();
                singleRow.position = row.FindElement(By.XPath(".//div[contains(@class,'rank')]")).Text;
                singleRow.name = row.FindElement(By.XPath(".//div[contains(@class,'participant_name')]")).Text.Replace("\r\n▼", "");
                singleRow.matchesCount = row.FindElement(By.XPath(".//div[contains(@class,'matches_played')]")).Text;
                singleRow.wins = row.FindElement(By.XPath(".//div[contains(@class,'wins_regular')]")).Text;
                singleRow.draws = row.FindElement(By.XPath(".//div[contains(@class,'draws')]")).Text;
                singleRow.losses = row.FindElement(By.XPath(".//div[contains(@class,'losses')]")).Text;
                singleRow.goals = row.FindElement(By.XPath(".//div[contains(@class,'goals')]")).Text;
                singleRow.points = row.FindElement(By.XPath(".//div[contains(@class,'points')]")).Text;

                FullTable.Add(singleRow);
            }

            string jsonTodayMatches = JsonConvert.SerializeObject(TodayMatches);
            string jsonLatestMatches = JsonConvert.SerializeObject(LatestMatches);
            string jsonUpcomingMatches = JsonConvert.SerializeObject(UpcomingMatches);
            string jsonTable = JsonConvert.SerializeObject(FullTable);
            string path1 = "Result/TodayMatches.json";
            string path2 = "Result/LatestMatches.json";
            string path3 = "Result/UpcomingMatches.json";
            string path4 = "Result/Table.json";

            if (File.Exists(path1))
            {
                File.Delete(path1);
                using (var tw = new StreamWriter(path1, true))
                {
                    tw.WriteLine(jsonTodayMatches.ToString());
                    tw.Close();
                }
            }

            else if (!File.Exists(path1))
            {
                using (var tw = new StreamWriter(path1, true))
                {
                    tw.WriteLine(jsonTodayMatches.ToString());
                    tw.Close();
                }
            }

            if (File.Exists(path2))
            {
                File.Delete(path2);
                using (var tw = new StreamWriter(path2, true))
                {
                    tw.WriteLine(jsonLatestMatches.ToString());
                    tw.Close();
                }
            }

            else if (!File.Exists(path2))
            {
                using (var tw = new StreamWriter(path2, true))
                {
                    tw.WriteLine(jsonLatestMatches.ToString());
                    tw.Close();
                }
            }

            if (File.Exists(path3))
            {
                File.Delete(path3);
                using (var tw = new StreamWriter(path3, true))
                {
                    tw.WriteLine(jsonUpcomingMatches.ToString());
                    tw.Close();
                }
            }

            else if (!File.Exists(path3))
            {
                using (var tw = new StreamWriter(path3, true))
                {
                    tw.WriteLine(jsonUpcomingMatches.ToString());
                    tw.Close();
                }
            }

            if (File.Exists(path4))
            {
                File.Delete(path4);
                using (var tw = new StreamWriter(path4, true))
                {
                    tw.WriteLine(jsonTable.ToString());
                    tw.Close();
                }
            }

            else if (!File.Exists(path4))
            {
                using (var tw = new StreamWriter(path4, true))
                {
                    tw.WriteLine(jsonTable.ToString());
                    tw.Close();
                }
            }

            driver.Close();
            driver.Quit();
        }
    }
}
