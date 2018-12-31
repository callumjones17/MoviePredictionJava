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

	public float runThroughNetworkOnce(Agent agent, List<Float>data, NetworkMap networkMap) {
		
		List<List<Float>> layers = new ArrayList<>();
		int numLayer1Nodes = -1;
		int numPrevLayerNodes = -1;
		
		// First Layer Handling
		if (networkMap.getIsFirstLayer1to1()) {
			
			List<Float> layerOne = new ArrayList<>();
			
			// Check there is enough data to pass in the first layer:
			if (data.size() != networkMap.getNodesByLayer(0)){
				erH.passError(ErrorCodes.INSUF_DATA_LAYER_1);
			}
			
			for (int i = 0; i<data.size(); i++) {
				layerOne.add((float)(data.get(i) * agent.getWeightings().get(i)));
			}
			
			layers.add(layerOne);
			numLayer1Nodes = layerOne.size();
			
		}else {
			
			List<Integer> firstLayer = new ArrayList<>();
			List<Float> layerOne = new ArrayList<>();
			int numTotalNodesFirstLayer = 0;
			
			firstLayer = networkMap.getFirstLayerMap();
			for (Integer node: firstLayer) {
				numTotalNodesFirstLayer += node;
			}
			
			// Check there is enough data to pass in the first layer:
			if (data.size() != numTotalNodesFirstLayer){
				erH.passError(ErrorCodes.INSUF_DATA_LAYER_1);
			}
			
			int dataIndex = 0;
			float sum = -1;
			for (int node : firstLayer) {
				sum = 0;
				for (int i = dataIndex; i<dataIndex+node; i++) {
					sum += (float)(agent.getWeightings().get(i) * data.get(dataIndex));
				}
				dataIndex += node;
				layerOne.add(fireNode(sum,node));
			}
			layers.add(layerOne);
			numLayer1Nodes = layerOne.size();
		}
		
		
		// All Other Layers:
		for (int i = 1; i < networkMap.getMap().size(); i++) {
			
		}
		
		return 0.0f;
	}
	
	
	public float fireNode(float sum, float numInputs) {
		float output = (float)((1/2)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		return output;
	}
	
	
	
}