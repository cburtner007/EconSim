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
