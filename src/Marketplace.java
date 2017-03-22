import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Marketplace {
	protected HashMap<Resources,List<SellOffer>> sellOffers;
	protected HashMap<Resources,List<BuyOffer>> buyOffers;
	protected List<JobOffer> offerListings;
	
	public Marketplace(){
		for(Resources r : Resources.values()){
			sellOffers.put(r,new ArrayList<SellOffer>());
			buyOffers.put(r,new ArrayList<BuyOffer>());
		}
		offerListings = new ArrayList<JobOffer>();
	}
	
	public void postSellOffer(Citizen c, Resources r, int ppResource, int nResources){
		SellOffer newSo = new SellOffer(r, c, ppResource, nResources);
		sellOffers.get(r).add(newSo);
		while(checkForCompatibleBuy(r,newSo) && !newSo.checkIfFilled()){
			newSo.fulfillBuy(findHighestBuy(r));
		}
		if(newSo.checkIfFilled()){
			this.removeSell(newSo);
		}
	}
	
	public void postBuyOffer(Citizen c, Resources r, int ppResource, int nResources){
		BuyOffer newBo = new BuyOffer(r, c, ppResource, nResources);
		buyOffers.get(r).add(newBo);
		while(checkForCompatibleSell(r,newBo) && !newBo.checkIfFilled()){
			newBo.fulfillSell(findLowestSale(r));
		}
		
		if(newBo.checkIfFilled()){
			this.removeBuy(newBo);
		}
	}

	public boolean checkForCompatibleBuy(Resources r, SellOffer so){
		boolean returnFlag = false;
		BuyOffer bo = this.findHighestBuy(r);
		if(bo.getPricePerResource() > so.getPricePerResource()){
			returnFlag = true;
		}
		return returnFlag;
	}
	 
	public boolean checkForCompatibleSell(Resources r, BuyOffer bo){
		boolean returnFlag = false;
		SellOffer so = this.findLowestSale(r);
		if(so.getPricePerResource() < bo.getPricePerResource()){
			returnFlag = true;
		}
		
		return returnFlag;
	}
	
	//There really isn't that much difference between a "Sell" and a "Buy" both are offers. 
	
	//Neither party should interact directly with the other. Transaction should take place through marketplace
	//fulfilling "compatible" orders
	
	public SellOffer findLowestSale(Resources r){
		SellOffer returnOffer = null;
		int currentLowest = -1;
		for(SellOffer so : sellOffers.get(r)){
			if(currentLowest == -1){
				returnOffer = so;
				currentLowest = so.getPricePerResource();
			}else if(currentLowest > so.getPricePerResource()){
				currentLowest = so.getPricePerResource();
				returnOffer = so;
			}
		}
		
		return returnOffer;
	}
	
	public SellOffer findHighestSale(Resources r){
		SellOffer returnOffer = null;
		int currentHighest = -1;
		for(SellOffer so : sellOffers.get(r)){
			if(currentHighest == -1){
				returnOffer = so;
				currentHighest = so.getPricePerResource();
			}else if(currentHighest < so.getPricePerResource()){
				currentHighest = so.getPricePerResource();
				returnOffer = so;
			}
		}
		
		return returnOffer;
	}
	
	public BuyOffer findHighestBuy(Resources r){
		BuyOffer returnOffer = null;
		int currentHighest = -1;
		
		for(BuyOffer bo : buyOffers.get(r)){
			if(currentHighest == -1){
				returnOffer = bo;
				currentHighest = bo.getPricePerResource();
			}else if(currentHighest < bo.getPricePerResource()){
				currentHighest = bo.getPricePerResource();
				returnOffer = bo;
			}
		}
		
		return returnOffer;
	}
	
	public BuyOffer findLowestBuy(Resources r){
		BuyOffer returnOffer = null;
		int currentLowest = -1;
		
		for(BuyOffer bo : buyOffers.get(r)){
			if(currentLowest == -1){
				returnOffer = bo;
				currentLowest = bo.getPricePerResource();
			}else if(currentLowest > bo.getPricePerResource()){
				currentLowest = bo.getPricePerResource();
				returnOffer = bo;
			}
		}
		
		return returnOffer;
	}
	
	public void removeBuy(BuyOffer buyToRemove){
		buyOffers.get(buyToRemove.getResourceToBuy()).remove(buyOffers.get(buyToRemove.getResourceToBuy()).indexOf(buyToRemove));
				
	}
	
	public void removeSell(SellOffer sellToRemove){
		buyOffers.get(sellToRemove.getResourceToSell()).remove(buyOffers.get(sellToRemove.getResourceToSell()).indexOf(sellToRemove));
	}
}
