package com.store.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.store.app.entity.Games;

public interface GamesService {
	ResponseEntity<String> addNewGame(Games game);

	ResponseEntity<String> deleteGamesById(Long id);

	ResponseEntity<String> editGame(Long id, Games game);

	boolean existsById(Long id);

	ResponseEntity<List<Games>> findByOrderByPriceAsc();

	Games findByTitle(String title);

	ResponseEntity<List<Games>> getAllGames();

	List<Games> getAllGamesCheapestFirst();

	ResponseEntity<Object> getGameById(Long id);

	ResponseEntity<Object> getGamesByGenre(String genre);

	ResponseEntity<Object> getGamesByTitle(String title);

	void save(Games game);

}
