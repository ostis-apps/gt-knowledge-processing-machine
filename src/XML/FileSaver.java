package XML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import student.Student;

public class FileSaver implements ActionListener {
	private JFileChooser jFileChooser;
	private List<Student> studentList;

	public FileSaver(List<Student> studentList) {
		this.studentList = studentList;
		jFileChooser = new JFileChooser();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (jFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				new XMLWriterAndReader(jFileChooser.getSelectedFile().getPath(),
						studentList).writeFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (TransformerException e1) {
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			}
		}
	}
}
