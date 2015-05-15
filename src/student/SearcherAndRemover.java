package student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import page.PageToggle;
import student.Student;
import table.ExamTableModel;

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

	private PageToggle pageToggle;
	private PageToggle pageToggleFirst;

	public SearcherAndRemover(JComboBox<Integer> minMarkComboBox,
			JComboBox<Integer> maxMarkComboBox, JTextField firstField,
			JTextField secondField, JTable searchTable,
			List<Student> studentList, boolean remove, JTable table,
			PageToggle pageToggle, PageToggle pageToggleFirst) {
		this.minMarkComboBox = minMarkComboBox;
		this.maxMarkComboBox = maxMarkComboBox;
		this.firstField = firstField;
		this.secondField = secondField;
		this.searchTable = searchTable;
		this.studentList = studentList;
		this.remove = remove;
		this.table = table;
		this.pageToggle = pageToggle;
		this.pageToggleFirst = pageToggleFirst;
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

		// pageToggle.addButtonActionListener(table);
		pageToggle.addButtonActionListener(searchTable);
		pageToggle.setNumberAvailableRecords(searchStudent.size());
		
		pageToggle.getLeftStartButton().doClick();

		searchTable.updateUI();

		if (remove) {
			examTableModel = (ExamTableModel) table.getModel();
			examTableModel.setStudentList(studentList);
			examTableModel.deleteDate(searchStudent);
			pageToggleFirst.addButtonActionListener(table);
			pageToggleFirst.getLeftStartButton().doClick();
			pageToggleFirst.setNumberAvailableRecords(studentList.size());
			table.updateUI();
		}

		JOptionPane.showMessageDialog(table, "Number of found records: "
				+ searchStudent.size());
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
			try {
				if (Integer.parseInt(firstField.getText()) == student
						.getNumberGroup()
						|| secondField.getText().equals(
								student.getFirstNameStudent())) {
					searchStudent.add(student);
				}
			} catch (NumberFormatException e) {
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
	
	public List<Student> getSearchList(){
		return searchStudent;
	}

}