package com.store.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.app.entity.Games;

public interface GamesRepository extends JpaRepository<Games, Long> {

	/**
	 * Returns game with given title.
	 * 
	 * @param game title
	 */

	Games findByTitle(String title);

	/**
	 * Returns games with given title.
	 * 
	 * @param game title
	 */
	List<Games> findAllByTitleIgnoreCaseContaining(String title);

	/**
	 * Returns games with given genre.
	 * 
	 * @param game category
	 */

	List<Games> findAllByGenre(String genre);

	/**
	 * Returns list of games starting with cheapest.
	 * 
	 */

	List<Games> findByOrderByPriceAsc();

}
