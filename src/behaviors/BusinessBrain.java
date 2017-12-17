package behaviors;

import Main.Business;
import Main.Job;

public class BusinessBrain extends Brain{
	private Business theBusiness;
	
	private int rateToHire = 0;
	private int operatingLevel = 0;
	private int hiringNeed = 0; // 0 = no need, 1 is some need, 4 is now. 
	public BusinessBrain(Business b){
		this.theBusiness = b;
	}
	
	@Override
	public void behave() {
		// TODO Auto-generated method stub
		
	}
	
	private void setPriceToHire(){
		if(hiringNeed == 0){
			rateToHire = 0;
		}else if(hiringNeed == 1){
			double stdDev = theBusiness.getCityMarket().getJobStats().getStandardDeviation();
			double mean = theBusiness.getCityMarket().getJobStats().getMean();
			rateToHire = (int) (mean - stdDev);
		}else if(hiringNeed == 2){
			rateToHire = (int)theBusiness.getCityMarket().getJobStats().getMean();
		}else if(hiringNeed == 3){
			double stdDev = theBusiness.getCityMarket().getJobStats().getStandardDeviation();
			double mean = theBusiness.getCityMarket().getJobStats().getMean();
			rateToHire = (int) (mean + stdDev);
		}else if(hiringNeed == 4){
			double stdDev = theBusiness.getCityMarket().getJobStats().getStandardDeviation();
			double mean = theBusiness.getCityMarket().getJobStats().getMean();
			rateToHire = (int) (mean + 2 * stdDev);
		}
	}
		
	private int currentPayPerTick(){
		int returnPay = 0;
		
		for(Job j : theBusiness.getJobs()){
			returnPay += j.getRate();
		}
		
		return returnPay;
	}
	
	//Can set to a negative number
	private void setNumberToHire(){
		/*
		 * How much money do we have?
		 * Can we last for X ticks before folding - assuming no income? 
		 * Fire until we can survive for X ticks, or hire until we can just barely survive for X ticks
		 */
	}
	
	/*
	 * Logic for firing
	 */
	
	/*
	 * logic for hiring
	 */

}
