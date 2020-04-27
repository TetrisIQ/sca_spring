package de.eos.chart.controller;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping("/random")
	public String getRandom() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@PostMapping("/random")
	public String GetRandomStrING() {
		return UUID.randomUUID().toString().replace("-", "");
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
	
	
	@GetMapping("/long") 
	public void veryLongMethod() throws Exception {
		//TODO: remove debug prints ;)
		int a = 0;
		int b = -1;
		if(b < a ) {
			System.out.println("Heureka");
			b=a;
			a+= 10;
			if(b < a ) {
				throw new Exception();
			}else {
				System.out.println("NASE");
				a += b;
			}
			switch (a) {
			case 1:
				System.out.println(1);
				break;
			case 2:
				System.out.println(2);
				break;
			case 3:
				System.out.println(3);
				break;
			case 4:
				System.out.println(4);
				break;
			case 5:
				System.out.println(5);
				break;
			case 6:
				System.out.println(6);
				break;
			case 7:
				System.out.println(6);
				break;
			case 8:
				System.out.println(8);
				break;
			case 9:
				System.out.println(9);
				break;
			case 10:
				System.out.println(10);
				break;
			case 11:
				System.out.println(11);
				break;
			case 12:
				System.out.println(14);
				break;
			case 14:
				System.out.println(15);
				break;
			case 15:
				System.out.println(16);
				break;
			case 16:
				System.out.println(17);
				break;
			case 17:
				System.out.println(18);
				break;
			case 18:
				System.out.println(19);
				break;
			case 20:
				System.out.println(20);
				break;

			default:
				System.out.println(a);
			}
			
		}
	}

}
