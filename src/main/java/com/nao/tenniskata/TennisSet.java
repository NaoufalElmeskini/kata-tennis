package com.nao.tenniskata;

import java.util.AbstractMap.SimpleEntry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.Getter;
import lombok.Setter;


public class TennisSet {
	@Getter @Setter private Long id;
	@Getter @Setter private int setNumber;
	
	@Getter @Setter private Player player0;
	@Getter @Setter private Player player1;
	
	@Getter private SimpleEntry<Player, Integer>[] setScore;	//Getter only, only marquerPoint() methods can modify this member
	@Getter private SimpleEntry<Player, GamePoint>[] gameScore;	//Getter only, only marquerPoint() methods can modify this member
	
	@Getter private boolean isFinished;	// Indicates if the set is finished or still on
	@Getter private Player setWinner; // The winner of this set. He is only determined at the end of the set.
	final static private Logger log = LogManager.getLogger(TennisSet.class);
	
	
	/************************ CONSTRUCTORS ******************************/
	
	public TennisSet(int setNumber, Player player0, Player player1) {
		super();
		this.setNumber = setNumber;
		this.player0 = player0;
		this.player1 = player1;
		
		setScore = new SimpleEntry[2];
		gameScore = new SimpleEntry[2];
		
		setScore[0] = new SimpleEntry<Player, Integer>(player0, 0);
		setScore[1] = new SimpleEntry<Player, Integer>(player1, 0);
		gameScore[0] = new SimpleEntry<Player, GamePoint>(player0, GamePoint.ZERO);
		gameScore[1] = new SimpleEntry<Player, GamePoint>(player1, GamePoint.ZERO);
	}
	
	/******************************************************************/

	
	@Override
	public String toString() {
		return "TennisSet [id=" + id + ", setNumber=" + setNumber + ", setScore=" + setScore + ", gameScore="
				+ gameScore + ", isFinished=" + isFinished + "]";
	}

	
	/** 
	 * 
	 * @param player
	 * @return This method returns the score of the player passed in parameter
	 */
	public GamePoint getGameScore(Player player){
		int playerIndex = getPlayerIndex(player);
		if(playerIndex == -1){
			return null;
		}
		return gameScore[playerIndex].getValue();
	}
	
	//TODO
	public void marquerPoint(Player player){
		int playerIndex = getPlayerIndex( player );
		
		if( (playerIndex == -1) || (this.isFinished) ){
			return;
		}
		GamePoint oldGameScore = gameScore[ playerIndex ].getValue();
		GamePoint newGameScore = oldGameScore.getNextPoint();
		gameScore[ playerIndex ].setValue( newGameScore );
		
		if(newGameScore.equals(GamePoint.GAME)){
			int newSetScore = setScore[playerIndex].getValue() +1; 
			setScore[ playerIndex ].setValue( newSetScore );// Increment set score
			reinitializeGameScore();
			
			if(newSetScore >= 6){
				int otherPlayerIndex = (playerIndex + 1) %2; // The index of the other player in the score tables
				int otherPlayerScore = setScore[ otherPlayerIndex ].getValue();
				if(newSetScore >= otherPlayerScore + 2){
					isFinished = true;
					setWinner = player;
					System.out.println("=====END OF SET " + setNumber +"=====");
					System.out.println("======================\n");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param Player of which we search for index
	 * @return Index of the player in parameter in both score tables.
	 */
	public int getPlayerIndex(Player player){
		for (int i = 0; i < 2; i++) {
			if( gameScore[i].getKey().equals( player ) ){
				return i;
			}
		}
		return -1;
	}
	
	private void reinitializeGameScore(){
		for (int i = 0; i < 2; i++) {
			gameScore[i].setValue(GamePoint.ZERO);
		}
	}
	
	/**
	 * Transforms the game score to a readable String
	 * @return
	 */
	public String gameScoreToString(){
		if(player0 == null || player1 == null){
			log.error("one of the players is null, score can't be displayed.");
			return null;
		}
		int player0index = getPlayerIndex(player0);
		int player1index = getPlayerIndex(player1);
		
		StringBuilder sb = new StringBuilder("Game score: \n");
		sb.append("\t" + player0.getLastName() + ": " + gameScore[player0index].getValue() + "\n");
		sb.append("\t" + player1.getLastName() + ": " + gameScore[player1index].getValue());
		
		return sb.toString();
	}
	
	/**
	 * Transforms the set score to a readable String
	 * @return
	 */
	public String setScoreToString(){
		if(player0 == null || player1 == null){
			log.error("one of the players is null, score can't be displayed.");
			return null;
		}
		int player0index = getPlayerIndex(player0);
		int player1index = getPlayerIndex(player1);
		
		StringBuilder sb = new StringBuilder("Set score: \n");
		sb.append("\t" + player0.getLastName() + ": " + setScore[player0index].getValue() + "\n");
		sb.append("\t" + player1.getLastName() + ": " + setScore[player1index].getValue());
		
		return sb.toString();
	}
		
}