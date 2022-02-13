package machine;

public class MouseDown extends MouseMoveMachine{
	public MouseDown(double dist,double tol){
		super(dist,tol);
	}
	public MouseDown(double dist){
		super(dist);
	}
	public boolean nextStep(double oldX, double oldY, double newX, double newY){
		double stepErr = newX - oldX;
		double stepDist = newY - oldY;
		return super.nextStep(stepDist,stepErr);
	}
}