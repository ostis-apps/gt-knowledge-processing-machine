package page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import table.ChangerRecords;
import table.ExamTableModel;
import constants.MenuName;
import constants.Path;

public class PageToggle {
	private JButton leftButton;
	private JButton leftStartButton;
	private JButton rightButton;
	private JButton rightEndButton;
	private JLabel numberAvailableRecordsLabel;
	private JComboBox<Integer> numberRecords;

	private LeftStartButtonPage leftStartButtonPage;
	private LeftButtonPage leftButtonPage;
	private RightButtonPage rightButtonPage;
	private RightEndButtonPage rightEndButtonPage;

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

		panel.add(leftStartButton);
		panel.add(leftButton);
		panel.add(rightButton);
		panel.add(rightEndButton);

		Color darkGreen = new Color(17, 111, 21);

		numberRecords = new JComboBox<Integer>(MenuName.MARK);
		numberRecords.setSelectedItem(2);

		JLabel numberAvailableRecords = new JLabel(
				"Number of the available records");
		numberAvailableRecordsLabel = new JLabel(String.valueOf(examTableModel
				.getStudentList().size()));

		JLabel numberRecordsLabel = new JLabel("Number of Records");
		numberRecordsLabel.setForeground(darkGreen);
		numberAvailableRecords.setForeground(darkGreen);
		numberAvailableRecordsLabel.setForeground(Color.BLUE);
		numberAvailableRecordsLabel.setFont(new Font("Calibri", Font.BOLD, 20));

		panel.add(numberRecordsLabel);
		panel.add(numberRecords);
		panel.add(numberAvailableRecords);
		panel.add(numberAvailableRecordsLabel);

		numberRecords.addActionListener(new ChangerRecords(table,
				numberRecords, leftStartButton));

		return panel;
	}

	public void addButtonActionListener(JTable table) {

		leftButton.removeActionListener(leftButtonPage);
		leftStartButton.removeActionListener(leftStartButtonPage);
		rightButton.removeActionListener(rightButtonPage);
		rightEndButton.removeActionListener(rightEndButtonPage);

		PageModel pageModel = new PageModel(table);

		leftButtonPage = new LeftButtonPage(pageModel);
		leftStartButtonPage = new LeftStartButtonPage(pageModel);
		rightButtonPage = new RightButtonPage(pageModel);
		rightEndButtonPage = new RightEndButtonPage(pageModel);

		// page = new Page(table);
		leftButton.addActionListener(leftButtonPage);
		leftStartButton.addActionListener(leftStartButtonPage);
		rightButton.addActionListener(rightButtonPage);
		rightEndButton.addActionListener(rightEndButtonPage);
		table.requestFocusInWindow();
	}

	public JButton getLeftStartButton() {
		return leftStartButton;
	}

	public JComboBox<Integer> getNumberRecords() {
		return numberRecords;
	}

	public void setNumberAvailableRecords(int number) {
		numberAvailableRecordsLabel.setText(String.valueOf(number));
	}
}
