package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class WheatBusiness extends Business {
	final static Resources[] INPUT_TYPES = {Resources.TOOLS};
	final static Resources[] OUTPUT_TYPES = {Resources.WHEAT};
	
	public WheatBusiness(){
		super(INPUT_TYPES, OUTPUT_TYPES);
		maxLaborPerHour = 5;
		inputPerLabor = new HashMap<Resources,Integer>();
		inputPerLabor.put(Resources.TOOLS, 1);
		outputPerLabor = new HashMap<Resources,Integer>();
		outputPerLabor.put(Resources.WHEAT, 4);
		gold = 0;
		employees = new ArrayList<Citizen>();
	}
	
	//Test Method
	public void addInput(int amount){
		warehouse.addInput(Resources.TOOLS, amount);
	}
	
	public String toString(){
		String returnString ="WHEAT BUSINESS";
		returnString += "\n";
		returnString += "			Amount of WHEAT produced - " + warehouse.checkOutputResource(Resources.WHEAT) + "\n";
		returnString += "			TOOLS left - " + warehouse.checkInputResource(Resources.TOOLS) + "\n";
		returnString += "			Gold left - " + gold + "\n";
		
		return returnString;
	}
	
	public static void main(String[] args) {
		WheatBusiness testBusiness = new WheatBusiness();
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
