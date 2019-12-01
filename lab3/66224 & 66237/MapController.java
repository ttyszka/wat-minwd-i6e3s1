package com.karol.web.controller;


import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class MapController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getPages() {

		ModelAndView model = new ModelAndView("map");

		return model;
	}

	@RequestMapping(value = "/getLocation", method = RequestMethod.GET)
	@ResponseBody
	public String getDomainInJsonFormat(@RequestParam String ipAddress) throws IOException {

		return getZtmData(ipAddress);
	}

	public static String getZtmData(String line) throws IOException {
		URL urlForGetRequest = new URL("https://api.um.warszawa.pl/api/action/busestrams_get/?resource_id=%20f2e5503e927d-4ad3-9500-4ab9e55deb59&apikey=a0af8d50-7e5d-44b7-8cd3-7d9bf48b8a78&type=2&line=" + line);
		String result = "";
		String readLine = null;
		HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
		conection.setRequestMethod("GET");
		int responseCode = conection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(conection.getInputStream()));
			StringBuffer response = new StringBuffer();
			while ((readLine = in.readLine()) != null) {
				response.append(readLine);
			}
			in.close();
			result = response.toString();
			System.out.println("JSON String Result " + result);
		} else {
			System.out.println("GET NOT WORKED");
		}

		Gson gsonObj = new Gson();
		LocalizationList localizations = gsonObj.fromJson(result, LocalizationList.class);

		StringBuilder markers = new StringBuilder("[");

		for (VehicleLocalization vehicleLocalization : localizations.getList()) {
			markers.append("[")
					.append(vehicleLocalization.getLat())
					.append(", ")
					.append(vehicleLocalization.getLon())
					.append("],");
		}

		markers.deleteCharAt(markers.length() - 1);
		markers.append("]");

		return markers.toString();
	}


}
