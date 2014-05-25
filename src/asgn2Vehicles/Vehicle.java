/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2Vehicles 
 * 19/04/2014
 * 
 */
package asgn2Vehicles;

import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;

/**
 * Vehicle is an abstract class specifying the basic state of a vehicle and the
 * methods used to set and access that state. A vehicle is created upon arrival,
 * at which point it must either enter the car park to take a vacant space or
 * become part of the queue. If the queue is full, then the vehicle must leave
 * and never enters the car park. The vehicle cannot be both parked and queued
 * at once and both the constructor and the parking and queuing state transition
 * methods must respect this constraint.
 * 
 * Vehicles are created in a neutral state. If the vehicle is unable to park or
 * queue, then no changes are needed if the vehicle leaves the carpark
 * immediately. Vehicles that remain and can't park enter a queued state via
 * {@link #enterQueuedState() enterQueuedState} and leave the queued state via
 * {@link #exitQueuedState(int) exitQueuedState}. Note that an exception is
 * thrown if an attempt is made to join a queue when the vehicle is already in
 * the queued state, or to leave a queue when it is not.
 * 
 * Vehicles are parked using the {@link #enterParkedState(int, int)
 * enterParkedState} method and depart using {@link #exitParkedState(int)
 * exitParkedState}
 * 
 * Note again that exceptions are thrown if the state is inappropriate: vehicles
 * cannot be parked or exit the car park from a queued state.
 * 
 * The method javadoc below indicates the constraints on the time and other
 * parameters. Other time parameters may vary from simulation to simulation and
 * so are not constrained here.
 * 
 * @author hogan
 * 
 */
public abstract class Vehicle {

	private String vehID;
	private int arrivalTime;
	private String state;
	private int parkingTime;
	private int intendedDuration;
	private int departureTime;
	private int exitQueueTime;
	private boolean isSatisfied;

	private static final String newState = "N";
	private static final String queuedState = "Q";
	private static final String parkedState = "P";
	private static final String archivedState = "A";

	/**
	 * Vehicle Constructor
	 * 
	 * @param vehID
	 *            String identification number or plate of the vehicle
	 * @param arrivalTime
	 *            int time (minutes) at which the vehicle arrives and is either
	 *            queued, given entry to the car park or forced to leave
	 * @throws VehicleException
	 *             if arrivalTime is <= 0
	 */
	public Vehicle(String vehID, int arrivalTime) throws VehicleException {
		if (arrivalTime <= 0) {
			throw new VehicleException(
					"Vehicle(): Vehicle arrived before time = 1");
		} else {
			this.vehID = vehID;
			this.arrivalTime = arrivalTime;
			this.state = newState;
			this.departureTime = 0;
			this.parkingTime = 0;
			this.exitQueueTime = 0;
			this.isSatisfied = false;
		}
	}

	/**
	 * Transition vehicle to parked state (mutator) Parking starts on arrival or
	 * on exit from the queue, but time is set here
	 * 
	 * @param parkingTime
	 *            int time (minutes) at which the vehicle was able to park
	 * @param intendedDuration
	 *            int time (minutes) for which the vehicle is intended to remain
	 *            in the car park. Note that the parkingTime + intendedDuration
	 *            yields the departureTime
	 * @throws VehicleException
	 *             if the vehicle is already in a parked or queued state, if
	 *             parkingTime < 0, or if intendedDuration is less than the
	 *             minimum prescribed in asgnSimulators.Constants
	 */
	public void enterParkedState(int parkingTime, int intendedDuration)
			throws VehicleException {
		if (parkingTime <= 0) {
			throw new VehicleException(
					"enterParkedState(): Vehicle arrived before time = 1");
		} else {
			if (this.state == parkedState || this.state == queuedState) {
				throw new VehicleException(
						"enterParkedState(): Vehicle in incorrect state");
			} else {
				if (intendedDuration < Constants.MINIMUM_STAY) {
					throw new VehicleException(
							"enterParkedState(): Intended duration is less than the minimum prescribed in asgnSimulators.Constants");
				} else {
					this.state = parkedState;
					this.parkingTime = parkingTime;
					this.intendedDuration = intendedDuration;
					this.departureTime = parkingTime + intendedDuration;
				}
			}
		}
	}

	/**
	 * Transition vehicle to queued state (mutator) Queuing formally starts on
	 * arrival and ceases with a call to {@link #exitQueuedState(int)
	 * exitQueuedState}
	 * 
	 * @throws VehicleException
	 *             if the vehicle is already in a queued or parked state
	 */
	public void enterQueuedState() throws VehicleException {
		if (this.state == queuedState || this.state == parkedState) {
			throw new VehicleException(
					"enterQueuedState(): Vehicle in incorrect state");
		} else {
			this.state = queuedState;
		}
	}

	/**
	 * Transition vehicle from parked state (mutator)
	 * 
	 * @param departureTime
	 *            int holding the actual departure time
	 * @throws VehicleException
	 *             if the vehicle is not in a parked state, is in a queued state
	 *             or if the revised departureTime < parkingTime
	 */
	public void exitParkedState(int departureTime) throws VehicleException {
		if (this.state != parkedState) {
			throw new VehicleException(
					"exitParkedState(): Vehicle in the incorrect state");
		} else {
			if (departureTime < this.parkingTime) {
				throw new VehicleException(
						"exitParkedState(): Departure time error");
			} else {
				this.state = archivedState;
				this.departureTime = departureTime;
			}
		}
	}

