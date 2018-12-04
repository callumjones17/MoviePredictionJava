package com.jones.ml.movie;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.jones.ml.movie.network.NetworkMap;

import junit.framework.TestCase;

class AgentFuncsUnitTest extends TestCase{
	
	private AgentFuncs testClass;
	private List<Integer> mapI = new ArrayList<>(); 
	private List<Agent> agentList = new ArrayList<>();
	private NetworkMap map;
	
	@Override
	@Before
	public void setUp() {
		
	}

	@Test
	void testCreateAgent() {
		mapI.clear();
		mapI.add(5);
		mapI.add(3);
		mapI.add(1);
		map = new NetworkMap(mapI);
		testClass = new AgentFuncs(map);
		if (testClass.createAgent().getNumWeightings() != 8) {
			fail("Not yet implemented"); // TODO
		}
		
		mapI.clear();
		mapI.add(5);
		mapI.add(5);
		mapI.add(3);
		mapI.add(2);
		mapI.add(1);
		map = new NetworkMap(mapI);
		testClass = new AgentFuncs(map);
		
		if (testClass.createAgent().getNumWeightings() != 28) {
			fail("Incorrect number of weightings!"); // TODO
		}
	}
	
	@Test
	void testCreateManyAgents() {
		mapI.add(5);
		mapI.add(3);
		mapI.add(1);
		map = new NetworkMap(mapI);
		testClass = new AgentFuncs(map);
		int numAgents = 5;
		int agentCount = 0;
		
		agentList.clear();
		agentList = testClass.createManyAgents(numAgents);
		for (Agent agent: agentList) {
			agentCount++;
		}
		assertEquals(numAgents, agentCount);
		
		
		numAgents = 10;
		agentCount = 0;
		
		agentList.clear();
		agentList = testClass.createManyAgents(numAgents);
		for (Agent agent: agentList) {
			agentCount++;
		}
		assertEquals(numAgents, agentCount);
	}
	
	
	@Test
	void testCreateManyAgentsForceFail() {
		mapI.add(5);
		mapI.add(3);
		mapI.add(1);
		map = new NetworkMap(mapI);
		testClass = new AgentFuncs(map);
		int numAgents = 5;
		int agentCount = 0;
		
		agentList.clear();
		agentList = testClass.createManyAgents(numAgents);
		for (Agent agent: agentList) {
			agentCount++;
		}
		assertNotEquals(numAgents+1, agentCount);
		
		
		numAgents = 10;
		agentCount = 0;
		
		agentList.clear();
		agentList = testClass.createManyAgents(numAgents);
		for (Agent agent: agentList) {
			agentCount++;
		}
		assertNotEquals(numAgents-1, agentCount);
	}

}
