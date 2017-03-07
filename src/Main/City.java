package Main;
import java.util.*;

import enums.Resources;
import behaviors.CityBrain;


public class City implements Agent{

	private CityBrain brain; 
	private LinkedList<Business> businesses;
	private Storage cityStore;
	private int availableSquareFeet;
	private int populationSize;
	private int employedCitizens; 
	private int unemployedCitizens; 
	
	public City(){
		businesses = new LinkedList<Business>();
		cityStore = new Storage();
		availableSquareFeet = 5000;
		brain = new CityBrain(this);
	}
	
	@Override
	public void tick() throws Exception {
		exploitBusinessOpportunities();
		activateBusinesses();
	}
	
	public void exploitBusinessOpportunities() throws Exception{
		if(brain.startWheatBusiness()){
			buildBusiness(Resources.WHEAT);
		}
	}
	
	public int getAvailableSquareFeet(){
		return availableSquareFeet;
	}
	
	public int getUnemployedCitizens(){
		return unemployedCitizens; 
	}
	
	private void buildBusiness(Resources resourceType) throws Exception{
		Business biz = null;
		switch (resourceType){
			case WHEAT:
				biz = new WheatBusiness(this);
				break;
			default:
				break;
		}
		businesses.add(biz);
		availableSquareFeet = availableSquareFeet - biz.getSquareFootage();
		if(availableSquareFeet < 0){
			throw new Exception();
		}
	}
	
	private void activateBusinesses(){
		for(Business b : businesses){
			b.tick();
		}
	}
}
