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
		_dests = new PlatformID[2];
		AID remote = new AID("amm@Laptop", AID.ISGUID);
		remote.addAddresses("http://DESKTOP-L0V7CP0:7778/acc");
		PlatformID destination = new PlatformID(remote);
		_dests[0] = destination;
		
		AID remote2 = new AID("amm@Desktop", AID.ISGUID);
		remote2.addAddresses("http://GeekWin:7778/acc");
		PlatformID destination2 =  new PlatformID(remote2);
		_dests[1] = destination2;
		
		addBehaviour(new CyclicBehaviour(this) {
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				switch(_state) {
				case 0:
					_state++;
					location = 0;
					System.out.println("**Agent is here**");
					if(Runtime.getRuntime().freeMemory() > availableResources) {
						availableResources = Runtime.getRuntime().freeMemory();
						mostAvailable = location;
					}
					System.out.println("Moving to " + _dests[0].getName() + "..");
					myAgent.doMove(_dests[0]);
					break;
				case 1:
					_state++;
					location = 1;
					System.out.println("**Agent is here**");
					if(Runtime.getRuntime().freeMemory() > availableResources) {
						availableResources = Runtime.getRuntime().freeMemory();
						mostAvailable = location;
					}
					System.out.println("Moving to " + _dests[1].getName() + "..");
					myAgent.doMove(_dests[1]);
					break;
				case 2:
					if(location != mostAvailable) {
						myAgent.doMove(_dests[mostAvailable]);
					}
					else {
						taskPerformed = true;
						System.out.println("**Agent is here**");
						System.out.println("Performing task..");
					}
				default:
					if(!taskPerformed) {
						taskPerformed = true;
						System.out.println("**Agent is here**");
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
