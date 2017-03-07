import java.util.HashMap;
import java.util.Map;

public class Storage {
	HashMap<Resources,Integer> input;
	HashMap<Resources,Integer> output;
	
	//Initialize input and output maps with specified resource types. 
	public Storage(Resources inputResourceTypes[], Resources outputResourceTypes[]){
		input = new HashMap<Resources, Integer>();
		output = new HashMap<Resources, Integer>();
		
		for(Resources res : inputResourceTypes){
			input.put(res, 0);
		}
		for(Resources res : outputResourceTypes){
			output.put(res, 0);
		}
	}
	
	public void addInput(Resources resType, int amountIn){
		int currentAmount = input.get(resType);
		int newAmount = currentAmount + amountIn;
		input.put(resType, newAmount);
	}
	public void addOutput(Resources resType, int amountIn){
		int currentAmount = output.get(resType);
		int newAmount = currentAmount + amountIn;
		output.put(resType, newAmount);
	}	
	
	public int checkInputResource(Resources resType){
		return input.get(resType);
	}
	public int checkOutputResource(Resources resType){
		return output.get(resType);
	}
	
	public int takeInput(Resources resType, int takeAmount){
		int currentAmount = input.get(resType);
		int finalTake = 0;		
		
		currentAmount = currentAmount - takeAmount;
		//Only take if there's enough. 
		if(currentAmount >= 0){
			finalTake = takeAmount; 
			input.put(resType, currentAmount);
		}else{
			finalTake = takeAmount + currentAmount; //currentAmount is some negative number. This will only give us the leftovers 
			input.put(resType, 0);
		}
		
		return finalTake;
	}
	public int takeOutput(Resources resType, int takeAmount){
		int currentAmount = output.get(resType);
		int finalTake = 0;		
		
		currentAmount = currentAmount - takeAmount;
		//Only take if there's enough. 
		if(currentAmount >= 0){
			finalTake = takeAmount; 
			output.put(resType, currentAmount);
		}else{
			finalTake = takeAmount + currentAmount; //currentAmount is some negative number. This will only give us the leftovers 
			output.put(resType, 0);
		}
		
		return finalTake;
	}

	public boolean hasInputForLabor(HashMap<Resources, Integer> inputPerLabor,int laborHours) {
		boolean returnFlag = true;
		
		for(Resources r : inputPerLabor.keySet()){
			//If we have less of resource r stored than what the requirement (inputPerLabor) asks for, return false
			if(input.get(r) < inputPerLabor.get(r)*laborHours){
				returnFlag = false;
			}
		}
		return returnFlag;
	}

	public HashMap<Resources, Integer> consumeInput(HashMap<Resources, Integer> inputPerLabor, HashMap<Resources, Integer>outputPerLabor, int laborHours) {
		HashMap<Resources, Integer> finalOutput = new HashMap<Resources, Integer>();
		for(Resources r : inputPerLabor.keySet()){
			input.put(r, input.get(r) - inputPerLabor.get(r)*laborHours);
		}
		for(Resources r : outputPerLabor.keySet()){
			finalOutput.put(r, outputPerLabor.get(r)*laborHours);
		}
		
		return finalOutput;
	}

	public void storeOutput(HashMap<Resources, Integer> producedGoods) {
		for(Resources r : producedGoods.keySet()){
			output.put(r, output.get(r)+producedGoods.get(r));
		}
	}
	
}
