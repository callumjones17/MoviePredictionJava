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
	private boolean scaled = false;
	private boolean passData = true;
	private float fireFactor = -1.0f;

	public NeuralNet() {
		this.scaled = false;
		this.passData = true;
	}
	public NeuralNet(boolean scaled, boolean passData, float fireFactor) {
		this.scaled = scaled;
		this.passData = passData;
		this.fireFactor = fireFactor;
	}
	public NeuralNet(boolean passData, float fireFactor) {
		this.scaled = false;
		this.passData = passData;
		this.fireFactor = fireFactor;
	}

	public List<List<Float>> runThroughNetworkOnce(Agent agent, List<Float>data, NetworkMap networkMap) {
		
		List<List<Float>> layers = new ArrayList<>();
		int numCurrentLayerNodes = -1;
		int numPrevLayerNodes = -1;
		int dataIndex = 0;
		
		// First Layer Handling
		if (networkMap.getIsFirstLayer1to1()) {
			
			List<Float> layerOne = new ArrayList<>();
			float nodeResult = -1;
			
			// Check there is enough data to pass in the first layer:
			if (data.size() != networkMap.getNodesByLayer(0)){
				erH.passError(ErrorCodes.INSUF_DATA_LAYER_1);
			}
			
			for (int i = 0; i<data.size(); i++) {
				
				nodeResult = (float)(data.get(i) * agent.getWeightings().get(i));
				
				if (passData) {
					layerOne.add(nodeResult);
				}else {
					if (nodeResult >= fireFactor) {
						layerOne.add(1.0f);
					}
					else {
						layerOne.add(0.0f);
					}
				}
					
				dataIndex = i;
			}

			layers.add(layerOne);
			
		}else {
			
			List<Integer> firstLayer = new ArrayList<>();
			List<Float> layerOne = new ArrayList<>();
			float nodeResult = -1;
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
				nodeResult = fireNode(sum,node);
				
				if (passData) {
					layerOne.add(nodeResult);
				}else {
					if (nodeResult >= fireFactor) {
						layerOne.add(1.0f);
					}
					else {
						layerOne.add(0.0f);
					}
				}
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
				float nodeResult = -1;

				for (int cNode = 0; cNode < numCurrentLayerNodes; cNode++) {

					sum = 0;

					for (int k = 0; k < numPrevLayerNodes; k++) {
						sum += (float)(layers.get(cLayer-1).get(k) * agent.getWeightings().get(dataIndex));
						dataIndex++;
					}
					
					nodeResult = fireNode(sum, numPrevLayerNodes);
					
					if (passData) {
						layerX.add(nodeResult);
					}else {
						if (nodeResult >= fireFactor) {
							layerX.add(1.0f);
						}
						else {
							layerX.add(0.0f);
						}
					}
					

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
				float nodeResult = -1;
				numPrevLayerNodes = layers.get(cLayer-1).size();
				numCurrentLayerNodes = networkMap.getNodesByLayer(cLayer);

				
				
				for (int cNode = 0; cNode < numCurrentLayerNodes; cNode++) {
					
					List<Integer> currentNodeRouteMap = new ArrayList<>();
					
					currentNodeRouteMap = networkMap.getRoutingMap().get(cLayer).get(cNode);
					
					for (int node: currentNodeRouteMap) {
						
						sum += (float)(layers.get(cLayer-1).get(node) * agent.getWeightings().get(dataIndex));
						dataIndex++;
						
					}
					
					nodeResult = fireNode(sum,currentNodeRouteMap.size());
					
					if (passData) {
						layerX.add(nodeResult);
					}else {
						if (nodeResult >= fireFactor) {
							layerX.add(1.0f);
						}
						else {
							layerX.add(0.0f);
						}
					}
				}
				
				layers.add(layerX);		

			}			
		}
		
		if (dataIndex+1 != agent.getWeightings().size()) { erH.passError(ErrorCodes.DATA_INDEX_NOT_EQUAL_TO_AGENT);}
		//return layers.get(layers.size()-1);
		return layers;
	}
		
	
	public float fireNode(float sum, float numInputs) {
		
		//System.out.println("Fire Node Starting");
		
		//System.out.println(sum);
		//System.out.println(numInputs);
		
		// Old
		//float output = (float)((1/2)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		
		//Fixed 
		//float output = (float)((1.0f/2.0f)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		
		// Adjusted
		//float output = (float)((1.0f/2.0f)*(Math.tanh(((float)(1/(numInputs/4)))*(sum-((float)(numInputs/2))))+1));
		
		float output = sigmoid(sum);
		
		
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
		
		if (scaled) {
			output = scaleResult(output);
		}
		
		return output;
	}
	
	
	public float sigmoid(float input) {
	    return (float)(1/( 1 + Math.pow(Math.E,(-1*(double)(input)))));
	}
	
	
	
	public float scaleResult(float input) {
		
		float output = -1;
		
		//output = 0.4f * (float)Math.exp((double)(-3*input));
		output = 1-input;
		
		return output;
	}
	
	public boolean isScaled() {
		return scaled;
	}

	public void setScalde(boolean scaled) {
		this.scaled = scaled;
	}
	
	
	
	
}
