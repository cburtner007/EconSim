package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import enums.Resources;


public class Marketplace {
	protected HashMap<Resources,List<SellOffer>> sellOffers;
	protected HashMap<Resources,List<BuyOffer>> buyOffers;
	
	private HashMap<Resources, DescriptiveStatistics> transactionStats;
	
	protected List<JobOffer> offerListings;
	
	public Marketplace(){
		transactionStats = new HashMap<Resources, DescriptiveStatistics>();
		sellOffers = new HashMap<Resources,List<SellOffer>>();
		buyOffers = new HashMap<Resources,List<BuyOffer>>();
		for(Resources r : Resources.values()){
			sellOffers.put(r,new ArrayList<SellOffer>());
			buyOffers.put(r,new ArrayList<BuyOffer>());
			transactionStats.put(r,  new DescriptiveStatistics(100));	//Window is 100 trades. Trades should only add entries at a fraction of their rate (depending on size of populace)
			
		}
		offerListings = new ArrayList<JobOffer>();
	}
	
	public void postJobOffer(JobOffer jo) 
	{
		offerListings.add(jo);
	}
	
	public void postSellOfer(Citizen c, Resources r, int ppResource, int nResources){
		SellOffer newSo = new SellOffer(r, c, ppResource, nResources);
		this.postSellOffer(newSo);
	}
	public void postSellOffer(SellOffer newSo){
		Resources r = newSo.getResourceToSell();
		sellOffers.get(r).add(newSo);	
		while(checkForCompatibleBuy(r,newSo) && !newSo.checkIfFilled()){
			BuyOffer highestBuy = findHighestBuy(r);
			int amountSold = newSo.fulfill(highestBuy);//If there's a compatible buy, then the most eligible will always be the highest buy
			
			if(highestBuy.checkIfFilled()){
				this.removeBuy(highestBuy);
			}
			
			for(int i = 0; i < amountSold; i++){
				transactionStats.get(r).addValue(highestBuy.getPricePerResource());
			}
		}
		if(newSo.checkIfFilled()){
			this.removeSell(newSo);
		}
	}
	
	public void postBuyOffer(Citizen c, Resources r, int ppResource, int nResources){
		BuyOffer newBo = new BuyOffer(r, c, ppResource, nResources);
		this.postBuyOffer(newBo);
	}
	
	public void postBuyOffer(BuyOffer newBo){
		Resources r = newBo.getResourceToBuy();
		buyOffers.get(r).add(newBo);
		while(checkForCompatibleSell(r,newBo) && !newBo.checkIfFilled()){
			SellOffer lowestSale = findLowestSale(r);
			int amountBought = newBo.fulfill(lowestSale);
			
			if(lowestSale.checkIfFilled()){
				this.removeSell(lowestSale);
			}
			
			for(int i = 0; i < amountBought; i++){
				transactionStats.get(r).addValue(lowestSale.getPricePerResource());
			}
		}		
		if(newBo.checkIfFilled()){
			this.removeBuy(newBo);
		}
	}

	public boolean checkForCompatibleBuy(Resources r, SellOffer so){
		boolean returnFlag = false;
		BuyOffer bo = this.findHighestBuy(r);
		if(bo != null && so != null && bo.getPricePerResource() > so.getPricePerResource()){
			returnFlag = true;
		}
		return returnFlag;
	}
	 
	public boolean checkForCompatibleSell(Resources r, BuyOffer bo){
		boolean returnFlag = false;
		SellOffer so = this.findLowestSale(r);
		if(so != null && bo != null && so.getPricePerResource() < bo.getPricePerResource()){
			returnFlag = true;
		}
		
		return returnFlag;
	}
	
	public JobOffer findBestJobOffer()
	{
		JobOffer returnOffer = null;
		for(JobOffer jo : offerListings){
			if (returnOffer == null){
				returnOffer = jo;
			} else if (jo.getPricePerLabor() > returnOffer.getPricePerLabor()){
				returnOffer = jo;
			}
		}
		
		return returnOffer;
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
		sellOffers.get(sellToRemove.getResourceToSell()).remove(sellOffers.get(sellToRemove.getResourceToSell()).indexOf(sellToRemove));
	}
	
	public List<SellOffer> getSellOffersForResource(Resources rType){
		return sellOffers.get(rType);
	}
	public List<BuyOffer> getBuyOffersForResource(Resources rType){
		return buyOffers.get(rType);
	}
	
	public DescriptiveStatistics getStatsForResource(Resources r){
		return transactionStats.get(r);
	}
	
	public static void main(String[] args){
		//Test existing buy
		Marketplace testMP = new Marketplace();
		
		Citizen buyMaker = new Citizen(1.0);
		buyMaker.setGold(500);
		System.out.println("buyer - " + buyMaker.toString());
		BuyOffer bo = new BuyOffer(Resources.WHEAT,buyMaker,10,15);
		System.out.println("buyer after offer - " + buyMaker.toString());
		testMP.postBuyOffer(bo);
		
		Citizen sellMaker1 = new Citizen(1.0);
		Citizen sellMaker2 = new Citizen(1.0);
		sellMaker1.getPocket().addOutput(Resources.WHEAT, 8);
		sellMaker2.getPocket().addOutput(Resources.WHEAT, 15);
		SellOffer so1 = new SellOffer(Resources.WHEAT, sellMaker1, 5, 8);
		SellOffer so2 = new SellOffer(Resources.WHEAT, sellMaker2, 9, 15);
		
		System.out.println("buy taker before sale- " + sellMaker1.toString());
		//sellMaker1.sellResource(bo, 8);
		testMP.postSellOffer(so1);
		System.out.println("buy taker after sale- " + sellMaker1.toString());
		System.out.println("buy taker2 before sale- " + sellMaker2.toString());
		//sellMaker2.sellResource(bo, 15);
		testMP.postSellOffer(so2);
		System.out.println("buy taker2 after sale- " + sellMaker2.toString());
		
		System.out.println("N is: " + testMP.getStatsForResource(Resources.WHEAT).getN());
		System.out.println("Sum is: " + testMP.getStatsForResource(Resources.WHEAT).getSum());
	}
}
