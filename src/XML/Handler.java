package XML;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import student.Student;
import constants.XMLTag;

public class Handler extends DefaultHandler {

	private List<Student> studentList;
	private String name;
	private String firstNameStudent;
	private String secondNameStudent;
	private String thirdNameStudent;
	private int numberGroup;
	private List<String> nameExam;
	private List<Integer> mark;

	public Handler(List<Student> studentList) {
		this.studentList = studentList;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		name = qName;
		if (name.equals(XMLTag.STUDENT)) {
			nameExam = new ArrayList<String>();
			mark = new ArrayList<Integer>();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName.equals(XMLTag.STUDENT)) {
			Student student = new Student(firstNameStudent, secondNameStudent,
					thirdNameStudent, numberGroup, nameExam, mark);
			studentList.add(student);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length).trim();
		if (value.equals("")) {
			return;
		}
		//System.out.println(name);
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
