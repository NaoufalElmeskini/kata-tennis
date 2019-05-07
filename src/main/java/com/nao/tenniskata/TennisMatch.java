package com.nao.tenniskata;

import java.util.LinkedList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TennisMatch {
	private Long id;
	private Player player0;
	private Player player1;
	private LinkedList <TennisSet> matchSets;
	private boolean isFinished;	// Indicates if the match is finished or still on
	
	
	
	public TennisMatch(int id, Player player0, Player player1) {
		super();
		this.id = (long) id;
		this.player0 = player0;
		this.player1 = player1;
		this.matchSets = new LinkedList<TennisSet>();
	}


	//TODO
	public String getTotalScore(){
		String score = "";
		return score;
	}
	
	//TODO
	public void marquerPoint(Player player){
		if( matchSets.isEmpty() || matchSets.getLast().isFinished()){
			TennisSet set = new TennisSet( matchSets.size() +1, player0, player1 );
			this.matchSets.add(set);
		}
		
		TennisSet lastSet = matchSets.getLast();
		lastSet.marquerPoint(player);
		if(lastSet.getSetNumber() == 5 && lastSet.isFinished()){ // End of Set 5 = End of match
			this.isFinished = true;
		}
	}


	public void start() {
		while( !this.isFinished ){
			// Display the last set score (if there is one)
			if(matchSets.size() >0){
				TennisSet lastSet = matchSets.getLast();
				System.out.println( lastSet.setScoreToString() );
				System.out.println( lastSet.gameScoreToString() );
				System.out.println("======================");
			}

			
			int rand = (int) Math.round(Math.random()); 
			if (rand == 0){
				marquerPoint(player0);
			}else{
				marquerPoint(player1);
			}
		}
	}
}
