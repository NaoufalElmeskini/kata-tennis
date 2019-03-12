package com.nao.tenniskata;

import lombok.Getter;
import lombok.Setter;

public class Player {
	@Getter @Setter	private Long id;
	@Getter @Setter private String firstName;
	@Getter @Setter private String lastName;
	
	public Player(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Player() {}

	@Override
	public String toString() {
		return "Player [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	

}
