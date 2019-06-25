package com.robert.stock.service;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/rest/stock")
public class StockService {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/getstock/{username}")
	public List<Stock> getStocks(@PathVariable("username") final String userName) {

		ResponseEntity<List<String>> response = restTemplate.exchange("http://db-service/rest/db/" + userName,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {});
		
		List<String> quotes = response.getBody();

		return quotes
				.stream()
				.map(this::getStockPrice)
				.collect(Collectors.toList());

	}

	private Stock getStockPrice(String quote) {
		try {
			return YahooFinance.get(quote);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Stock(quote);

	}
}
