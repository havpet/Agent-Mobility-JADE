package no.hib.mod252.oblig3;

import jade.core.*;

public class MobileAgent extends Agent {
	
	public void setup() {		
		AID remote = new AID("ams@127.0.0.1:1099/JADE", AID.ISGUID);
		remote.addAddresses("http://GeekWin:7778/acc");
		
		PlatformID destination = new PlatformID(remote);
		
		doMove(destination);
		
		
	}
	
	

}
