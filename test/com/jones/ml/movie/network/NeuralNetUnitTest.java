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
	List<Integer> map = new ArrayList<>();
	private Random random = new Random();
	NetworkMap nm;
	Agent agent;
	int numLayer1Nodes = 3;
	int numLayer2Nodes = 1;
	int numWeightings = -1;
	
	@Before
	@Override
	public void setUp() {
	}
	
	@Test
	void testRunThroughNetworkOnce1to1() {
		
		nn = new NeuralNet();
		
		System.out.println("Testing Run Through Once");
		List<Float> data = new ArrayList<>();		
		
		long millis = System.currentTimeMillis() % 1000;
		random.setSeed(millis);
		
		
		for (float i = 0.0f; i<numLayer1Nodes; i++) {
			data.add((random.nextFloat()*2.0f)-1.0f);
		}
		map.add(3);
		map.add(5);
		map.add(8);
		map.add(5);
		map.add(3);
		map.add(1);
		nm = new NetworkMap(map);
		numWeightings = nm.calculateNumberOfWeightingsRequired();
		Agent agent = new Agent(numWeightings,true); 
		Agent agentB = new Agent(numWeightings,true);
		System.out.println(agent.getWeightings());
		System.out.println(agentB.getWeightings());
		
		//System.out.println(agent.getWeightings());
		//System.out.println(agent.getNumWeightings());
		List<List<Float>> layers = nn.runThroughNetworkOnce(agent, data, nm);
		
		System.out.println("End of Testing Run Through Once");
		for (List<Float> layer: layers) {
			System.out.println(layer);
		}
		
		List<List<Float>> layersB = nn.runThroughNetworkOnce(agentB, data, nm);
		
		System.out.println("End of Testing Run Through Once");
		for (List<Float> layer: layersB) {
			System.out.println(layer);
		}
	}
	
	@Test
	void testFireNode() {
		
		/*float value = -1;
		boolean result = false;
		
		value = nn.fireNode(5, 6);
		if (value <= 1 & value > 0 ) {result = true; assertEquals(true,result); result = false;}
		
		value = nn.fireNode(6, 6);
		if (value <= 1 & value > 0 ) {result = true; assertEquals(true,result); result = false;}
		
		value = nn.fireNode(1, 6);
		if (value <= 1 & value > 0 ) {result = true; assertEquals(true,result); result = false;}
		
		value = nn.fireNode(0.6f, 6);
		if (value <= 1 & value > 0.1f ) {result = true; assertEquals(true,result); result = false;}
		//System.out.println(value);
		
		value = nn.fireNode(0.2f, 3);
		if (value <= 1 & value > 0 ) {result = true; assertEquals(true,result); result = false;}
		
		value = nn.fireNode(0.2810223f, 3.0f);
		//System.out.print("Testing Fire Node: ");
		//System.out.println(value);b
*/
	}

}
