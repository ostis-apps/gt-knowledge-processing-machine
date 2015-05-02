package student;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private String firstNameStudent;
	private String secondNameStudent;
	private String thirdNameStudent;
	private int numberGroup;
	private List<String> nameExam;
	private List<Integer> mark;

	public Student(String firstNameStudent, String secondNameStudent,
			String thirdNameStudent, int numberGroup, List<String> nameExam,
			List<Integer> mark) {

		this.firstNameStudent = firstNameStudent;
		this.secondNameStudent = secondNameStudent;
		this.thirdNameStudent = thirdNameStudent;
		this.numberGroup = numberGroup;
		this.nameExam = nameExam;
		this.mark = mark;
	}

	public String getFirstNameStudent() {
		return firstNameStudent;
	}

	public void setFirstNameStudent(String firstNameStudent) {
		this.firstNameStudent = firstNameStudent;
	}

	public String getSecondNameStudent() {
		return secondNameStudent;
	}

	public void setSecondNameStudent(String secondNameStudent) {
		this.secondNameStudent = secondNameStudent;
	}

	public String getThirdNameStudent() {
		return thirdNameStudent;
	}

	public void setThirdNameStudent(String thirdNameStudent) {
		this.thirdNameStudent = thirdNameStudent;
	}

	public int getNumberGroup() {
		return numberGroup;
	}

	public void setNumberGroup(int numberGroup) {
		this.numberGroup = numberGroup;
	}

	public List<String> getNameExam() {
		return nameExam;
	}

	public void setNameExam(List<String> nameExam) {
		this.nameExam = nameExam;
	}

	public List<Integer> getMark() {
		return mark;
	}

	public void setMark(List<Integer> mark) {
		this.mark = mark;
	}
	
	public List<Object> getRow(){
		List<Object> studentInformation = new ArrayList<Object>();
		studentInformation.add(firstNameStudent + " " + secondNameStudent + " " + thirdNameStudent);
		studentInformation.add(numberGroup);
		
		for (int i = 0; i < nameExam.size(); i++) {
			studentInformation.add(nameExam.get(i));
			studentInformation.add(mark.get(i));
		}
		return studentInformation;
		
	}

}
