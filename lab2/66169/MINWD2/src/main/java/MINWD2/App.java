package MINWD2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class App {

    private static WebDriver webDriver;
    private static WebDriverWait webDriverWait;

    public static void main(String[] args) throws IOException {

        System.setProperty("webdriver.chrome.driver", "src/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        webDriver = new ChromeDriver(options);
        webDriverWait = new WebDriverWait(webDriver, 30);
        webDriver.get("https://www.flashscore.pl/pilka-nozna/anglia/premier-league/");
        //https://www.flashscore.pl/pilka-nozna/wlochy/serie-a/

        JSONObject obj = new JSONObject();
        JSONObject obj2 = new JSONObject();

        int i = 1;

        List<WebElement> tables = webDriver.findElements(By.xpath("//div[@class='sportName soccer']"));

        for (WebElement table : tables) {
            System.out.println();
            if (i == 1 && tables.size() == 3) System.out.println("Dzisiejsze mecze");
            else if (i == 2 && tables.size() == 3 || (i == 1 && tables.size() != 3))
                System.out.println("Najswiezsze wyniki");
            else if ((i == 3 && tables.size() == 3) || (i == 2 && tables.size() != 3))
                System.out.println("Nadchodzące spotkania");
            List<WebElement> matches = table.findElements(By.xpath(".//div[contains(@class,'event__match event__match')]"));

            for (WebElement match : matches) {

                JSONArray results = new JSONArray();

                String homeName = match.findElement(By.xpath(".//div[contains(@class,'participant--home')]")).getText();
                String result = match.findElement(By.xpath(".//div[contains(@class,'event__score')]")).getText();
                String visitorName = match.findElement(By.xpath(".//div[contains(@class,'participant--away')]")).getText();
                String time = match.findElement(By.xpath(" .//div[@class='event__stage' or @class='event__time']")).getText();

                if (i == 1 && tables.size() == 3)
                    System.out.print("Minuta spotkania: " + time + "    ");
                else
                    System.out.print("Data spotkania: " + time + "    ");

                System.out.println();
                System.out.print(homeName + " ");


                obj.put("Gospodarze: ", homeName);
                obj.put("Goście: ", visitorName);
                obj.put("Time: ", time);

                obj2.put("Gospodarze: ", homeName);
                obj2.put("Goście: ", visitorName);
                obj2.put("Time: ", time);


                List<String> resultElements = Arrays.asList(result.split("\n"));
                for (String element : resultElements) {
                    System.out.print(element + " ");
                    results.add(element);
                    obj.put("Wynik: ", results);
                    obj2.put("Wynik: ", results);

                }


                System.out.print(visitorName);

                String idFull = match.getAttribute("id");
                String id = idFull.substring(idFull.lastIndexOf("_") + 1);
                String detailsLink = "https://www.flashscore.pl/mecz/" + id + "/#szczegoly-meczu";
                System.out.println();

                if (i != tables.size()) {
                    System.out.println("Szczegoly meczu: " + detailsLink);
                    obj.put("Szczegoly", detailsLink);
                }

                System.out.println("-------------------------------------------");

                Gson tmp = new GsonBuilder().setPrettyPrinting().create();
                String json = tmp.toJson(obj);
                String json2 = tmp.toJson(obj2);


                if (i == 1 && tables.size() == 3) {
                    try (BufferedWriter writer1 = new BufferedWriter(new FileWriter("dzisiejszeMecze.txt", true))) {
                       writeToFile(json, writer1);

                    }
                } else if (i == 2 && tables.size() == 3 || (i == 1 && tables.size() != 3)) {
                    try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("najswiezszeWyniki.txt", true))) {
                        writeToFile(json, writer2);
                    }
                } else if ((i == 3 && tables.size() == 3) || (i == 2 && tables.size() != 3)) {
                    try (BufferedWriter writer3 = new BufferedWriter(new FileWriter("nadchodzaceMecze.txt", true))) {
                        writeToFile(json2, writer3);
                    }

                }

            }
            i++;
        }

        System.out.println();
        System.out.println("Tabela ligowa:");
        System.out.println("Pozycja     Nazwa       Mecze     Wygrane    Remisy    Przegrane     Bilans    Punkty");
        JSONObject obj3 = new JSONObject();
        List<WebElement> tableRow = webDriver.findElements(By.xpath("//div[contains(@class,'table__row')]"));
        for (WebElement row : tableRow) {
            String position = row.findElement(By.xpath(".//div[contains(@class,'rank')]")).getText();
            String name = row.findElement(By.xpath(".//div[contains(@class,'participant_name')]")).getText().replaceAll("\n", "");
            String matches = row.findElement(By.xpath(".//div[contains(@class,'matches_played')]")).getText();
            String wins = row.findElement(By.xpath(".//div[contains(@class,'wins_regular')]")).getText();
            String draws = row.findElement(By.xpath(".//div[contains(@class,'draws')]")).getText();
            String losses = row.findElement(By.xpath(".//div[contains(@class,'losses')]")).getText();
            String goals = row.findElement(By.xpath(".//div[contains(@class,'goals')]")).getText();
            String points = row.findElement(By.xpath(".//div[contains(@class,'points')]")).getText();

            printTable(position,10);
            printTable(name,20);
            printTable(matches,10);
            printTable(wins,10);
            printTable(draws,10);
            printTable(losses,10);
            printTable(goals,10);
            printTable(points,10);
            System.out.println();


            obj3.put("Pozycja: ", position);
            obj3.put("Nazwa: ", name);
            obj3.put("Mecze: ", matches);
            obj3.put("Wygrane: ", wins);
            obj3.put("Remisy: ", draws);
            obj3.put("Przegrane: ", losses);
            obj3.put("Bilans: ", goals);
            obj3.put("Punkty: ", points);

            BufferedWriter writer4 = new BufferedWriter(new FileWriter("tabelaLigowa.txt", true));
            Gson tmp2 = new GsonBuilder().setPrettyPrinting().create();
            String json = tmp2.toJson(obj3);
            writeToFile(json, writer4);

        }
        webDriver.quit();
    }

    private static void printTable(String value, int width) {
        System.out.print(value);
        for (int i =0 ; i<width-value.length();i++) System.out.print(" ");
    }


    private static void writeToFile(String json, BufferedWriter writer2) throws IOException {
        writer2.flush();
        writer2.write("\n" + "Sysdate: " + String.valueOf(LocalDate.now()) + "\n");
        writer2.append("\n" + json);
        writer2.append(' ');
        writer2.close();
    }
}