package de.eos.chart.controller;

import java.math.BigDecimal;
import java.security.SecureRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import be.ceau.chart.BarChart;
import be.ceau.chart.DoughnutChart;
import be.ceau.chart.RadarChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.BarData;
import be.ceau.chart.data.DoughnutData;
import be.ceau.chart.data.RadarData;
import be.ceau.chart.dataset.BarDataset;
import be.ceau.chart.dataset.DoughnutDataset;
import be.ceau.chart.dataset.RadarDataset;
import be.ceau.chart.options.DoughnutOptions;

@Controller
public class HomeController {

	private final Color solconOrange = new Color(237, 147, 64);

	private final Double PI = Math.PI;

	@GetMapping("/chart/{chartId}")
	public String chart(Model model, @PathVariable String chartId) {
		switch (chartId) { // 0 - 2
		case "0":
			model.addAttribute("json", demoSampleDoughnut());
			break;
		case "1":
			model.addAttribute("json", demoSampleRadar());
			break;
		case "2":
			model.addAttribute("json", demoSampleWeek());
			break;
		default:
			break;
		}

		return "index";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		int random = new SecureRandom().nextInt(3); // 0 - 2
		return chart(model, random + "");
	}

	// chart Datas

	@GetMapping("/chart/sampleDoughnut")
	@ResponseBody
	private String demoSampleDoughnut() {
		DoughnutDataset dataset = new DoughnutDataset().setLabel("sample doughnut").setData(152, 246, 89, 80, 69, 67, 6)
				.addBackgroundColors(Color.RED, Color.BLACK, Color.BLUE, Color.YELLOW, Color.MAGENTA,
						Color.YELLOW_GREEN);

		DoughnutData data = new DoughnutData()
				.addLabels("SPD", "CDP", "AFD", "FDP", "Die Linke", "Bündnis 90/Die Grünen", "Fraktionslos")
				.addDataset(dataset);

		DoughnutOptions options = new DoughnutOptions();
		options.setRotation(new BigDecimal(PI)).setCircumference(new BigDecimal(PI));
		return new DoughnutChart(data).setOptions(options).toJson();
	}

	@GetMapping("/cahrt/sampleRadar")
	@ResponseBody
	private String demoSampleRadar() {
		RadarDataset dataset = new RadarDataset().setLabel("sample radar").setData(10, 10, 10, 10, 10, 10, 10)
				.setBorderColor(solconOrange).setBackgroundColor(new Color(solconOrange, 0.7));

		RadarData data = new RadarData()
				.addLabels("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
				.addDataset(dataset);
		return new RadarChart(data).toJson();
	}

	@GetMapping("/chart/sampleWeek")
	@ResponseBody
	private String demoSampleWeek() {
		BarDataset dataset = new BarDataset().setLabel("sample chart").setData(65, 59, 80, 81, 56, 55, 40, 0)
				.addBackgroundColor(solconOrange).setBorderWidth(2);

		BarData data = new BarData()
				.addLabels("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
				.addDataset(dataset);

		return new BarChart(data).toJson();
	}

}
