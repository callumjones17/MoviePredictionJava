/**
 * 
 */
package com.jones.ml.movie.genAlg;

import java.util.*;
import com.jones.ml.movie.*;
import com.jones.ml.movie.sorting.bubbleSorting;

/**
 * @author Callum Jones
 *
 */
public class GeneticAlgorithm {
	
	private Random random = new Random();
	private bubbleSorting bs = new bubbleSorting();

	public int numToBreed(int numAgents, int numSurv) {
		
		int num = 0;
		if ((numAgents-numSurv)%2 == 0) {
	        num = (numAgents-numSurv)/2;
		}else {
	        num = (numAgents-numSurv-1)/2;
		}
		if (num%2 == 0) {
	        num = num/2;
		}else {
	        num = (num-1)/2;
		}
		
		return num;
	}
	
	public List<Agent> selectionProcess(List<Agent> agents, List<Integer> agentScores, float minCutoff, float maxCutoff){
		
		int numAgents = agents.size();
		Float normalisedScore = 0.00f;
		List<Float> normalisedScores = new ArrayList<>();
		List<Float> accumulatedScores = new ArrayList<>();
		float cutOffPoint = -1.00f;
		int actualCutPoint = -1;
		
		for (Integer score: agentScores) {
			normalisedScore += score;
		}
		for (Float i = 0.0f; i < numAgents; i++) {
			normalisedScores.add(i/normalisedScore);
		}
		
		//Good Practice to ensure the agents are sorted here. From Lowest to smallest!!
		//Need to fix bubble sort to go in other direction
		//Normally I would sort first, but I think sorting here is a better option!
		agents = bs.sortAgentsByLowestScore(agents, agentScores);
		
		accumulatedScores = normalisedScores;
		for (int i = 1; i < numAgents; i++) {
			accumulatedScores.set(i, accumulatedScores.get(i-1)+normalisedScores.get(i));
			//System.out.println(accumulatedScores);
		}
		
		cutOffPoint = (random.nextFloat()*(maxCutoff-minCutoff)) + minCutoff;
		
		for (int i = 0; i < accumulatedScores.size(); i++) {
			if (accumulatedScores.get(i) > cutOffPoint) {
				actualCutPoint = i;
				break;
			}
		}
		
		agents = agents.subList(0, actualCutPoint);
		
		return agents;
	}
	
}
