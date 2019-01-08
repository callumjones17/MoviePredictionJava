/**
 * 
 */
package com.jones.ml.movie;

import java.sql.Time;
import java.time.LocalTime;
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
		long millis = System.currentTimeMillis() % 1000;
		random.setSeed(millis);
		
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
	
	public Agent(int numWeightings, boolean neg) {
		this.numWeightings = numWeightings;
		for (int i = 0; i < numWeightings; i++) {
			float rand = random.nextFloat();
			if (neg) {
				rand = (rand * 2.0f) - 1.0f;
				this.weightings.add(rand);
			}else {
				this.weightings.add(rand);
			}
		}
	}
	
	public Agent(int numWeightings, boolean limit, float max, float min) {
		this.numWeightings = numWeightings;
		if (!limit) {
			for (int i = 0; i < numWeightings; i++) {
				this.weightings.add(random.nextFloat());
			}
		}else {
			for (int i = 0; i < numWeightings; i++) {
				this.weightings.add(((max-min)*random.nextFloat())+min);
			}
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
