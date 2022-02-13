package machine;

public class MouseUp extends MouseMoveMachine{
	public MouseUp(double dist,double tol){
		super(dist,tol);
	}
	public MouseUp(double dist){
		super(dist);
	}
	public boolean nextStep(double oldX, double oldY, double newX, double newY){
		double stepErr = newX - oldX;
		double stepDist = oldY - newY;
		return super.nextStep(stepDist,stepErr);
	}
	
}