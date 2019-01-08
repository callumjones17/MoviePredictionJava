/**
 * 
 */
package com.jones.ml.movie;

import com.jones.ml.movie.network.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Callum Jones, 2018
 * Class Not Being Used !!!!!
 *
 */
public class AgentFuncs {
	
	private NetworkMap map;
	
	public AgentFuncs(NetworkMap map) {
		this.map = map;
	}
	
	//Deprecated!!!! PLEASE DO NOT USE THIS
	public Agent createAgent() {
		// First determine how many layers are in the network (number of elements in the array)
		// Next next, add first element to the sum of the next element by the element after
		// Return total number of weights.
		int layerCount = 0;
		int weightSum = 0;
		for (Integer layer : map.getMap()) {
			layerCount++;
		}
		if (map.getIsFirstLayer1to1()) {
			weightSum = map.getNodesByLayer(0);
			for (int i = 1; i < (layerCount-1); i++) {
				weightSum += map.getNodesByLayer(i) * map.getNodesByLayer(i+1);
			}
		}else {
			weightSum = 0;
			for (int i = 0; i < (layerCount-1); i++) {
				weightSum += map.getNodesByLayer(i) * map.getNodesByLayer(i+1);
			}
		}
		//System.out.println(weightSum);
		return new Agent(weightSum, map);
	}
	
	
	public List<Agent> createManyAgents(int number) {
		List<Agent> agentList = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			agentList.add(createAgent());
		}
		return agentList;
	}
	
}
