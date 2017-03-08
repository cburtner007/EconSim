
public class SellOffer {
	private Resources resourceToSell;
	private Citizen seller;
	private int pricePerResource;
	private int resourcesLeftToBuy;
	
	public SellOffer(Resources rToBuy, Citizen b, int ppResource, int nToBuy){
		resourceToSell = rToBuy;
		seller = b;
		pricePerResource = ppResource;
		resourcesLeftToBuy = nToBuy;
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

	public int getResourcesLeftToBuy() {
		return resourcesLeftToBuy;
	}

	public void setResourcesLeftToBuy(int resourcesLeftToBuy) {
		this.resourcesLeftToBuy = resourcesLeftToBuy;
	}

}
