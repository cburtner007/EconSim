package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GoldBusiness extends Business {
	final static Resources[] INPUT_TYPES = {Resources.TOOLS};
	final static Resources[] OUTPUT_TYPES = {};
	final static int GOLD_PER_TOOL = 15;
	
	public GoldBusiness(){
		super(INPUT_TYPES, OUTPUT_TYPES);
		maxLaborPerHour = 5;
		inputPerLabor = new HashMap<Resources,Integer>();
		inputPerLabor.put(Resources.TOOLS, 1);
		outputPerLabor = new HashMap<Resources,Integer>();
		gold = 0;
		employees = new ArrayList<Citizen>();
	}
	
	//Test Method
	public void addInput(int amount){
		warehouse.addInput(Resources.TOOLS, amount);
	}
	
	public void produce(){
		int toolsBefore = warehouse.checkInputResource(Resources.TOOLS);
		super.produce();	//super.produce() will use our input resources for us, but gold production is a special case so we handle it here
		int toolsAfter = warehouse.checkInputResource(Resources.TOOLS);
		
		gold = gold + (toolsBefore - toolsAfter) * GOLD_PER_TOOL;
	}
	
	public void tick(){
		this.produce();
	}
	
	public String toString(){
		String returnString ="GOLD BUSINESS";
		returnString += "\n";
		returnString += "			Gold left - " + gold + "\n";
		
		return returnString;
	}
	
	public static void main(String[] args) {
		GoldBusiness testBusiness = new GoldBusiness();
		ArrayList<Citizen> peepsToEmploy = new ArrayList<Citizen>();
		for(int i = 0 ; i < 5 ; i++){
			peepsToEmploy.add(new Citizen(1.0));
		}
		testBusiness.addGold(1000);
		testBusiness.setGoldPerLabor(1);
		testBusiness.addInput(100);
		testBusiness.hire(peepsToEmploy);
		for(int i = 0 ; i <= 100 ; i++){
			for(Citizen c : peepsToEmploy){
				c.tick();
			}
			testBusiness.tick();
			System.out.println(testBusiness.toString());
		}
	}

}
