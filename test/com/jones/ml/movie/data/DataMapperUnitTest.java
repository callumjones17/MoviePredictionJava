package com.jones.ml.movie.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class DataMapperUnitTest extends TestCase {

	DataMapper dm = new DataMapper(true);
	
	@Before
	@Override
	public void setUp() {
		
	}
	
	@Test
	void testMapInt() {
		
		//dm.mapInt(5, 3);
		//dm.mapInt(-1,4);
		
		assertEquals(0.5f,dm.mapInt(75, 100));
		assertEquals(-1.0f,dm.mapInt(0, 100));
		assertEquals(1.0f,dm.mapInt(100, 100));
		assertEquals(-0.5f,dm.mapInt(25, 100));
	}
	
	@Test
	void testMapFloat() {
		
		assertEquals(0.5f,dm.mapFloat(75, 100));
		assertEquals(-1.0f,dm.mapFloat(0, 100));
		assertEquals(1.0f,dm.mapFloat(100, 100));
		assertEquals(-0.5f,dm.mapFloat(25, 100));
	}

}
