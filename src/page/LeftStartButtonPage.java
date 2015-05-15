package page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import student.Student;
import table.ExamTableModel;

public class LeftStartButtonPage implements ActionListener {
	private List<Student> newList = null;

	private PageModel pageModel;

	public LeftStartButtonPage(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ExamTableModel examTableModel = (ExamTableModel) pageModel.getTable()
				.getModel();

		List<Student> studentList = pageModel.getStudentList();
		int numberRecords = examTableModel.getNumberRecords();
		int size = pageModel.getSize();
		int endPage = size % numberRecords;

		if (endPage == 0) {
			endPage = numberRecords;
		}

		if (numberRecords >= size) {
			newList = studentList;
			examTableModel.setStudentList(newList);
			pageModel.getTable().updateUI();
			return;
		}

		newList = studentList.subList(0, numberRecords);

		pageModel.setLeft(false);
		pageModel.setRight(false);
		pageModel.setCurrentRecord(0);

		examTableModel.setStudentList(newList);
		pageModel.getTable().updateUI();

	}

}
