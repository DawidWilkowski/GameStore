package com.store.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.store.app.repository.GamesRepository;

@RestController
@RequestMapping("/api")
public class GamesController {
	@Autowired
	private GamesRepository gamesRepository;

	/**
	 * Returns list of games from database.
	 */

	@GetMapping(value = "/guest/games")
	public ResponseEntity<List<Games>> getAllGames() {
		return new ResponseEntity<List<Games>>(gamesRepository.findAll(), HttpStatus.OK);
	}

	/**
	 * Returns game with given id.
	 * 
	 * @param game id
	 * 
	 */

	@GetMapping(value = "/guest/games/{id}")
	public ResponseEntity<Games> getGameById(@PathVariable(value = "id") Long id) {
		return gamesRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	/**
	 * Returns list of games starting from cheapest.
	 */

	@GetMapping(value = "/guest/games/cheapest")
	public ResponseEntity<List<Games>> getAllGamesCheapestFirst() {
		return new ResponseEntity<List<Games>>(gamesRepository.findByOrderByPriceAsc(), HttpStatus.OK);
	}

	/**
	 * Returns list of games from database with given title.
	 * 
	 * @param game category
	 */

	@GetMapping(value = "/guest/games/title/{title}")
	public ResponseEntity<List<Games>> getGamesByTitle(@PathVariable(value = "title") String title) {
		if (gamesRepository.findAllByTitleIgnoreCaseContaining(title).size() > 0) {
			return new ResponseEntity<List<Games>>(gamesRepository.findAllByTitleIgnoreCaseContaining(title),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Games>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Returns list of games from database with given category.
	 * 
	 * @param game category
	 */

	@GetMapping(value = "/guest/games/genre/{genre}")
	public ResponseEntity<List<Games>> getGamesByGenre(@PathVariable(value = "genre") String genre) {
		if (gamesRepository.findAllByGenre(genre).size() > 0) {
			return new ResponseEntity<List<Games>>(gamesRepository.findAllByGenre(genre), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Games>>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Delete game from database.
	 * 
	 * @param game id
	 */

	@DeleteMapping(value = "/admin/games/{id}")
	public ResponseEntity<String> deleteGameById(@PathVariable(value = "id") Long id) {
		if (gamesRepository.existsById(id)) {
			gamesRepository.deleteById(id);
			return new ResponseEntity<String>("Game succesfully deleted ", HttpStatus.OK);
		}
		return new ResponseEntity<String>("No game with id: " + id, HttpStatus.NOT_FOUND);

	}

	/**
	 * Adds new game to database.
	 * 
	 * @param game { "id": " ", "title": " ", "price": " ", genre: " ",
	 *             releaseDate:" " }
	 */

	@PostMapping(value = "/admin/newGame")
	public ResponseEntity<String> addNewGame(@RequestBody Games game) {
		if (gamesRepository.findByTitle(game.getTitle()) == null) {
			gamesRepository.save(game);
			return new ResponseEntity<String>("Succesfully added new game", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Game already exist", HttpStatus.CONFLICT);

		}
	}

	/**
	 * Edits game.
	 * 
	 * @param game { "id": " ", "title": " ", "price": " ", genre: " ",
	 *             releaseDate:"yyyy-mm-dd " }
	 */

	@PutMapping(value = "/admin/editGame/{id}")
	public ResponseEntity<String> editGame(@PathVariable(value = "id") Long id, @RequestBody Games game) {
		if (gamesRepository.existsById(id)) {
			gamesRepository.deleteById(id);
			gamesRepository.save(game);
			return new ResponseEntity<String>("Succesfully edited game", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No game with id: " + id, HttpStatus.NOT_FOUND);

		}
	}

}
