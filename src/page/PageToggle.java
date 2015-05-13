package page;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import constants.Path;

public class PageToggle {
	private JButton leftButton;
	private JButton leftStartButton;
	private JButton rightButton;
	private JButton rightEndButton;
	private Page page;
	/*private int currentRecord = 0;
	private boolean left;
	private boolean right;
*/
	public JPanel addPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

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

		return panel;
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

	/*public int getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
	}

	public JButton getLeftStartButton() {
		return leftStartButton;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isRight() {
		return right;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}*/
}
