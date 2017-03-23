package Main;
import java.util.HashMap;
import java.util.Map;

public class Storage {
	//Do we need both input and output? We could put everything in one. This was silly to do
	HashMap<Resources,Integer> input;
	HashMap<Resources,Integer> output;
	HashMap<Resources,Integer> store;
	
	//Initialize input and output maps with specified resource types. 
	public Storage(Resources inputResourceTypes[], Resources outputResourceTypes[]){
		input = new HashMap<Resources, Integer>();
		output = new HashMap<Resources, Integer>();
		store = new HashMap<Resources, Integer>();
		
		for(Resources res : inputResourceTypes){
			input.put(res, 0);
			store.put(res, 0);
		}
		for(Resources res : outputResourceTypes){
			output.put(res, 0);
			store.put(res, 0);
		}
	}
	
	public Storage(){
		input = new HashMap<Resources, Integer>();
		output = new HashMap<Resources, Integer>();
		store = new HashMap<Resources, Integer>();
		for(Resources r : Resources.values()){
			input.put(r,0);
			output.put(r,0);
			store.put(r,0);
		}
	}
	
	public void addInput(Resources resType, int amountIn){
		int currentAmount = store.get(resType);
		int newAmount = currentAmount + amountIn;
		store.put(resType, newAmount);
	}
	public void addOutput(Resources resType, int amountIn){
		int currentAmount = store.get(resType);
		int newAmount = currentAmount + amountIn;
		store.put(resType, newAmount);
	}	
	
	public int checkInputResource(Resources resType){
		return store.get(resType);
	}
	public int checkOutputResource(Resources resType){
		return store.get(resType);
	}
	
	public int takeInput(Resources resType, int takeAmount){
		int currentAmount = store.get(resType);
		int finalTake = 0;		
		
		currentAmount = currentAmount - takeAmount;
		//Only take if there's enough. 
		if(currentAmount >= 0){
			finalTake = takeAmount; 
			store.put(resType, currentAmount);
		}else{
			finalTake = takeAmount + currentAmount; //currentAmount is some negative number. This will only give us the leftovers 
			store.put(resType, 0);
		}
		
		return finalTake;
	}
	public int takeOutput(Resources resType, int takeAmount){
		int currentAmount = store.get(resType);
		int finalTake = 0;		
		
		currentAmount = currentAmount - takeAmount;
		//Only take if there's enough. 
		if(currentAmount >= 0){
			finalTake = takeAmount; 
			store.put(resType, currentAmount);
		}else{
			finalTake = takeAmount + currentAmount; //currentAmount is some negative number. This will only give us the leftovers 
			store.put(resType, 0);
		}
		
		return finalTake;
	}

	public boolean hasInputForLabor(HashMap<Resources, Integer> inputPerLabor,int laborHours) {
		boolean returnFlag = true;
		
		for(Resources r : inputPerLabor.keySet()){
			//If we have less of resource r stored than what the requirement (inputPerLabor) asks for, return false
			if(store.get(r) < inputPerLabor.get(r)*laborHours){
				returnFlag = false;
			}
		}
		return returnFlag;
	}

	public HashMap<Resources, Integer> consumeInput(HashMap<Resources, Integer> inputPerLabor, HashMap<Resources, Integer>outputPerLabor, int laborHours) {
		HashMap<Resources, Integer> finalOutput = new HashMap<Resources, Integer>();
		for(Resources r : inputPerLabor.keySet()){
			store.put(r, store.get(r) - inputPerLabor.get(r)*laborHours);
		}
		for(Resources r : outputPerLabor.keySet()){
			finalOutput.put(r, outputPerLabor.get(r)*laborHours);
		}
		
		return finalOutput;
	}

	public void storeInput(HashMap<Resources, Integer> resources){
		for(Resources r : resources.keySet()){
			store.put(r, store.get(r)+resources.get(r));
		}
	}
	
	public void storeOutput(HashMap<Resources, Integer> producedGoods) {
		for(Resources r : producedGoods.keySet()){
			store.put(r, store.get(r)+producedGoods.get(r));
		}
	}
	
}
