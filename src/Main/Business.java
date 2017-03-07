package Main;

import enums.Resources;

public abstract class Business {
	protected Storage warehouse;
	protected int currentWorkerCount; 
	protected int maxWorkerCount; 
	protected int squareFootage;
	protected int ticksToProduce;
	
	public Business(Resources[] inResTypes, Resources[] outResTypes, int sqFootage, int ticksToProduce, int initialCapacity){
		warehouse = new Storage(inResTypes, outResTypes, initialCapacity);
		squareFootage = sqFootage;
		this.ticksToProduce = ticksToProduce; 
	}
	
	public abstract void tick();
	
	public int getSquareFootage(){
		return squareFootage;
	}
}
