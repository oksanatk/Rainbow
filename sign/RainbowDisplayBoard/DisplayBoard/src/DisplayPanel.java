import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DisplayPanel extends JPanel {
	private JTextField outputField;
	private JTextField inputField;
	public DisplayPanel(InputGUI dad){
		outputField = new JTextField();
		outputField.setFont(new Font("Serif", Font.BOLD, 50));
		inputField = new JTextField();
		inputField.setFont(new Font("Serif", Font.PLAIN, 40));
		outputField.setEditable(false);
		inputField.setEditable(false);
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1,5,10,10));
		top.add(outputField);
		top.add(new JLabel());
		this.setLayout(new GridLayout(2,1));
		this.add(top);
		this.add(inputField);
		
	}
	
	public void deleteChar(){
		inputField.setText(inputField.getText().substring(0,inputField.getText().length()-1));
	}
	
	public void clearOut(){
		outputField.setText("");
		inputField.setText("");
	}
	public void setNumber(){
		outputField.setText(inputField.getText());	
		inputField.setText("");
	}
	public void addToInput(String ch){
		inputField.setText(inputField.getText()+ ch);
	}
	public String getOutput(){
		return outputField.getText();
	}
	
}
