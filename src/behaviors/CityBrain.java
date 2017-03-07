package behaviors;
import Main.City;
import Main.WheatBusiness;
public class CityBrain {

	private City cityReference;
	
	public CityBrain(City thisCity){
		cityReference = thisCity; 
	}
	
	public boolean startWheatBusiness(){
		boolean returnFlag = false;
		if(cityReference.getUnemployedCitizens() > 0 && 
				(cityReference.getAvailableSquareFeet() - WheatBusiness.SQUARE_FOOTAGE) >= 0){
			returnFlag = true; 
		}
		return returnFlag; 
	}
}
