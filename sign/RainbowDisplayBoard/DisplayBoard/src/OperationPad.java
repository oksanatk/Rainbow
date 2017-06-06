import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class OperationPad extends JPanel {
	private JButton [] buttons;
	private InputGUI parent;
	public OperationPad(InputGUI dad){
		parent = dad;
		this.setLayout(new GridLayout(3,1));
		buttons = new JButton[5];
		buttons[0] = new JButton("Delete");
		buttons[1] = new JButton("Clear");
		buttons[2] = new JButton("Enter");
		for(int i = 0; i < 3; i++){
			buttons[i].setFont(new Font("Serif", Font.PLAIN, 40));
			buttons[i].addActionListener(parent);
			this.add(buttons[i]);
		}
	}
}
