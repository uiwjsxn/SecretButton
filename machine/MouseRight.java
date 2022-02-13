package machine;

public class MouseRight extends MouseMoveMachine{
	public MouseRight(double dist,double tol){
		super(dist,tol);
	}
	public MouseRight(double dist){
		super(dist);
	}
	public boolean nextStep(double oldX, double oldY, double newX, double newY){
		double stepErr = newY - oldY;
		double stepDist = newX - oldX;
		return super.nextStep(stepDist,stepErr);
	}
}