package XML;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import student.Student;

public class FileChooserListener implements ActionListener {
	private JFileChooser jFileChooser;
	private List<Student> studentList;
	private JButton button;

	public FileChooserListener(List<Student> studentList, JButton button ) {
		this.studentList = studentList;
		this.button = button;
		jFileChooser = new JFileChooser();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		jFileChooser.setCurrentDirectory(new File("E:\\MyTestFiles"));
		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			if (jFileChooser.getSelectedFile().getName().contains(".xml")) {

				try {
					new XMLFile(jFileChooser.getSelectedFile().getPath(),
							studentList).readFile();
				} catch (ParserConfigurationException | SAXException
						| IOException e1) {
					e1.printStackTrace();
				}
				button.doClick();

			}
		}
	}
}
