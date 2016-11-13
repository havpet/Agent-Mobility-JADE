package no.hib.mod252.oblig3;

import jade.core.*;
import jade.core.behaviours.CyclicBehaviour;
import java.lang.Runtime;

public class MobileAgent extends Agent {
	
	private int _state = 0;
	private int mostAvailable = -1;
	private long availableResources = 0;
	private int location = 0;
	private boolean taskPerformed = false;
	private PlatformID[] _dests;
	
	public void setup() {	
		//Creating array of possible destinations
		_dests = new PlatformID[3];
		
		//Adds the wanted destinations. Have to be computers in the same network
		AID remote = new AID("amm@Laptop", AID.ISGUID);
		remote.addAddresses("http://DESKTOP-L0V7CP0:7778/acc");
		PlatformID destination = new PlatformID(remote);
		_dests[0] = destination;
		
		AID remote2 = new AID("amm@Desktop", AID.ISGUID);
		remote2.addAddresses("http://GeekWin:7778/acc");
		PlatformID destination2 =  new PlatformID(remote2);
		_dests[1] = destination2;
		
		AID remote3 = new AID("amm@Laptop2", AID.ISGUID);
		remote3.addAddresses("http://LAPTOP-93M2G80U:7778/acc");
		PlatformID destination3 =  new PlatformID(remote3);
		_dests[2] = destination3;
		
		System.out.println("Creating agent");
		
		//Adding behaviour so the moving can start
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				switch(_state) {
				
				case 0:
					_state++;
					//Agent starts at "host" computer
					System.out.println("Agent started");
					System.out.println("Moving to " + _dests[location].getName() + "..");
					myAgent.doMove(_dests[location]);
					break;
					
				case 1:
					//Agent is moved to first computer in _dests-array. 
					//Checks if it has more than max available resources
					//If there are more addresses in _dests, carry on
					_state++;
					System.out.println(">>Agent is here<<");
					if(Runtime.getRuntime().freeMemory() > availableResources) {
						availableResources = Runtime.getRuntime().freeMemory();
						mostAvailable = location;
					}
					location++;
					if(location < _dests.length) {	
						System.out.println("Moving to " + _dests[location].getName() + "..");
						myAgent.doMove(_dests[location]);
					}
					break;
					
				case 2:
					//Agent is moved to second computer in _dests-array. 
					//Does the same as in case 1
					_state++;
					System.out.println(">>Agent is here<<");
					if(Runtime.getRuntime().freeMemory() > availableResources) {
						availableResources = Runtime.getRuntime().freeMemory();
						mostAvailable = location;
					}
					location++;
					if(location < _dests.length) {	
						System.out.println("Moving to " + _dests[location].getName() + "..");
						myAgent.doMove(_dests[location]);
					}
					break;
					
				case 3:
					//Agent is now in the last case before deciding
					//Either it is in the same as in case 2, or in another one
					_state++;
					System.out.println(">>Agent is here<<");
					if(Runtime.getRuntime().freeMemory() > availableResources) {
						availableResources = Runtime.getRuntime().freeMemory();
						mostAvailable = location;
					}
					location++;
					if(location < _dests.length) {	
						System.out.println("Moving to " + _dests[location].getName() + "..");
						myAgent.doMove(_dests[location]);
					}
					break;
					
				case 4:
					//Agent is now deciding which computer had most RAM available
					//Transfers itself to the "best" computer
					_state++;
					if(location != mostAvailable) {
						System.out.println("Moving to " + _dests[mostAvailable].getName() + " because it won..");
						myAgent.doMove(_dests[mostAvailable]);
					}
					else {
						taskPerformed = true;
						System.out.println("Performing task..");
					}
					break;
					
				default:
					//If the task wasn't performed in last step because of movement, agent performs it here
					_state++;
					if(!taskPerformed) {
						taskPerformed = true;
						System.out.println(">>Agent is here<<");
						System.out.println("Performing task..");
					}
					myAgent.doDelete();
				}
			}
			
		});
		
	}
	
	

}
