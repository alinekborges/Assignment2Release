/**
 * 
 * This file is part of the CarParkSimulator Project, written as 
 * part of the assessment for INB370, semester 1, 2014. 
 *
 * CarParkSimulator
 * asgn2CarParks 
 * 21/04/2014
 * 
 */
package asgn2CarParks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import asgn2Exceptions.SimulationException;
import asgn2Exceptions.VehicleException;
import asgn2Simulators.Constants;
import asgn2Simulators.Simulator;
import asgn2Vehicles.Car;
import asgn2Vehicles.MotorCycle;
import asgn2Vehicles.Vehicle;

/**
 * The CarPark class provides a range of facilities for working with a car park
 * in support of the simulator. In particular, it maintains a collection of
 * currently parked vehicles, a queue of vehicles wishing to enter the car park,
 * and an historical list of vehicles which have left or were never able to gain
 * entry.
 * 
 * The class maintains a wide variety of constraints on small cars, normal cars
 * and motorcycles and their access to the car park. See the method javadoc for
 * details.
 * 
 * The class relies heavily on the asgn2.Vehicle hierarchy, and provides a
 * series of reports used by the logger.
 * 
 * @author hogan
 * 
 */

public class CarPark {

	// car park variables
	private int maxCarSpaces;
	private int maxSmallCarSpaces;
	private int maxMotorCycleSpaces;
	private int maxQueueSize;

	// simulation variables
	// private boolean isFull;
	// private boolean isEmpty;
	private int count;

	private int numSmallCars;
	private int numCars;
	private int numMotorCycles;
	private int numDissatisfied;

	private String status;

	private List<Vehicle> archive;
	private List<Vehicle> queue;
	private List<Vehicle> carPark;

	/**
	 * CarPark constructor sets the basic size parameters. Uses default
	 * parameters
	 * 
	 * @author Aline Borges
	 */
	public CarPark() {
		this(Constants.DEFAULT_MAX_CAR_SPACES,
				Constants.DEFAULT_MAX_SMALL_CAR_SPACES,
				Constants.DEFAULT_MAX_MOTORCYCLE_SPACES,
				Constants.DEFAULT_MAX_QUEUE_SIZE);
	}

	/**
	 * CarPark constructor sets the basic size parameters.
	 * 
	 * @param maxCarSpaces
	 *            maximum number of spaces allocated to cars in the car park
	 * @param maxSmallCarSpaces
	 *            maximum number of spaces (a component of maxCarSpaces)
	 *            restricted to small cars
	 * @param maxMotorCycleSpaces
	 *            maximum number of spaces allocated to MotorCycles
	 * @param maxQueueSize
	 *            maximum number of vehicles allowed to queue
	 * @author Aline Borges
	 */
	public CarPark(int maxCarSpaces, int maxSmallCarSpaces,
			int maxMotorCycleSpaces, int maxQueueSize) {
		this.maxCarSpaces = maxCarSpaces;
		this.maxMotorCycleSpaces = maxMotorCycleSpaces;
		this.maxQueueSize = maxQueueSize;
		this.maxSmallCarSpaces = maxSmallCarSpaces;

		this.count = 0;
		this.numCars = 0;
		this.numMotorCycles = 0;
		this.numSmallCars = 0;
		this.numDissatisfied = 0;

		status = "";

		// create archive list
		this.archive = new ArrayList<Vehicle>();
		this.queue = new ArrayList<Vehicle>();
		this.carPark = new ArrayList<Vehicle>();
	}

	/**
	 * Archives vehicles exiting the car park after a successful stay. Includes
	 * transition via Vehicle.exitParkedState().
	 * 
	 * @param force
	 *            boolean forcing departure to clear car park
	 * @throws VehicleException
	 *             if vehicle to be archived is not in the correct state
	 * @throws SimulationException
	 *             if one or more departing vehicles are not in the car park
	 *             when operation applied
	 * @author Aline Borges
	 */
	public void archiveDepartingVehicles(int time, boolean force)
			throws VehicleException, SimulationException {
		// TODO

		if (force == true) {
			for (int i = 0; i < this.carPark.size(); i++) {
				Vehicle v = this.carPark.get(i);
				this.unparkVehicle(v, time);
				this.archiveNewVehicle(v);
				status += this.setVehicleMsg(v, "P", "A");
			}
		}

		else {
			List<Vehicle> toUnpark = new ArrayList<Vehicle>();
			for (int i = 0; i < this.carPark.size(); i++) {
				Vehicle v = this.carPark.get(i);
				int t = v.getDepartureTime();
				if (time >= v.getDepartureTime()) {
					toUnpark.add(v);
				}

			}

			for (Vehicle v : toUnpark) {
				this.unparkVehicle(v, time);
				this.archiveNewVehicle(v);
				status += this.setVehicleMsg(v, "P", "A");
			}

		}

	}

