package com.store.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.app.entity.Games;
import com.store.app.repository.GamesRepository;

@RequestMapping("/view")
@Controller
public class GamesControllerView {

	@Autowired
	private GamesRepository gamesRepository;

	/**
	 * Returns thymeleaf template with api GUI.
	 * 
	 */
	@GetMapping(value = "/")
	public String index() {
		return "index";
	}

	/**
	 * Returns thymeleaf template with shop page.
	 * 
	 */
	@GetMapping(value = "/page")
	public String page(Model model) {
		List<Games> games = gamesRepository.findAll().stream().limit(4).collect(Collectors.toList());
		model.addAttribute("games", games);
		return "page";
	}
}