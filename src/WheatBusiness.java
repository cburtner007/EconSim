
public class WheatBusiness extends Business {
	final static Resources[] INPUT_TYPES = {Resources.TOOLS};
	final static Resources[] OUTPUT_TYPES = {Resources.WHEAT}; 
	final static int MAX_WORKERS = 100;
	
	public WheatBusiness(){
		super(INPUT_TYPES, OUTPUT_TYPES);
		maxWorkerCount = MAX_WORKERS;
	}
	
	//Test Method
	public void addInput(int amount){
		warehouse.addInput(Resources.TOOLS, amount);
	}
	
	public void hire(int workerNumber){
		if(workerNumber + currentWorkerCount <= maxWorkerCount){
			currentWorkerCount += workerNumber;	
		}else{
			currentWorkerCount = maxWorkerCount;
		}
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
