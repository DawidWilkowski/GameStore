package com.store.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.app.entity.Games;

public interface GamesRepository extends JpaRepository<Games, Long> {
	/**
	 * Returns game with given title.
	 * 
	 * @param game title
	 */
	Games findByTitle(String title);

}
