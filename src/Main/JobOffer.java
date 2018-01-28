package Main;

public class JobOffer {

	Business businessHiring;
	Marketplace owningMarket;
	int numberLeftToHire;
	int pricePerLabor;
	
	public JobOffer (Business b, int nLeftToHire, Marketplace m){
		businessHiring = b;
		pricePerLabor = b.getGoldPerLabor();
		numberLeftToHire = nLeftToHire;
		owningMarket = m;
	}
	
	public JobOffer (Business b, int payRate, int nLeftToHire, Marketplace m){
		businessHiring = b;
		pricePerLabor = b.getGoldPerLabor();
		numberLeftToHire = nLeftToHire;
		owningMarket = m;
	}
	
	public void takeApplicant(Citizen c){
		if(numberLeftToHire > 0){
			Job theJob = new Job(c, pricePerLabor, businessHiring);
			businessHiring.hire(theJob);
			c.setJob(theJob);
			owningMarket.getJobStats().addValue(pricePerLabor);
			numberLeftToHire--;
		}
	}
	
	public boolean jobsFilled(){
		boolean returnFlag = false;
		if(numberLeftToHire == 0){
			returnFlag = true;
		}
		return returnFlag;
	}
	
	public int getPricePerLabor(){
		return pricePerLabor;
	}
	
	public Business getBusinessHiring(){
		return businessHiring;
	}
}
