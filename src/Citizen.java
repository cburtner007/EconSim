import java.util.Random;


public class Citizen implements Agent{
	
	private City city;
	
	private int foodProduce;
	private double chanceToProduce;
	
	private int gold = 0;
	
	private double laborPerHour = 0;
	private double laborAvailable = 0;
	private double maxLaborAvailable = 0;	//A citizen can store x10 of their laborAvailable 
	
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
	
	public void pay(int goldToPay){
		this.gold += goldToPay;
	}
	
	//Add labor to available pool, up to the max
	private void accrueLabor(){
		this.laborAvailable += this.laborPerHour;
		if(this.laborAvailable > this.maxLaborAvailable){
			this.laborAvailable = this.maxLaborAvailable;
		}
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
	
	public boolean isDead(){
		return isKill;
	}
	
	public String toString(){
		String returnString ="";
		
		returnString += "			Amount of Food to Produce - " + foodProduce + "\n";
		returnString += "			Chance to Produce - " + chanceToProduce + "\n";
		returnString += "			Amount to Consume - " + foodConsume + "\n";
		returnString += "			Current Health - " + health + "\n";
		returnString += "			Max Health - " + maxHealth + "\n";
		returnString += "			Has Eaten - " + hasConsumed + "\n";
		
		return returnString;
	}
	
	public int getFoodConsume(){
		return foodConsume;
	}

}
