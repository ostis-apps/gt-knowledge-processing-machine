package table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import student.Student;

@SuppressWarnings("serial")
public class ExamTableModel extends AbstractTableModel {
	private int numberExams;
	int numberRecords = 2;
	private List<Student> studentList;

	public ExamTableModel(int numberExams) {
		this.numberExams = numberExams;
		studentList = new ArrayList<Student>();
		// stud = studentList;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public int getRowCount() {// количество строк таблице
		return studentList.size();
	}

	@Override
	public int getColumnCount() {// количество колонок
		return numberExams * 2 + 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Student student = studentList.get(rowIndex);
		if (columnIndex > student.getRow().size() - 1) {
			return null;
		}
		return student.getRow().get(columnIndex);

	}

	public void addDate(Student student) {
		studentList.add(student);
		// stud.add(student);
	}

	public void deleteDate(List<Student> searchStudent) {
		studentList.removeAll(searchStudent);
		// stud.removeAll(searchStudent);
	}

	public void clearDate() {
		// dataArrayList.clear();

	}

	public int getNumberExams() {
		return numberExams;
	}

	@Override
	public String getColumnName(int column) {

		String columnName[] = new String[numberExams * 2 + 2];
		columnName[0] = "ФИО";
		columnName[1] = "Группа";

		for (int i = 2; i < columnName.length; i++) {
			columnName[i] = "Наименование";
			columnName[++i] = "Оценка";
		}
		return columnName[column];
	}

	public int getNumberRecords() {
		return numberRecords;
	}

	public void setNumberRecords(int records) {
		numberRecords = records;
	}

	public void setNumberExams(Integer numberExams2) {
		numberExams = numberExams2;
	}

	/*
	 * public void setStud(List<Student> stud){ this.stud = stud; }
	 * 
	 * public List<Student> getStud(){ return stud; }
	 */
}
