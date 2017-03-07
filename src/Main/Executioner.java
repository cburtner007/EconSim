package Main;
import java.util.List;


public class Executioner implements Agent{

	double chanceToExecute;
	
	public Executioner(double ce){
		chanceToExecute = ce;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public boolean shouldExecute(List<Citizen> currentPopulace, int currentFoodStores){
		boolean returnFlag = false;
		int foodToConsume = 0;
		
		for(Citizen person : currentPopulace){
			foodToConsume += person.getFoodConsume();
		}
		
		if(foodToConsume > currentFoodStores){
			returnFlag = true;
		}
		
		return returnFlag;
	}
	
	public void thinPopulace(List<Citizen> currentPopulace, int currentFoodStores){
		
		while(shouldExecute(currentPopulace, currentFoodStores)){
			execute(currentPopulace);
		}
	}
	
	private Citizen execute(List<Citizen> currentPopulace){
		Citizen personToExecute = null;
		int i;
		
		//Don't stop until he has decided on who to kill
		while(personToExecute == null){
			
			for(i = 0; i < currentPopulace.size() ; i++){
				//Roll to see if we will execute this person
				if(World.random.nextDouble() < chanceToExecute){
					personToExecute = currentPopulace.remove(i);
				}					
			}

		}
		
		return personToExecute;
	}
}