	/**
	 * Method to archive new vehicles that don't get parked or queued and are
	 * turned away
	 * 
	 * @param v
	 *            Vehicle to be archived
	 * @throws SimulationException
	 *             if vehicle is currently queued or parked
	 * @author Aline Borges
	 */
	public void archiveNewVehicle(Vehicle v) throws SimulationException {

		if (v.isParked() == true) {
			throw new SimulationException(
					"Vehicle is currently parked, it can't be archived");
		}
		if (v.isQueued() == true) {
			throw new SimulationException(
					"Vehicle is currently queued, it can't be archived");
		}

		this.archive.add(v);
		// status += this.setVehicleMsg(v, "", target)

	}

	/**
	 * Archive vehicles which have stayed in the queue too long
	 * 
	 * @param time
	 *            int holding current simulation time
	 * @throws VehicleException
	 *             if one or more vehicles not in the correct state or if timing
	 *             constraints are violated
	 * @author Aline Borges
	 */
	public void archiveQueueFailures(int time) throws VehicleException {
		status = "";
		// iterate from the end of the queue to the beggining.
		// If one vehicle isn't queued for too long, we can assume no other
		// vehicle is
		for (int i = this.queue.size() - 1; i >= 0; i++) {
			Vehicle v = this.queue.get(i);
			int timeQueued = time - v.getArrivalTime();
			if (timeQueued > Constants.MAXIMUM_QUEUE_TIME) {
				v.exitQueuedState(time);
				queue.remove(v);
				archive.add(v);
				this.numDissatisfied++;
				this.status += this.setVehicleMsg(v, "Q", "A");
			} else {
				break;
			}

		}

	}

