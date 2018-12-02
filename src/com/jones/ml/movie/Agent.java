/**
 * 
 */
package com.jones.ml.movie;

/**
 * @author Callum Jones, 2018
 *
 */
public class Agent {

	private int numWeightings = 0;
	
	
	public Agent(int numWeightings) {
		this.numWeightings = numWeightings;
	}

	public int getNumWeightings() {
		return numWeightings;
	}

	public void setNumWeightings(int numWeightings) {
		this.numWeightings = numWeightings;
	}
	
}
