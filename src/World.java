import java.util.Random;


public class World {
	public static Random random = new Random(); 
	
	public static void main(String[] args){
		int[] produceRange = {1,5};
		double[] chanceRange = {.5, .999};
		int[] consumeRange = {1,5};
		int[] healthRange = {2,4};
		City c = new City(10,10,produceRange,chanceRange,consumeRange,healthRange);
	
		System.out.print(c.toString());
		while(true){
			c.tick();
			System.out.print(c.shortStats());
		}
	}
	
}
