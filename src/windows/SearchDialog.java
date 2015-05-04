package windows;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import student.Student;
import table.ColumnModel;
import table.ExamTableModel;
import table.GroupableTableHeader;
import table.Page;
import constants.MenuName;
import constants.Path;

public class SearchDialog {
	private JTable table;
	private JTable searchTable;
	private JDialog searchDialog;
	private JFrame frame;
	private List<Student> studentList;
	private SearcherAndRemover searcherAndRemover;
	private boolean remove;
	private JComboBox<Integer> numberRecords;;
	private ExamTableModel searchTableModel;
	private JButton leftStartButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton rightEndButton;

	public SearchDialog(JFrame frame, JTable table, List<Student> studentList) {
		this.table = table;
		this.frame = frame;
		this.studentList = studentList;
		creteSearchDialog();
	}

	void creteSearchDialog() {
		searchDialog = new JDialog(frame, "Search", false);
		searchDialog.setSize(400, 400);
		searchDialog.setLocationRelativeTo(frame);
		searchDialog.setVisible(true);
		searchDialog.setLayout(new BorderLayout());
		searchDialog.setResizable(false);

		JPanel searchCriterionPanel = new JPanel();
		JPanel searchPanel = new JPanel();
		JPanel pagePanel = new JPanel();
		JPanel criterionPanel = new JPanel();

		criterionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		pagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		searchDialog.add(pagePanel, BorderLayout.SOUTH);
		searchDialog.add(searchPanel, BorderLayout.NORTH);

		searchPanel.setLayout(new BorderLayout());
		searchCriterionPanel.setLayout(new GridBagLayout());
		searchPanel.add(searchCriterionPanel, BorderLayout.CENTER);
		searchPanel.add(criterionPanel, BorderLayout.SOUTH);

		leftButton = new JButton(new ImageIcon(Path.LEFT_ICON.getPath()));
		leftStartButton = new JButton(new ImageIcon(
				Path.LEFT_START_ICON.getPath()));
		rightButton = new JButton(new ImageIcon(Path.RIGHT_ICON.getPath()));
		rightEndButton = new JButton(new ImageIcon(
				Path.RIGHT_END_ICON.getPath()));

		pagePanel.add(leftStartButton);
		pagePanel.add(leftButton);
		pagePanel.add(rightButton);
		pagePanel.add(rightEndButton);

		createTable();
		addCriterion(searchCriterionPanel, criterionPanel);

		leftButton.setActionCommand("Left");
		leftStartButton.setActionCommand("Left Start");
		rightButton.setActionCommand("Right");
		rightEndButton.setActionCommand("Right End");

		/*
		 * Page page = new Page(searchTable);
		 * leftStartButton.addActionListener(page);
		 * leftButton.addActionListener(page);
		 * rightButton.addActionListener(page);
		 * rightEndButton.addActionListener(page);
		 */
	}

	@SuppressWarnings("serial")
	void createTable() {

		ExamTableModel examTableModel = (ExamTableModel) table.getModel();
		searchTableModel = new ExamTableModel(examTableModel.getNumberExams());
		// studentList = examTableModel.getStudentList();
		searchTableModel.setStudentList(studentList);
		// searchTableModel.getStudentList().clear();

		searchTableModel.setNumberRecords(examTableModel.getNumberRecords());

		searchTable = new JTable(searchTableModel) {
			protected JTableHeader createDefaultTableHeader() {
				return new GroupableTableHeader(columnModel);
			}
		};

		new ColumnModel(searchTable, searchTableModel.getNumberExams());
		JScrollPane scrollPane = new JScrollPane(searchTable);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		searchDialog.add(scrollPane, BorderLayout.CENTER);

		table.requestFocusInWindow();
	}

	void addCriterion(JPanel searchCriterionPanel, JPanel criterionPanel) {

		JRadioButton meanScoreName = new JRadioButton("Mean score and surname");
		JRadioButton numberGroupName = new JRadioButton(
				"Number group and surname");
		JRadioButton nameMark = new JRadioButton("Surname and mark");

		searchCriterionPanel.add(meanScoreName, new GridBagConstraints(0, 0, 1,
				1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 10));
		searchCriterionPanel.add(numberGroupName, new GridBagConstraints(0, 1,
				1, 1, 0, 0, GridBagConstraints.NORTH,
				GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
		searchCriterionPanel.add(nameMark, new GridBagConstraints(0, 2, 1, 1,
				0, 0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
				new Insets(2, 2, 2, 2), 0, 10));

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(meanScoreName);
		buttonGroup.add(numberGroupName);
		buttonGroup.add(nameMark);

		JButton oKButton = new JButton("OK");

		JTextField nameField = new JTextField(5);
		JTextField numberGroupField = new JTextField(5);
		JComboBox<Integer> minMarkComboBox = new JComboBox<Integer>(
				MenuName.MARK);
		JComboBox<Integer> maxMarkComboBox = new JComboBox<Integer>(
				MenuName.MARK);

		meanScoreName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				criterionPanel.removeAll();
				criterionPanel.add(minMarkComboBox);
				criterionPanel.add(maxMarkComboBox);
				criterionPanel.add(nameField);
				criterionPanel.add(oKButton);
				oKButton.removeActionListener(searcherAndRemover);
				oKButton.setActionCommand("meanScoreName");
				searcherAndRemover = new SearcherAndRemover(minMarkComboBox,
						maxMarkComboBox, nameField, numberGroupField,
						searchTable, studentList, remove, table,
						leftStartButton, leftButton, rightButton,
						rightEndButton);
				oKButton.addActionListener(searcherAndRemover);

				searchDialog.setVisible(true);

			}
		});

		numberGroupName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				criterionPanel.removeAll();
				criterionPanel.add(numberGroupField);
				criterionPanel.add(nameField);
				criterionPanel.add(oKButton);
				oKButton.removeActionListener(searcherAndRemover);
				oKButton.setActionCommand("numberGroupName");
				searcherAndRemover = new SearcherAndRemover(minMarkComboBox,
						maxMarkComboBox, numberGroupField, nameField,
						searchTable, studentList, remove, table,
						leftStartButton, leftButton, rightButton,
						rightEndButton);
				oKButton.addActionListener(searcherAndRemover);

				searchDialog.setVisible(true);

			}
		});

		nameMark.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				criterionPanel.removeAll();
				criterionPanel.add(nameField);
				criterionPanel.add(minMarkComboBox);
				criterionPanel.add(maxMarkComboBox);
				criterionPanel.add(oKButton);
				oKButton.removeActionListener(searcherAndRemover);
				oKButton.setActionCommand("nameMark");
				searcherAndRemover = new SearcherAndRemover(minMarkComboBox,
						maxMarkComboBox, nameField, numberGroupField,
						searchTable, studentList, remove, table,
						leftStartButton, leftButton, rightButton,
						rightEndButton);
				oKButton.addActionListener(searcherAndRemover);

				// leftstartbutton.doclick();
				searchDialog.setVisible(true);

			}
		});
	}

	public void startRemove(boolean remove) {
		this.remove = remove;
	}

}
