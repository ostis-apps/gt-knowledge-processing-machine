package page;

import java.util.List;

import javax.swing.JTable;

import student.Student;
import table.ExamTableModel;

public class PageModel {
	private JTable table;
	private List<Student> studentList;
	private ExamTableModel examTableModel;
	private int numberRecords;

	private int size;

	private int currentRecord = 0;
	private boolean left;
	private boolean right;

	public PageModel(JTable table) {
		this.table = table;
		examTableModel = (ExamTableModel) table.getModel();
		studentList = examTableModel.getStudentList();
		size = studentList.size();
	}

	public int getNumberRecords() {
		return numberRecords;
	}

	public void setNumberRecords(int size) {
		numberRecords = size;
	}
	
	public JTable getTable() {
		return table;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public int getCurrentRecord() {
		return currentRecord;
	}

	public void setCurrentRecord(int currentRecord) {
		this.currentRecord = currentRecord;
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
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
