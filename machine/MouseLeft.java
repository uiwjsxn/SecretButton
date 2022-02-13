package machine;

public class MouseLeft extends MouseMoveMachine{
	public MouseLeft(double dist,double tol){
		super(dist,tol);
	}
	public MouseLeft(double dist){
		super(dist);
	}
	public boolean nextStep(double oldX, double oldY, double newX, double newY){
		double stepErr = newY - oldY;
		double stepDist = oldX - newX;
		return super.nextStep(stepDist,stepErr);
	}
}
