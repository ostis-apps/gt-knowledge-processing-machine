package XML;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import student.Student;
import constants.XMLTag;

public class XMLWriterAndReader {
	private Element rootStud;
	private List<Student> studentList;
	private String nameFile;

	public XMLWriterAndReader(String nameFile, List<Student> studentList) {
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

			Element name = document.createElement(XMLTag.NAME);
			stud.appendChild(name);

			Element firstName = document.createElement(XMLTag.FIRST_NAME);
			firstName.appendChild(document.createTextNode(student
					.getFirstNameStudent()));
			name.appendChild(firstName);

			Element secondName = document.createElement(XMLTag.SECOND_NAME);
			secondName.appendChild(document.createTextNode(student
					.getSecondNameStudent()));
			name.appendChild(secondName);

			Element thirdName = document.createElement(XMLTag.THIRD_NAME);
			thirdName.appendChild(document.createTextNode(student
					.getThirdNameStudent()));
			name.appendChild(thirdName);
			/*
			 * stud.setAttribute(XMLTag.FIRST_NAME,
			 * student.getFirstNameStudent());
			 * stud.setAttribute(XMLTag.SECOND_NAME,
			 * student.getSecondNameStudent());
			 * stud.setAttribute(XMLTag.THIRD_NAME,
			 * student.getThirdNameStudent());
			 */

			for (int iterator = 0; iterator < student.getMark().size(); iterator++) {
				Element exam = document.createElement(XMLTag.EXAM);
				stud.appendChild(exam);

				Element examName = document.createElement(XMLTag.NAME_EXAM);
				examName.appendChild(document.createTextNode(student
						.getNameExam().get(iterator)));
				exam.appendChild(examName);

				Element mark = document.createElement(XMLTag.MARK);
				mark.appendChild(document.createTextNode(String.valueOf(student
						.getMark().get(iterator))));
				exam.appendChild(mark);

				/*
				 * exam.setAttribute(XMLTag.MARK,
				 * String.valueOf(student.getMark().get(iterator)));
				 * exam.setAttribute(XMLTag.NAME_EXAM,
				 * student.getNameExam().get(iterator)); stud.appendChild(exam);
				 */
			}

			Element numberGroup = document.createElement(XMLTag.NUMBER_GROUP);
			stud.appendChild(numberGroup);
			numberGroup.appendChild(document.createTextNode(String
					.valueOf(student.getNumberGroup())));

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(nameFile));
			transformer.transform(source, result);
		}
	}


	public void readFile() throws SAXException, IOException,
			ParserConfigurationException {
		// Handler handler = new Handler(studentList);
		File file = new File(nameFile);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		
		SAXParser parser = saxParserFactory.newSAXParser();
		DefaultHandler defaultHandler = new DefaultHandler() {
			private String name;

			private String firstNameStudent;
			private String secondNameStudent;
			private String thirdNameStudent;
			private int numberGroup;
			private List<String> nameExam = new ArrayList<String>();
			private List<Integer> mark = new ArrayList<Integer>();

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
				if (name.equals(XMLTag.STUDENT)) {
					nameExam = new ArrayList<String>();
					mark = new ArrayList<Integer>();
				}
			}

			@Override
			public void endElement(String arg0, String arg1, String arg2)
					throws SAXException {
				if (arg2.equals(XMLTag.STUDENT)) {
					Student student = new Student(firstNameStudent,
							secondNameStudent, thirdNameStudent, numberGroup,
							nameExam, mark);
					studentList.add(student);
				}
			}

			@Override
			public void characters(char[] arg0, int arg1, int arg2)
					throws SAXException {
				String value = new String(arg0, arg1, arg2).trim();
				if(value.equals("")){return;}
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
		};
		
		parser.parse(file, defaultHandler);

	}
}
