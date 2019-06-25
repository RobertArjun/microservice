package com.robert.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.robert.db.shared.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Integer>{

	List<Quote> findByUserName(String name);

}
