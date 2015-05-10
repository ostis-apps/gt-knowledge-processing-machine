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
import table.ExamTableModel;
import windows.PageToggle;

public class FileChooserListener implements ActionListener {
	private JFileChooser jFileChooser;
	private List<Student> studentList;
	private PageToggle pageToggle;
	private JTable table;

	public FileChooserListener(List<Student> studentList, PageToggle pageToggle, JTable table) {
		this.studentList = studentList;
		this.pageToggle = pageToggle;
		this.table = table;
		jFileChooser = new JFileChooser();
		jFileChooser.setCurrentDirectory(new File("D:\\TestXML"));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		int result = jFileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			if (jFileChooser.getSelectedFile().getName().contains(".xml")) {

				studentList.clear();
					try {
						new XMLWriterAndReader(jFileChooser.getSelectedFile().getPath(),
								studentList).readFile();
					} catch (SAXException | IOException
							| ParserConfigurationException e1) {
						e1.printStackTrace();
					}
				
				ExamTableModel examTableModel = (ExamTableModel) table.getModel();
				examTableModel.setStudentList(studentList);
				pageToggle.addButtonActionListener(table);
				pageToggle.getLeftStartButton().doClick();

			}
		}
	}
}
