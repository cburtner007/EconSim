import java.util.HashMap;
import java.util.List;


public class Business implements Agent {
	protected Storage warehouse;
	
	protected HashMap<Resources,Integer> inputPerLabor;
	protected HashMap<Resources,Integer> outputPerLabor;
	
	protected int gold;
	protected int goldPerLabor;
	protected int maxLaborPerHour;
	
	protected int currentWorkerCount; 
	protected int maxWorkerCount;
	
	protected List<Citizen> employees;
	public Business(Resources[] inResTypes, Resources[] outResTypes){
		warehouse = new Storage(inResTypes, outResTypes);
	}

	public void hire(Citizen employeeToHire){
		employees.add(employeeToHire);
		employeeToHire.setIsEmployed(true);
	}
	
	public void hire(List<Citizen> employeesToHire){
		employees.addAll(employeesToHire);
	}
	
	public void produce(){
		produce(maxLaborPerHour);//Produce at maximum capacity
	}
	
	public void produce(int amountToProduce){
		int laborTotal = 0;
		int laborNeeded = 0;
		int laborProduced = 0;
		
		if(amountToProduce == -1 || amountToProduce > maxLaborPerHour){
			laborNeeded = maxLaborPerHour;
		}else{
			laborNeeded = amountToProduce;
		}
		
		int laborBefore = 0;
		while(laborTotal < laborNeeded && hasEnoughInput()){
			if(gold < goldPerLabor){
				break;
			}
			
			for(Citizen c : employees){
				laborProduced = 0;
				
				//If we have enough resources to pay for an hour of labor
				if(hasEnoughInput()){
					laborProduced = c.drainLabor(1);	//Take 1 labor (can be 0)
					c.pay(laborProduced * goldPerLabor);//Pay based on amount of labor provided
					gold = gold - (laborProduced * goldPerLabor); //Deduct gold from treasury
					HashMap<Resources,Integer> producedGoods = warehouse.consumeInput(inputPerLabor, outputPerLabor, 1);//Take input resources out of warehouse
					warehouse.storeOutput(producedGoods);//Put output resources in warehouse
				}
				
				laborTotal += laborProduced; //Add produced labor to total
				if(laborTotal >= laborNeeded){
					break;
				}
			}
			//This is only true if there's no labor left to take
			if(laborBefore == laborTotal){
				break;
			}
			laborBefore = laborTotal;
		}
	}
	
	//Check to see if we have enough input resources for at least an hour of labor
	private boolean hasEnoughInput(){
		boolean returnFlag = false;
		if(gold >= goldPerLabor && warehouse.hasInputForLabor(inputPerLabor, 1)){
			returnFlag = true;
		}
		return returnFlag;
	}
	
	public void addGold(int g){
		this.gold += g;
	}
	
	public int checkGold(){
		return gold;
	}
		
	public void setGoldPerLabor(int gpl){
		this.goldPerLabor = gpl;
	}
	
	public int getGoldPerLabor(){
		return goldPerLabor;
	}
	
	public void tick(){
		this.produce();
	}
}
