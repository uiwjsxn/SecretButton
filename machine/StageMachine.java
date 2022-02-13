package machine;

public class StageMachine{
	private MouseMoveMachine[] stages;
	private int machineNum;
	private int index = 0;
	boolean done = false;
	boolean begin = false;
	public StageMachine(MouseMoveMachine... newStages){
		stages = newStages;
		machineNum = stages.length;
		for(MouseMoveMachine stage : stages){
			System.out.println(stage.getClass().getSimpleName() + ", dist: ");
		}
		System.out.println(machineNum);
	}
	protected void nextStage(){ //provided to MouseMoveMachine for calling
		if(index+1 < machineNum){
			++index;
			System.out.println("next Stage set");
		}else{
			done = true;
			System.out.println("done is set");
		}
	}
	protected void reset(){
		index = 0;
		done = false;
		begin = false;
		for(MouseMoveMachine stage : stages){
			stage.reset();
		}
	}
	
	public void next(double oldX, double oldY, double newX, double newY){
		if(begin && !stages[index].nextStep(oldX, oldY, newX, newY)){
			reset();
		}
	}
	public boolean done(){
		boolean res = done;
		reset();
		return res;
	}
	public void start(){
		reset();
		begin = true;
	}
}