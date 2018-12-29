/**
 * 
 */
package com.jones.ml.movie.genAlg;

import java.util.*;
import com.jones.ml.movie.*;
import com.jones.ml.movie.sorting.bubbleSorting;
import com.jones.ml.error.*;

/**
 * @author Callum Jones
 *
 */
public class GeneticAlgorithm {
	
	private Random random = new Random();
	private bubbleSorting bs = new bubbleSorting();
	private ErrorHandler erH = new ErrorHandler();

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
		List<Agent> output = new ArrayList<>();
		float cutOffPoint = -1.00f;
		int numKeepAlive = 0;
		
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
			if (!(accumulatedScores.get(i) > cutOffPoint)) {
				numKeepAlive += 1;
			}else {
				break;
			}
		}
		System.out.println(numKeepAlive);
		System.out.println(agents.size());
		// THERE is AN ISSUE HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! (Why 4?)
		//output = agents.subList(agents.size()+1-numKeepAlive, 4);
		output = agents.subList(agents.size()+1-numKeepAlive, agents.size());
		System.out.println(output);
		
		return output;
	}
	
	
	public List<Agent> singleCrossover(List<Agent> remAgents, int numToCreate, int slicePoint, boolean JUnitTest){
		
		List<Float> newAgA = new ArrayList<>();
		List<Float> newAgB = new ArrayList<>();
		List<Float> newAgC = new ArrayList<>();
		List<Float> newAgD = new ArrayList<>();
		int numWeightings = remAgents.get(0).getWeightings().size();
		int numRemAgents = remAgents.size();
		
		if (JUnitTest) {
			for (Agent agent : remAgents) {
				if (agent.getNumWeightings() != numWeightings) {
					erH.passError(ErrorCodes.AGENTS_MUST_BE_SAME_LEN);
				}
			}
		}
		
		if (numRemAgents < 2*numToCreate && !JUnitTest) {
			erH.passError(ErrorCodes.NOT_ENOUGH_REM_AGENTS);
		}
		
		if (numToCreate%2 != 0) {
			numToCreate -= 1;
		}
		
		for (int agIndex = 0; agIndex < numToCreate/2; agIndex+=1) {
			int i = 0;
			int temp = 0;
			newAgA = new ArrayList<>();
			newAgB = new ArrayList<>();
			newAgC = new ArrayList<>();
			newAgD = new ArrayList<>();

			
			// Please note, this is only half the number of combinations. There are 4 more (they are just opposite)
			//Agent 1 - Start of 1 	- 	End of 2  		(0-3,14-19) (A)
			//Agent 2 - Start of 1 	- 	Start of 2 		(0-3,10-17) (B)
			//Agent 3 - End of 1 	- 	Start of 2		(4-9,10-17) (C)
			//Agent 4 - End of 1    - 	End of 2		(4-9,14-19) (D)
			
			
			for (i = 0; i < slicePoint; i++) {
				newAgA.add(remAgents.get(agIndex*2).getWeightings().get(i));
				newAgB.add(remAgents.get(agIndex*2).getWeightings().get(i));
				temp = i;
			}
			for (i = temp+1; i<numWeightings; i++) {
				newAgC.add(remAgents.get(agIndex*2).getWeightings().get(i));
				newAgD.add(remAgents.get(agIndex*2).getWeightings().get(i));
			}
			
			for (i = 0; i < (numWeightings-slicePoint); i++) {
				newAgB.add(remAgents.get((agIndex*2)+1).getWeightings().get(i));
				newAgC.add(remAgents.get((agIndex*2)+1).getWeightings().get(i));
			}
			for (i = temp+1; i<(numWeightings); i++) {
				newAgA.add(remAgents.get((agIndex*2)+1).getWeightings().get(i));
				newAgD.add(remAgents.get((agIndex*2)+1).getWeightings().get(i));
			}
			
			// C and D are too long - should fix this!!
			newAgC = newAgC.subList(0, numWeightings);
			newAgD = newAgD.subList(0, numWeightings);			
			
			remAgents.add(new Agent(newAgA));
			remAgents.add(new Agent(newAgB));
			remAgents.add(new Agent(newAgC));
			remAgents.add(new Agent(newAgD));
			
		}
		
		return remAgents;
	}
	
	
	
	
	public List<Agent> mutateAgents(List<Agent> agents, int numRunThroughs){
		// Each Gene, from each Chromosone has a 1/L chance of mutation.
		// Where L is the length of the Chromosone
		
		List<Agent> mutatedAgents = new ArrayList<Agent>();
		List<Float> listNums = new ArrayList<>();
		int numChrom = agents.get(0).getNumWeightings();
		int index = -1;
		
		for (Agent agent: agents) {
			listNums = agent.getWeightings();
			index = -1;
			for (Float chromosone: listNums) {
				index++;
				if (random.nextFloat()<(1/numChrom)) {
					listNums.set(index, 1-chromosone);
				}
			}
			mutatedAgents.add(new Agent(listNums));
		}
		
		return agents;
	}
	
	
	
	
}