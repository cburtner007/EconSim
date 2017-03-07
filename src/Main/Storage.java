package Main;
import java.util.HashMap;

import enums.Resources;

public class Storage {
	HashMap<Resources,Integer> input;
	HashMap<Resources,Integer> output;
	HashMap<Resources,Integer> store;
	
	int storeCapacity; 
	
	//All resources
	public Storage(){
		input = new HashMap<Resources, Integer>();
		output = new HashMap<Resources, Integer>();
		store = new HashMap<Resources, Integer>();
		for(Resources res : Resources.values()){
			store.put(res,0);
		}
	}
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
	
	public Storage(Resources inputResourceTypes[], Resources outputResourceTypes[], int capacity){
		this(inputResourceTypes, outputResourceTypes);
		storeCapacity = capacity;
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
	
	public void putStore(Resources resType, int amountIn){
		int currentAmount = store.get(resType);
		int newAmount = currentAmount + amountIn;
		store.put(resType, newAmount);
	}

	public int takeStore(Resources resType, int takeAmount){
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
	
}
