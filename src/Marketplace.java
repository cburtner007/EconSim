import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Marketplace {
	protected HashMap<Resources,List<SellOffer>> sellOffers;
	protected HashMap<Resources,List<BuyOffer>> buyOffers;
	
	public Marketplace(){
		for(Resources r : Resources.values()){
			sellOffers.put(r,new ArrayList<SellOffer>());
			buyOffers.put(r,new ArrayList<BuyOffer>());
		}
	}
	
	public void postSellOffer(Citizen c, Resources r, int ppResource, int nResources){
		sellOffers.get(r).add(new SellOffer(r, c, ppResource, nResources));
	}
	
	public void postBuyOffer(Citizen c, Resources r, int ppResource, int nResources){
		buyOffers.get(r).add(new BuyOffer(r, c, ppResource, nResources));
	}
	
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
	
	public BuyOffer findHighestBuy(Resources r){
		BuyOffer returnOffer = null;
		int currentLowest = -1;
		
		for(BuyOffer bo : buyOffers.get(r)){
			if(currentLowest == -1){
				returnOffer = bo;
				currentLowest = bo.getPricePerResource();
			}else if(currentLowest < bo.getPricePerResource()){
				currentLowest = bo.getPricePerResource();
				returnOffer = bo;
			}
		}
		
		return returnOffer;
	}
}
