package XML;

import java.util.ArrayList;
import java.util.List;

import constants.XMLTag;
import student.Student;
import jdk.internal.org.xml.sax.Attributes;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

	private List<Student> studentList;
	private String name;

	private String firstNameStudent;
	private String secondNameStudent;
	private String thirdNameStudent;
	private int numberGroup;
	private List<String> nameExam = new ArrayList<String>();
	private List<Integer> mark = new ArrayList<Integer>();

	public Handler(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public void startDocument() throws SAXException {
	}

	@Override
	public void endDocument() throws SAXException {
	}

	@Override
	public void startElement(String arg0, String arg1, String arg2,
			Attributes arg3) throws SAXException {
		name = arg2;
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		if (arg2.equals(XMLTag.STUDENT)) {
			Student student = new Student(firstNameStudent, secondNameStudent,
					thirdNameStudent, numberGroup, nameExam, mark);
			studentList.add(student);
		} 
	}

	@Override
	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		String value = new String(arg0, arg1, arg2);
		
		if (name.equals(XMLTag.FIRST_NAME)) {
			firstNameStudent = value;
		} else if (name.equals(XMLTag.SECOND_NAME)) {
			secondNameStudent = value;
		} else if (name.equals(XMLTag.THIRD_NAME)) {
			thirdNameStudent = value;
		} else if (name.equals(XMLTag.NAME_EXAM)) {
			nameExam.add(value);
		} else if (name.equals(XMLTag.MARK)) {
			mark.add(Integer.parseInt(value));
		} else if (name.equals(XMLTag.NUMBER_GROUP)) {
			numberGroup = Integer.parseInt(value);
		}

	}

}
