package com.store.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.app.entity.Games;
import com.store.app.service.GamesService;

@RestController
@RequestMapping("/api")
public class GamesController {
	@Autowired
	private GamesService gamesService;

	/**
	 * Adds new game to database.
	 * 
	 * @param game { "id": " ", "title": " ", "price": " ", genre: " ",
	 *             releaseDate:" " }
	 */

	@PostMapping(value = "/admin/newGame")
	public ResponseEntity<String> addNewGame(@RequestBody Games game) {
		return gamesService.addNewGame(game);
	}

	/**
	 * Delete game from database.
	 * 
	 * @param game id
	 */

	@DeleteMapping(value = "/admin/games/{id}")
	public ResponseEntity<String> deleteGameById(@PathVariable(value = "id") Long id) {
		return gamesService.deleteGamesById(id);

	}

	/**
	 * Edits game.
	 * 
	 * @param game { "id": " ", "title": " ", "price": " ", genre: " ",
	 *             releaseDate:"yyyy-mm-dd " }
	 */

	@PutMapping(value = "/admin/editGame/{id}")
	public ResponseEntity<String> editGame(@PathVariable(value = "id") Long id, @RequestBody Games game) {
		return gamesService.editGame(id, game);
	}

	/**
	 * Returns list of games from database.
	 */

	@GetMapping(value = "/guest/games")
	public ResponseEntity<List<Games>> getAllGames() {
		return gamesService.getAllGames();
	}

	/**
	 * Returns list of games starting from cheapest.
	 */

	@GetMapping(value = "/guest/games/cheapest")
	public ResponseEntity<List<Games>> getAllGamesCheapestFirst() {
		return gamesService.findByOrderByPriceAsc();
	}

	/**
	 * Returns game with given id.
	 * 
	 * @param game id
	 * 
	 */

	@GetMapping(value = "/guest/games/{id}")
	public ResponseEntity<Object> getGameById(@PathVariable(value = "id") Long id) {
		return gamesService.getGameById(id);
	}

	/**
	 * Returns list of games from database with given category.
	 * 
	 * @param game category
	 */

	@GetMapping(value = "/guest/games/genre/{genre}")
	public ResponseEntity<Object> getGamesByGenre(@PathVariable(value = "genre") String genre) {
		return gamesService.getGamesByGenre(genre);
	}

	/**
	 * Returns list of games from database with given title.
	 * 
	 * @param game category
	 */

	@GetMapping(value = "/guest/games/title/{title}")
	public ResponseEntity<Object> getGamesByTitle(@PathVariable(value = "title") String title) {
		return gamesService.getGamesByTitle(title);

	}

}
