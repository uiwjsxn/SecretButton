/*find the hiden window*/
package test;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Point2D;
import machine.StageMachine;
import machine.MouseMoveMachine;
import machine.MouseLeft;
import machine.MouseRight;
import machine.MouseUp;
import machine.MouseDown;

public class Test extends Application{
	private StageMachine machine;
	private double oldX;
	private double oldY;
	
	private void setMachine(Scene scene, Node start, Node end, Button hidenButton){
		start.setOnMouseClicked(e->{
			oldX = e.getSceneX();
			oldY = e.getSceneY();
			machine.start();
			System.out.println("the machine has started");
		});
		end.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			System.out.println("the machine has done");
			if(machine.done()){
				hidenButton.setVisible(true);
				System.out.println("true");
			}
		});
		scene.addEventHandler(MouseEvent.MOUSE_MOVED, e->{
			double newX = e.getSceneX();
			double newY = e.getSceneY();
			machine.next(oldX, oldY, newX, newY);
			oldX = newX;
			oldY = newY;
		});
	}
	private void setRoute(double left,double down,double right,double up){ //usage for StageMachine
		MouseLeft mouseLeft = new MouseLeft(left);
		MouseDown mouseDown = new MouseDown(down);
		MouseRight mouseRight = new MouseRight(right);
		machine = new StageMachine(mouseRight, mouseDown, mouseLeft);
		mouseLeft.setMachine(machine);
		mouseDown.setMachine(machine);
		mouseRight.setMachine(machine);
	}
	
	private void createHidenStage(){
		Stage hidenStage = new Stage();
		Label label = new Label("You have found the hiden Window");
		label.setId("hidenLabel");
		Button closeBtn = new Button("close");
		closeBtn.setOnAction(e->hidenStage.close());
		VBox root = new VBox(label);
		root.getStyleClass().add("hidenVBox");
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("test/test.css");
		hidenStage.setScene(scene);
		hidenStage.setTitle("Hiden Window");
		hidenStage.show();
	}
	@Override public void start(Stage stage){
		Text text = new Text("欢迎登陆");
		System.out.println(text.getStyleClass());
		text.getStyleClass().add("myText");
		
		Label nameLbl = new Label("用户名:");
		Label passwdLbl = new Label("密码:");
		TextField nameTfd = new TextField();
		TextField passwdTfd = new TextField();
		
		
		
		VBox labelVBox = new VBox(nameLbl,passwdLbl);
		VBox textFieldVBox = new VBox(nameTfd,passwdTfd);
		labelVBox.getStyleClass().add("vbox");
		textFieldVBox.getStyleClass().add("vbox");
		
		Button loginBtn = new Button("登入");
		loginBtn.setVisible(false);
		loginBtn.setOnAction(e->{
			loginBtn.setVisible(false);
			createHidenStage();
		});
		HBox content = new HBox(labelVBox,textFieldVBox);
		VBox root = new VBox(text,content,loginBtn);
		
		
		Scene scene = new Scene(root,400,170);
		scene.getStylesheets().add("test/test.css");
		stage.setScene(scene);
		stage.setTitle("Main Window");
		stage.show();
		
		double width = nameTfd.getWidth() + nameLbl.getWidth()/2.0;
		Point2D local2Scene = nameTfd.localToScene(nameTfd.getLayoutBounds().getMinX(),nameTfd.getLayoutBounds().getMinY());
		Point2D name2passwd = passwdTfd.sceneToLocal(local2Scene);
		double height = -name2passwd.getY();
		/*System.out.println("width: " + width + "height: " + height);
		System.out.println(nameTfd.getWidth() + ", " + nameLbl.getWidth());
		System.out.println(nameTfd.getHeight());*/
		setRoute(width,height,width,height);
		setMachine(scene,nameLbl,passwdLbl,loginBtn);
	}
	public static void main(String[] args){
		Application.launch(args);
	}	
}