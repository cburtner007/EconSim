import java.util.Random;


public class Citizen implements Agent{
	
	private City city;
	
	private Storage pocket;
	
	private int gold = 0;
	
	private double laborPerHour = 0;
	private double laborAvailable = 0;
	private double maxLaborAvailable = 0;	//A citizen can store x2 of their laborPerHour 
	
	private boolean isEmployed;
	
	public Citizen(City c, double laborPerHour){
		this.laborPerHour = laborPerHour;
		this.maxLaborAvailable = laborPerHour * 2;
		city = c;
		pocket = new Storage();
		
	}
	
	public Citizen(double laborPerHour){
		this.laborPerHour = laborPerHour;
		this.maxLaborAvailable = laborPerHour * 2;
		pocket = new Storage();	
	}

	@Override
	public void tick() {
		accrueLabor();
	}
	
	public int drainLabor(int requestedLabor){
		int amountToReturn = 0; 
		
		if(requestedLabor >  this.laborAvailable){
			amountToReturn = 0;
		}else{
			amountToReturn = requestedLabor;
		}
		
		laborAvailable = laborAvailable - amountToReturn;
		
		return amountToReturn;
	}
	
	public int buyResource(SellOffer so, int amountToBuy){
		int ppR = so.getPricePerResource();
		int finalBuy = amountToBuy;
		
		if(so.getResourcesLeftToSell() < finalBuy){
			finalBuy = so.getResourcesLeftToSell();
		}
		
		int amountBought = so.buyFromSeller(finalBuy);
		int amountToPay = amountBought * ppR;
		this.gold = this.gold - amountToPay;

		pocket.addInput(so.getResourceToSell(), amountBought);
		
		return amountBought;
	}
	
	public int sellResource(BuyOffer bo, int amountToSell){
		int finalSell = amountToSell;
		
		if(bo.getResourcesLeftToBuy() < finalSell){
			finalSell = bo.getResourcesLeftToBuy();
		}
		this.gold = this.gold + bo.sellToBuyer(finalSell);
		this.pocket.takeOutput(bo.getResourceToBuy(), finalSell);
		return finalSell;
	}
	
	public void receiveResource(Resources rToReceive, int amount){
		pocket.addInput(rToReceive, amount);
	}
	
	public void pay(int goldToPay){
		this.gold += goldToPay;
	}
	
	public int takeGold(int goldToTake){
		int finalTake = goldToTake;
		if(gold - finalTake < 0){
			finalTake = gold;
			gold = 0;
		}else{
			gold = gold - finalTake;	
		}
		
		return finalTake;
	}
	
	//Add labor to available pool, up to the max
	private void accrueLabor(){
		this.laborAvailable += this.laborPerHour;
		if(this.laborAvailable > this.maxLaborAvailable){
			this.laborAvailable = this.maxLaborAvailable;
		}
	}
	
	public Storage getPocket(){
		return pocket;
	}
		
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public double getLaborPerHour() {
		return laborPerHour;
	}

	public void setLaborPerHour(double laborPerHour) {
		this.laborPerHour = laborPerHour;
	}

	public double getLaborAvailable() {
		return laborAvailable;
	}

	public void setLaborAvailable(double laborAvailable) {
		this.laborAvailable = laborAvailable;
	}

	public double getMaxLaborAvailable() {
		return maxLaborAvailable;
	}

	public void setMaxLaborAvailable(double maxLaborAvailable) {
		this.maxLaborAvailable = maxLaborAvailable;
	}

	public void setPocket(Storage pocket) {
		this.pocket = pocket;
	}
	
	public void setIsEmployed(boolean employed){
		this.isEmployed = employed; 
	}
	
	public String toString(){
		String returnString ="CITIZEN \n";
		
		returnString += "			Gold in Pocket - " + gold + "\n";
		
		return returnString;
	}


}
