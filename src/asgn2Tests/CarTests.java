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
	/**
	 * @throws java.lang.Exception
	 * @author Aline Borges
	 */
	@Before
	public void setUp() throws Exception {
		this.car = new Car(this.vehID, this.arrivalTime, this.isSmall);
	}

	/**
	 * @throws java.lang.Exception
	 * @author Aline Borges
	 */
	@After
	public void tearDown() throws Exception {
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
	 */
	@Test
	public void testCar() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link asgn2Vehicles.Car#isSmall()}.
	 * @author Aline Borges
	 */
	@Test
	public void testIsSmall() {
		assertEquals(true, this.car.isSmall());
	}

}
