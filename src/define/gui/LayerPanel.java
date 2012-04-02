package define.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LayerPanel extends JPanel {
	
	private JLabel layerNameLabel = new JLabel("Naam layer");
	private JLabel layerLevelLabel = new JLabel("Level layer");
	
	private JTextField layerNameField = new JTextField(10);
	private JTextField layerLevelField = new JTextField(10);
	
	public LayerPanel() {
		this.setSize(200,100);
	}
}
