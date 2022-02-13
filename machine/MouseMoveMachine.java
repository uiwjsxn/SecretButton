package machine;

public abstract class MouseMoveMachine{
	private StageMachine machine;
	//immutable fields
	private double constDist;
	private double tolerance;
	//mutable fields
	private double distance;
	private double err = 0;
	
	protected void reset(){
		distance = constDist;
		err = 0;
	}
	public MouseMoveMachine(double dist,double tol){
		this(dist);
		tolerance = tol;
	}
	public MouseMoveMachine(double dist){
		constDist = dist;
		distance = dist;
		tolerance = dist*0.05;
		if(tolerance < 10.0){
			tolerance = 10;
		}
	}
	public void setMachine(StageMachine machine_){
		machine = machine_;
	}
	//public abstract bool nextStep();
	public boolean nextStep(double stepDist,double stepErr){
		double newErr = stepErr + err;
		double newDist = distance - stepDist;
		//stepDist < 0:	 you can not turn back
		//newDist < -tolerance:	when you have done walking all the distance, you can go further than tolerance	(newDist is the left distance need to walk)
		//newErr: for example, if you use mouse to move right for some distance, you can not move mouse up or down, the newErr indicates the distance you have mod up or down
		if(stepDist < 0 || newDist < -tolerance/2.0 || newErr > tolerance || newErr < -tolerance){
			return false;
		}
		err = newErr;
		distance = newDist;
		System.out.println("err: " + err + ", distance: " + distance);
		if(newDist <= tolerance/2.0 && newDist >= -tolerance/2.0){ //enter into next stage
			System.out.println("next stage launched");
			machine.nextStage();
		}
		return true;
	}
	public abstract boolean nextStep(double oldX, double oldY, double newX, double newY);
}	