	/**
	 * Transition vehicle from queued state (mutator) Queuing formally starts on
	 * arrival with a call to {@link #enterQueuedState() enterQueuedState} Here
	 * we exit and set the time at which the vehicle left the queue
	 * 
	 * @param exitTime
	 *            int holding the time at which the vehicle left the queue
	 * @throws VehicleException
	 *             if the vehicle is in a parked state or not in a queued state,
	 *             or if exitTime is not later than arrivalTime for this vehicle
	 */
	public void exitQueuedState(int exitTime) throws VehicleException {
		if (this.state != queuedState) {
			throw new VehicleException(
					"exitQueuedState(): Vehicle in incorrect state");
		} else {
			if (exitTime < this.arrivalTime) {
				throw new VehicleException(
						"exitQueuedState(): Vehicle exit queue time error");
			} else {
				this.exitQueueTime = exitTime;
				this.state = newState;
			}
		}
	}

	/**
	 * Simple getter for the arrival time
	 * 
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return this.arrivalTime;
	}

	/**
	 * Simple getter for the departure time from the car park Note: result may
	 * be 0 before parking, show intended departure time while parked; and
	 * actual when archived
	 * 
	 * @return the departureTime
	 */
	public int getDepartureTime() {
		if (this.departureTime == 0) {
			return 0;
		} else {
			if (this.state == parkedState || this.state == archivedState) {
				return this.departureTime;
			} else {
				return 0;
			}
		}
	}

	/**
	 * Simple getter for the parking time Note: result may be 0 before parking
	 * 
	 * @return the parkingTime
	 */
	public int getParkingTime() {
		if (this.parkingTime == 0) {
			return 0;
		} else {
			return this.parkingTime;
		}
	}

	/**
	 * Simple getter for the vehicle ID
	 * 
	 * @return the vehID
	 */
	public String getVehID() {
		return this.vehID;
	}

	/**
	 * Boolean status indicating whether vehicle is currently parked
	 * 
	 * @return true if the vehicle is in a parked state; false otherwise
	 */
	public boolean isParked() {
		if (this.state == parkedState) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Boolean status indicating whether vehicle is currently queued
	 * 
	 * @return true if vehicle is in a queued state, false otherwise
	 */
	public boolean isQueued() {
		if (this.state == queuedState) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Boolean status indicating whether customer is satisfied or not Satisfied
	 * if they park; dissatisfied if turned away, or queuing for too long Note
	 * that calls to this method may not reflect final status
	 * 
	 * @return true if satisfied, false if never in parked state or if queuing
	 *         time exceeds max allowable
	 */
	public boolean isSatisfied() {
		// TODO
		if (wasParked()) {
			// parked
			return true;
		} else {
			// Not parked
			if (!wasParked() && !wasQueued()) {
				// turned away
				return false;
			} else {
				// was queued
				if (!wasParked() && isQueued()) {
					// waiting patiently in the queue
					return true;
				} else {
					if (this.exitQueueTime > 0 && this.parkingTime == 0) {
						// stayed too long in the queue
						return false;
					}
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Vehicle vehID: ");
		stringBuilder.append(this.vehID);
		stringBuilder.append("\n");
		stringBuilder.append("Arrival Time: ");
		stringBuilder.append(this.arrivalTime);
		stringBuilder.append("\n");
		if (wasQueued()) {
			stringBuilder.append("Exit from Queue: ");
			stringBuilder.append(this.exitQueueTime);
			stringBuilder.append("\n");
			stringBuilder.append("Queuing Time: ");
			stringBuilder.append(this.arrivalTime - this.exitQueueTime);
		} else {
			stringBuilder.append("Vehicle was not queued");
		}
		stringBuilder.append("\n");
		if (wasParked()) {
			stringBuilder.append("Entry to Car Park: ");
			stringBuilder.append(this.parkingTime);
			stringBuilder.append("\n");
			stringBuilder.append("Exit from Car Park: ");
			stringBuilder.append(this.departureTime);
			stringBuilder.append("\n");
			stringBuilder.append("Parking Time: ");
			stringBuilder.append(this.departureTime - this.parkingTime);
		} else {
			stringBuilder.append("Vehicle was not parked");
		}
		stringBuilder.append("\n");
		if (isSatisfied) {
			stringBuilder.append("Customer was satisfied");
		} else {
			stringBuilder.append("Customer was not satisfied");
		}
		stringBuilder.append("\n");
		if (this.vehID.startsWith("C")) {
			stringBuilder.append("Car cannot use small parking space");
		}
		stringBuilder.append("\n");

		/*
		 * Vehicle vehID: C9 Arrival Time: 9 Vehicle was not queued Entry to Car
		 * Park: 9 Exit from Car Park: 124 Parking Time: 115 Customer was
		 * satisfied Car cannot use small parking space
		 */return stringBuilder.toString();
	}

	/**
	 * Boolean status indicating whether vehicle was ever parked Will return
	 * false for vehicles in queue or turned away
	 * 
	 * @return true if vehicle was or is in a parked state, false otherwise
	 */
	public boolean wasParked() {
		if (this.parkingTime == 0) {
			return false;
		} else {
			if (this.state == parkedState) {
				return true;
			} else {
				return true;
			}
			// TODO
		}
	}

	/**
	 * Boolean status indicating whether vehicle was ever queued
	 * 
	 * @return true if vehicle was or is in a queued state, false otherwise
	 */
	public boolean wasQueued() {
		if (this.exitQueueTime > 0) {
			return true;
		} else {
			if (this.state == queuedState) {
				return true;
			} else {
				return false;
			}
		}
	}
}
