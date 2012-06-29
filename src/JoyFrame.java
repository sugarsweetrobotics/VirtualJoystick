import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JoyFrame extends JFrame {

	private int state = DEF;

	final public static int UP = 0;
	final public static int DOWN = 1;
	final public static int RIGHT = 2;
	final public static int LEFT = 3;
	final public static int DEF = 4;

	public int getState() {
		return state;
	}
	
	public void setState(int state) {
		this.state = state;
	}

	public class JoyPanel extends JPanel {

		public JoyPanel() {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new GridLayout(3, 3));

			this.add(new JLabel("Up:(0,1)"));
			this.add(new JButton(new AbstractAction("Up") {
				public void actionPerformed(ActionEvent arg0) {
					state = UP;
				}
			}));
			this.add(new JLabel("Right:(1,0)"));
			this.add(new JButton(new AbstractAction("Left") {
				public void actionPerformed(ActionEvent arg0) {
					state = LEFT;
				}
			}));
			this.add(new JButton(new AbstractAction("Default") {
				public void actionPerformed(ActionEvent arg0) {
					state = DEF;
				}
			}));
			this.add(new JButton(new AbstractAction("Right") {
				public void actionPerformed(ActionEvent arg0) {
					state = RIGHT;
				}
			}));
			this.add(new JLabel("Left:(-1,0)"));
			this.add(new JButton(new AbstractAction("Down") {
				public void actionPerformed(ActionEvent arg0) {
					state = DOWN;
				}
			}));
			this.add(new JLabel("Down:(0,-1)"));

		}
	}

	public JoyFrame() {
		initPresentation();
		setLocation(740, 0);
		setSize(300, 300);
		setVisible(true);
	}

	public void initPresentation() {

		setContentPane(new JoyPanel());
	}

	public static void main(String[] s) {
		JoyFrame j = new JoyFrame();
	}

}
