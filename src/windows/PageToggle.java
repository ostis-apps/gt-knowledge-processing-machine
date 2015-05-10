package windows;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import table.Page;
import constants.Path;

public class PageToggle {
	private JButton leftButton;
	private JButton leftStartButton;
	private JButton rightButton;
	private JButton rightEndButton;
	private Page page;

	public PageToggle(JPanel panel) {
		leftButton = new JButton(new ImageIcon(Path.LEFT_ICON.getPath()));
		leftStartButton = new JButton(new ImageIcon(
				Path.LEFT_START_ICON.getPath()));
		rightButton = new JButton(new ImageIcon(Path.RIGHT_ICON.getPath()));
		rightEndButton = new JButton(new ImageIcon(
				Path.RIGHT_END_ICON.getPath()));

		leftButton.setActionCommand("Left");
		leftStartButton.setActionCommand("Left Start");
		
		rightButton.setActionCommand("Right");
		rightEndButton.setActionCommand("Right End");

		panel.add(leftStartButton);
		panel.add(leftButton);
		panel.add(rightButton);
		panel.add(rightEndButton);
	}

	public void addButtonActionListener(JTable table) {
		leftButton.removeActionListener(page);
		leftStartButton.removeActionListener(page);
		rightButton.removeActionListener(page);
		rightEndButton.removeActionListener(page);

		page = new Page(table);
		leftButton.addActionListener(page);
		leftStartButton.addActionListener(page);
		rightButton.addActionListener(page);
		rightEndButton.addActionListener(page);
		table.requestFocusInWindow();
	}

	public JButton getLeftStartButton() {
		return leftStartButton;
	}

}
