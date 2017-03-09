
public class SellOffer {
	private Resources resourceToSell;
	private Citizen seller;
	private int pricePerResource;
	private int resourcesLeftToSell;
	
	public SellOffer(Resources rToSell, Citizen b, int ppResource, int nToSell){
		resourceToSell = rToSell;
		seller = b;
		pricePerResource = ppResource;
		resourcesLeftToSell = b.getPocket().takeOutput(rToSell, nToSell);	//Take resources from seller to put on offer
	}

	public int takeResources(int takeAmount){
		int currentAmount = resourcesLeftToSell;
		int finalTake = 0;		
		
		currentAmount = currentAmount - takeAmount;
		//Only take if there's enough. 
		if(currentAmount >= 0){
			finalTake = takeAmount; 
			resourcesLeftToSell = currentAmount;
		}else{
			finalTake = takeAmount + currentAmount; //currentAmount is some negative number. This will only give us the leftovers 
			resourcesLeftToSell = 0;
		}
		
		return finalTake;
	}
	
	public Resources getResourceToSell() {
		return resourceToSell;
	}

	public void setResourceToSell(Resources resourceToSell) {
		this.resourceToSell = resourceToSell;
	}

	public Citizen getSeller() {
		return seller;
	}

	public void setSeller(Citizen seller) {
		this.seller = seller;
	}

	public int getPricePerResource() {
		return pricePerResource;
	}

	public void setPricePerResource(int pricePerResource) {
		this.pricePerResource = pricePerResource;
	}

	public int getResourcesLeftToSell() {
		return resourcesLeftToSell;
	}

	public void setResourcesLeftToSell(int resourcesLeftToBuy) {
		this.resourcesLeftToSell = resourcesLeftToBuy;
	}

	public static void main(String[] args) {
		Citizen sellMaker = new Citizen(1.0);
		sellMaker.getPocket().addOutput(Resources.WHEAT, 30);
		System.out.println("Seller - " + sellMaker.toString());
		SellOffer so = new SellOffer(Resources.WHEAT,sellMaker,10,15);
		Citizen sellTaker1 = new Citizen(1.0);
		Citizen sellTaker2 = new Citizen(1.0);
		sellTaker1.setGold(50);
		sellTaker2.setGold(150);
		
		System.out.println("sell taker before sale- " + sellTaker1.toString());
		sellTaker1.buyResource(so, 10);
		System.out.println("sell taker after sale- " + sellTaker1.toString());
		System.out.println("sell taker2 before sale- " + sellTaker2.toString());
		sellTaker2.buyResource(so, 15);
		System.out.println("sell taker2 after sale- " + sellTaker2.toString());
		
		
		System.out.println("Seller - " + sellMaker.toString());
	}
	
}
