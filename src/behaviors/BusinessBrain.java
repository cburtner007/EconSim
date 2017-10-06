package behaviors;

import Main.Business;

public class BusinessBrain extends Brain{
	private Business theBusiness;
	private int operatingLevel = 0;
	public BusinessBrain(Business b){
		this.theBusiness = b;
	}
	
	@Override
	public void behave() {
		// TODO Auto-generated method stub
		
	}
	
	private void setPriceToHire(){
		
	}

	
	/*
	 * What is the rate needed to pay in order to hire people?
	 * Based on that, how many can we hire and keep for X amount of time assuming no income?
	 * Do we need to fire anyone so we can survive for Y amount of time, assuming no income?
	 * Repeat? 
	 */
}
