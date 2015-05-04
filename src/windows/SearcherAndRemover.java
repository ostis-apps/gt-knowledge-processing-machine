package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

import student.Student;
import table.ExamTableModel;
import table.Page;

public class SearcherAndRemover implements ActionListener {

	private JTextField firstField;
	private JTextField secondField;
	private JComboBox<Integer> minMarkComboBox;
	private JComboBox<Integer> maxMarkComboBox;
	private List<Student> studentList;
	private ExamTableModel examTableModel;
	private JTable searchTable;
	private List<Student> searchStudent = new ArrayList<Student>();
	private boolean remove;
	private JTable table;

	private JButton leftStartButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton rightEndButton;
	Page page;
	public SearcherAndRemover(JComboBox<Integer> minMarkComboBox,
			JComboBox<Integer> maxMarkComboBox, JTextField firstField, JTextField secondField,
			JTable searchTable, List<Student> studentList, boolean remove,
			JTable table, JButton leftStartButton, JButton leftButton, JButton rightButton, JButton rightEndButton) {
		this.minMarkComboBox = minMarkComboBox;
		this.maxMarkComboBox = maxMarkComboBox;
		this.firstField = firstField;
		this.secondField = secondField;
		this.searchTable = searchTable;
		this.studentList = studentList;
		this.remove = remove;
		this.table = table;
		this.leftStartButton = leftStartButton;
		this.leftButton = leftButton;
		this.rightButton = rightButton;
		this.rightEndButton = rightEndButton;
		maxMarkComboBox.setSelectedIndex(9);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		examTableModel = (ExamTableModel) searchTable.getModel();
		searchStudent.clear();
		if (e.getActionCommand().equals("meanScoreName")) {
			searchMeanScoreName();
		} else if (e.getActionCommand().equals("numberGroupName")) {
			searchNumberGroupName();
		} else {
			searchNameMark();
		}

		examTableModel.setStudentList(searchStudent);
		
		leftButton.removeActionListener(page);
		leftStartButton.removeActionListener(page);
		rightButton.removeActionListener(page);
		rightEndButton.removeActionListener(page);
		
		page = new Page(searchTable);
		leftStartButton.addActionListener(page);
		leftButton.addActionListener(page);
		rightButton.addActionListener(page);
		rightEndButton.addActionListener(page);
		
		leftStartButton.doClick();

		searchTable.updateUI();

		if (remove) {
			ExamTableModel examTableModel = (ExamTableModel) table.getModel();
			examTableModel.setStudentList(studentList);
			examTableModel.deleteDate(searchStudent);
			table.updateUI();
		}

	}

	void searchMeanScoreName() {
		for (Student student : studentList) {
			List<Integer> markList = student.getMark();
			double sum = 0;
			for (Integer mark : markList) {
				sum += mark;
			}
			double meanScore = sum / markList.size();

			if ((meanScore >= (Integer) minMarkComboBox.getSelectedItem() && meanScore <= (Integer) maxMarkComboBox
					.getSelectedItem())
					|| student.getFirstNameStudent().equals(
							firstField.getText())) {
				searchStudent.add(student);
			}
		}
	}

	void searchNumberGroupName() {
		for (Student student : studentList) {
			if (Integer.parseInt(firstField.getText()) == student
					.getNumberGroup()
					|| secondField.getText().equals(
							student.getFirstNameStudent())) {
				searchStudent.add(student);
			}

		}
	}

	void searchNameMark() {
		for (Student student : studentList) {
			boolean b = true;
			List<Integer> markList = student.getMark();
			for (Integer mark : markList) {
				if (((Integer) minMarkComboBox.getSelectedItem() <= mark && (Integer) maxMarkComboBox
						.getSelectedItem() >= mark)) {
					b = true;
				} else {
					b = false;
					break;
				}
			}
			if (firstField.getText().equals(student.getFirstNameStudent())
					|| b == true) {
				searchStudent.add(student);
			}
		}
	}

}
