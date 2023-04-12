package com.store.app.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
import org.springframework.security.test.context.support.WithMockUser;
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
		mockMvc.perform(get("/api/guest/games")).andExpect(status().isOk());
	}

	@Test
	public void getGameByIdTest() throws Exception {
		mockMvc.perform(get("/api/guest/games/{id}", 1)).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}

	@Test
	public void getGameByGenreTest() throws Exception {
		mockMvc.perform(get("/api/guest/games/genre/{genre}", "RPG"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value("RPG"));
	}

	@Test
	public void deleteGameByIdWithoutAdmin() throws Exception {
		mockMvc.perform(delete("/games/{id}", 2)).andExpect(status().isUnauthorized());
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
		mockMvc.perform(post("/newGame").with(user("admin").roles("ADMIN")).contentType(MediaType.APPLICATION_JSON)
				.content(jsonInString)).andExpect(status().isOk());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void deleteGameByIdTest() throws Exception {
		mockMvc.perform(delete("/games/{id}", 2)).andExpect(status().isOk());
	}
}
