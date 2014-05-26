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
import org.junit.Test;

import asgn2CarParks.CarPark;
import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Simulators.Simulator;
import asgn2Vehicles.Car;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * @author Lucas
 * 
 */
public class CarParkTests {

	private static final int maxCarSpaces = 100;
	private static final int maxSmallCarSpaces = 20;
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
		assertFalse("CarParkEmpty() Error: Incorrect reading",
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
		assertTrue("CarParkEmpty() Error: Incorrect reading",
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
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
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
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		assertTrue("CarParkFull() Error", carPark.carParkFull());

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
		assertFalse("CarParkFull() Error", carPark.carParkFull());
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
	public void testGetNumCarsCarsComeAndGo() throws SimulationException,
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
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertEquals("testNumCarsFullCarPark() Error", maxCarSpaces
				+ maxSmallCarSpaces, carPark.getNumCars());
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
		motorCycle = new MotorCycle(vehicleID2, defaultArrival2);
		carPark.parkVehicle(motorCycle, defaultArrival2, defaultIntendedStay);
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
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		assertEquals("testGetNumSmallCars() Error", 1,
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
		assertEquals("testGetNumSmallCars() Error", 0,
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
	public void testGetNumSmallCarSpacesFull() throws SimulationException,
			VehicleException {
		for (int j = 1; j <= maxSmallCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		assertEquals("testGetNumSmallCarsFull() Error", maxSmallCarSpaces,
				carPark.getNumSmallCars());
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
		for (int j = 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		assertEquals("testGetNumSmallCarsFull() Error", maxSmallCarSpaces
				+ maxCarSpaces, carPark.getNumSmallCars());
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
	public void testNumVehiclesInQueueComeAndGo() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		carPark.enterQueue(smallCar);
		carPark.enterQueue(motorCycle);
		carPark.exitQueue(car, maxDefaultQueueDeparture);
		assertEquals("testNumVehiclesInQueue() Error", 2,
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
		// this test
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		for (int i = maxSmallCarSpaces + maxCarSpaces + 1; i <= maxSmallCarSpaces
				+ maxCarSpaces + maxQueueSize; i++) {
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
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces, false);
		carPark.parkVehicle(car, maxSmallCarSpaces + maxCarSpaces,
				defaultIntendedStay);
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
		motorCycle = new MotorCycle(vehicleID1, maxMotorCycleSpaces);
		carPark.parkVehicle(motorCycle, maxMotorCycleSpaces,
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
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces, true);
		carPark.parkVehicle(smallCar, maxSmallCarSpaces + maxCarSpaces,
				defaultIntendedStay);
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
		for (int i = 1; i <= maxCarSpaces - 1; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces
				- 1; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces - 1; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		car = new Car(vehicleID1, maxCarSpaces - 1, false);
		smallCar = new Car(vehicleID2, maxSmallCarSpaces + maxCarSpaces - 1,
				true);
		motorCycle = new MotorCycle(vehicleID3, maxMotorCycleSpaces - 1);
		carPark.parkVehicle(motorCycle, maxMotorCycleSpaces - 1,
				defaultIntendedStay);
		carPark.parkVehicle(smallCar, maxSmallCarSpaces + maxCarSpaces - 1,
				defaultIntendedStay);
		carPark.parkVehicle(car, maxCarSpaces - 1, defaultIntendedStay);
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
	public void testParkVehicleNoMotorCycleSpacesOneSmallCarSpace()
			throws SimulationException, VehicleException {
		for (int j = 1; j <= maxSmallCarSpaces - 1; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxMotorCycleSpaces);
		carPark.parkVehicle(motorCycle, maxMotorCycleSpaces,
				defaultIntendedStay);
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
	public void testParkVehicleNoSmallSpacesOneNormalCarSpace()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxCarSpaces - 1; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces, true);
		carPark.parkVehicle(smallCar, maxSmallCarSpaces + maxCarSpaces,
				defaultIntendedStay);
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
	public void testProcessQueueQueueIsEmpty() throws SimulationException,
			VehicleException {
		simulator = new Simulator();
		carPark.processQueue(defaultArrival1, simulator);
		assertTrue("testProcessQueueQueueIsEmpty() Error", carPark.queueEmpty());
	}

	// Queue is not full & Spaces For All
	@Test
	public void testProcessQueueQueueIsNotFullSpacesForAll()
			throws VehicleException, SimulationException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces - 2; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces
				- 2; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces - 2; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertTrue("testProcessQueueQueueIsNotFullSpacesForAll() Error",
				carPark.queueEmpty());
	}

	// Queue is not full & Spaces for some
	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsNormalCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCarNormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCarNormalCarSpaceAvailable() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycle() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycleSmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycle() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCarNormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCarNormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 6, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycleSmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycleSmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());

	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCarNormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCarNormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycleSmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 4, simulator);
		assertEquals(
				"testProcessQueueQueueIsNotFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycleSmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	// Something else

	@Test
	public void testProcessQueueQueueIsNotFullSpacesForNone()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 7, simulator);
		assertEquals("testProcessQueueQueueIsNotFullSpacesForNone() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	// Queue is full

	@Test
	public void testProcessQueueQueueIsFullSpacesForAll()
			throws VehicleException, SimulationException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces - 3; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces
				- 3; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces - 4; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertTrue("testProcessQueueQueueIsFullSpacesForAll() Error",
				carPark.queueEmpty());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsNormalCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCar() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCarNormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsSmallCarNormalCarSpaceAvailable() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7,
				true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycle() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycleSmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7,
				true);
		carPark.enterQueue(smallCar);

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForFirstButNotLastFirstIsMotorCycle() Error",
				queueSize - 1, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCarNormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsSmallCarNormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10,
				true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycleSmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForMiddleButNotFirstAndNotLastMiddleIsMotorCycleSmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsNormalCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsNormalCar() Error",
				queueSize, carPark.numVehiclesInQueue());

	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCar()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10,
				true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCar() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCarNormalCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i < maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 1);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 2);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 4);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 5);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 7);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 8);
		carPark.enterQueue(motorCycle);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10,
				true);
		carPark.enterQueue(smallCar);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsSmallCarNormalCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycle()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k < maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7,
				true);
		carPark.enterQueue(smallCar);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycle() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	@Test
	public void testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycleSmallCarSpaceAvailable()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j < maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 3, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 6, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8, false);
		carPark.enterQueue(car);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 9, false);
		carPark.enterQueue(car);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 10);
		carPark.enterQueue(motorCycle);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals(
				"testProcessQueueQueueIsFullSpacesForSomeSpaceForLastButNotFirstLastIsMotorCycleSmallCarSpaceAvailable() Error",
				queueSize, carPark.numVehiclesInQueue());
	}

	// Something else

	@Test
	public void testProcessQueueQueueIsFullSpacesForNone()
			throws SimulationException, VehicleException {
		simulator = new Simulator();

		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}

		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 1, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 2,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 3);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 4, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 5,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 6);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 7, false);
		carPark.enterQueue(car);
		smallCar = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 8,
				true);
		carPark.enterQueue(smallCar);
		motorCycle = new MotorCycle(vehicleID1, maxSmallCarSpaces
				+ maxCarSpaces + 9);
		carPark.enterQueue(motorCycle);
		car = new Car(vehicleID1, maxSmallCarSpaces + maxCarSpaces + 10, false);
		carPark.enterQueue(car);

		int queueSize = carPark.numVehiclesInQueue();
		carPark.processQueue(maxSmallCarSpaces + maxCarSpaces + 11, simulator);
		assertEquals("testProcessQueueQueueIsFullSpacesForNone() Error",
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
	public void testQueueEmptyFullQueue() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize - 2; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		carPark.enterQueue(smallCar);
		carPark.enterQueue(motorCycle);
		assertFalse("testQueueEmptyFull() Error", carPark.queueEmpty());
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

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}. Testing the
	 * limit of the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueFullAlmostFull() throws SimulationException,
			VehicleException {
		for (int i = 1; i <= maxQueueSize - 1; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.enterQueue(car);
		}
		assertFalse("testQueueFull() Error", carPark.queueEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}. Testing the
	 * lower limit of the queue.
	 * 
	 * @throws SimulationException
	 * @throws VehicleException
	 */
	@Test
	public void testQueueFullAlmostEmpty() throws SimulationException,
			VehicleException {
		carPark.enterQueue(car);
		assertFalse("testQueueFull() Error", carPark.queueEmpty());
	}

	/**
	 * Test method for {@link asgn2CarParks.CarPark#queueFull()}. The queue is
	 * empty.
	 */
	@Test
	public void testQueueFullEmpty() {
		assertFalse("testQueueFull() Error", carPark.queueFull());
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
	public void testSpacesAvailableSmallCar() {
		assertTrue("testSpacesAvailable() Error",
				carPark.spacesAvailable(smallCar));
	}

	/**
	 * Test method for
	 * {@link asgn2CarParks.CarPark#spacesAvailable(asgn2Vehicles.Vehicle)}.
	 * testing for motorcycles.
	 */
	@Test
	public void testSpacesAvailableMotorCycle() {
		assertTrue("testSpacesAvailable() Error",
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
	public void testSpacesAvailableOneParkedCar() throws SimulationException,
			VehicleException {
		carPark.parkVehicle(car, defaultArrival1, defaultIntendedStay);
		assertTrue("testSpacesAvailableOneParkedCar() Error",
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
	public void testSpacesAvailableSmallCarOneParkedSmallCar()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(smallCar, defaultArrival2, defaultIntendedStay);
		assertTrue("testSpacesAvailableSmallCarOneParkedSmallCar() Error",
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
	public void testSpacesAvailableMotorCycleOneParkedMotorCycle()
			throws SimulationException, VehicleException {
		carPark.parkVehicle(motorCycle, defaultArrival3, defaultIntendedStay);
		assertTrue("testSpacesAvailableMotorCycleOneParkedMotorCycle() Error",
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
	public void testSpacesAvailableNormalCarSpacesAlmostFull()
			throws VehicleException, SimulationException {
		for (int i = 1; i <= maxCarSpaces - 1; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertTrue("testSpacesAvailableNormalCarSpacesAlmostFull() Error",
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
	public void testSpacesAvailableSmallCarSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces - 1; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertTrue("testSpacesAvailableSmallCarSpacesAlmostFull() Error",
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
	public void testSpacesAvailableMotorCycleSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxMotorCycleSpaces - 1; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		assertTrue("testSpacesAvailableSmallCarSpacesAlmostFull() Error",
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
	public void testSpacesAvailableNormalCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailableNormalCarSpacesFull() Error",
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
	public void testSpacesAvailableSmallCarSpacesFullAvailableNormalCarSpaces()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailableSmallCarSpacesFullAvailableNormalCarSpaces() Error",
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
	public void testSpacesAvailableMotorCycleSpacesFullAvailableSmallCarSpaces()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxMotorCycleSpaces; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailableMotorCycleSpacesFullAvailableSmallCarSpaces() Error",
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
	public void testSpacesAvailableSmallCarSpacesFullNormalCarSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		for (int i = maxSmallCarSpaces + 1; i <= maxCarSpaces
				+ maxSmallCarSpaces - 1; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailableSmallCarSpacesFullNormalCarSpacesAlmostFull() Error",
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
	public void testSpacesAvailableMotorCycleSpacesFullSmallCarSpacesAlmostFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxMotorCycleSpaces; i++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(i)), i);
			carPark.parkVehicle(motorCycle, i, defaultIntendedStay);
		}
		for (int i = 1; i <= maxSmallCarSpaces - 1; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		assertTrue(
				"testSpacesAvailableMotorCycleSpacesFullSmallCarSpacesAlmostFull() Error",
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
	public void testSpacesAvailableSmallandNormalCarSpacesFull()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxSmallCarSpaces; i++) {
			smallCar = new Car((vehicleID2 + Integer.toString(i)), i, true);
			carPark.parkVehicle(smallCar, i, defaultIntendedStay);
		}
		for (int i = maxSmallCarSpaces + 1; i <= maxCarSpaces
				+ maxSmallCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailableSmallandNormalCarSpacesFull() Error",
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
	public void testSpacesAvailableMotorCycleAndSmallCarSpacesFull()
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
				"testSpacesAvailableMotorCycleAndSmallCarSpacesFull() Error",
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
	public void testSpacesAvailableAllFullCarTriesToPark()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailableAllFullCarTriesToPark() Error",
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
	public void testSpacesAvailableAllFullSmallCarTriesToPark()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailableAllFullSmallCarTriesToPark() Error",
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
	public void testSpacesAvailableAllFullMotorCycleTriesToPark()
			throws SimulationException, VehicleException {
		for (int i = 1; i <= maxCarSpaces; i++) {
			car = new Car((vehicleID1 + Integer.toString(i)), i, false);
			carPark.parkVehicle(car, i, defaultIntendedStay);
		}
		for (int j = maxCarSpaces + 1; j <= maxSmallCarSpaces + maxCarSpaces; j++) {
			smallCar = new Car((vehicleID2 + Integer.toString(j)), j, true);
			carPark.parkVehicle(smallCar, j, defaultIntendedStay);
		}
		for (int k = 1; k <= maxMotorCycleSpaces; k++) {
			motorCycle = new MotorCycle((vehicleID3 + Integer.toString(k)), k);
			carPark.parkVehicle(motorCycle, k, defaultIntendedStay);
		}
		assertFalse("testSpacesAvailableAllFullMotorCycleTriesToPark() Error",
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
	 */
	@Test
	public void testTryProcessNewVehicles() {
		fail("Not yet implemented"); // TODO
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
	public void testUnparkVehicleSimulationException() {
		// TODO
	}
}
