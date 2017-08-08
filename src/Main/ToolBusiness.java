package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import enums.Resources;


public class ToolBusiness extends Business {
	final static Resources[] INPUT_TYPES = {};
	final static Resources[] OUTPUT_TYPES = {Resources.TOOLS};
	
	public ToolBusiness(){
		super(INPUT_TYPES, OUTPUT_TYPES);
		maxLaborPerHour = 3;
		inputPerLabor = new HashMap<Resources,Integer>();
		//inputPerLabor.put(Resources.TOOLS, 1);
		outputPerLabor = new HashMap<Resources,Integer>();
		outputPerLabor.put(Resources.TOOLS, 1);
		gold = 0;
		employees = new ArrayList<Citizen>();
	}
	
	//Test Method
	public void addInput(int amount){
		//warehouse.addInput(Resources.TOOLS, amount);
	}
	
	public String toString(){
		String returnString ="TOOL BUSINESS";
		returnString += "\n";
		returnString += "			Amount of TOOLS produced - " + warehouse.checkOutputResource(Resources.TOOLS) + "\n";
		//returnString += "			TOOLS left - " + warehouse.checkInputResource(Resources.TOOLS) + "\n";
		returnString += "			Gold left - " + gold + "\n";
		
		return returnString;
	}
	
	public static void main(String[] args) {
		ToolBusiness testBusiness = new ToolBusiness();
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
