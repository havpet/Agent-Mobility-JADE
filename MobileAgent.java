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
		_dests = new PlatformID[3];
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
		
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				switch(_state) {
				
				case 0:
					_state++;
					System.out.println("Agent started");
					System.out.println("Moving to " + _dests[location].getName() + "..");
					myAgent.doMove(_dests[location]);
					break;
					
				case 1:
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
					_state++;
					System.out.println(">>Agent is here<<");
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
	
	public void beforeMove() {
	}
	
	public void afterMove() {
    }
	
	

}
