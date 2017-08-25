package behaviors;

import enums.Resources;
import Main.Citizen;
import Main.SellOffer;
import Main.BuyOffer;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;


public class CitizenBrain extends Brain {
	private Citizen cRef; //we gotta make decisions about ourself, so we gotta know ourself
	private int needFoodLevel = 0;	//0 = No need, 1 = Some Need, 2 = Much Need, 3 = Desperate
	private SummaryStatistics foodSellStats;
	
	private BuyOffer standingOffer = null;
	
	public CitizenBrain(Citizen c){
		this.cRef = c;
		this.foodSellStats = new SummaryStatistics();
	}
	
	@Override
	public void behave() {
		// TODO Auto-generated method stub
		decideFoodNeed();
		setBuyFoodOffer();
	}
	
	private void decideFoodNeed(){
		int foodAmount = cRef.getPocket().checkInputResource(Resources.WHEAT);	//We could also check the "output" they're both the same map :( 
		int timeToNextMeal = cRef.TIME_PER_MEAL - cRef.getLastMealTime();
		if(foodAmount == 0){
			if(timeToNextMeal < 3){
				needFoodLevel = 3;	
			}else if(timeToNextMeal < 6){
				needFoodLevel = 2;
			}else if(timeToNextMeal <= 8){
				needFoodLevel = 1;
			}
		}else if(foodAmount == 1){
			needFoodLevel = 1;
		}else{
			needFoodLevel = 0;
		}
	}
	
	//This will set the standingOffer based on all the logic you can imagine
	private BuyOffer setBuyFoodOffer(){	
		if(standingOffer != null){
			standingOffer.returnOffer();
		}
		
		BuyOffer bo = new BuyOffer();
		bo.setResourceToBuy(Resources.WHEAT);
		bo.setResourcesLeftToBuy(1);
		
		if(foodSellStats.getN() > 0){
			double currentGold = this.cRef.getGold();	
			//Should use factory + strategy pattern
			switch(needFoodLevel){
				case 0:
				case 1:
					if(currentGold * .25 > foodSellStats.getMean()){
						bo.setPricePerResource((int)foodSellStats.getMean());//Rounds the mean down to an int					}
					}else{
						bo.setPricePerResource((int)(currentGold * .25));
					}
				case 2:
					if(currentGold * .50 > (foodSellStats.getMean() + foodSellStats.getStandardDeviation())){
						bo.setPricePerResource((int) (foodSellStats.getMean() + foodSellStats.getStandardDeviation()));					
					}else{
						bo.setPricePerResource((int)(currentGold * .50));	
					}
				case 3:
					if(currentGold > (foodSellStats.getMean() + 2*foodSellStats.getStandardDeviation())){
						bo.setPricePerResource((int) (foodSellStats.getMean() + 2*foodSellStats.getStandardDeviation()));
					}else{
						bo.setPricePerResource((int)currentGold);
					}
				default:
			
			}
		}else{
			//Base price is 4 gold per wheat (a tool is basically always worth 15 gold and 1 tool = 4 Wheat)
			bo.setPricePerResource(4);
		}
		
		standingOffer = bo;
		return bo;
	}

	//If no appropriate sells, make buy offer
}
