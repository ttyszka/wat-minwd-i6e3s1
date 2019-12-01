package MINWD3;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        List<String> cities = new ArrayList<>();
        cities.add("Warsaw,pl");
        cities.add("Moscow,ru");
        cities.add("MIAMI,us");
        cities.add("SYDNEY,au");
        cities.add("Phuket Province,th");

        OWAApi api = OWAApi.getInstance();
        String result;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder visibility = new StringBuilder();
        StringBuilder humidity = new StringBuilder();
        StringBuilder pressure = new StringBuilder();
        StringBuilder temperature = new StringBuilder();
        StringBuilder cloudiness = new StringBuilder();
        StringBuilder wind = new StringBuilder();
        StringBuilder main = new StringBuilder();
        StringBuilder description = new StringBuilder();


        for (String city : cities) {

            JSONObject object = api.getWeather(city);

            sb1.append("<th>" + city + "</th>");

            visibility.append("<td>" + object.get("visibility") + "</td>");
            temperature.append("<td>" + ((JSONObject) object.get("main")).get("temp") + "</td>");
            cloudiness.append("<td>" + ((JSONObject) object.get("clouds")).get("all") + "</td>");
            wind.append("<td>" + ((JSONObject) object.get("wind")).get("speed") + "</td>");
            pressure.append("<td>" + ((JSONObject) object.get("main")).get("pressure") + "</td>");
            humidity.append("<td>" + ((JSONObject) object.get("main")).get("humidity") + "</td>");
            description.append("<td>"+object.getJSONArray("weather").getJSONObject(0).get("description")+"</td>");
            main.append("<td>"+object.getJSONArray("weather").getJSONObject(0).get("main")+"</td>");
        }
        result = "<head><style>" +
                "table, th, td {" +
                "  border: 1px solid black;" +
                "  border-collapse: collapse;" +
                "}" +
                "th, td {" +
                "  padding: 5px;" +
                "}" +
                "th {" +
                "  text-align: left;" +
                "}" +
                "</style>" +
                "</head><body><table style=\"width:100%\"> <tr>" + "<th>" + "Miasto" + "</th>" + sb1.toString().replaceAll("\"", "") + "</tr>" + "<th>Tempreratura[°C]</th>" + temperature.toString() + "</tr>" + "<tr><th>Ciśnienie[hPa]</th>" + pressure.toString() + "</tr>" +
                "<tr><th>Zachmurzenie[%]</th>" + cloudiness.toString() + "</tr>" + "<tr><th>Widoczność[m]</th>" + visibility.toString() + "</tr>" + "<tr><th>Prędkość wiatru[m/s]</th>" + wind.toString() + "</tr>" +
                "<tr><th>Wilgotność[%]</th>" + humidity.toString() + "</tr>" +  "<tr><th>Typ</th>" + main.toString() + "</tr>" + "<tr><th>Opis</th>" + description.toString() + "</tr>" +"</table></body>";




        File f = new File("wynik.html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(result);
        bw.close();
    }

}