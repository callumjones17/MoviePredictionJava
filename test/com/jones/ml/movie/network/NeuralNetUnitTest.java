/**
 * 
 */
package com.jones.ml.movie.network;

import com.jones.ml.movie.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

/**
 * @author Callum Jones, 2018
 *
 */
class NeuralNetUnitTest extends TestCase {

	NeuralNet nn = new NeuralNet();
	NetworkMap nm;
	int numLayer1Nodes = 3;
	
	@Before
	@Override
	public void setUp() {
		List<Integer> map = new ArrayList<>();
		map.add(numLayer1Nodes);
		nm = new NetworkMap(map);
	}
	
	@Test
	void testRunThroughNetworkOnce1to1() {
		
		List<Float> data = new ArrayList<>();
		for (float i = 0.0f; i<numLayer1Nodes; i++) {
			data.add(i);
		}
		
		Agent agent = new Agent(numLayer1Nodes); 
		
		nn.runThroughNetworkOnce(agent, data, nm);
	}
	
	@Test
	void testFireNode() {
		
		float value = -1;
		boolean result = false;
		
		value = nn.fireNode(5, 6);
		if (value <= 1 & value >= 0 ) {result = true; assertEquals(true,result); result = false;}
		
		value = nn.fireNode(6, 6);
		if (value <= 1 & value >= 0 ) {result = true; assertEquals(true,result); result = false;}
		
		value = nn.fireNode(1, 6);
		if (value <= 1 & value >= 0 ) {result = true; assertEquals(true,result); result = false;}
	}

}
