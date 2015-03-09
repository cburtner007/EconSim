import java.util.*;


public class City implements Agent{

	private List<Citizen> population;	//The citizens that are apart of this city
	private int foodStored;	//How much food is stored in the city
	
	/**
	 * 
	 * @param populationSize How many people are in the city
	 * @param intialFood How much food the city starts with 
	 * @param produceRange an int array of size 2. Defines how much citizen can produce. Index 0 is min, index 1 is max. 
	 * @param chanceRange a double array of size 2. Chance citizen has to produce. Index 0 is min, index 1 is max. 
	 * @param consumeRange an int array of size 2. Defines how much citizen consumes. Index 0 is min, index 1 is max. 
	 * @param healthRange an int array of size 2. Defines how healthy citizen can be. Index 0 is min, index 1 is max. 
	 */
	public City(int populationSize, int intialFood, int[] produceRange, double[] chanceRange, int[] consumeRange, int[] healthRange){
		population = new ArrayList<Citizen>(populationSize);
		Citizen temp;
		for(int i = 0; i < populationSize ; i++){
			temp = new Citizen(this, produceRange, chanceRange, consumeRange, healthRange);
			population.add(temp);
		}
		
		foodStored = intialFood;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		for(Citizen p : population){
			p.produce();
		}
		for(Citizen p : population){
			p.consume();
		}
		
		for(int i = 0; i < population.size() ; i++){
			if(population.get(i).isDead()){
				population.remove(i);
			}
		}
	}
	
	public void addFood(int amount){
		foodStored += amount;
	}
	
	public boolean giveFood(int amountToConsume){
		boolean fulfilled = false;
		
		if(foodStored >= amountToConsume){
			fulfilled = true;
			foodStored = foodStored - amountToConsume;
		}else{
			foodStored = 0; 
		}
		
		return fulfilled;
	}
	
	public String toString(){
		String returnString = "";
		
		returnString += "City Stats: " + "\n";
		returnString += "	Population Size - " + population.size() + "\n";
		returnString += "	Food Stored - " + foodStored + "\n";
		returnString += "\n";
		returnString += "	Citizens:\n";
		for(Citizen c : population){
			returnString += "		Citizen:\n";
			returnString += c.toString();
		}
		
		return returnString;
	}
	
	public String shortStats(){
		String returnString = "";
		
		returnString += "City Stats: " + "\n";
		returnString += "	Population Size - " + population.size() + "\n";
		returnString += "	Food Stored - " + foodStored + "\n";
		
		return returnString;
	}

}
