package page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import student.Student;
import table.ExamTableModel;

public class RightButtonPage implements ActionListener{

	private List<Student> newList = null;
	
	private PageModel pageModel;
	private int currentRecord;
	private boolean left;
	private boolean right;
	
	public RightButtonPage(PageModel pageModel) {
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
		
		if (right) {
			currentRecord -= numberRecords;
			right = !right;
		}
		left = true;
		currentRecord += numberRecords;
		if (currentRecord + numberRecords >= size) {
			currentRecord = size - numberRecords;
			newList = studentList.subList(size - endPage, size);
		} else {
			newList = studentList.subList(currentRecord, currentRecord
					+ numberRecords);
		}
		
		
		pageModel.setLeft(left);
		pageModel.setRight(right);
		pageModel.setCurrentRecord(currentRecord);
		
		examTableModel.setStudentList(newList);
		pageModel.getTable().updateUI();
	}

}
