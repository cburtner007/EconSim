package Main;
import java.util.*;


public class City implements Agent{
	
	private List<Citizen> citizens;
	private Marketplace marketplace;
	
	public City(){
		citizens = new ArrayList<Citizen>();
		marketplace = new Marketplace();
	}
	
	@Override
	public void tick() {
		for(Citizen c : citizens){
			c.tick();
		}
	}
	
	public Marketplace getMarketplace(){
		return marketplace;
	}
}

/*
 * I should do a test with just citizens to see if they fill up the marketplace with buys
 * 
 * I need to implement job searching and job offer placing and design how a citizen runs a business (add a brain to the citizen brain?)
 */