	/**
	 * Simple status showing whether carPark is empty
	 * 
	 * @return true if car park empty, false otherwise
	 * @author Aline Borges
	 */
	public boolean carParkEmpty() {
		if (this.carPark.size() == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Simple status showing whether carPark is full
	 * 
	 * @return true if car park full, false otherwise
	 * @author Aline Borges
	 */
	public boolean carParkFull() {
		int totalSpaces = this.maxCarSpaces + this.maxMotorCycleSpaces
				+ this.maxSmallCarSpaces;
		if (this.carPark.size() >= totalSpaces) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to add vehicle successfully to the queue Precondition is a test
	 * that spaces are available Includes transition through
	 * Vehicle.enterQueuedState
	 * 
	 * @param v
	 *            Vehicle to be added
	 * @throws SimulationException
	 *             if queue is full
	 * @throws VehicleException
	 *             if vehicle not in the correct state
	 * @author Aline Borges
	 */
	public void enterQueue(Vehicle v) throws SimulationException,
			VehicleException {

		if (this.queueFull() == true) {
			throw new SimulationException(
					"Error trying to queue new vehicle. Queue is full");
		}

		v.enterQueuedState();
		this.queue.add(v);
	}

	/**
	 * Method to remove vehicle from the queue after which it will be parked or
	 * removed altogether. Includes transition through Vehicle.exitQueuedState.
	 * 
	 * @param v
	 *            Vehicle to be removed from the queue
	 * @param exitTime
	 *            int time at which vehicle exits queue
	 * @throws SimulationException
	 *             if vehicle is not in queue
	 * @throws VehicleException
	 *             if the vehicle is in an incorrect state or timing constraints
	 *             are violated
	 * @author Aline Borges
	 */
	public void exitQueue(Vehicle v, int exitTime) throws SimulationException,
			VehicleException {

		if (v.isQueued() == false) {
			throw new SimulationException(
					"Impossible to remove vehicle from the queue. Vehicle is not queued");
		}

		v.exitQueuedState(exitTime);
		queue.remove(v);

	}

	/**
	 * State dump intended for use in logging the final state of the carpark All
	 * spaces and queue positions should be empty and so we dump the archive
	 * 
	 * @return String containing dump of final carpark state
	 * @author Aline Borges
	 */
	public String finalState() {
		String str = "Vehicles Processed: count:" + this.count + ", logged: "
				+ this.archive.size() + "\nVehicle Record: \n";
		for (Vehicle v : this.archive) {
			str += v.toString() + "\n\n";
		}
		return str + "\n";
	}

	/**
	 * Simple getter for number of cars in the car park
	 * 
	 * @return number of cars in car park, including small cars
	 * @author Aline Borges
	 */
	public int getNumCars() {
		return this.numCars + this.numSmallCars;
	}

	/**
	 * Simple getter for number of motorcycles in the car park
	 * 
	 * @return number of MotorCycles in car park, including those occupying a
	 *         small car space
	 * @author Aline Borges
	 */
	public int getNumMotorCycles() {
		return this.numMotorCycles;
	}

	/**
	 * Simple getter for number of small cars in the car park
	 * 
	 * @return number of small cars in car park, including those not occupying a
	 *         small car space.
	 * @author Aline Borges
	 */
	public int getNumSmallCars() {
		return this.numSmallCars;
	}

	/**
	 * Method used to provide the current status of the car park. Uses private
	 * status String set whenever a transition occurs. Example follows (using
	 * high probability for car creation). At time 262, we have 276 vehicles
	 * existing, 91 in car park (P), 84 cars in car park (C), of which 14 are
	 * small (S), 7 MotorCycles in car park (M), 48 dissatisfied (D), 176
	 * archived (A), queue of size 9 (CCCCCCCCC), and on this iteration we have
	 * seen: car C go from Parked (P) to Archived (A), C go from queued (Q) to
	 * Parked (P), and small car S arrive (new N) and go straight into the car
	 * park<br>
	 * 262::276::P:91::C:84::S:14::M:7::D:48::A:176::Q:9CCCCCCCCC|C:P>A||C:Q>P||
	 * S:N>P|
	 * 
	 * @return String containing current state
	 * @author Aline Borges
	 */
	public String getStatus(int time) {
		String str = time + "::" + this.count + "::" + "P:"
				+ this.carPark.size() + "::" + "C:" + this.numCars + "::S:"
				+ this.numSmallCars + "::M:" + this.numMotorCycles + "::D:"
				+ this.numDissatisfied + "::A:" + this.archive.size() + "::Q:"
				+ this.queue.size();
		for (Vehicle v : this.queue) {
			if (v instanceof Car) {
				if (((Car) v).isSmall()) {
					str += "S";
				} else {
					str += "C";
				}
			} else {
				str += "M";
			}
		}
		/*
		 * str += "      "; str += "SpacesAvailable::C:"+this.getCarSpaces()
		 * +"::S:"+this.getSmallCarSpaces() +"::M:"+this.getMotorcycleSpaces();
		 */
		str += this.status;

		this.status = "";

		return str + "\n";
	}

	/**
	 * State dump intended for use in logging the initial state of the carpark.
	 * Mainly concerned with parameters.
	 * 
	 * @return String containing dump of initial carpark state
	 * @author Aline Borges
	 */
	public String initialState() {
		return "CarPark [maxCarSpaces: " + this.maxCarSpaces
				+ " maxSmallCarSpaces: " + this.maxSmallCarSpaces
				+ " maxMotorCycleSpaces: " + this.maxMotorCycleSpaces
				+ " maxQueueSize: " + this.maxQueueSize + "]";
	}

	/**
	 * Simple status showing number of vehicles in the queue
	 * 
	 * @return number of vehicles in the queue
	 * @author Aline Borges
	 */
	public int numVehiclesInQueue() {
		return this.queue.size();
	}

	/**
	 * Method to add vehicle successfully to the car park store. Precondition is
	 * a test that spaces are available. Includes transition via
	 * Vehicle.enterParkedState.
	 * 
	 * @param v
	 *            Vehicle to be added
	 * @param time
	 *            int holding current simulation time
	 * @param intendedDuration
	 *            int holding intended duration of stay
	 * @throws SimulationException
	 *             if no suitable spaces are available for parking
	 * @throws VehicleException
	 *             if vehicle not in the correct state or timing constraints are
	 *             violated
	 * @author Aline Borges
	 */
	public void parkVehicle(Vehicle v, int time, int intendedDuration)
			throws SimulationException, VehicleException {
		if (this.spacesAvailable(v) == false) {
			throw new SimulationException(
					"Error trying to park the vehicle, not enought suitable spaces");
		}

		v.enterParkedState(time, intendedDuration);
		this.carPark.add(v);

		if (v instanceof Car) {
			if (((Car) v).isSmall() == false) {
				this.numCars++;
			} else {
				this.numSmallCars++;
			}
		} else {
			this.numMotorCycles++;
		}
	}

	/**
	 * Silently process elements in the queue, whether empty or not. If
	 * possible, add them to the car park. Includes transition via
	 * exitQueuedState where appropriate Block when we reach the first element
	 * that can't be parked.
	 * 
	 * @param time
	 *            int holding current simulation time
	 * @throws SimulationException
	 *             if no suitable spaces available when parking attempted
	 * @throws VehicleException
	 *             if state is incorrect, or timing constraints are violated
	 * @author Aline Borges
	 */
	public void processQueue(int time, Simulator sim) throws VehicleException,
			SimulationException {

		List<Vehicle> vehiclesToPark = new ArrayList<Vehicle>();

		for (int i = 0; i < this.queue.size(); i++) {
			Vehicle v = this.queue.get(i);
			if (spacesAvailable(v) == true) {
				vehiclesToPark.add(v);
				if (v instanceof MotorCycle) {
					int a = 0;
				}
			} else {
				break;
			}
		}

		for (Vehicle v : vehiclesToPark) {
			if (spacesAvailable(v)) {
				exitQueue(v, time);
				parkVehicle(v, time, sim.setDuration());
				status += this.setVehicleMsg(v, "Q", "P");
			}

		}

	}

	/**
	 * Simple status showing whether queue is empty
	 * 
	 * @return true if queue empty, false otherwise
	 * @author Aline Borges
	 */
	public boolean queueEmpty() {
		if (this.queue.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Simple status showing whether queue is full
	 * 
	 * @return true if queue full, false otherwise
	 * @author Aline Borges
	 */
	public boolean queueFull() {
		if (this.queue.size() >= this.maxQueueSize) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method determines, given a vehicle of a particular type, whether there
	 * are spaces available for that type in the car park under the parking
	 * policy in the class header.
	 * 
	 * @param v
	 *            Vehicle to be stored.
	 * @return true if space available for v, false otherwise
	 * @author Aline Borges
	 */
	public boolean spacesAvailable(Vehicle v) {
		if (v instanceof Car) {
			if (((Car) v).isSmall() == false) {
				if (this.getCarSpaces() > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				if (this.getSmallCarSpaces() > 0) {
					return true;
				} else if (this.getCarSpaces() > 0) {
					return true;
				} else {
					return false;
				}
			}
		}
		// it's a motorcycle
		else {
			if (this.getMotorcycleSpaces() > 0) {
				return true;
			} else if (this.getSmallCarSpaces() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return null;
	}

	/**
	 * Method to try to create new vehicles (one trial per vehicle type per time
	 * point) and to then try to park or queue (or archive) any vehicles that
	 * are created
	 * 
	 * @param sim
	 *            Simulation object controlling vehicle creation
	 * @throws SimulationException
	 *             if no suitable spaces available when operation attempted
	 * @throws VehicleException
	 *             if vehicle creation violates constraints
	 * @author Aline Borges
	 */
	public void tryProcessNewVehicles(int time,Simulator sim) throws VehicleException, SimulationException {
	
		if (sim.newCarTrial() == true) {
			String id = "C" + count;
			Car v = new Car(id, time, sim.smallCarTrial());
			processNewVehicle(v, time, sim);
			count++;
		}

		if (sim.motorCycleTrial() == true) {
			String id = "MC" + count;
			MotorCycle v = new MotorCycle(id, time);
			processNewVehicle(v, time, sim);
			count++;
		}

	}

	/**
	 * Method to remove vehicle from the carpark. For symmetry with parkVehicle,
	 * include transition via Vehicle.exitParkedState. So vehicle should be in
	 * parked state prior to entry to this method.
	 * 
	 * @param v
	 *            Vehicle to be removed from the car park
	 * @throws VehicleException
	 *             if Vehicle is not parked, is in a queue, or violates timing
	 *             constraints
	 * @throws SimulationException
	 *             if vehicle is not in car park
	 * @author Aline Borges
	 */
	public void unparkVehicle(Vehicle v, int departureTime)
			throws VehicleException, SimulationException {
		if (this.carPark.contains(v) == false) {
			throw new SimulationException(
					"Error trying to unpark vehicle (it's not parked)");
		}

		v.exitParkedState(departureTime);
		this.carPark.remove(v);

		if (v instanceof Car) {
			if (((Car) v).isSmall() == false) {
				this.numCars--;
			} else {
				this.numSmallCars--;
			}
		} else {
			this.numMotorCycles--;
		}

	}

	/**
	 * Helper to set vehicle message for transitions
	 * 
	 * @param v
	 *            Vehicle making a transition (uses S,C,M)
	 * @param source
	 *            String holding starting state of vehicle (N,Q,P,A)
	 * @param target
	 *            String holding finishing state of vehicle (Q,P,A)
	 * @return String containing transition in the form:
	 *         |(S|C|M):(N|Q|P|A)>(Q|P|A)|
	 * @author Aline Borges
	 */
	private String setVehicleMsg(Vehicle v, String source, String target) {
		String str = "";
		if (v instanceof Car) {
			if (((Car) v).isSmall()) {
				str += "S";
			} else {
				str += "C";
			}
		} else {
			str += "M";
		}
		return "|" + str + ":" + source + ">" + target + "|";
	}

	private void processNewVehicle(Vehicle v, int time, Simulator sim)
			throws SimulationException, VehicleException {
		// TODO

		if (this.queueFull() == true) {
			this.archiveNewVehicle(v);
		} else {
			if (this.spacesAvailable(v)) {
				boolean a = this.queueEmpty();
				if (this.queueEmpty() == true) {
					int intendedDuration = sim.setDuration();
					status += this.setVehicleMsg(v, "N", "P");
					parkVehicle(v, time, intendedDuration);
				} else {
					if (this.queueFull() == false) {
						this.enterQueue(v);
						status += this.setVehicleMsg(v, "N", "Q");
					} else {
						status += this.setVehicleMsg(v, "N", "A");
						this.archiveNewVehicle(v);
					}
				}
			} else { // no spaces available for that car
				if (this.queueFull() == false) {
					this.enterQueue(v);
					status += this.setVehicleMsg(v, "N", "Q");
				} else {
					status += this.setVehicleMsg(v, "N", "A");
					this.archiveNewVehicle(v);
				}
			}
		}

	}

	/**
	 * Helper to get the number of still available small car spaces, considering
	 * that motorcycles can use this spaces
	 * 
	 * @return number of small car spaces still available
	 */
	private int getSmallCarSpaces() {

		// motorcycle is not full, so we don't need to worry
		if (this.getMotorcycleSpaces() != 0) {
			int spaces = this.maxSmallCarSpaces - this.numSmallCars;
			if (spaces < 0) {
				return 0;
			} else {
				return spaces;
			}
		} else {
			int overflow = this.numMotorCycles - this.maxMotorCycleSpaces;
			int spaces = this.maxSmallCarSpaces - this.numSmallCars - overflow;
			if (spaces < 0) {
				return 0;
			} else {
				return spaces;
			}
		}

	}

	/**
	 * Helper function to get the number of spaces still available for
	 * motorcycles considering the rules
	 * 
	 * @return number of motorcycle spaces still available
	 */
	private int getMotorcycleSpaces() {
		if (this.numMotorCycles >= this.maxMotorCycleSpaces) {
			return 0;
		} else {
			return this.maxMotorCycleSpaces - this.numMotorCycles;
		}
	}

	/**
	 * Helper functions to get the number of spaces still available for normal
	 * cars considering the given rules
	 * 
	 * @return number of car spaces still available
	 */
	private int getCarSpaces() {

		if (this.getSmallCarSpaces() != 0) {
			return this.maxCarSpaces - this.numCars;
		} else {
			// if the motorcycles are taking up some space in small cars
			if (this.getMotorcycleSpaces() == 0
					&& this.getNumMotorCycles() > this.maxMotorCycleSpaces) {
				int overflowMotorcycle = this.numMotorCycles
						- this.maxMotorCycleSpaces;
				int overflow = this.numSmallCars - this.maxSmallCarSpaces
						- overflowMotorcycle;
				int spaces = this.maxCarSpaces - this.numCars - overflow;
				if (spaces < 0) {
					return 0;
				} else {
					return spaces;
				}
			} else {
				int overflow = this.numSmallCars - this.maxSmallCarSpaces;
				int spaces = this.maxCarSpaces - this.numCars - overflow;
				if (spaces < 0) {
					return 0;
				} else {
					return spaces;
				}
			}

		}
	}

}
