package com.store.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.store.app.entity.Games;
import com.store.app.repository.GamesRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class GamesControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	GamesRepository gamesRepository;

	@Test
	public void getAllGamesTest() throws Exception {
		mockMvc.perform(get("/games")).andExpect(status().isOk());
	}

	@Test
	public void getGameByIdTest() throws Exception {
		mockMvc.perform(get("/games/{id}", 1)).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	/**
	 * NOT PASSING
	 */
	@Test
	public void createGameTest() throws Exception {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = df.parse("02-02-2002");
		Games game = new Games(null, "Testgame", 100, "Racing", date);
		Gson gson = new Gson();
		String jsonInString = gson.toJson(game);
		mockMvc.perform(post("/newGame").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

	@Test
	public void deleteGameByIdTest() throws Exception {
		mockMvc.perform(delete("/games/{id}", 2)).andExpect(status().isOk());
	}
}
