package page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import student.Student;
import table.ExamTableModel;

public class RightEndButtonPage implements ActionListener{

	private List<Student> newList = null;
	
	private PageModel pageModel;
	
	
	public RightEndButtonPage(PageModel pageModel) {
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
		
		size = pageModel.getSize();
		newList = studentList.subList(size - endPage, size);
		
		pageModel.setLeft(false);
		pageModel.setRight(false);
		pageModel.setCurrentRecord(size);
		
		examTableModel.setStudentList(newList);
		pageModel.getTable().updateUI();
		
	}

}
