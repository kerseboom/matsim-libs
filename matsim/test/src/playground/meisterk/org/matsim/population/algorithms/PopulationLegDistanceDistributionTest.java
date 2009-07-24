/* *********************************************************************** *
 * project: org.matsim.*
 * PopulationLegDistanceDistributionTest.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package playground.meisterk.org.matsim.population.algorithms;

import java.util.HashMap;

import org.matsim.api.basic.v01.TransportMode;
import org.matsim.core.api.experimental.population.Leg;
import org.matsim.core.basic.v01.IdImpl;
import org.matsim.core.network.LinkImpl;
import org.matsim.core.network.NetworkLayer;
import org.matsim.core.network.NodeImpl;
import org.matsim.core.population.ActivityImpl;
import org.matsim.core.population.PersonImpl;
import org.matsim.core.population.PlanImpl;
import org.matsim.core.population.PopulationImpl;
import org.matsim.core.population.routes.NodeNetworkRoute;
import org.matsim.core.utils.geometry.CoordImpl;
import org.matsim.testcases.MatsimTestCase;

public class PopulationLegDistanceDistributionTest extends MatsimTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetDistanceClassIndex() {
		
		HashMap<Double, Integer> candidates = new HashMap<Double, Integer>();
		candidates.put(333.3, 3);
		candidates.put(0.0, 0);
		candidates.put(120345.1, 11);
		
		for (Double distance : candidates.keySet()) {
			assertEquals(candidates.get(distance).intValue(), PopulationLegDistanceDistribution.getDistanceClassIndex(distance));
		}
		
	}
	
	public void testGenerationDistribution() {
		
		NetworkLayer testNetwork = new NetworkLayer();
		NodeImpl node1 = testNetwork.createNode(new IdImpl("1"), new CoordImpl(0.0, 0.0));
		NodeImpl node2 = testNetwork.createNode(new IdImpl("2"), new CoordImpl(500.0, 500.0));
		NodeImpl node3 = testNetwork.createNode(new IdImpl("3"), new CoordImpl(1000.0, 1000.0));
		LinkImpl startLink = testNetwork.createLink(new IdImpl("101"), node1, node2, 500.0, 27.7778, 2000.0, 1.0);
		LinkImpl endLink = testNetwork.createLink(new IdImpl("102"), node2, node3, 1000.0, 27.7778, 2000.0, 1.0);
		
		PersonImpl testPerson = new PersonImpl(new IdImpl("1000"));
		PlanImpl testPlan = testPerson.createPlan(true);
		
		ActivityImpl act = testPlan.createActivity("startActivity", startLink);
		
		Leg leg = testPlan.createLeg(TransportMode.car);
		
		NodeNetworkRoute route = new NodeNetworkRoute(startLink, endLink);
		route.setDistance(1200.0);
		
		leg.setRoute(route);
		
		act = testPlan.createActivity("endActivity", endLink);
		
		PopulationImpl pop = new PopulationImpl();
		pop.setIsStreaming(true);
		
		PopulationLegDistanceDistribution testee = new PopulationLegDistanceDistribution();
		pop.addAlgorithm(testee);
		
		pop.addPerson(testPerson);
		
		assertEquals(1, testee.getLegDistanceDistribution().size());
		assertTrue(testee.getLegDistanceDistribution().containsKey(TransportMode.car));
		
	}
	
}
