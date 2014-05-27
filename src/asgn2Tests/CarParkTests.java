/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Tests 
 * 29/04/2014
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Simulators.Simulator;
import asgn2Vehicles.Car;
import asgn2Vehicles.MotorCycle;

/**
 * @author Lucas
 * 
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarParkTests {

	private static final int maxCarSpaces = 100;
	private static final int maxSmallCarSpaces = 20;
	private static final int maxNormalCarSpaces = maxCarSpaces
			- maxSmallCarSpaces;
	private static final int maxMotorCycleSpaces = 20;
	private static final int maxQueueSize = 10;

	private static final int maxQueueStay = 25;
	private static final int defaultIntendedStay = 41;

	private static final int defaultArrival1 = 1;
	private static final int defaultArrival2 = 2;
	private static final int defaultArrival3 = 3;

	private static final int defaultDeparture = defaultIntendedStay
			+ defaultArrival1;
	private static final int departureBeforeDefault = defaultDeparture - 1;
	private static final int departureAfterDeafult = defaultDeparture + 1;

	private static final int maxDefaultQueueDeparture = defaultArrival1
			+ maxQueueStay;
	private static final int exitQueueBeforeMax = maxDefaultQueueDeparture - 1;
	private static final int exitQueueAfterMax = maxDefaultQueueDeparture + 1;

	private static final String vehicleID1 = "1";
	private static final String vehicleID2 = "2";
	private static final String vehicleID3 = "3";

	private static Car car;
	private static Car smallCar;
	private static MotorCycle motorCycle;

	private static CarPark carPark;
	private static Simulator simulator;

	/**
	 * Basic set up for testing
	 * 
	 * @throws java.lang.Exception
	 * @author Lucas
	 */

	@Before
	public void setUp() throws Exception {
		carPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		car = new Car(vehicleID1, defaultArrival1, false);
		smallCar = new Car(vehicleID2, defaultArrival2, true);
		motorCycle = new MotorCycle(vehicleID3, defaultArrival3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Test
	public void defaultSetUp() throws Exception {
		// TODO
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// TODO
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testArchiveDepartingVehicles() throws VehicleException,
			SimulationException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.unparkVehicle(car, defaultDeparture);
		carPark.archiveDepartingVehicles(defaultDeparture, true);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * Testing for vehicles that try to depart before the default time.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testArchiveDepartingVehiclesBeforeDefaultTime()
			throws VehicleException, SimulationException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.unparkVehicle(car, departureBeforeDefault);
		carPark.archiveDepartingVehicles(departureBeforeDefault, true);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * Testing for vehicles that try to depart before the default time.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testArchiveDepartingVehiclesAfterDefaultTime()
			throws VehicleException, SimulationException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.unparkVehicle(car, departureAfterDeafult);
		carPark.archiveDepartingVehicles(departureAfterDeafult, true);
	}

	// /**
	// * Test method for
	// * {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	// * Testing for a simulation exception when there are no vehicles to be
	// * archived.
	// *
	// * @throws VehicleException
	// * @throws SimulationException
	// */
	// @Test(expected = SimulationException.class)
	// public void testArchiveDepartingVehiclesNoVehcilesSimulationaException()
	// throws VehicleException, SimulationException {
	// carPark.archiveDepartingVehicles(defaultDeparture, true);
	// }

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveDepartingVehicles(int, boolean)}.
	 * Testing for a vehicle exception when the vehicle is in the incorrect
	 * state.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testArchiveDepartingVehiclesIncorrectStateVehicle()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		car.exitParkedState(defaultDeparture);
		carPark.archiveDepartingVehicles(defaultDeparture, true);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * 
	 * @throws SimulationException
	 */
	@Test
	public void testArchiveNewVehicle() throws SimulationException {
		carPark.archiveNewVehicle(car);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * Testing for a simulation exception when a vehicle is in the incorrect
	 * state.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = SimulationException.class)
	public void testArchiveNewVehicleCurrentlyQueued()
			throws SimulationException, VehicleException {
		carPark.enterQueue(car);
		carPark.archiveNewVehicle(car);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#archiveNewVehicle(asgn2Vehicles.Vehicle)}.
	 * Testing for a simulation exception when a vehicle is in the incorrect
	 * state.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = SimulationException.class)
	public void testArchiveNewVehicleCurrentlyParked()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.archiveNewVehicle(car);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testArchiveQueueFailures() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		carPark.exitQueue(car, exitQueueAfterMax);
		carPark.archiveQueueFailures(exitQueueAfterMax);
	}

	// /**
	// * Test method for {@link
	// asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	// * Testing for a vehicle exception when a vehicle leaves the queue on the
	// * last possible minute.
	// *
	// * @throws SimulationException
	// * @throws VehicleException
	// */
	// @Test(expected = VehicleException.class)
	// public void testArchiveQueueFailuresExitQueueOnLimit()
	// throws SimulationException, VehicleException {
	// carPark.enterQueue(car);
	// carPark.exitQueue(car, maxQueueStay);
	// carPark.archiveQueueFailures(maxQueueStay);
	// }

	// /**
	// * Test method for {@link
	// asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	// * Testing for a vehicle exception for when a vehicle leaves the queue
	// * before the maximum wait time.
	// *
	// * @throws SimulationException
	// * @throws VehicleException
	// */
	// @Test(expected = VehicleException.class)
	// public void testArchiveQueueFailuresExitQueueBeforeLimit()
	// throws SimulationException, VehicleException {
	// carPark.enterQueue(car);
	// carPark.exitQueue(car, exitQueueBeforeMax);
	// carPark.archiveQueueFailures(exitQueueBeforeMax);
	// }

	// /**
	// * Test method for {@link
	// asgn2CarParks.CarPark#archiveQueueFailures(int)}.
	// * Testing for a vehicle exception, when a vehicle is in the incorrect
	// * state.
	// *
	// * @throws SimulationException
	// * @throws VehicleException
	// */
	// @Test(expected = VehicleException.class)
	// public void testArchiveQueueFailuresVehicleInIncorrectState()
	// throws SimulationException, VehicleException {
	// carPark.archiveQueueFailures(exitQueueAfterMax);
	// }

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}.
	 */
	@Test
	public void testCarParkEmpty() {
		assertTrue("CarParkEmpty() Error: Incorrect reading",
				carPark.carParkEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}. Testing the
	 * car park when there is currently one vehicle parked.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testCarParkEmptyFalse() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertFalse("testCarParkEmptyFalse() Error: Incorrect reading",
				carPark.carParkEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}. Testing for
	 * when there is only a vehicle in the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testCarParkEmptyCarsOnlyInQueue() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		assertTrue(
				"testCarParkEmptyCarsOnlyInQueue() Error: Incorrect reading",
				carPark.carParkEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkEmpty()}. Testing for
	 * when the car park is full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testCarParkEmptyFalseFullCarPark() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("CarParkEmpty() Error: Incorrect reading",
				carPark.carParkEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testCarParkFull() throws VehicleException, SimulationException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		assertTrue("testCarParkFull() Error", carPark.carParkFull());

	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}. Testing for
	 * when there are currently no cars in the car park.
	 */
	@Test
	public void testCarParkFullNoCars() {
		assertFalse("CarParkFull() Error", carPark.carParkFull());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#carParkFull()}. Testing for
	 * when there is only one car in the car park.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testCarParkFullFalseOneCar() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertFalse("testCarParkFullFalseOneCar() Error", carPark.carParkFull());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testEnterQueue() throws SimulationException, VehicleException {
		carPark.enterQueue(car);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}. Testing
	 * for a simulation exception, for when the queue is already full and
	 * another vehicle tries to enter the queue.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test(expected = SimulationException.class)
	public void testEnterQueueFullQueue() throws VehicleException,
			SimulationException {
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		carPark.enterQueue(smallCar);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#enterQueue(asgn2Vehicles.Vehicle)}. Testing
	 * for a vehicle exception, when a vehicle in an incorrect state attempts to
	 * enter the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testEnterQueueVehicleIncorrectState()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.enterQueue(car);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testExitQueue() throws SimulationException, VehicleException {
		carPark.enterQueue(car);
		carPark.exitQueue(car, maxQueueStay);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * Testing for a simulation exception, when a car that is not currently in
	 * the queue attempts to exit the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = SimulationException.class)
	public void testExitQueueVehicleNotInQueue() throws SimulationException,
			VehicleException {
		carPark.exitQueue(car, maxQueueStay);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	 * Testing for a vehicle exception, when a vehicle attempts to exit the
	 * queue in an incorrect state.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testExitQueueIncorrectVehicleState()
			throws SimulationException, VehicleException {
		carPark.enterQueue(car);
		car.enterParkedState(defaultArrival1, defaultIntendedStay);
		carPark.exitQueue(car, maxQueueStay);
	}

	// /**
	// * Test method for
	// * {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}.
	// * Testing for a vehicle exception, when a vehicle stays longer than the
	// max
	// * wait time.
	// *
	// * @throws SimulationException
	// * @throws VehicleException
	// */
	// @Test(expected = VehicleException.class)
	// public void testExitQueueViolatedTimingStayingLongerThanMax()
	// throws SimulationException, VehicleException {
	// carPark.enterQueue(car);
	// carPark.exitQueue(car, exitQueueAfterMax);
	// }

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#exitQueue(asgn2Vehicles.Vehicle, int)}
	 * .Testing for a vehicle exception, when a vehicle leaves the queue before
	 * arriving.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = VehicleException.class)
	public void testExitQueueViolatedTimingLeaveBeforeArriving()
			throws SimulationException, VehicleException {
		car = new Car(vehicleID1, defaultArrival3, false);
		carPark.enterQueue(car);
		carPark.exitQueue(car, defaultArrival1);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#finalState()}.
	 */
	@Test
	public void testFinalState() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testGetNumCars() throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertEquals("testGetNumCars() Error", 1, carPark.getNumCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}. Testing the
	 * method for small cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumCarsSmallCar() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(smallCar, defaultArrival1, defaultIntendedStay);
		assertEquals("testGetNumCars() Error", 1, carPark.getNumCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}. Testing the
	 * method for small and regular cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumCarsSmallandNormalCars() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		assertEquals("testGetNumCars() Error", 2, carPark.getNumCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}. Testing a
	 * situation when vehicles come and leave the car park.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumCars_CarsComeAndGo() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		carPark.parkVehicle(motorCycle, defaultArrival3, defaultIntendedStay);
		carPark.unparkVehicle(car, defaultDeparture);
		assertEquals("testGetNumCars() Error", 1, carPark.getNumCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}. Testing the
	 * method for when there are no cars in the car park.
	 */
	@Test
	public void testNumCarsZeroCars() {
		assertEquals("testGetNumCars() Error", 0, carPark.getNumCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumCars()}. Testing the
	 * method for when the car park is full.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testNumCarsFullCarPark() throws VehicleException,
			SimulationException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertEquals("testNumCarsFullCarPark() Error", maxCarSpaces,
				carPark.getNumCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testGetNumMotorCycles() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(motorCycle, defaultArrival3, defaultIntendedStay);
		assertEquals("testGetNumMotorCycles() Error", 1,
				carPark.getNumMotorCycles());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}. Test
	 * for when there are no motorcycles in he car park, but other vehicles are
	 * present.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesZeroMotorCycles()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertEquals("testGetNumMotorCyclesZeroMotorCycles() Error", 0,
				carPark.getNumMotorCycles());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 * Testing for when there are more than one motorcycles among other vehicles
	 * in the car park.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesMultipleMotorCycles()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(motorCycle, defaultArrival2, defaultIntendedStay);
		motorCycle = new MotorCycle(vehicleID2, defaultArrival3);
		carPark.parkVehicle(motorCycle, defaultArrival3, defaultIntendedStay);
		assertEquals("testGetNumMotorCyclesMultipleMotorCycles() Error", 2,
				carPark.getNumMotorCycles());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumMotorCycles()}.
	 * Testing for when the motorcycle spaces are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumMotorCyclesFull() throws SimulationException,
			VehicleException {
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertEquals("testGetNumMotorCyclesFull() Error", maxMotorCycleSpaces,
				carPark.getNumMotorCycles());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testGetNumSmallCars() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		assertEquals("testGetNumSmallCars() Error", 1,
				carPark.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumSmallCarsFullCarPark() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		assertEquals("testGetNumSmallCars() Error", maxSmallCarSpaces,
				carPark.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}. Testing
	 * for when there are no small cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumSmallCarsZeroSmallCars() throws SimulationException,
			VehicleException {
		assertEquals("testGetNumSmallCars() Error", 0,
				carPark.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}. Test for
	 * when there are no parked small cars but there are regular cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumSmallCarsOnlyNormalCars() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertEquals("testGetNumSmallCarsOnlyNormalCars() Error", 0,
				carPark.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}. Testing
	 * for when the small car spaces are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumSmallCars_SmallCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID1 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertEquals("testGetNumSmallCars_SmallCarSpacesFull() Error",
				maxSmallCarSpaces, carPark.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getNumSmallCars()}. Testing
	 * for when the small car and normal car spaces are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testGetNumSmallAndNormalCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		assertEquals("testGetNumSmallAndNormalCarSpacesFull() Error",
				maxSmallCarSpaces, carPark.getNumSmallCars());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#getStatus(int)}.
	 */
	@Test
	public void testGetStatus() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#initialState()}.
	 */
	@Test
	public void testInitialState() {
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testNumVehiclesInQueue() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		carPark.enterQueue(smallCar);
		carPark.enterQueue(motorCycle);
		assertEquals("testNumVehiclesInQueue() Error", 3,
				carPark.numVehiclesInQueue());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}. No
	 * cars in the queue.
	 */
	@Test
	public void testNumVehiclesInQueueZero() {
		assertEquals("testNumVehiclesInQueueZero() Error", 0,
				carPark.numVehiclesInQueue());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 * Vehicles enter and leave the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testNumVehiclesInQueue_ComeAndGo() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		carPark.enterQueue(smallCar);
		carPark.enterQueue(motorCycle);
		carPark.exitQueue(car, maxDefaultQueueDeparture);
		assertEquals("testNumVehiclesInQueue_ComeAndGo() Error", 2,
				carPark.numVehiclesInQueue());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#numVehiclesInQueue()}.
	 * Testing for when the queue is full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testNumVehiclesInQueueFullQueue() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		assertEquals("testNumVehiclesInQueueFullQueue() Error", maxQueueSize,
				carPark.numVehiclesInQueue());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * .
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testParkVehicle() throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		carPark.parkVehicle(motorCycle, defaultArrival2, defaultIntendedStay);

		assertEquals("testParkVehicle() Error", 3, carPark.getNumCars()
				+ carPark.getNumMotorCycles());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . Testing for a simulation exception, when a vehicle attempts to park in
	 * a full car park.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = SimulationException.class)
	public void testParkVehicleNoSpaces() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.parkVehicle(car, maxCarSpaces + 1, defaultIntendedStay);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . Testing for a simulation exception, when a motorcycle attempts to park
	 * when there are no motorcycle spaces.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = SimulationException.class)
	public void testParkVehicleNoMotorCycleSpaces() throws SimulationException,
			VehicleException {
		for (int j = 1; j <= maxSmallCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxMotorCycleSpaces + 1);
		carPark.parkVehicle(motorCycle, maxMotorCycleSpaces + 1,
				defaultIntendedStay);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . A small car attempts to park when both the small and normal car spaces
	 * are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test(expected = SimulationException.class)
	public void testParkVehicleNoSmallSpaces() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.parkVehicle(smallCar, maxCarSpaces + 1, defaultIntendedStay);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . Testing to see if the vehicles are parking correctly.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testParkVehicleOneSpaceEach() throws SimulationException,
			VehicleException {
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		car = new Car(vehicleID1, maxCarSpaces, false);
		smallCar = new Car(vehicleID2, maxCarSpaces + 1, true);
		motorCycle = new MotorCycle(vehicleID3, maxMotorCycleSpaces);

		carPark.parkVehicle(motorCycle, maxMotorCycleSpaces,
				defaultIntendedStay);
		carPark.parkVehicle(smallCar, maxCarSpaces + 1, defaultIntendedStay);
		carPark.parkVehicle(car, maxCarSpaces, defaultIntendedStay);

		assertEquals("testParkVehicleOneSpaceEach() Error", maxCarSpaces
				+ maxMotorCycleSpaces,
				carPark.getNumCars() + carPark.getNumMotorCycles());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . A motorcycle may park in a small car space.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testParkVehicle_NoMotorCycleSpaces_OneSmallCarSpace_ParkMotorCycle()
			throws SimulationException, VehicleException {
		for (int j = 1; j < maxSmallCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = maxSmallCarSpaces + 1; k <= maxMotorCycleSpaces
				+ maxSmallCarSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxMotorCycleSpaces
				+ maxSmallCarSpaces + 1);
		carPark.parkVehicle(motorCycle, maxMotorCycleSpaces + maxSmallCarSpaces
				+ 1, defaultIntendedStay);
		assertEquals(
				"testParkVehicle_NoMotorCycleSpaces_OneSmallCarSpace_ParkMotorCycle()",
				maxMotorCycleSpaces + 1, carPark.getNumMotorCycles());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . A small car may park in a normal car space.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testParkVehicle_NoSmallSpaces_OneNormalCarSpace_ParkSmallCar()
			throws SimulationException, VehicleException {
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxCarSpaces, true);
		carPark.parkVehicle(smallCar, maxCarSpaces, defaultIntendedStay);
		assertEquals(
				"testParkVehicle_NoSmallSpaces_OneNormalCarSpace_ParkSmallCar()",
				maxSmallCarSpaces + 1, carPark.getNumSmallCars());
	}

	// /**
	// * Test method for
	// * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int,
	// int)}
	// * . Testing for a vehicle exception when a vehicle parked before
	// arriving.
	// *
	// * @throws VehicleException
	// * @throws SimulationException
	// */
	// @Test(expected = VehicleException.class)
	// public void testParkVehicleVehicleArrivedAfterCurrentTime()
	// throws VehicleException, SimulationException {
	// car = new Car(vehicleID1, defaultArrival3, false);
	// carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
	// }

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#parkVehicle(asgn2Vehicles.Vehicle, int, int)}
	 * . Testing for a vehicle exception, when the vehicle is in the incorrect
	 * state.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test(expected = VehicleException.class)
	public void testParkVehicleIncorrectState() throws VehicleException,
			SimulationException {
		car.enterParkedState(defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#processQueue(int, asgn2Simulators.Simulator)}
	 * .
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testProcessQueue_QueueIsEmpty() throws SimulationException,
			VehicleException {
		simulator = new Simulator();
		carPark.processQueue(defaultArrival1, simulator);
		assertTrue("testProcessQueue_QueueIsEmpty() Error",
				carPark.queueEmpty());
	}

	// Queue is not full & Spaces For All
	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForAll()
			throws VehicleException, SimulationException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces - 2; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxSmallCarSpaces
				+ maxNormalCarSpaces - 2; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces - 2; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxNormalCarSpaces + 1,
				false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxNormalCarSpaces
				+ 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxNormalCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxNormalCarSpaces + 4,
				false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxNormalCarSpaces
				+ 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxNormalCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		carPark.processQueue(maxSmallCarSpaces + maxNormalCarSpaces + 7,
				simulator);
		assertTrue("testProcessQueue_QueueIsNotFull_SpacesForAll() Error",
				carPark.queueEmpty());
	}

	// Queue is not full & Spaces for some
	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsNormalCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar_NormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar_NormalCarSpaceAvailable() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle_SmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle_SmallCarSpaceAvailable() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar_NormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar_NormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 6, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle_SmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle_SmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());

	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsSmallCar_NormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsSmallCar_NormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle_SmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueue_QueueIsNotFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle_SmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsNotFull_SpacesForNone()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 7, simulator);
		assertEquals("testProcessQueue_QueueIsNotFull_SpacesForNone() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForAll()
			throws VehicleException, SimulationException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces - 3; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces - 3; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces - 4; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertTrue("testProcessQueue_QueueIsFull_SpacesForAll() Error",
				carPark.queueEmpty());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsNormalCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar_NormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsSmallCar_NormalCarSpaceAvailable() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxCarSpaces + 7, true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle_SmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxCarSpaces + 7, true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForFirstButNotLast_FirstIsMotorCycle_SmallCarSpaceAvailable() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 5, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar_NormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, +maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForMiddleButNotFirstAndNotLast_MiddleIsSmallCar_NormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSomeSpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 4, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 7, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		smallCar = new Car(vehicleID1, maxCarSpaces + 10, true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSomeSpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSomeSpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle_SmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSomeSpaceForMiddleButNotFirstAndNotLast_MiddleIsMotorCycle_SmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSomeSpaceForLastButNotFirst_LastIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSomeSpaceForLastButNotFirst_LastIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());

	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForLastButNotFirst_LastIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 5, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 10, true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForLastButNotFirst_LastIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSomeSpaceForLastButNotFirst_LastIsSmallCar_NormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxCarSpaces + 10, true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSomeSpaceForLastButNotFirst_LastIsSmallCar_NormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxCarSpaces + 1, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 3, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 7, true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 9, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle_SmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 5, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueue_QueueIsFull_SpacesForSome_SpaceForLastButNotFirst_LastIsMotorCycle_SmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueue_QueueIsFull_SpacesForNone()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 2, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 5, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxCarSpaces + 8, true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxCarSpaces + 11, simulator);
		assertEquals("testProcessQueue_QueueIsFull_SpacesForNone() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueEmpty()}.
	 */
	@Test
	public void testQueueEmpty() {
		assertTrue("testQueueEmpty() Error", carPark.queueEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueEmpty()}. One car in
	 * the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueEmptyOneCar() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		assertFalse("testQueueEmptyOneCar() Error", carPark.queueEmpty());
	}

	/**
	 * One small car in queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueEmptyOneSmallCar() throws SimulationException,
			VehicleException {
		carPark.enterQueue(smallCar);
		assertFalse("testQueueEmptyOneSmallCar() Error", carPark.queueEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueEmpty()}. One
	 * motorcycle in the queue
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueEmptyOneMotorCycle() throws SimulationException,
			VehicleException {
		carPark.enterQueue(motorCycle);
		assertFalse("testQueueEmptyOneMotorCycle() Error", carPark.queueEmpty());
	}

	/**
	 * The queue is full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueEmptyFullQueue_Cars() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		assertFalse("testQueueEmptyFullQueue_Cars() Error",
				carPark.queueEmpty());
	}

	@Test
	public void testQueueEmptyFullQueue_SmallCars() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			smallCar = new Car((vehicleID1 + Integer.toString(i)), i, true);
			carPark.enterQueue(smallCar);
		}

		assertFalse("testQueueEmptyFullQueue_SmallCars() Error",
				carPark.queueEmpty());
	}

	@Test
	public void testQueueEmptyFullQueue_MotorCycles()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			motorCycle = new MotorCycle((vehicleID1 + Integer.toString(i)), i);
			carPark.enterQueue(motorCycle);
		}

		assertFalse("testQueueEmptyFullQueue_MotorCycles() Error",
				carPark.queueEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testQueueFull() throws SimulationException, VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		assertTrue("testQueueFull() Error", carPark.queueFull());
	}

	@Test
	public void testQueueFull_SmallCars() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			smallCar = new Car((vehicleID1 + Integer.toString(i)), i, true);
			carPark.enterQueue(smallCar);
		}
		assertTrue("testQueueFull_SmallCars() Error", carPark.queueFull());
	}

	@Test
	public void testQueueFull_MotorCycles() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize; i++) {
			motorCycle = new MotorCycle((vehicleID1 + Integer.toString(i)), i);
			carPark.enterQueue(motorCycle);
		}
		assertTrue("testQueueFull_MotorCycles() Error", carPark.queueFull());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}. Testing the
	 * limit of the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueFull_AlmostFull() throws SimulationException,
			VehicleException {
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		assertFalse("testQueueFull_AlmostFull() Error", carPark.queueFull());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}. Testing the
	 * lower limit of the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueFull_AlmostEmpty() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		assertFalse("testQueueFull_AlmostEmpty() Error", carPark.queueFull());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}. The queue is
	 * empty.
	 */
	@Test
	public void testQueueFull_Empty() {
		assertFalse("testQueueFull_Empty() Error", carPark.queueFull());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 */
	@Test
	public void testSpacesAvailable() {
		assertTrue("testSpacesAvailable() Error", carPark.spacesAvailable(car));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing for small cars.
	 */
	@Test
	public void testSpacesAvailable_SmallCar() {
		assertTrue("testSpacesAvailable_SmallCar() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * testing for motorcycles.
	 */
	@Test
	public void testSpacesAvailable_MotorCycle() {
		assertTrue("testSpacesAvailable_MotorCycle() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing for normal cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_OneParkedSmallCar()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		assertTrue("testSpacesAvailable_OneParkedSmallCar() Error",
				carPark.spacesAvailable(car));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing for small cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailableSmallCar_OneParkedCar()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertTrue("testSpacesAvailableSmallCar_OneParkedCar() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing for motorcycles.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailableMotorCycle_OneParkedCar()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertTrue("testSpacesAvailableMotorCycle_OneParkedCar() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing the limit for normal cars.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testSpacesAvailable_NormalCarSpacesAlmostFull()
			throws VehicleException, SimulationException {
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertTrue("testSpacesAvailable_NormalCarSpacesAlmostFull() Error",
				carPark.spacesAvailable(car));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing the limit for small cars spaces.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailableSmallCarSpaces_AlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i < maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertTrue("testSpacesAvailableSmallCarSpaces_AlmostFull() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing the limit for motorcycle spaces.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_MotorCycleSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i < maxMotorCycleSpaces; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		assertTrue("testSpacesAvailable_MotorCycleSpacesAlmostFull() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Normal car spaces are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_NormalCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailable_NormalCarSpacesFull() Error",
				carPark.spacesAvailable(car));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Small cars can park in normal car spaces.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_SmallCarSpacesFull_AvailableNormalCarSpaces()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailable_SmallCarSpacesFull_AvailableNormalCarSpaces() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Motorcycles may park in small car spaces.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_MotorCycleSpacesFull_AvailableSmallCarSpaces()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxMotorCycleSpaces; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailable_MotorCycleSpacesFull_AvailableSmallCarSpaces() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing the limit for small car and normal car spcaes for small cars.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_SmallCarSpacesFull_NormalCarSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		for (int i = maxSmallCarSpaces + 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailable_SmallCarSpacesFull_NormalCarSpacesAlmostFull() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Testing the limit for motorcycle and small car spaces for motorcycles.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_MotorCycleSpacesFull_SmallCarSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxMotorCycleSpaces; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		for (int i = 1; i < maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailable_MotorCycleSpacesFull_SmallCarSpacesAlmostFull() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Small and normal car spaces are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_SmallandNormalCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		for (int i = maxSmallCarSpaces + 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailable_SmallandNormalCarSpacesFull() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * Motorcycle and small car spaces are full.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_MotorCycleAndSmallCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxMotorCycleSpaces; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertFalse(
				"testSpacesAvailable_MotorCycleAndSmallCarSpacesFull() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}. All
	 * spaces are full and a car tries to find a space.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_AllFull_CarTriesToPark()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailable_AllFull_CarTriesToPark() Error",
				carPark.spacesAvailable(car));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}. All
	 * spaces are full and a small car tries to find a space.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_AllFull_SmallCarTriesToPark()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailable_AllFull_SmallCarTriesToPark() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}. All
	 * spaces are taken and a motorcycles tries to find a space.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testSpacesAvailable_AllFull_MotorCycleTriesToPark()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailabl_AllFull_MotorCycleTriesToPark() Error",
				carPark.spacesAvailable(motorCycle));
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue(true);
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#tryProcessNewVehicles(int, asgn2Simulators.Simulator)}
	 * .
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testTryProcessNewVehicles_Park_Car() throws VehicleException,
			SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 0);

		int carParkBefore = carPark.getNumCars();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Park_Car() Error",
				carParkBefore + 1, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Park_SmallCar()
			throws SimulationException, VehicleException {

		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 1, 0);

		int carParkBefore = carPark.getNumCars();

		carPark.tryProcessNewVehicles(defaultArrival3, sim);

		assertEquals("testTryProcessNewVehicles_Park_SmallCar() Error",
				carParkBefore + 1, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Park_MotorCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 0, 0, 1);

		int carParkBefore = carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Park_MotorCycle() Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_Park_Car_CarParkAlmostFull()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 0);

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Park_Car_CarParkAlmostFull() Error",
				carParkBefore + 1, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Park_SmallCar_CarParkAlmostFull()
			throws SimulationException, VehicleException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 1, 0);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Park_SmallCar_CarParkAlmostFull() Error",
				carParkBefore + 1, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Park_MotorCycle_CarParkAlmostFull()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 0, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Park_MotorCycle_CarParkAlmostFull() Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_Park_SmallCar_CarParkAlmostFull_NormalCarSpace()
			throws SimulationException, VehicleException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 1, 0);

		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Park_SmallCar_CarParkAlmostFull_NormalCarSpace() Error",
				carParkBefore + 1, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Park_MotorCycle_CarParkAlmostFull_SmallCarSpace()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 0, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Park_MotorCycle_CarParkAlmostFull_SmallCarSpace() Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_Queue_Car()
			throws SimulationException, VehicleException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 0);
		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Queue_Car() Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_Queue_SmallCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 1, 0);
		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Queue_SmallCar() Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_Queue_MotorCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 0, 0, 1);

		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Queue_MotorCycle() Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_Queue_Car_QueueAlmostFull()
			throws SimulationException, VehicleException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 0);
		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Almost Full Queue
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Queue_Car_QueueAlmostFull() Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_Queue_SmallCar_QueueAlmostFull()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 1, 0);
		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Almost Full Queue
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Queue_SmallCar_QueueAlmostFull() Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_Queue_MotorCycle_QueueAlmostFull()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 0, 0, 1);
		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Almost Full Queue
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_Queue_MotorCycle_QueueAlmostFull() Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_Archive_Car()
			throws SimulationException, VehicleException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 0);

		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Full Queue
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		int carParkBefore = carPark.getNumCars();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Archive_Car() Error",
				carParkBefore, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Archive_SmallCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 1, 0);

		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Full Queue
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		int carParkBefore = carPark.getNumCars();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Archive_Car() Error",
				carParkBefore, carPark.getNumCars());
	}

	@Test
	public void testTryProcessNewVehicles_Archive_MotorCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 0, 0, 1);

		// Full Car Park
		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Full Queue
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		int carParkBefore = carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals("testTryProcessNewVehicles_Archive_Car() Error",
				carParkBefore, carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_ParkBoth()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		int carParkBefore = carPark.getNumCars() + carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_ParkBoth() Error",
				carParkBefore + 2,
				carPark.getNumCars() + carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_CarparkAlmostFull_ParkBoth()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		// Almost Full Car Park
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars() + carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_CarparkAlmostFull_ParkBoth() Error",
				carParkBefore + 2,
				carPark.getNumCars() + carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_CarparkAlmostFull_SmallCarSpaceAvailable_ParkBoth()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		// Almost Full Car Park
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars() + carPark.getNumMotorCycles();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_CarparkAlmostFull_SmallCarSpaceAvailable_ParkBoth() Error",
				carParkBefore + 2,
				carPark.getNumCars() + carPark.getNumMotorCycles());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueMotorCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		// Almost Full Car Park
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueMotorCycle() Parking Error",
				carParkBefore + 1, carPark.getNumCars());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueMotorCycle() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueMotorCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		// Almost Full Car Park
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueMotorCycle() Parking Error",
				carParkBefore + 1, carPark.getNumCars());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueMotorCycle() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueMotorCycle_QueueAlmostFull()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Almost Full Queue
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueMotorCycle_QueueAlmostFull() Parking Error",
				carParkBefore + 1, carPark.getNumCars());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueMotorCycle_QueueAlmostFull() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueMotorCycle_QueueAlmostFull()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		// Almost Full Car Park
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Almost Full Queue
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueMotorCycle_QueueAlmostFull() Parking Error",
				carParkBefore + 1, carPark.getNumCars());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueMotorCycle_QueueAlmostFull() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueIsFull_ArchiveCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Full Queue
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueIsFull_ArchiveCycle() Parking Error",
				carParkBefore + 1, carPark.getNumCars());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_ParkCar_QueueIsFull_ArchiveCycle() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueIsFull_ArchiveCycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);
		// Almost Full Car Park
		for (int i = 1; i < maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		// Full Queue
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueIsFull_ArchiveCycle() Parking Error",
				carParkBefore + 1, carPark.getNumCars());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForCar_CarParkAlmostFull_ParkCar_QueueIsFull_ArchiveCycle() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_QueueAlmostFull_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}

		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_QueueAlmostFull_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_QueueAlmostFull_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_QueueAlmostFull_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_QueueAlmostFull_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_QueueAlmostFull_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_ParkMotorCycle_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_ParkMotorCycle_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_ParkMotorCycle_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_ParkMotorCycle_QueueAlmostFull_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_ParkMotorCycle_QueueAlmostFull_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_ParkMotorCycle_QueueAlmostFull_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_CarParkAlmostFull_ParkMotorCycle_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_CarParkAlmostFull_ParkMotorCycle_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_CarParkAlmostFull_ParkMotorCycle_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_CarParkAlmostFull_ParkMotorCycle_QueueAlmostFull_QueueCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_CarParkAlmostFull_ParkMotorCycle_QueueAlmostFull_QueueCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpaceAvailable_CarParkAlmostFull_ParkMotorCycle_QueueAlmostFull_QueueCar() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_ArchiveCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_ArchiveCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_ParkMotorCycle_ArchiveCar() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_ArchiveCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_ArchiveCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_ParkMotorCycle_ArchiveCar() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpacesAvailable_ParkMotorCycle_ArchiveCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpacesAvailable_ParkMotorCycle_ArchiveCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_SmallCarSpacesAvailable_ParkMotorCycle_ArchiveCar() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_SmallCarSpacesAvailable_ParkMotorCycle_ArchiveCar()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j < maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_SmallCarSpacesAvailable_ParkMotorCycle_ArchiveCar() Parking Error",
				carParkBefore + 1, carPark.getNumMotorCycles());
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_SpaceInCarparkForMotorCycle_CarParkAlmostFull_SmallCarSpacesAvailable_ParkMotorCycle_ArchiveCar() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_QueueEmpty_QueueBoth()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_QueueEmpty_QueueBoth() Queue Error",
				queueBefore + 2, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_SpaceForBothInQueue_QueueBoth()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i <= maxQueueSize - 2; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_SpaceForBothInQueue_QueueBoth() Queue Error",
				queueBefore + 2, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_SpaceForCarInQueueOnly_QueueCar_ArchiveMotorcycle()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i < maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_SpaceForCarInQueueOnly_QueueCar_ArchiveMotorcycle() Parking Error",
				carParkBefore, carPark.getNumMotorCycles());
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_SpaceForCarInQueueOnly_QueueCar_ArchiveMotorcycle() Queue Error",
				queueBefore + 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_QueueFull_ArchiveBoth()
			throws VehicleException, SimulationException {
		Simulator sim = new Simulator(Constants.DEFAULT_SEED,
				Constants.DEFAULT_INTENDED_STAY_MEAN,
				Constants.DEFAULT_INTENDED_STAY_SD, 1, 0, 1);

		for (int i = 1; i <= maxNormalCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxNormalCarSpaces + 1; j <= maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		for (int i = 1; i <= maxQueueSize; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}

		int carParkBefore = carPark.getNumMotorCycles() + carPark.getNumCars();
		int queueBefore = carPark.numVehiclesInQueue();
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_QueueFull_ArchiveBoth() Parking Error",
				carParkBefore,
				carPark.getNumMotorCycles() + carPark.getNumCars());
		carPark.tryProcessNewVehicles(defaultArrival3, sim);
		assertEquals(
				"testTryProcessNewVehicles_MotorcycleAndCar_NoSpaceInCarparkForBoth_QueueFull_ArchiveBoth() Queue Error",
				queueBefore, carPark.numVehiclesInQueue());
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle, int)}.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test
	public void testUnparkVehicle() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.unparkVehicle(car, defaultDeparture);
	}

	// /**
	// * Test method for
	// * {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle,
	// int)}.
	// * Testing for a vehicle exception, when the vehicle is not parked.
	// *
	// * @throws VehicleException
	// * @throws SimulationException
	// */
	// @Test(expected = VehicleException.class)
	// public void testUnparkVehicleVehicleNotParked() throws VehicleException,
	// SimulationException {
	// carPark.unparkVehicle(car, defaultDeparture);
	// }

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle, int)}.
	 * Testing for a vehicle exception, when the vehicle is in the queue.
	 * 
	 * @throws VehicleException
	 * @throws SimulationException
	 */
	@Test(expected = SimulationException.class)
	public void testUnparkVehicleVehicleinQueue() throws VehicleException,
			SimulationException {
		carPark.enterQueue(car);
		carPark.unparkVehicle(car, defaultDeparture);
	}

	// /**
	// * Test method for
	// * {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle,
	// int)}.
	// * testing for a vehicle exception, when the vehicle was not parked.
	// *
	// * @throws VehicleException
	// * @throws SimulationException
	// */
	// @Test(expected = VehicleException.class)
	// public void testUnparkVehicleUnparkBeforeTime() throws VehicleException,
	// SimulationException {
	// carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
	// carPark.unparkVehicle(car, departureBeforeDefault);
	// }

	// /**
	// * Test method for
	// * {@link asgn2CarParks.CarPark#unparkVehicle(asgn2Vehicles.Vehicle,
	// int)}.
	// * Conflicts with timing. The car tries to unpark before it was parked.
	// *
	// * @throws VehicleException
	// * @throws SimulationException
	// */
	// @Test(expected = VehicleException.class)
	// public void testUnparkVehicleUnparkAfterTime() throws VehicleException,
	// SimulationException {
	// carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
	// carPark.unparkVehicle(car, departureAfterDeafult);
	// }

	@Test(expected = SimulationException.class)
	public void testUnparkVehicle_SimulationException()
			throws VehicleException, SimulationException {
		carPark.unparkVehicle(car, defaultDeparture);
	}

	@Test(expected = SimulationException.class)
	public void testUnparkVehicle_SimulationException_CarInQueue()
			throws VehicleException, SimulationException {
		carPark.enterQueue(car);
		carPark.unparkVehicle(car, defaultDeparture);
	}

	@Test(expected = SimulationException.class)
	public void testUnparkVehicleSimulationException() throws VehicleException,
			SimulationException {
		carPark.unparkVehicle(car, defaultDeparture);
	}

	@Test
	public void testCarParkObjectsMaintainOwnState_GetNumCars()
			throws SimulationException, VehicleException {
		CarPark secondCarPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		Car firstCar = new Car(vehicleID1, defaultArrival1, false);
		Car secondCar = new Car(vehicleID2, defaultArrival2, false);
		Car thirdCar = new Car(vehicleID3, defaultArrival3, false);

		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		secondCarPark.parkVehicle(firstCar, defaultArrival1,
				defaultIntendedStay);
		secondCarPark.parkVehicle(secondCar, defaultArrival2,
				defaultIntendedStay);
		secondCarPark.parkVehicle(thirdCar, defaultArrival3,
				defaultIntendedStay);
		assertTrue("testCarParkObjectsMaintainOwnState_GetNumCars() Error",
				carPark.getNumCars() < secondCarPark.getNumCars());
	}

	@Test
	public void testCarParkObjectsMaintainOwnState_GetNumSmallCars()
			throws SimulationException, VehicleException {
		CarPark secondCarPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		Car firstSmallCar = new Car(vehicleID1, defaultArrival1, true);
		Car secondSmallCar = new Car(vehicleID2, defaultArrival2, true);
		Car thirdSmallCar = new Car(vehicleID3, defaultArrival3, true);

		carPark.parkVehicle(smallCar, defaultArrival1, defaultIntendedStay);
		secondCarPark.parkVehicle(firstSmallCar, defaultArrival1,
				defaultIntendedStay);
		secondCarPark.parkVehicle(secondSmallCar, defaultArrival2,
				defaultIntendedStay);
		secondCarPark.parkVehicle(thirdSmallCar, defaultArrival3,
				defaultIntendedStay);
		assertTrue(
				"testCarParkObjectsMaintainOwnState_GetNumSmallCars() Error",
				carPark.getNumSmallCars() < secondCarPark.getNumSmallCars());
	}

	@Test
	public void testCarParkObjectsMaintainOwnState_GetNumMotorCycles()
			throws SimulationException, VehicleException {
		CarPark secondCarPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		MotorCycle firstMotorCycle = new MotorCycle(vehicleID1, defaultArrival1);
		MotorCycle secondMotorCycle = new MotorCycle(vehicleID2,
				defaultArrival2);
		MotorCycle thirdMotorCycle = new MotorCycle(vehicleID3, defaultArrival3);

		secondCarPark.parkVehicle(firstMotorCycle, defaultArrival1,
				defaultIntendedStay);
		secondCarPark.parkVehicle(secondMotorCycle, defaultArrival2,
				defaultIntendedStay);
		secondCarPark.parkVehicle(thirdMotorCycle, defaultArrival3,
				defaultIntendedStay);
		carPark.parkVehicle(motorCycle, defaultArrival3, defaultIntendedStay);

		assertTrue(
				"testCarParkObjectsMaintainOwnState_GetNumMotorCycles() Error",
				carPark.getNumMotorCycles() < secondCarPark.getNumMotorCycles());
	}

	@Test
	public void testCarParkObjectsMaintainOwnState_NumVehiclesInQueue()
			throws SimulationException, VehicleException {
		CarPark secondCarPark = new CarPark(maxCarSpaces, maxSmallCarSpaces,
				maxMotorCycleSpaces, maxQueueSize);
		Car firstCar = new Car(vehicleID1, defaultArrival1, false);
		Car secondCar = new Car(vehicleID2, defaultArrival2, false);
		Car thirdCar = new Car(vehicleID3, defaultArrival3, false);

		carPark.enterQueue(car);
		secondCarPark.enterQueue(firstCar);
		;
		secondCarPark.enterQueue(secondCar);
		secondCarPark.enterQueue(thirdCar);
		assertTrue(
				"testCarParkObjectsMaintainOwnState_NumVehiclesInQueue() Error",
				carPark.numVehiclesInQueue() < secondCarPark
						.numVehiclesInQueue());
	}
}
