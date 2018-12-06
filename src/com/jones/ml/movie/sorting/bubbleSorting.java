// This will be hard because in java I can't directly access the data.

package com.jones.ml.movie.sorting;

import java.util.*;

import com.jones.ml.movie.Agent;

public class bubbleSorting {
	
	//Lowest to Highest
	public List<Agent> sortAgentsByLowestScore(List<Agent> agents, List<Integer> agentScores){
		
		int listSize = agents.size();
		int maxIterations = listSize * listSize;
		boolean changeOccured = true;
		
		for (int i = 0; i < maxIterations; i++) {
			changeOccured = false;
			for (int j = 0; j < (listSize-1); j++) {
				if (agentScores.get(j) > agentScores.get(j+1)) {
					agents = changeAgentPos(agents,j,j+1);
					agentScores = changeIntPos(agentScores,j,j+1);
					changeOccured = true;
				}
			}
			if (!changeOccured) {
				//System.out.println("this func works");
				break;
			}
		}
		
		return agents;
	}
	
	
	
	
	// Highest to lowest eg. [5,5,3,2,1,1,]
	public List<Integer> sortList(List<Integer> listToSort){
		
		int listSize = listToSort.size();
		int maxIterations = listSize * listSize;
		boolean changeOccured = true;
		
		for (int i = 0; i < maxIterations; i++) {
			changeOccured = false;
			for (int j = 0; j < (listSize-1); j++) {
				if (listToSort.get(j) < listToSort.get(j+1)) {
					listToSort = changeIntPos(listToSort,j,j+1);
					changeOccured = true;
				}
			}
			if (!changeOccured) {
				//System.out.println("this func works");
				break;
			}
		}
		
		return listToSort;
	}
	
	public List<Integer> changeIntPos(List<Integer> list, int fromIndex, int toIndex){
		
		Integer temp = list.get(fromIndex);
		list.set(fromIndex, list.get(toIndex));
		list.set(toIndex, temp);
		
		return list;
	}
	
	
	public List<Agent> changeAgentPos(List<Agent> list, int fromIndex, int toIndex){
		
		Agent temp = list.get(fromIndex);
		list.set(fromIndex, list.get(toIndex));
		list.set(toIndex, temp);
		
		return list;
	}
	
}
