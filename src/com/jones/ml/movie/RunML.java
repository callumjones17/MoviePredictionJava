/**
 * 
 */
package com.jones.ml.movie;

import java.util.*;

import com.jones.ml.movie.data.*;
import com.jones.ml.movie.network.*;

/**
 * @author Callum Jones
 *
 */
public class RunML {
	
	// Custom Params
	final int numAgents = 20;
	final int numGenerations = 500;
	final String dataFile = "D:\\zTestFiles\\movies.csv";
	
	// Classes
	private NetworkMap map;
	private AgentFuncs agentF;
	private ExcelDataHandler excelH = new ExcelDataHandler(dataFile);
	
	// Private Params
	private List<Agent> agents = new ArrayList<>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RunML run = new RunML();
		run.Run();
	}
	
	private void Run() {
		ArrayList<Integer> mapInts = new ArrayList<>();
		mapInts.add(5);
		mapInts.add(3);
		mapInts.add(1);
		map = new NetworkMap(mapInts);
		
		agentF = new AgentFuncs(map);
		agents = agentF.createManyAgents(numAgents);
	}

}
