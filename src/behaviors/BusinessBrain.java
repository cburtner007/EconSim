package behaviors;

import Main.Business;
import Main.Job;
import Main.JobOffer;

public class BusinessBrain extends Brain{
	private Business theBusiness;
	
	private JobOffer currentOffer;
	
	private boolean isHiring = false;
	private int hiringLength = 0;
	private boolean losingWorkers = false;
	
	private int employeesLastTick = 0;
	private int employeesThisTick = 0;
	
	private int rateToHire = 0;
	private int operatingLevel = 0;
	private int numToHire = 0;
	private int hiringNeed = 0; // 0 = no need, 1 is some need, 4 is now. 
	public BusinessBrain(Business b){
		this.theBusiness = b;
	}
	
	@Override
	public void behave() {
		// TODO Auto-generated method stub
		
	}
	
	private void monitorState()
	{
		
	}
	
	private void setHiringNeed()
	{
		boolean underCapacity = (theBusiness.getCurrentWorkerCount() < theBusiness.getMaxWorkerCount());
		if (hiringLength == 0 && underCapacity && !losingWorkers)
		{
			hiringNeed = 1;	//Start off slow.	
		}
		else if (hiringLength == 8)
		{
			hiringNeed = 2;
		}
		else if (hiringLength == 16)
		{
			hiringNeed = 3;
		}
		else if (hiringLength == 24)
		{
			hiringNeed = 4;
		}
		
		if (losingWorkers && (hiringNeed < 4))
		{
			hiringNeed++;
		}
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
		int goldOnHand = theBusiness.getGold();
		int goldSpentPerTick = theBusiness.getJobs().size() * rateToHire;	//An overestimate
		int ticksToSurviveFor = 5; //Arbitrary. TO-DO: Use constant. TO-DO: Have this determined by NPC's willingness to take risks?
		
		int surplus = goldOnHand - (ticksToSurviveFor * goldSpentPerTick);
		numToHire = surplus/rateToHire;	//Integer division. Should floor. 
		
	}
	
	private void hireOrFire()
	{
		if (numToHire > 0)
		{
			currentOffer = new JobOffer(theBusiness, rateToHire, numToHire, theBusiness.getCityMarket());
			theBusiness.getCityMarket().postJobOffer(currentOffer);
			theBusiness.setAllRates(rateToHire); //Change rates of all current hires - even though we've only made an offer
		}
		else if (numToHire < 0)
		{
			for(Job j : theBusiness.getJobs())
			{
				j.terminate();
				numToHire++;
				//Only fire for so long
				if (numToHire == 0)
				{
					break;
				}
			}
			theBusiness.setAllRates(rateToHire);	//Change all rates, since this is what we fired based on
		}
		//
		else
		{
			theBusiness.getCityMarket().removeJobOffer(currentOffer);//remove currentOffer
		}
	}
}
