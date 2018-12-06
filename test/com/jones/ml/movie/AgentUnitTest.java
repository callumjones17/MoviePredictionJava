package com.jones.ml.movie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jones.ml.movie.network.NetworkMap;

import java.util.*;

import junit.framework.TestCase;

class AgentUnitTest extends TestCase {
	
	private Agent agent;
	private List<Integer> mapI = new ArrayList<>();
	private NetworkMap map;
	
	@Override
	@Before
	public void setUp() {
		
	}

	@Test
	void testAgent() {
		mapI.clear();
		mapI.add(5);
		mapI.add(3);
		mapI.add(1);
		map = new NetworkMap(mapI); // Num Weightings is know to be 8. Will just hardcode for this test
		
		agent = new Agent(8,map);
		assertEquals(8, agent.getWeightings().size());
	}


}
