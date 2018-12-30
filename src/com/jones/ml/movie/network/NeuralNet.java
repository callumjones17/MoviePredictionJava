/**
 * 
 */
package com.jones.ml.movie.network;

import java.util.*;
import com.jones.ml.movie.*;
import com.jones.ml.error.*;

/**
 * @author Callum Jones, 2018
 *
 */
public class NeuralNet {
	
	ErrorHandler erH = new ErrorHandler();

	public float runThroughNetworkOnce(List<Agent> agents, List<Float>data, NetworkMap networkMap) {
		
		List<List<Float>> layers = new ArrayList<>();
		
		// First Layer Handling
		if (networkMap.getIsFirstLayer1to1()) {
			
			// Check there is enough data to pass in the first layer:
			if (data.size() != networkMap.getNodesByLayer(0)){
				erH.passError(ErrorCodes.INSUF_DATA_LAYER_1);
			}
			
			layers.add(data);
			
		}else {
			
			List<Integer> firstLayer = new ArrayList<>();
			int numTotalNodesFirstLayer = 0;
			
			firstLayer = networkMap.getFirstLayerMap();
			for (Integer node: firstLayer) {
				numTotalNodesFirstLayer += node;
			}
			
			// Check there is enough data to pass in the first layer:
			if (data.size() != numTotalNodesFirstLayer){
				erH.passError(ErrorCodes.INSUF_DATA_LAYER_1);
			}
			
		}
		
		return 0.0f;
	}
	
	
	public float fireNode(float sum, float numInputs) {
		float output = (float)((1/2)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		return output;
	}
	
	
	
}
