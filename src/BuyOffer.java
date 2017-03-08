
public class BuyOffer {
	private Resources resourceToBuy;
	private Citizen buyer;
	private int pricePerResource;
	private int resourcesLeftToBuy;
	
	public BuyOffer(Resources rToBuy, Citizen b, int ppResource, int nToBuy){
		resourceToBuy = rToBuy;
		buyer = b;
		pricePerResource = ppResource;
		resourcesLeftToBuy = nToBuy;
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
}
