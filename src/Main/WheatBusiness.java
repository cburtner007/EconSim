package Main;

import enums.Resources;

public class WheatBusiness extends Business {
	final static Resources[] INPUT_TYPES = {Resources.TOOLS};
	final static Resources[] OUTPUT_TYPES = {Resources.WHEAT}; 
	final static int MAX_WORKERS = 100;
	public final static int SQUARE_FOOTAGE = 1000;	//Guuuhh, publiiiicc?
	final static int TICKS_TO_PRODUCE = 10;
	final static int INITIAL_STORE_CAPACITY = 600; 
	
	private City theCity; 
	
	public WheatBusiness(){
		super(INPUT_TYPES, OUTPUT_TYPES, SQUARE_FOOTAGE, TICKS_TO_PRODUCE,INITIAL_STORE_CAPACITY);
		maxWorkerCount = MAX_WORKERS;
	}
	public WheatBusiness(City city){
		super(INPUT_TYPES, OUTPUT_TYPES, SQUARE_FOOTAGE, TICKS_TO_PRODUCE, INITIAL_STORE_CAPACITY);
		maxWorkerCount = MAX_WORKERS;
		theCity = city; 
	}
	
	//Test Method
	public void addInput(int amount){
		warehouse.addInput(Resources.TOOLS, amount);
	}
	
	public int hire(int workerNumber){
		int numberHired = 0;
		if(workerNumber + currentWorkerCount <= maxWorkerCount){
			currentWorkerCount += workerNumber;	
			numberHired = workerNumber; 
		}else{
			numberHired = maxWorkerCount - currentWorkerCount;
			currentWorkerCount = maxWorkerCount;
		}
		
		return numberHired;
	}
	
	public void produce(){
		int amountProduced = warehouse.takeInput(Resources.TOOLS, currentWorkerCount)*2;
		warehouse.addOutput(Resources.WHEAT, amountProduced);
	}
	
	public void tick(){
		this.produce();
	}
	
	public static void main(String[] args) {
		WheatBusiness testBusiness = new WheatBusiness();
		testBusiness.hire(150);
		for(int i = 0 ; i <= 100 ; i++){
			testBusiness.tick();
			testBusiness.addInput(100);
		}
	}

}
