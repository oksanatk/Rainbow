import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class InputGUI extends JFrame implements ActionListener {
	// TODO make this a clickable app from UDOO and put it onto UDOO
	private NumberKeyPad myNumPad;
	private OperationPad myOpPad;
	private DisplayPanel myDisplay;
	private final String beginPath = "/sys/class/gpio/gpio";

	public InputGUI(String title) {
		super(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 800);
		myNumPad = new NumberKeyPad(this);
		this.setLayout(new BorderLayout());
		this.add(myNumPad, BorderLayout.CENTER);
		myOpPad = new OperationPad(this);
		this.add(myOpPad, BorderLayout.EAST);
		myDisplay = new DisplayPanel(this);
		this.add(myDisplay, BorderLayout.NORTH);
	}

	public void actionPerformed(ActionEvent event) {
		JButton src = (JButton) event.getSource();
		String label = src.getText();

		if (label.equals("Delete")) {
			myDisplay.deleteChar();
			
		} else if (label.equals("Clear")) {
			myDisplay.clearOut();
			clearBoardAction();
			
		} else if (label.equals("Enter")) {
			myDisplay.setNumber();
			setNumber(myDisplay.getOutput().substring(0,2));
			
			
		} else {
			myDisplay.addToInput(label);
		} 
	}
	public int getGpioNum(int num){
		if (num==0){
			return 116;
		}else if (num==1){
			return 112;
		}else if (num==2){
			return 20;
		}else if (num==3){
			return 16;
		}else if (num==4){
			return 17;
		}else if (num==5){
			return 18;
		}else if (num==6){
			return 41;
		}else if (num==7){
			return 42;
		}else if (num==8){
			return 21;
		}else if (num==9){
			return 19;
		}else if (num==10){
			return 1;
		}else if (num==11){
			return 9;
		}else if (num==12){
			return 3;
		}else if (num==13){
			return 40;
		}else{
			return -1;
		}
	}
	public void printToFile(String filename, String contents){
		
		try{
			File gpio = new File(beginPath+filename);
			PrintWriter out = new PrintWriter(gpio);
			out.println(contents);
			out.close();
		}catch(FileNotFoundException e){
			System.out.println(beginPath+filename+" wasn't found. Please try again");
		}
	}
	
	
	public void clearBoardAction(){
		for(int i=0;i<14;i++){
			// print in in direction file, turns led off
			printToFile(getGpioNum(i)+"/direction","in");	
		}
	}
	public void turnOnSegment(int seg){
		// print out in direction file
		if (getGpioNum(seg)==-1){
			System.out.println("Check wiring, port number "+seg+" not found.");
		}else{
			printToFile(getGpioNum(seg)+"/direction","out");
		}
	}
	
	public void setNumber(String numberStr){
		// get the needed 7 segments, print out in direction files
		numberStr = numberStr.trim();
		if (numberStr.length()>2){
			numberStr = numberStr.substring(0, 2);
		}
		if (numberStr.length()==1){
			numberStr = "0"+numberStr;
		}
		boolean first = true;
		
		for(int i=0;i<2;i++){
			
			int[] segments = getSegments(Integer.parseInt(numberStr.substring(i,i+1)), first);
			if (segments[0]==-1){
				System.out.println("Something went wrong with the code input. Check code for single digit input");
			}else{
			for(int seg:segments){
				turnOnSegment(seg);
				}
			}
			
			first = false;
		}
	}
	
	public int[] getSegments(int num, boolean first){
		// return corresponding segments for ONE digit only
	
		if(num==0){
			if(first){
				return new int[] {0,2,3,4,5,6};
			}
			return new int[] {7,9,10,11,12,13};
		}else if(num==1){
			if(first){
				return new int[] {5,6};
			}
			return new int[] {12,13};
		}else if(num==2){
			if(first){
				return new int[] {0,1,2,4,5};
			}
			return new int[] {7,8,9,11,12};
		}else if(num==3){
			if(first){
				return new int[] {0,1,2,5,6};
			}
			return new int[] {7,8,9,12,13};
		}else if(num==4){
			if(first){
				return new int[] {1,3,5,6};
			}
			return new int[] {8,10,12,13};
		}else if(num==5){
			if(first){
				return new int[] {0,1,2,3,6};
			}
			return new int[] {7,8,9,10,13};
		}else if(num==6){
			if(first){
				return new int[] {0,1,2,3,4,6};
			}
			return new int[] {7,8,9,10,11,13};
		}else if(num==7){
			if(first){
				return new int[] {0,5,6};
			}
			return new int[] {7,12,13};
		}else if(num==8){
			if(first){
				return new int[] {0,1,2,3,4,5,6};
			}
			return new int[] {7,8,9,10,11,12,13};
		}else if(num==9){
			if(first){
				return new int[] {0,1,3,5,6};
			}
			return new int[] {7,8,10,12,13};
		}
	
		return new int[] {-1};
	}
	
	public static void main(String[] args) {
		InputGUI frame = new InputGUI("Display App");
		frame.setVisible(true);
	}
}
