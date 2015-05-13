package table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;

import page.PageToggle;
import student.Student;

public class ChangerExams implements ActionListener{

	private ExamTableModel tableModel;
	private JComboBox<Integer> numberRecords;
	private PageToggle pageToggle;
	private JComboBox<Integer> numberExams;
	private List<Student> studentList;
	private JTable table;
	
	
	
	public ChangerExams(ExamTableModel tableModel/*,
			JComboBox<Integer> numberRecords*/, PageToggle pageToggle,
			JComboBox<Integer> numberExams, List<Student> studentList,
			JTable table) {
		super();
		this.tableModel = tableModel;
		//this.numberRecords = numberRecords;
		this.pageToggle = pageToggle;
		this.numberExams = numberExams;
		this.studentList = studentList;
		this.table = table;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		tableModel = new ExamTableModel((Integer) numberExams
				.getSelectedItem());
		table.setModel(tableModel);
		new ColumnModel(table, (Integer) numberExams.getSelectedItem());
		tableModel.setStudentList(studentList);

		pageToggle.addButtonActionListener(table);

		//numberRecords.setSelectedItem(numberRecords.getSelectedItem()); ???
	}

}
