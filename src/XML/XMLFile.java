package XML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import student.Student;
import constants.XMLTag;

public class XMLFile {
	private Element rootStud;
	private List<Student> studentList;
	private String nameFile;

	public XMLFile(String nameFile, List<Student> studentList) {
		this.nameFile = nameFile;
		this.studentList = studentList;
	}

	public void writeFile() throws IOException, TransformerException,
			ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document document = builder.newDocument();

		rootStud = document.createElement(XMLTag.STUDENTS);
		document.appendChild(rootStud);

		for (Student student : studentList) {
			Element stud = document.createElement(XMLTag.STUDENT);
			rootStud.appendChild(stud);
			stud.setAttribute(XMLTag.FIRST_NAME, student.getFirstNameStudent());
			stud.setAttribute(XMLTag.SECOND_NAME,
					student.getSecondNameStudent());
			stud.setAttribute(XMLTag.THIRD_NAME, student.getThirdNameStudent());

			Element numberGroup = document.createElement(XMLTag.NUMBER_GROUP);
			stud.appendChild(numberGroup);
			numberGroup.appendChild(document.createTextNode(String
					.valueOf(student.getNumberGroup())));

			for (int iterator = 0; iterator < student.getMark().size(); iterator++) {
				Element exam = document.createElement(XMLTag.EXAM);
				exam.setAttribute(XMLTag.MARK,
						String.valueOf(student.getMark().get(iterator)));
				exam.setAttribute(XMLTag.NAME_EXAM,
						student.getNameExam().get(iterator));
				stud.appendChild(exam);
			}

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(nameFile));
			transformer.transform(source, result);
		}
	}

	public void readFile() throws ParserConfigurationException, SAXException,
			IOException {
		studentList.clear();
		File xmlFile = new File(nameFile);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);
		document.getDocumentElement().normalize();

		String firstNameStudent;
		String secondNameStudent;
		String thirdNameStudent;
		int numberGroup;
		List<String> nameExam;
		List<Integer> mark;

		NodeList studentNodeList = document
				.getElementsByTagName(XMLTag.STUDENT);
		for (int studentInerator = 0; studentInerator < studentNodeList
				.getLength(); studentInerator++) {
			nameExam = new ArrayList<String>();
			mark = new ArrayList<Integer>();
			
			Element elementStudent = (Element) studentNodeList
					.item(studentInerator);

			firstNameStudent = elementStudent.getAttribute(XMLTag.FIRST_NAME);
			secondNameStudent = elementStudent.getAttribute(XMLTag.SECOND_NAME);
			thirdNameStudent = elementStudent.getAttribute(XMLTag.THIRD_NAME);
			
			numberGroup = Integer.parseInt(elementStudent.getElementsByTagName(XMLTag.NUMBER_GROUP).item(0).getTextContent());
			
			NodeList examsNodeList = elementStudent
					.getElementsByTagName(XMLTag.EXAM);

			for (int examIterator = 0; examIterator < examsNodeList
					.getLength(); examIterator++) {
				Element elementExam = (Element) examsNodeList
						.item(examIterator);
				mark.add(Integer.parseInt(elementExam.getAttribute(XMLTag.MARK)));
				nameExam.add(elementExam.getAttribute(XMLTag.NAME_EXAM));
			}


			studentList.add(new Student(firstNameStudent, secondNameStudent, thirdNameStudent, numberGroup, nameExam, mark));
		}
	}

}
