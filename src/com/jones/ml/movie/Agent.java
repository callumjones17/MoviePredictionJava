/**
 * 
 */
package com.jones.ml.movie;

import com.jones.ml.movie.network.NetworkMap;

/**
 * @author Callum Jones, 2018
 *
 */
public class Agent {

	private int numWeightings = 0;
	
	public Agent(int numWeightings, NetworkMap map) {
		this.numWeightings = numWeightings;
	}

	public int getNumWeightings() {
		return numWeightings;
	}

	public void setNumWeightings(int numWeightings) {
		this.numWeightings = numWeightings;
	}
	
}
