/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 22/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.VehicleException;
import asgn2Vehicles.MotorCycle;

/**
 * @author hogan
 *
 */
public class MotorCycleTests {
	
	
	MotorCycle motorCycle;
	private final String vehID = "EGS-9834";
	private final int arrivalTime = 100;
	private final int parkingTime = 100; 
	private final int intendedDuration = 60; //one hour
	
	//test constants
	private final int negativeArrivalTime = -10;
	private final int boundaryArrivalTime = 0;
	

	/**
	 * @throws java.lang.Exception
	 * @author Aline Borges
	 */
	@Before
	public void setUp() throws Exception {
		this.motorCycle = new MotorCycle(vehID, arrivalTime);
	}

	/**
	 * @throws java.lang.Exception
	 * @author Aline Borges
	 */
	@After
	public void tearDown() throws Exception {
		
		//TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 * Tests exception for  negative values
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testMotorCycleNegative() throws VehicleException {
		motorCycle = new MotorCycle(this.vehID, this.negativeArrivalTime);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.MotorCycle#MotorCycle(java.lang.String, int)}.
	 * Tests for boundary case arrivalTime = 0;
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testMotorCycleBoundary() throws VehicleException {
		motorCycle = new MotorCycle(this.vehID, this.boundaryArrivalTime);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#Vehicle(java.lang.String, int)}.
	 * @author Aline Borges
	 */
	@Test (expected = VehicleException.class)
	public void testVehicle() {
		//TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getVehID()}.
	 * @author Aline Borges
	 */
	@Test
	public void testGetVehID() {
		assertEquals(this.vehID, this.motorCycle.getVehID());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getArrivalTime()}.
	 * @author Aline Borges
	 */
	@Test
	public void testGetArrivalTime() {
		assertEquals(this.arrivalTime, this.motorCycle.getArrivalTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterQueuedState() throws VehicleException {
		motorCycle.enterQueuedState();
		assertTrue(this.motorCycle.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * Throws exception if car is already in queued state
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterQueuedStateException1() throws VehicleException {
		motorCycle.enterQueuedState();
		
		//in this call, the motorcycle  is already in a queued state
		motorCycle.enterQueuedState();
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testEnterQueuedStateException2() throws VehicleException {
		motorCycle.enterQueuedState();
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * @author Aline Borges
	 */
	@Test
	public void testExitQueuedState() {
		//TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * @author Aline Borges
	 */
	@Test
	public void testEnterParkedState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * @author Aline Borges
	 */
	@Test
	public void testExitParkedStateInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * @author Aline Borges
	 */
	@Test
	public void testExitParkedState() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * @author Aline Borges
	 */
	@Test
	public void testIsParked() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * @author Aline Borges
	 */
	@Test
	public void testIsQueued() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * @author Aline Borges
	 */
	@Test
	public void testGetParkingTime() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * @author Aline Borges
	 */
	@Test
	public void testGetDepartureTime() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueued() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * @author Aline Borges
	 */
	@Test
	public void testWasParked() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @author Aline Borges
	 */
	@Test
	public void testIsSatisfied() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#toString()}.
	 * @author Aline Borges
	 */
	@Test
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
