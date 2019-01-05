package com.jones.ml.movie.network;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class NetworkMapUnitTest extends TestCase {

	
	NeuralNet nn = new NeuralNet();
	List<Integer> map = new ArrayList<>();
	List<Integer> firstLayer = new ArrayList<>();
	List<List<List<Integer>>> routeMap = new ArrayList<>();
	
	NetworkMap nm;
	int numLayer1Nodes = 3;
	int numLayer2Nodes = 1;
	int numWeightings = -1;
	
	@Before
	@Override
	public void setUp() {
		
	}
	
	
	@Test
	void testNetworkMap() {
		
		assertEquals(1,1);
	}

	@Test
	void testGetNodesByLayer() {
		
		assertEquals(1,1);
	}
	
	@Test
	void testCalculateWeightings() {
		
		map.add(numLayer1Nodes);
		map.add(numLayer2Nodes);
		nm = new NetworkMap(map);
		numWeightings = nm.calculateNumberOfWeightingsRequired();
		System.out.println(numWeightings);
		assertEquals(6, numWeightings);
		
		map = new ArrayList<>();
		map.add(3);
		map.add(1);
		firstLayer.add(1);
		firstLayer.add(3);
		firstLayer.add(1);
		nm = new NetworkMap(map, false, firstLayer);
		//System.out.println(nm.getFirstLayerMap());
		numWeightings = nm.calculateNumberOfWeightingsRequired();
		System.out.println(numWeightings);
		assertEquals(8, numWeightings);
		
		
		
		map = new ArrayList<>();
		map.add(3);
		map.add(2);
		map.add(1);
		firstLayer = null;
		
		List<Integer> nodeIndex = new ArrayList<>();
		List<List<Integer>> layerIndex = new ArrayList<>();
		nodeIndex.add(0);
		nodeIndex.add(1);
		layerIndex.add(nodeIndex);
		nodeIndex = new ArrayList<>();
		nodeIndex.add(2);
		layerIndex.add(nodeIndex);
		routeMap.add(layerIndex);
		
		layerIndex = new ArrayList<>();
		nodeIndex = new ArrayList<>();
		nodeIndex.add(0);
		nodeIndex.add(1);
		layerIndex.add(nodeIndex);
		routeMap.add(layerIndex);
		
		nm = new NetworkMap(map,true, firstLayer, true, routeMap);
		
		numWeightings = nm.calculateNumberOfWeightingsRequired();
		System.out.println(numWeightings);
		assertEquals(8, numWeightings);
		
		
		
		
		map = new ArrayList<>();
		map.add(3);
		map.add(2);
		map.add(1);
		firstLayer = new ArrayList<>();
		firstLayer.add(1);
		firstLayer.add(3);
		firstLayer.add(1);
		
		nodeIndex = new ArrayList<>();
		layerIndex = new ArrayList<>();
		nodeIndex.add(0);
		nodeIndex.add(1);
		layerIndex.add(nodeIndex);
		nodeIndex = new ArrayList<>();
		nodeIndex.add(2);
		layerIndex.add(nodeIndex);
		routeMap.add(layerIndex);
		
		layerIndex = new ArrayList<>();
		nodeIndex = new ArrayList<>();
		nodeIndex.add(0);
		nodeIndex.add(1);
		layerIndex.add(nodeIndex);
		routeMap.add(layerIndex);
		
		nm = new NetworkMap(map,false, firstLayer, true, routeMap);
		
		numWeightings = nm.calculateNumberOfWeightingsRequired();
		System.out.println(numWeightings);
		assertEquals(10, numWeightings);
	}

}
