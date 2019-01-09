/**
 * 
 */
package com.jones.ml.movie.data;

import com.jones.ml.error.ErrorCodes;
import com.jones.ml.error.ErrorHandler;

/**
 * @author Callum Jones, 2018
 *
 */
public class DataMapper {
	
	private boolean neg = false;
	private ErrorHandler erH = new ErrorHandler();
	
	public DataMapper(boolean neg){
		this.neg = neg;
	}

	public float mapInt(int data, int max) {
		
		// Error Handling
		if (data < 0) {erH.passError(ErrorCodes.DATA_LESS_THAN_ZERO,"Data: " + data);}
		else if (data > max) { erH.passError(ErrorCodes.DATA_GREATER_THAN_MAX, "Data: " + data + " Max: " + max);}
		
		float scaled = -2;
		if (neg) {
			float perc = (float)data/(float)max;
			scaled = (perc*2.0f)-1.0f;
		}else {
			scaled = (float)data/(float)max;
		}
		
		return scaled;
	}
	
	
	public float mapFloat(float data, float max) {
		
		// Error Handling
		if (data < 0) {erH.passError(ErrorCodes.DATA_LESS_THAN_ZERO,"Data: " + data);}
		else if (data > max) { erH.passError(ErrorCodes.DATA_GREATER_THAN_MAX, "Data: " + data + " Max: " + max);}
		
		float scaled = -2;
		if (neg) {
			float perc = data/max;
			scaled = (perc*2.0f)-1.0f;
		}else {
			scaled = data/max;
		}
		
		return scaled;
	}
	
}
