package com.nao.tenniskata;

import static org.junit.Assert.*;

import org.junit.Test;

public class GamePointTest {
	private GamePoint gamePoint;

	@Test
	public void testGetNextPoint() {
		assertEquals(GamePoint.ZERO.getNextPoint(), GamePoint.FIFTEEN);
		assertEquals(GamePoint.FIFTEEN.getNextPoint(), GamePoint.THIRTY);
		assertEquals(GamePoint.THIRTY.getNextPoint(), GamePoint.FOURTY);
		assertEquals(GamePoint.FOURTY.getNextPoint(), GamePoint.GAME);
		assertEquals(GamePoint.ADV.getNextPoint(), GamePoint.GAME);
		assertEquals(GamePoint.DEUCE.getNextPoint(), GamePoint.GAME);
		assertEquals(GamePoint.GAME.getNextPoint(), GamePoint.GAME);
		
		assertEquals( GamePoint.getNextPoint(GamePoint.ZERO), GamePoint.FIFTEEN );
		assertEquals( GamePoint.getNextPoint( GamePoint.FIFTEEN ), GamePoint.THIRTY );
		assertEquals( GamePoint.getNextPoint(GamePoint.THIRTY), GamePoint.FOURTY );
		assertEquals( GamePoint.getNextPoint(GamePoint.FOURTY), GamePoint.GAME );
		assertEquals( GamePoint.getNextPoint(GamePoint.ADV), GamePoint.GAME );
		assertEquals( GamePoint.getNextPoint(GamePoint.DEUCE), GamePoint.GAME );
		assertEquals( GamePoint.getNextPoint(GamePoint.GAME), GamePoint.GAME );
	}
	


}
