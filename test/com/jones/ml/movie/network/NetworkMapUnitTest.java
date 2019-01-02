package com.jones.ml.movie.network;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class NetworkMapUnitTest extends TestCase {

	
	NeuralNet nn = new NeuralNet();
	List<Integer> map = new ArrayList<>();
	
	NetworkMap nm;
	int numLayer1Nodes = 3;
	int numLayer2Nodes = 1;
	int numWeightings = -1;
	
	@Before
	@Override
	public void setUp() {
		
	}
	
	
	@Test
	void testNetworkMap() {
		assertEquals(1,1);
	}

	@Test
	void testGetNodesByLayer() {
		assertEquals(1,1);
	}
	
	@Test
	void testCalculateWeightings() {
		
		map.add(numLayer1Nodes);
		map.add(numLayer2Nodes);
		nm = new NetworkMap(map);
		numWeightings = nm.calculateNumberOfWeightingsRequired();
		System.out.println(numWeightings);
		assertEquals(6, numWeightings);
		
	}

}
