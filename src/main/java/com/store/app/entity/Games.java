package com.store.app.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Games")
public class Games {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private float price;
	@Lob
	@Column(name = "GAME_DESCRIPTION")
	private String gameDescription;
	private String genre;
	@Column(name = "PATH_TO_IMAGE")
	private String pathToImage;
	@Temporal(TemporalType.DATE)
	@Column(name = "RELEASE_DATE")
	private Date releaseDate;
//
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "gameDetailsId", referencedColumnName = "id")
//	private GamesDetails gameDetails;
}
