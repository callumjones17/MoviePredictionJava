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

	public List<List<Float>> runThroughNetworkOnce(Agent agent, List<Float>data, NetworkMap networkMap) {
		
		List<List<Float>> layers = new ArrayList<>();
		int numCurrentLayerNodes = -1;
		int numPrevLayerNodes = -1;
		int dataIndex = 0;
		
		// First Layer Handling
		if (networkMap.getIsFirstLayer1to1()) {
			
			List<Float> layerOne = new ArrayList<>();
			
			// Check there is enough data to pass in the first layer:
			if (data.size() != networkMap.getNodesByLayer(0)){
				erH.passError(ErrorCodes.INSUF_DATA_LAYER_1);
			}
			
			for (int i = 0; i<data.size(); i++) {
				layerOne.add((float)(data.get(i) * agent.getWeightings().get(i)));
				dataIndex = i;
			}
			
			layers.add(layerOne);
			
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
		}
		
		
		if (!networkMap.getIsCustomRouting()) {

			// All Other Layers:
			for (int cLayer = 1; cLayer < networkMap.getMap().size(); cLayer++) {

				List<Float> layerX = new ArrayList<>();
				float sum = -1;
				numPrevLayerNodes = layers.get(cLayer-1).size();
				numCurrentLayerNodes = networkMap.getNodesByLayer(cLayer);

				for (int cNode = 0; cNode < numCurrentLayerNodes; cNode++) {

					sum = 0;

					for (int k = 0; k < numPrevLayerNodes; k++) {
						sum += (float)(layers.get(cLayer-1).get(k) * agent.getWeightings().get(dataIndex));
						dataIndex++;
					}

					layerX.add(fireNode(sum, numPrevLayerNodes));

				}
				
				layers.add(layerX);

			}

			if (dataIndex+1 != agent.getWeightings().size()) { System.out.println(dataIndex); erH.passError(ErrorCodes.DATA_INDEX_NOT_EQUAL_TO_AGENT);}

			//return layers.get(layers.size()-1);
			return layers;
		}else {
			
			if (networkMap.getMap().size() != networkMap.getRoutingMap().size()-1) { erH.passError(ErrorCodes.CUSTOM_ROUTES_NOT_EQUAL_TO_NUM_LAYERS);}
			
			// Custom Routing of Nodes - Fully Custom
			
			for (int cLayer = 1; cLayer < networkMap.getMap().size(); cLayer++) {

				List<Float> layerX = new ArrayList<>();
				float sum = -1;
				numPrevLayerNodes = layers.get(cLayer-1).size();
				numCurrentLayerNodes = networkMap.getNodesByLayer(cLayer);

				
				
				for (int cNode = 0; cNode < numCurrentLayerNodes; cNode++) {
					
					List<Integer> currentNodeRouteMap = new ArrayList<>();
					
					currentNodeRouteMap = networkMap.getRoutingMap().get(cLayer).get(cNode);
					
					for (int node: currentNodeRouteMap) {
						
						sum += (float)(layers.get(cLayer-1).get(node) * agent.getWeightings().get(dataIndex));
						dataIndex++;
						
					}
					
					layerX.add(fireNode(sum,currentNodeRouteMap.size()));
				}
				
				layers.add(layerX);		

			}			
		}
		
		if (dataIndex+1 != agent.getWeightings().size()) { erH.passError(ErrorCodes.DATA_INDEX_NOT_EQUAL_TO_AGENT);}
		//return layers.get(layers.size()-1);
		return layers;
	}
	
	
	public float fireNode(float sum, float numInputs) {
		
		System.out.println("Fire Node Starting");
		
		System.out.println(sum);
		System.out.println(numInputs);
		
		// Old
		//float output = (float)((1/2)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		
		//Fixed 
		//float output = (float)((1.0f/2.0f)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		
		// Adjusted
		float output = (float)((1.0f/2.0f)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		
		
		/*float output = (float)(numInputs/4);
		System.out.println(output);
		output = 1/output;
		System.out.println(output);
		float half = (float)(numInputs/2);
		System.out.println(half);
		half = sum - half;
		System.out.println(half);
		output = output * half;
		System.out.println(output);
		output = (float)Math.tanh((double)output);
		System.out.println(output);
		output = output + 1;
		System.out.println(output);
		half = (float)(1.0f/2.0f);
		System.out.println(half);
		output = half * output;
		System.out.println(output);
		System.out.println("Fire Node Done");*/
		
		return output;
	}
	
	
	
}
