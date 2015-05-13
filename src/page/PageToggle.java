package page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import student.Student;
import table.ChangerRecords;
import table.ExamTableModel;
import constants.MenuName;
import constants.Path;

public class PageToggle {
	private JButton leftButton;
	private JButton leftStartButton;
	private JButton rightButton;
	private JButton rightEndButton;
	private Page page;
	private JLabel numberAvailableRecordsLabel;
	private JTable table;
	private JComboBox<Integer> numberRecords;
	/*private int currentRecord = 0;
	private boolean left;
	private boolean right;
*/
	
	
	public JPanel addPanel(JTable table) {
		ExamTableModel examTableModel = (ExamTableModel) table.getModel();
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

		Color darkGreen = new Color(17, 111, 21);
		
		numberRecords = new JComboBox<Integer>(MenuName.MARK);;
		numberRecords.setSelectedItem(2);
		
		JPanel numberAvailableRecordsPanel = new JPanel();
		numberAvailableRecordsPanel.setLayout(new FlowLayout());

		JLabel numberAvailableRecords = new JLabel(
				"Number of the available records");
		numberAvailableRecordsLabel = new JLabel(String.valueOf(examTableModel
				.getStudentList().size()));
		
		
		JLabel numberRecordsLabel = new JLabel("Number of Records");
		numberRecordsLabel.setForeground(darkGreen);
		numberAvailableRecords.setForeground(darkGreen);
		numberAvailableRecordsLabel.setForeground(Color.BLUE);
		numberAvailableRecordsLabel.setFont(new Font("Calibri", Font.BOLD, 20));

		numberAvailableRecordsPanel.add(numberAvailableRecords);
		numberAvailableRecordsPanel.add(numberAvailableRecordsLabel);
		
		panel.add(numberRecordsLabel);
		panel.add(numberRecords);
		panel.add(numberAvailableRecordsPanel);
		
		numberRecords.addActionListener(new ChangerRecords(table,
				numberRecords, leftStartButton));
		
		
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
*/
	public JButton getLeftStartButton() {
		return leftStartButton;
	}

	public void addTableListener(List<Student> list, JTable table) {
		table.addContainerListener(new ContainerListener() {

			@Override
			public void componentRemoved(ContainerEvent e) {
				/*
				 * model = (ExamTableModel) table.getModel();
				 * numberAvailableRecordsLabel.setText(String.valueOf(model
				 * .getStudentList().size()));
				 */
				// pageToggle.addButtonActionListener(table);
				numberAvailableRecordsLabel.setText(String.valueOf(list.size()));
			}

			@Override
			public void componentAdded(ContainerEvent e) {
				/*
				 * model = (ExamTableModel) table.getModel();
				 * numberAvailableRecordsLabel.setText(String.valueOf(model
				 * .getStudentList().size()));
				 */
				// pageToggle.addButtonActionListener(table);
				numberAvailableRecordsLabel.setText(String.valueOf(list.size()));
			}
		});
	}
	
	/*public boolean isLeft() {
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
	
	public JComboBox<Integer> getNumberRecords(){
		return numberRecords;
	}
}
