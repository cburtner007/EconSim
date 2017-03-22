
public class JobOffer {

	Business businessHiring;
	int numberLeftToHire;
	int pricePerLabor;
	
	public JobOffer (Business b, int nLeftToHire){
		businessHiring = b;
		pricePerLabor = b.getGoldPerLabor();
		numberLeftToHire = nLeftToHire;
	}
	
	public void takeApplicant(Citizen c){
		if(numberLeftToHire > 0){
			businessHiring.hire(c);
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
}
