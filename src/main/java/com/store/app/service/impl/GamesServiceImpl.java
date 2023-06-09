package com.store.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.store.app.entity.Games;
import com.store.app.repository.GamesRepository;
import com.store.app.service.GamesService;

@Service
public class GamesServiceImpl implements GamesService {

	@Autowired
	private GamesRepository gamesRepository;

	@Override
	public ResponseEntity<String> addNewGame(Games game) {
		if (findByTitle(game.getTitle()) == null) {
			save(game);
			return new ResponseEntity<String>("Succesfully added new game", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Game already exist", HttpStatus.CONFLICT);
		}
	}

	@Override
	public ResponseEntity<String> deleteGamesById(Long id) {
		if (gamesRepository.existsById(id)) {
			gamesRepository.deleteById(id);
			return new ResponseEntity<String>("Game succesfully deleted ", HttpStatus.OK);
		}
		return new ResponseEntity<String>("No game with id: " + id, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> editGame(Long id, Games game) {
		if (existsById(id)) {
			deleteGamesById(id);
			save(game);
			return new ResponseEntity<String>("Succesfully edited game", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No game with id: " + id, HttpStatus.NOT_FOUND);

		}
	}

	@Override
	public boolean existsById(Long id) {
		if (gamesRepository.existsById(id))
			return true;
		else
			return false;
	}

	@Override
	public ResponseEntity<List<Games>> findByOrderByPriceAsc() {
		return new ResponseEntity<List<Games>>(gamesRepository.findByOrderByPriceAsc(), HttpStatus.OK);

	}

	@Override
	public Games findByTitle(String title) {
		return gamesRepository.findByTitle(title);
	}

	public ResponseEntity<List<Games>> getAllGames() {
		return new ResponseEntity<List<Games>>(gamesRepository.findAll(), HttpStatus.OK);
	}

	@Override
	public List<Games> getAllGamesCheapestFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Object> getGameById(Long id) {
		Games gameWithId = gamesRepository.findById(id).orElse(null);
		if (gameWithId == null) {
			return new ResponseEntity<Object>("No game with id: " + id, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(gameWithId, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getGamesByGenre(String genre) {
		if (gamesRepository.findAllByGenre(genre).size() > 0) {
			return new ResponseEntity<Object>(gamesRepository.findAllByGenre(genre), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("No game with genre: " + genre, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Object> getGamesByTitle(String title) {
		if (gamesRepository.findAllByTitleIgnoreCaseContaining(title).size() > 0) {
			return new ResponseEntity<Object>(gamesRepository.findAllByTitleIgnoreCaseContaining(title), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>("No game with title: " + title, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public void save(Games game) {
		gamesRepository.save(game);

	}
}
