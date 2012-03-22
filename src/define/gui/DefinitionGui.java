package define.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DefinitionGui extends JPanel {
	
	public DefinitionGui() {
		
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		DefinitionGui gui = new DefinitionGui();
		frame.add(gui);
		frame.show();
	}
}
