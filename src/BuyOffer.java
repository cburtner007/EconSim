import java.util.ArrayList;


public class BuyOffer {
	private Resources resourceToBuy;
	private Citizen buyer;
	private int pricePerResource;
	private int resourcesLeftToBuy;
	private int goldOnOffer;
	
	public BuyOffer(Resources rToBuy, Citizen b, int ppResource, int nToBuy){
		resourceToBuy = rToBuy;
		buyer = b;
		pricePerResource = ppResource;
		goldOnOffer = b.takeGold(nToBuy * ppResource);	//Take gold from buyer to put on offer
		resourcesLeftToBuy = nToBuy;
	}

	public int sellToBuyer(int amountToSell){
		int finalGoldTake = 0;//amountToSell * pricePerResource;
		int finalAmountToSell = amountToSell;
		
		if(resourcesLeftToBuy < finalAmountToSell){
			finalAmountToSell = resourcesLeftToBuy;
			resourcesLeftToBuy = 0;
		}else{
			resourcesLeftToBuy = resourcesLeftToBuy - finalAmountToSell;	
		}
		buyer.receiveResource(resourceToBuy, finalAmountToSell);
		finalGoldTake = finalAmountToSell * pricePerResource;
		goldOnOffer = goldOnOffer - finalGoldTake;
		
		return finalGoldTake;
	}
	
	public Resources getResourceToBuy() {
		return resourceToBuy;
	}

	public void setResourceToBuy(Resources resourceToBuy) {
		this.resourceToBuy = resourceToBuy;
	}

	public Citizen getBuyer() {
		return buyer;
	}

	public void setBuyer(Citizen buyer) {
		this.buyer = buyer;
	}

	public int getPricePerResource() {
		return pricePerResource;
	}

	public void setPricePerResource(int pricePerResource) {
		this.pricePerResource = pricePerResource;
	}

	public int getResourcesLeftToBuy() {
		return resourcesLeftToBuy;
	}

	public void setResourcesLeftToBuy(int resourcesLeftToBuy) {
		this.resourcesLeftToBuy = resourcesLeftToBuy;
	}
	
	public static void main(String[] args) {
		Citizen buyMaker = new Citizen(1.0);
		buyMaker.setGold(500);
		System.out.println("buyer - " + buyMaker.toString());
		BuyOffer bo = new BuyOffer(Resources.WHEAT,buyMaker,10,15);
		System.out.println("buyer after offer - " + buyMaker.toString());
		Citizen buyTaker1 = new Citizen(1.0);
		Citizen buyTaker2 = new Citizen(1.0);
		buyTaker1.getPocket().addOutput(Resources.WHEAT, 8);
		buyTaker2.getPocket().addOutput(Resources.WHEAT, 15);
		
		System.out.println("buy taker before sale- " + buyTaker1.toString());
		buyTaker1.sellResource(bo, 8);
		System.out.println("buy taker after sale- " + buyTaker1.toString());
		System.out.println("buy taker2 before sale- " + buyTaker2.toString());
		buyTaker2.sellResource(bo, 15);
		System.out.println("buy taker2 after sale- " + buyTaker2.toString());
	}
}
