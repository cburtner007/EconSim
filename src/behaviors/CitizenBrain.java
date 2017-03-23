package behaviors;

import Main.Citizen;


public class CitizenBrain extends Brain {
	Citizen cRef; //we gotta make decisions about ourself, so we gotta know ourself
	public CitizenBrain(Citizen c){
		this.cRef = c;
	}
	
	@Override
	public void behave() {
		// TODO Auto-generated method stub
		
	}

}
