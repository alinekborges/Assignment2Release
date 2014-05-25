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
	private final int exitQueueTime = 110;
	private final int parkingTime = 110; 
	private final int intendedDuration = 60; //one hour
	private final int exitTime = 165;
	
	//test constants
	private final int negativeArrivalTime = -10;
	private final int negativeParkingTime = -10;
	private final int boundaryArrivalTime = 0;
	private final int exitTimeBeforeArrival = arrivalTime - 20;
	private final int intendedLessThanMinimun = asgn2Simulators.Constants.MINIMUM_STAY;
	private final int exitTimeBeforeParking = parkingTime - 20;
	private final int parkingTimeBeforeParking = 0;
	private final int departureTimeBeforeLeaving = 0;
	

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
	@Test (expected = VehicleException.class)
	public void testEnterQueuedStateAlreadyQueued() throws VehicleException {
		motorCycle.enterQueuedState();
		
		//in this call, the motorcycle  is already in a queued state
		motorCycle.enterQueuedState();
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterQueuedState()}.
	 * test exception if car is already parked
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testEnterQueuedStateAlreadyParked() throws VehicleException {
		motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		motorCycle.enterQueuedState();
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * Tests for exception if motorcycle is already parked
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testExitQueuedStateExceptionParked() throws VehicleException {
		this.motorCycle.enterQueuedState();
		this.motorCycle.enterParkedState(parkingTime, intendedDuration);
		this.motorCycle.exitQueuedState(exitTime);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * Tests for exception if motorcycle is not in a queued state
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testExitQueuedStateExceptionNotQueued() throws VehicleException {
		this.motorCycle.exitQueuedState(exitTime);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * Tests for exception if exitTime < arrivalTime
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testExitQueuedStateExceptionExitTime() throws VehicleException {
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitQueuedState(this.exitTimeBeforeArrival);
	}
	
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitQueuedState(int)}.
	 * Check if car left the queued state
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testExitQueuedState() throws VehicleException {
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitQueuedState(exitTime);
		
		assertFalse(this.motorCycle.isQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * Test exception if vehicle is already parked
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testEnterParkedStateAlreadyParked() throws VehicleException {
		motorCycle.enterQueuedState();
		motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);

	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * Test exception if parkingTime < 0;
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testEnterParkedStateNegativeParkingTime() throws VehicleException {
		motorCycle.enterParkedState(this.negativeParkingTime, this.intendedDuration);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * Test if intended duration is less than minimun as defined in constants
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testEnterParkedStateNotQueued() throws VehicleException {
		motorCycle.enterParkedState(this.parkingTime, this.intendedLessThanMinimun);
		//asgn2Simulators.Constants.MINIMUM_STAY;
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * Test if the enter parking time is saved correctly
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test 
	public void testEnterParkedState() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertEquals(this.parkingTime, this.motorCycle.getParkingTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * Test if the enter parking time is saved correctly
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test 
	public void testEnterParkedStateIsParked() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertTrue(this.motorCycle.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#enterParkedState(int, int)}.
	 * Test if departure time is updated to parkingTime + intendedDuration
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test 
	public void testEnterParkedStateDepartureTime() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		int departureTime = this.parkingTime + this.intendedDuration;
		assertEquals(this.motorCycle.getDepartureTime(), departureTime);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * exception if car is not in parked state
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testExitParkedStateNotParked() throws VehicleException {
		this.motorCycle.exitParkedState(this.exitTime);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * exception if car is in a queued state
	 * @author Aline Borges
	 */
	@Test (expected = VehicleException.class)
	public void testExitParkedStateIsQueued() throws VehicleException {
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitParkedState(this.exitTime);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState(int)}.
	 * exception if departureTime < parkingTime;
	 * @author Aline Borges
	 */
	@Test (expected = VehicleException.class)
	public void testExitParkedStateWrongTime() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTimeBeforeParking);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#exitParkedState()}.
	 * Tests if departure time is updated with actual leaving time
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testExitParkedStateExitTime() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		
		assertEquals(this.motorCycle.getDepartureTime(), this.exitTime);
		
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * Test false for new objects
	 * @author Aline Borges
	 */
	@Test
	public void testIsParkedNew() {
		assertFalse(this.motorCycle.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * Test false for Queued vehicles
	 * @author Aline Borges
	 */
	@Test
	public void testIsParkedQueued() throws VehicleException {
		this.motorCycle.enterQueuedState();
		assertFalse(this.motorCycle.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * Test true for parked vehicles
	 * @author Aline Borges
	 */
	@Test
	public void testIsParkedParked() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertTrue(this.motorCycle.isParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isParked()}.
	 * Test false for Parked Vehicles that already left
	 * @author Aline Borges
	 */
	@Test
	public void testIsParkedAlreadyLeft() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertFalse(this.motorCycle.isParked());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * Test for false in a new vehicle
	 * @author Aline Borges
	 */
	@Test
	public void testIsQueuedNew() {
		assertFalse(this.motorCycle.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * Test for true as the car enters the queue
	 * @author Aline Borges
	 */
	@Test
	public void testIsQueuedQueued() throws VehicleException{
		this.motorCycle.enterQueuedState();
		assertTrue(this.motorCycle.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * Test for false as the car is parked
	 * @author Aline Borges
	 */
	@Test
	public void testIsQueuedParked() throws VehicleException{
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitQueuedState(this.exitQueueTime);
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertFalse(this.motorCycle.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isQueued()}.
	 * Test for false as the car was parked and already left
	 * @author Aline Borges
	 */
	@Test
	public void testIsQueuedAlreadyLeft() throws VehicleException{
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitQueuedState(this.exitQueueTime);
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertFalse(this.motorCycle.isQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * Tests if method returns 0 when vehicle is created
	 * @author Aline Borges
	 */
	@Test
	public void testGetParkingTimeNew() {
		assertEquals(this.parkingTimeBeforeParking, this.motorCycle.getParkingTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * Tests if method returns 0 if vehicle is queued
	 * @author Aline Borges
	 */
	@Test
	public void testGetParkingTimeQueued() throws VehicleException{
		this.motorCycle.enterQueuedState();
		assertEquals(this.parkingTimeBeforeParking, this.motorCycle.getParkingTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * Tests if method returns parking time when vehicle is parked
	 * @author Aline Borges
	 */
	@Test
	public void testGetParkingTimeParked() throws VehicleException{
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertEquals(this.parkingTime, this.motorCycle.getParkingTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getParkingTime()}.
	 * Assures parking time remains the same after car leaves
	 * @author Aline Borges
	 */
	@Test
	public void testGetParkingTimeAlreadyLeft() throws VehicleException{
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertEquals(this.parkingTime, this.motorCycle.getParkingTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * Tests for TimeDeparture = 0 when vehicle is new
	 * @author Aline Borges
	 */
	@Test
	public void testGetDepartureTimeNew() {
		assertEquals(this.departureTimeBeforeLeaving, this.motorCycle.getDepartureTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * Tests for TimeDeparture = 0 when vehicle is queued
	 * @author Aline Borges
	 */
	@Test
	public void testGetDepartureTimeQueued() throws VehicleException {
		this.motorCycle.enterQueuedState();
		assertEquals(this.departureTimeBeforeLeaving, this.motorCycle.getDepartureTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * Tests for TimeDeparture = Parking time + intended stay when parked
	 * @author Aline Borges
	 */
	@Test
	public void testGetDepartureTimeParked() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		int intendedStay = this.parkingTime + this.intendedDuration;
		assertEquals(intendedStay, this.motorCycle.getDepartureTime());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#getDepartureTime()}.
	 * Tests for DepartureTime equals exit time after vehicle leaves car park
	 * @author Aline Borges
	 */
	@Test
	public void testGetDepartureTimeDeparted() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertEquals(this.exitTime, this.motorCycle.getDepartureTime());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * Tests for false on new object
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueuedNew() {
		assertFalse(this.motorCycle.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * Tests for true once vehicle is queued
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueuedQueued() throws VehicleException{
		this.motorCycle.enterQueuedState();
		assertTrue(this.motorCycle.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * Tests for true once vehicle is queued
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueuedAndParked() throws VehicleException{
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitQueuedState(this.exitQueueTime);
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertTrue(this.motorCycle.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * Tests for true once vehicle is queued
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueuedAndParkedDeparted() throws VehicleException{
		this.motorCycle.enterQueuedState();
		this.motorCycle.exitQueuedState(this.exitQueueTime);
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertTrue(this.motorCycle.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * Tests for false if vehicle goes straight to parking
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueuedParked() throws VehicleException{
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertFalse(this.motorCycle.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasQueued()}.
	 * Tests for false if vehicle goes straight to parking and Departed
	 * @author Aline Borges
	 */
	@Test
	public void testWasQueuedParkedDeparted() throws VehicleException{
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertFalse(this.motorCycle.wasQueued());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * Tests for false when object is new
	 * @author Aline Borges
	 */
	@Test
	public void testWasParkedNew() {
		assertFalse(this.motorCycle.wasParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * Tests for false when object is queued
	 * @author Aline Borges
	 */
	@Test
	public void testWasParkedQueued() throws VehicleException {
		this.motorCycle.enterQueuedState();
		assertFalse(this.motorCycle.wasParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * Tests for true when vehicle is parked
	 * @author Aline Borges
	 */
	@Test
	public void testWasParked() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		assertTrue(this.motorCycle.wasParked());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#wasParked()}.
	 * Tests for true when vehicle was parked and already departed
	 * @author Aline Borges
	 */
	@Test
	public void testWasParkedDeparted() throws VehicleException {
		this.motorCycle.enterParkedState(this.parkingTime, this.intendedDuration);
		this.motorCycle.exitParkedState(this.exitTime);
		assertTrue(this.motorCycle.wasParked());
	}

	/**
	 * Test method for {@link asgn2Vehicles.Vehicle#isSatisfied()}.
	 * @author Aline Borges
	 */
	@Test
	public void testIsSatisfied() {
		// TODO
		//fail("Not yet implemented"); 
		this.motorCycle.enterParkedState(this.parkingTime, this.exitTime);
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
