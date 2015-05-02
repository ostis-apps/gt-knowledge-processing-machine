package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import constants.MenuName;
import student.StudentCreator;

public class ExamDialog {
	private int numberExams;
	private JTable table;

	public ExamDialog(JFrame frame, JTable table, int numberExams) {
		this.numberExams = numberExams;
		createExamDialog(frame, table);
	}

	public void setNumberExams(int number) {
		numberExams = number;
	}

	void createExamDialog(JFrame frame, JTable table) {
		JDialog examDialog = new JDialog(frame, "Exams", false);
		examDialog.setSize(330, 300);
		examDialog.setLocationRelativeTo(frame);
		examDialog.setVisible(true);
		examDialog.setLayout(new BorderLayout());
		examDialog.setResizable(false);

		JPanel jPanel = new JPanel();
		JScrollPane pane = new JScrollPane(jPanel);
		jPanel.setLayout(new GridBagLayout());
		examDialog.add(pane);

		JLabel firstNameLabel = new JLabel("First Name:");
		JLabel secondNameLabel = new JLabel("Second Name:");
		JLabel thirdNameLabel = new JLabel("Third Name:");
		JLabel numberGroup = new JLabel("Number Group:");
		JLabel incorrectLabel = new JLabel("       Incorrect");
		incorrectLabel.setForeground(Color.RED);
		incorrectLabel.setVisible(false);

		JTextField firstNameField = new JTextField(5);
		JTextField secondNameField = new JTextField(5);
		JTextField thirdNameField = new JTextField(5);
		JTextField numberGroupField = new JTextField(5);

		jPanel.add(firstNameLabel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));
		jPanel.add(firstNameField, new GridBagConstraints(1, 0, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		jPanel.add(secondNameLabel, new GridBagConstraints(0, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));
		jPanel.add(secondNameField, new GridBagConstraints(1, 1, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		jPanel.add(thirdNameLabel, new GridBagConstraints(0, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));
		jPanel.add(thirdNameField, new GridBagConstraints(1, 2, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		jPanel.add(numberGroup, new GridBagConstraints(0, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));
		jPanel.add(numberGroupField, new GridBagConstraints(1, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 0));
		jPanel.add(incorrectLabel, new GridBagConstraints(2, 3, 1, 1, 0, 0,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));

		List<JTextField> nameExamList = new ArrayList<JTextField>();
		List<JComboBox> markList = new ArrayList<JComboBox>();

		for (int i = 0; i < numberExams; i++) {
			JLabel nameExamLabel = new JLabel("       Exam");
			JLabel markExamLabel = new JLabel("       Mark: ");
			JTextField nameExamField = new JTextField(7);
			JComboBox<Integer> markComboBox= new JComboBox<Integer>(MenuName.MARK);
			
			jPanel.add(nameExamLabel, new GridBagConstraints(0, i + 4, 1, 1, 0,
					0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(2, 2, 2, 2), 0, 10));
			jPanel.add(nameExamField, new GridBagConstraints(1, i + 4, 1, 1, 0,
					0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(2, 2, 2, 2), 0, 0));
			jPanel.add(markExamLabel, new GridBagConstraints(2, i + 4, 1, 1, 0,
					0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(2, 2, 2, 2), 0, 10));
			jPanel.add(markComboBox, new GridBagConstraints(3, i + 4, 1, 1, 0,
					0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
					new Insets(2, 2, 2, 2), 0, 0));

			nameExamList.add(nameExamField);
			markList.add(markComboBox);
		}

		JButton oKButton = new JButton("OK");
		jPanel.add(oKButton, new GridBagConstraints(1, numberExams + 4, 2, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));

		oKButton.addActionListener(new StudentCreator(examDialog, table,
				firstNameField, secondNameField, thirdNameField,
				numberGroupField, nameExamList, markList, incorrectLabel));
	}

}
