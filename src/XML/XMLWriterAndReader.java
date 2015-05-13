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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import student.Student;
import constants.XMLTag;

public class XMLWriterAndReader {
	private Element rootStudend;
	private List<Student> studentList;
	private String nameFile;

	public XMLWriterAndReader(String nameFile, List<Student> studentList) {
		this.nameFile = nameFile;
		this.studentList = studentList;
	}

	public void writeFile() throws IOException, TransformerException,
			ParserConfigurationException {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		Document document = builder.newDocument();

		rootStudend = document.createElement(XMLTag.STUDENTS);
		document.appendChild(rootStudend);

		for (Student student : studentList) {
			Element stud = document.createElement(XMLTag.STUDENT);
			rootStudend.appendChild(stud);

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
		File file = new File(nameFile);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		SAXParser parser = saxParserFactory.newSAXParser();

		parser.parse(file, new Handler(studentList));

	}
}
