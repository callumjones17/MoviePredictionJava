package com.jones.ml.movie.genAlg;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jones.ml.movie.Agent;
import com.jones.ml.movie.sorting.bubbleSorting;

import junit.framework.TestCase;

class GeneticAlgorithmUnitTest extends TestCase{

	private GeneticAlgorithm ga = new GeneticAlgorithm();
	private bubbleSorting bs = new bubbleSorting();
	
	@Override
	@Before
	public void setUp() {
		
	}
	
	@Test
	void testNumToBreed() {
		
		System.out.println("Testing num to breed");
		
		int agents = 40;
		int survivors = 15;
		int expectedBreed = 6;
		
		assertEquals(expectedBreed,ga.numToBreed(agents, survivors));
		
		agents = 40;
		survivors = 18;
		expectedBreed = 5;
		
		assertEquals(expectedBreed,ga.numToBreed(agents, survivors));
		
		//fail("Not yet implemented"); // TODO
	}
	
	@Test
	void testSelectionProcess() {
		
		System.out.println("Test Selection Process of Agents");
		
		List<Agent> agents = new ArrayList<>();
		List<Agent> expectedAgents = new ArrayList<>();
		List<Integer> agentScores = new ArrayList<>();
		int numAgents = 4;
		int numWeightings = 1;
		
		for (int i = 0; i < numAgents; i++) {
			agents.add(new Agent(numWeightings));
		}
		System.out.println("Unsorted");
		System.out.println(agents);
		
		agentScores.add(2);
		agentScores.add(3);
		agentScores.add(1);
		agentScores.add(4);
		expectedAgents.add(agents.get(1));
		expectedAgents.add(agents.get(3));
		System.out.println("Expected");
		System.out.println(expectedAgents);
		
		agents = bs.sortAgentsByLowestScore(agents, agentScores);
		System.out.println("Sorted");
		System.out.println(agents);
		
		agents = ga.selectionProcess(agents, agentScores, 0.4f, 0.6f);
		
		assertEquals(expectedAgents,agents);
	}

}
