package com.robert.db.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.robert.db.model.Quotes;
import com.robert.db.repository.QuotesRepository;
import com.robert.db.shared.Quote;

@RestController
@RequestMapping("/rest/db")
public class DBService {

	private QuotesRepository quotesRepository;

	@Autowired
	public DBService(QuotesRepository quotesRepository) {
		this.quotesRepository = quotesRepository;
	}

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String userName) {

		return getQuotesByUserName(userName);
	}

	private List<String> getQuotesByUserName(String userName) {
		
		return quotesRepository.findByUserName(userName)
				.stream()
				.map(quote -> quote.getQuote())
				.collect(Collectors.toList());
	}
	
	@PostMapping("/add")
	public List<String> addQuotes(@RequestBody final Quotes quotes) {

		quotes.getQuotes()
		.stream()
		.map(quote -> new Quote(quotes.getUserName(), quote))
		.forEach(quote -> quotesRepository.save(quote));
		
		return getQuotesByUserName(quotes.getUserName());

	}
	
	@PostMapping("/delete/{username}")
	public List<String> deleteQuote(@PathVariable("username") final String userName) {
		List<Quote> quotes = quotesRepository.findByUserName(userName);
		quotesRepository.deleteAll(quotes);
		
		return getQuotesByUserName(userName);
	}

}
