import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NumberKeyPad extends JPanel {
	private JButton [] buttons;
	private InputGUI parent;
	public NumberKeyPad(InputGUI dad){
		parent = dad;
		this.setLayout(new GridLayout(4,3));
		buttons = new JButton[10];
		for(int i = 0; i < 10; i++){
			buttons[i] = new JButton(""+i);
			buttons[i].setFont(new Font("Serif", Font.PLAIN, 25));
			buttons[i].addActionListener(parent);
		}
		buttons[0].setPreferredSize(new Dimension(3000,100));
		add(buttons[7]);
		add(buttons[8]);
		add(buttons[9]);
		add(buttons[4]);
		add(buttons[5]);
		add(buttons[6]);
		add(buttons[1]);
		add(buttons[2]);
		add(buttons[3]);
		add(new JButton(""));
		add(buttons[0]);
		add(new JButton(""));
	}
}
