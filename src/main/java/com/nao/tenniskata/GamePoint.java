package com.nao.tenniskata;

public enum GamePoint {
	ZERO, FIFTEEN, THIRTY, FOURTY, ADV, DEUCE, GAME;
	
	public static GamePoint getNextPoint(GamePoint gamePoint){
		GamePoint result = GamePoint.GAME;
		int ordinal = gamePoint.ordinal();
		GamePoint[] values = GamePoint.values();
		
		if(ordinal < 3){
			result = values[ordinal +1];
		}
		
		return result;
	}
	
	public GamePoint getNextPoint(){
		GamePoint result = GamePoint.GAME;
		int ordinal = this.ordinal();
		GamePoint[] values = GamePoint.values();
		
		if(ordinal < 3){
			result = values[ordinal +1];
		}
		
		return result;
	}

}
