package table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTable;

import student.Student;

public class Page implements ActionListener {
	private JTable table;
	private List<Student> studentList;
	private int numberRecords = 1;
	private int currentRecord;
	private ExamTableModel examTableModel;
	private List<Student> newList = null;
	private boolean left;
	private boolean right;

	public Page(JTable table) {
		this.table = table;
		examTableModel = (ExamTableModel) table.getModel();
		studentList = examTableModel.getStudentList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		numberRecords = examTableModel.getNumberRecords();
		int size = studentList.size();

		if(numberRecords > size){
			return;
		}	
		int endPage = size % numberRecords;

		if (endPage == 0) {
			endPage = numberRecords;
		}

		if (e.getActionCommand().equals("Left Start")) {
			newList = studentList.subList(0, numberRecords);
			currentRecord = 0;
			left = right = false;
		} else if (e.getActionCommand().equals("Right End")) {
			newList = studentList.subList(size - endPage, size);
			currentRecord = size;
			left = right = false;
		} else if (e.getActionCommand().equals("Right")) {
			if (right) {
				currentRecord -= numberRecords;
				right = !right;
			}
			left = true;
			currentRecord += numberRecords;
			if (currentRecord + numberRecords > size) {
				currentRecord = size - numberRecords;
				newList = studentList.subList(size - endPage, size);
			} else {
				newList = studentList.subList(currentRecord, currentRecord
						+ numberRecords);
			}

		} else if (e.getActionCommand().equals("Left")) {
			if (left) {
				currentRecord += numberRecords;
				left = !left;
			}
			right = true;
			currentRecord -= numberRecords;
			if (currentRecord - numberRecords < 0) {
				currentRecord = numberRecords;
				newList = studentList.subList(0, currentRecord);
			} else {
				newList = studentList.subList(currentRecord - numberRecords,
						currentRecord);
			}
		}

		examTableModel.setStudentList(newList);
		table.updateUI();
	}

	

	
	

}
