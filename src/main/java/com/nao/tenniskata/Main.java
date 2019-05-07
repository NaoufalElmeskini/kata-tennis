package com.nao.tenniskata;

public class Main {

	public static void main(String[] args) {
		Player _Djoko=  new Player((long) 1, "Novak", "Djokovic");
		Player _Federer=  new Player((long) 2, "Roger", "Federer");
		
		TennisMatch match = new TennisMatch(1, _Djoko, _Federer);
		match.start();
	}

}
