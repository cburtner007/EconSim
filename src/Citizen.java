import java.util.Random;


public class Citizen implements Agent{
	
	private City city;
	
	private int foodProduce;
	private double chanceToProduce;
	
	private int foodConsume;
	private boolean hasConsumed;
	
	private int health;
	private int maxHealth;
	
	private boolean isKill;
	
	public Citizen(int[] produceRange, double[] chanceRange, int[] consumeRange, int[] healthRange){
		foodProduce = World.random.nextInt(produceRange[1] - produceRange[0] + 1) + produceRange[0];
		
		chanceToProduce =chanceRange[0] + World.random.nextDouble() * (chanceRange[1] - chanceRange[0]);	//min + Math.random() * (max - min)
				
		foodConsume = World.random.nextInt(consumeRange[1] - consumeRange[0] + 1) + consumeRange[0];
		
		maxHealth = World.random.nextInt(healthRange[1] - healthRange[0] + 1) + healthRange[0];
		health = maxHealth;		
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
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

}
