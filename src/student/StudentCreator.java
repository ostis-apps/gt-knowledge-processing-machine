package student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import page.PageToggle;
import table.ExamTableModel;

public class StudentCreator implements ActionListener {
	private JTextField firstNameField, secondNameField, thirdNameField,
			numberGroupField;
	private List<String> nameExam = new ArrayList<String>();
	private List<Integer> mark = new ArrayList<Integer>();
	private JDialog examDialog;
	private List<JTextField> nameExamList;
	private List<JComboBox> markList;
	private ExamTableModel tableModel;
	private JTable table;
	private JLabel incorrectLable;
	private List<Student> studentList;
	private PageToggle pageToggle;

	public StudentCreator(JDialog examDialog, JTable table,
			List<Student> studentList, JTextField firstNameField,
			JTextField secondNameField, JTextField thirdNameField,
			JTextField numberGroupField, List<JTextField> nameExamList,
			List<JComboBox> markList, JLabel incorrectLable,
			PageToggle pageToggle) {
		this.firstNameField = firstNameField;
		this.secondNameField = secondNameField;
		this.thirdNameField = thirdNameField;
		this.numberGroupField = numberGroupField;
		this.nameExamList = nameExamList;
		this.markList = markList;
		this.examDialog = examDialog;
		this.pageToggle = pageToggle;
		this.table = table;
		this.tableModel = (ExamTableModel) table.getModel();
		this.incorrectLable = incorrectLable;
		this.studentList = studentList;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int nameExamIterator = 0; nameExamIterator < nameExamList.size(); nameExamIterator++) {
			nameExam.add(nameExamList.get(nameExamIterator).getText());
			mark.add((Integer) markList.get(nameExamIterator).getSelectedItem());
		}

		try {
			Student student = new Student(firstNameField.getText(),
					secondNameField.getText(), thirdNameField.getText(),
					Integer.parseInt(numberGroupField.getText()), nameExam,
					mark);

			examDialog.setVisible(false);
			tableModel.setStudentList(studentList);
			tableModel.addDate(student);
			pageToggle.addButtonActionListener(table);
			pageToggle.getLeftStartButton().doClick();
			table.updateUI();

		} catch (NumberFormatException exept) {
			incorrectLable.setVisible(true);
		}

	}

}
