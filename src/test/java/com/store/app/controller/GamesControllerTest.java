package com.store.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.google.gson.JsonElement;
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
		mockMvc.perform(delete("/api/admin/games/{id}", 2)).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void createGameTest() throws Exception {
		Games game = new Games(null, "Testgame", 100, "Racing", "test.png", null);
		Gson gson = new Gson();
		JsonElement jsonElement = gson.toJsonTree(game);
		jsonElement.getAsJsonObject().addProperty("releaseDate", "2012-02-02");
		String jsonInString = gson.toJson(jsonElement);
		mockMvc.perform(post("/api/admin/newGame").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
				.andExpect(status().isOk());
	}

//	@Test
//	@WithMockUser(roles = "ADMIN")
//	public void editGameTest() throws Exception {
//		Games game = new Games(null, "Testgame", 100, "Racing", "test.png", null);
//		Gson gson = new Gson();
//		JsonElement jsonElement = gson.toJsonTree(game);
//		jsonElement.getAsJsonObject().addProperty("releaseDate", "2012-02-02");
//		String jsonInString = gson.toJson(jsonElement);
//		mockMvc.perform(
//				put("/api/admin/editGame/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonInString))
//				.andExpect(status().isOk());
//	}

	@Test
	@WithMockUser(roles = "ADMIN")
	public void deleteGameByIdTest() throws Exception {
		mockMvc.perform(delete("/api/admin/games/{id}", 3)).andExpect(status().isOk());
	}
}
