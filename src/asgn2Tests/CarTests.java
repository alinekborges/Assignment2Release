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
import asgn2Vehicles.Car;


//testing commits from Aline's computer
/**
 * @author hogan
 *
 */
public class CarTests {
	
	
	Car car;

	private final String vehID = "AEG-2039";
	private final boolean isSmall = true;
	private final int arrivalTime = 100;
	private final int negativeArrivalTime = -1;
	private final int arrivalTimeZero = 0;
	/**
	 * @throws java.lang.Exception
	 * @author Aline Borges
	 */
	@Before
	public void setUp() throws Exception {
		this.car = new Car(this.vehID, this.arrivalTime, this.isSmall);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#toString()}.
	 * @author Aline Borges
	 */
	@Test 
	public void testToString() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testCarNegativeTime() throws VehicleException {
		this.car = new Car(vehID, negativeArrivalTime, isSmall);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testCarTimeZero() throws VehicleException {
		this.car = new Car(vehID, arrivalTimeZero, isSmall);
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#Car(java.lang.String, int, boolean)}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test (expected = VehicleException.class)
	public void testCarTimeNormal() throws VehicleException {
		this.car = new Car(vehID, arrivalTime, isSmall);
		assertTrue(true);
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmallTrue() throws VehicleException {
		this.car = new Car(vehID, arrivalTime, true);
		assertTrue( this.car.isSmall());
	}
	
	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @author Aline Borges
	 * @throws VehicleException 
	 */
	@Test
	public void testIsSmallFalse() throws VehicleException {
		this.car = new Car(vehID, arrivalTime, false);
		assertFalse(this.car.isSmall());
	}

}
