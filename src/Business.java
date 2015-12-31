
public class Business {
	protected Storage warehouse;
	protected int currentWorkerCount; 
	protected int maxWorkerCount; 
	
	public Business(Resources[] inResTypes, Resources[] outResTypes){
		warehouse = new Storage(inResTypes, outResTypes);
	}
}
