import java.util.Random;


public class Citizen implements Agent{
	
	private City city;
	
	private Storage pocket;
	
	private int foodProduce;
	private double chanceToProduce;
	
	private int gold = 0;
	
	private double laborPerHour = 0;
	private double laborAvailable = 0;
	private double maxLaborAvailable = 0;	//A citizen can store x10 of their laborPerHour 
	
	private int foodConsume;
	private boolean hasConsumed;
	
	private int health;
	private int maxHealth;
	
	private boolean isEmployed;
	private boolean isKill;
	
	public Citizen(City c, int[] produceRange, double[] chanceRange, int[] consumeRange, int[] healthRange){
		foodProduce = World.random.nextInt(produceRange[1] - produceRange[0] + 1) + produceRange[0];
		
		chanceToProduce =chanceRange[0] + World.random.nextDouble() * (chanceRange[1] - chanceRange[0]);	//min + Math.random() * (max - min)
				
		foodConsume = World.random.nextInt(consumeRange[1] - consumeRange[0] + 1) + consumeRange[0];
		
		maxHealth = World.random.nextInt(healthRange[1] - healthRange[0] + 1) + healthRange[0];
		health = maxHealth;		
		
		city = c;
		
		isKill = false; 
	}
	
	public Citizen(City c, double laborPerHour){
		this.laborPerHour = laborPerHour;
		this.maxLaborAvailable = laborPerHour * 10;
		city = c;
		pocket = new Storage();
		
		isKill = false; 
	}
	
	public Citizen(double laborPerHour){
		this.laborPerHour = laborPerHour;
		this.maxLaborAvailable = laborPerHour * 10;
		pocket = new Storage();
		
		isKill = false; 
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

		int amountBought = so.takeResources(amountToBuy);
		int amountToPay = amountBought * ppR;
		this.gold = this.gold - amountToPay;
		
		so.getSeller().pay(amountToPay);
		pocket.addInput(so.getResourceToSell(), amountBought);
		
		return amountBought;
	}
	
	public void sellResource(BuyOffer bo, int amountToSell){
		int finalSell = amountToSell;
		if(bo.getResourcesLeftToBuy() - finalSell < 0){
			finalSell = bo.getResourcesLeftToBuy();
		}
		this.gold = this.gold + bo.takeGold(finalSell);
		bo.getBuyer().receiveResource(bo.getResourceToBuy(), this.pocket.takeOutput(bo.getResourceToBuy(), finalSell));		
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
	
//Old code--------------------------------------------------------------------------------------------------------------------------	
	public void produce(){
		if(Math.random() < chanceToProduce){
			city.addFood(foodProduce);
		}
	}
	
	public void consume(){
		hasConsumed = city.giveFood(foodConsume);	//Has consumed will be set to "true" if citizen gets his fill
		
		if(hasConsumed){
			health = maxHealth;
		}else{
			health--;
		}
		
		if(health == 0){
			isKill = true; 
		}
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

	public boolean isDead(){
		return isKill;
	}
	
	public String toString(){
		String returnString ="CITIZEN \n";
		
		returnString += "			Gold in Pocket - " + gold + "\n";
		
		return returnString;
	}
	
	public int getFoodConsume(){
		return foodConsume;
	}

}
