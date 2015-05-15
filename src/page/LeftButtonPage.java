package page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import student.Student;
import table.ExamTableModel;

public class LeftButtonPage implements ActionListener{

	private List<Student> newList = null;
	
	private PageModel pageModel;
	private int currentRecord;
	private boolean left;
	private boolean right;
	
	public LeftButtonPage(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		ExamTableModel examTableModel = (ExamTableModel) pageModel.getTable().getModel();
		
		List <Student> studentList = pageModel.getStudentList();
		int numberRecords = examTableModel.getNumberRecords();
		int size = pageModel.getSize();
		int endPage = size % numberRecords;
		
		if (numberRecords >= size) {
			newList = studentList;
			examTableModel.setStudentList(newList);
			pageModel.getTable().updateUI();
			return;
		}

		if (endPage == 0) {
			endPage = numberRecords;
		}
		
		currentRecord = pageModel.getCurrentRecord();
		left = pageModel.isLeft();
		right = pageModel.isRight();
		
		if (pageModel.isLeft()) {
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
		
		pageModel.setLeft(left);
		pageModel.setRight(right);
		pageModel.setCurrentRecord(currentRecord);
		
		examTableModel.setStudentList(newList);
		pageModel.getTable().updateUI();
		
	}

}
