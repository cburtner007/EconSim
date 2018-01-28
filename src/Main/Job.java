package Main;
/**
 * Simple wrapper for citizens to allow businesses to interact with them
 * @author Curtis guy
 *
 */
public class Job {
	private Business employer;
	private Citizen employee;
	private int rate;

	public Job(Citizen c, int rate){
		employee = c;
		c.setJob(this);
		this.rate = rate;
	}
	
	public Job(Citizen c, int rate, Business employer){
		employee = c;
		c.setJob(this);
		this.rate = rate;
	}
	
	public Job(Citizen c){
		employee = c;
		c.setJob(this);
		rate = 0;
	}

	public void terminate() {
		employee.setJob(null);
		employer.removeJob(this);
		employee = null;
		employer = null;
	}

	public Citizen getEmployee() {
		return employee;
	}
	
	public int getRate(){
		return rate;
	}
	
	public void setRate(int r)
	{
		rate = r;
	}
}
