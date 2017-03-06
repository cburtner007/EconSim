import java.util.ArrayList;
import java.util.List;


public class WheatBusiness extends Business {
	final static Resources[] INPUT_TYPES = {Resources.TOOLS};
	final static Resources[] OUTPUT_TYPES = {Resources.WHEAT};
	
	private int gold = 0;
	private int goldPerLabor = 0;
	private int maxLaborPerHour = 0;
	private List<Citizen> employees;
	
	public WheatBusiness(){
		super(INPUT_TYPES, OUTPUT_TYPES);
		maxLaborPerHour = 5;
		employees = new ArrayList<Citizen>();
	}
	
	//Test Method
	public void addInput(int amount){
		warehouse.addInput(Resources.TOOLS, amount);
	}
	
	public void hire(List<Citizen> employeesToHire){
		employees.addAll(employeesToHire);
	}
	
	public void produce(){
		int laborTotal = 0;
		int laborNeeded = maxLaborPerHour;
		int laborProduced = 0;
		
		int laborBefore = 0;
		while(laborTotal < maxLaborPerHour){
			if(gold < goldPerLabor){
				break;
			}
			for(Citizen c : employees){
				laborProduced = 0;
				
				//If we have enough gold to pay for an hour of labor
				if(gold >= goldPerLabor){
					laborProduced = c.drainLabor(1);	//Take 1 labor (can be 0)
					c.pay(laborProduced * goldPerLabor);//Pay based on amount of labor provided
					gold = gold - (laborProduced * goldPerLabor); //Deduct gold from treasury
				}
				
				laborTotal += laborProduced; //Add produced labor to total
				if(laborTotal == maxLaborPerHour){
					break;
				}
			}
			if(laborTotal == laborBefore){
				break;
			}
			laborBefore = laborTotal;
		}

		
		int amountProduced = warehouse.takeInput(Resources.TOOLS, currentWorkerCount)*2;
		warehouse.addOutput(Resources.WHEAT, amountProduced);
	}
	
	public void tick(){
		this.produce();
	}
	
	public static void main(String[] args) {
		WheatBusiness testBusiness = new WheatBusiness();
		
		for(int i = 0 ; i <= 100 ; i++){
			testBusiness.tick();
			testBusiness.addInput(100);
		}
	}

}
