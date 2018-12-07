/**
 * 
 */
package com.jones.ml.movie;

import java.util.*;

import com.jones.ml.movie.network.NetworkMap;

/**
 * @author Callum Jones, 2018
 *
 */
public class Agent {

	private int numWeightings = 0;
	private List<Float> weightings = new ArrayList<>();
	private Random random = new Random();
	
	public Agent(int numWeightings, NetworkMap map) {
		this.numWeightings = numWeightings;
		for (int i = 0; i < numWeightings; i++) {
			this.weightings.add(random.nextFloat());
		}
	}
	
	public Agent(int numWeightings) {
		this.numWeightings = numWeightings;
		for (int i = 0; i < numWeightings; i++) {
			this.weightings.add(random.nextFloat());
		}
	}
	
	// Should only really be used for debugging
	public Agent(List<Float> weights) {
		this.numWeightings = weights.size();
		this.weightings = weights;
	}

	public int getNumWeightings() {
		return this.numWeightings;
	}

	public void setNumWeightings(int numWeightings) {
		this.numWeightings = numWeightings;
	}
	
	public List<Float> getWeightings() {
		return this.weightings;
	}
	
}
