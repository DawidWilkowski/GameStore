//package com.store.app.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.store.app.entity.Games;
//import com.store.app.repository.GamesRepository;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//public class GamesControllerTest {
//	@Autowired
//	MockMvc mockMvc;
//
//	@Autowired
//	GamesRepository gamesRepository;
//
//	/**
//	 * Get all games
//	 * 
//	 * @result GET request is properly performed and status 200 is returned
//	 */
//	@Test
//	public void getAllGamesTest() throws Exception {
//		mockMvc.perform(get("/api/guest/games")).andExpect(status().isOk());
//	}
//
//	/**
//	 * Get game with id 1
//	 * 
//	 * @result GET request is properly performed and game with id 1 is returned
//	 */
//
//	@Test
//	public void getGameByIdTest() throws Exception {
//		mockMvc.perform(get("/api/guest/games/{id}", 1)).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
//	}
//
//	/**
//	 * Get games with genre RPG
//	 * 
//	 * @result GET request is properly performed and game with genre - RPG is
//	 *         returned
//	 */
//	@Test
//	public void getGameByGenreTest() throws Exception {
//		mockMvc.perform(get("/api/guest/games/genre/{genre}", "RPG"))
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].genre").value("RPG"));
//	}
//
//	/**
//	 * Delete game without admin role
//	 * 
//	 * @result DELETE request is not performed and status 401 is returned
//	 */
//	@Test
//	public void deleteGameByIdWithoutAdmin() throws Exception {
//		mockMvc.perform(delete("/api/admin/games/{id}", 2)).andExpect(status().isUnauthorized());
//	}
//
//	/**
//	 * Post new game with admin role
//	 * 
//	 * @result POST request is performed and status 200 is returned
//	 */
//	@Test
//	// @WithMockUser(roles = "ADMIN")
//	public void createGameTest() throws Exception {
//		Games game = new Games(null, "Testgame", 100, "Racing", "test.png", null, null);
//		Gson gson = new Gson();
//		JsonElement jsonElement = gson.toJsonTree(game);
//		jsonElement.getAsJsonObject().addProperty("releaseDate", "2012-02-02");
//		String jsonInString = gson.toJson(jsonElement);
//		mockMvc.perform(post("/api/admin/newGame").contentType(MediaType.APPLICATION_JSON).content(jsonInString))
//				.andExpect(status().isOk());
//	}
//
//	/**
//	 * Edit existing game with admin role
//	 * 
//	 * @result PUT request is performed and status 200 is returned
//	 */
//	@Test
//	// @WithMockUser(roles = "ADMIN")
//	public void editGameTest() throws Exception {
//		Games game = new Games(null, "Testgame", 100, "Racing", "test.png", null, null);
//		Gson gson = new Gson();
//		JsonElement jsonElement = gson.toJsonTree(game);
//		jsonElement.getAsJsonObject().addProperty("releaseDate", "2012-02-02");
//		String jsonInString = gson.toJson(jsonElement);
//		mockMvc.perform(
//				put("/api/admin/editGame/{id}", 1).contentType(MediaType.APPLICATION_JSON).content(jsonInString))
//				.andExpect(status().isOk());
//	}
//
//	/**
//	 * DELETE existing game with admin role
//	 * 
//	 * @result DELETE request is performed and status 200 is returned
//	 */
//	@Test
//	 @WithMockUser(roles = "ADMIN")
//	public void deleteGameByIdTest() throws Exception {
//		mockMvc.perform(delete("/api/admin/games/{id}", 3)).andExpect(status().isOk());
//	}
//}
