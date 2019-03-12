package com.nao.tenniskata;


import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class TennisSetTest extends TestCase{
	private TennisSet set;
	private Player player0;
	private Player player1;
	
	@Before
	public void setUp(){
		player0=  new Player((long) 1, "Novak", "Djokovic");
		player1=  new Player((long) 2, "Rafael", "Nadal");
		set = new TennisSet(1, player0, player1);
	}
	
	@Test
	public void testGetGameScore(){
		assertEquals(GamePoint.ZERO, set.getGameScore(player0));
	}
	
	@Test
	public void testGetPlayerIndex(){
		assertEquals(0, set.getPlayerIndex(player0));
		assertEquals(1, set.getPlayerIndex(player1));
		
		Player newPlayer = new Player();
		assertEquals(-1, set.getPlayerIndex(newPlayer));
		assertEquals(-1, set.getPlayerIndex(null));
	}
	
	@Test
	public void testMarquerPoint() {
		set.marquerPoint(player0);
		assertEquals(GamePoint.FIFTEEN, set.getGameScore( player0 ));
		set.marquerPoint(player0);
		assertEquals(GamePoint.THIRTY, set.getGameScore( player0 ));
		
		set.marquerPoint(player1);
		assertEquals(GamePoint.FIFTEEN, set.getGameScore( player1 ));
		assertEquals(GamePoint.THIRTY, set.getGameScore( player0 ));
	}
	
	@Test
	public void testFinishSet(){
		assertFalse( set.isFinished() );
		for(int i = 0; i < 23; i++){
			set.marquerPoint(player0);
		}
		int playerIndex = set.getPlayerIndex(player0);
		assertEquals(5, set.getSetScore()[playerIndex].getValue().intValue());
		assertFalse( set.isFinished() );
		
		set.marquerPoint(player0);
		assertEquals(6, set.getSetScore()[playerIndex].getValue().intValue());
		assertTrue( set.isFinished() );
	}
	
	@Test
	public void testGameScoreToString(){
		
		StringBuilder expected = new StringBuilder("Game score: \n");
		expected.append("\tDjokovic: ZERO\n");
		expected.append("\tNadal: ZERO");
		assertEquals(expected.toString(), set.gameScoreToString());
		
		set.marquerPoint(player1);
		expected = new StringBuilder("Game score: \n");
		expected.append("\tDjokovic: ZERO\n");
		expected.append("\tNadal: FIFTEEN");
		assertEquals(expected.toString(), set.gameScoreToString());
		
		
		set.setPlayer0(null);
		assertNull( set.gameScoreToString() );
	}
	
	@Test
	public void testSetScoreToString(){
		
		StringBuilder expected = new StringBuilder("Set score: \n");
		expected.append("\tDjokovic: 0\n");
		expected.append("\tNadal: 0");
		assertEquals(expected.toString(), set.setScoreToString());
		for (int i = 0; i < 4; i++) {
			set.marquerPoint(player1);
		}
		expected = new StringBuilder("Set score: \n");
		expected.append("\tDjokovic: 0\n");
		expected.append("\tNadal: 1");
		assertEquals(expected.toString(), set.setScoreToString());
		
		
		set.setPlayer0(null);
		assertNull( set.gameScoreToString() );
	}

}
