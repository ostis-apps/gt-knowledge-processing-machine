package windows;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import student.Student;
import table.ExamTableModel;

public class NumberAvailableRecords {
	private ExamTableModel tableModel;
	private JLabel numberAvailableRecordsLabel;
	private JTable table;

	public NumberAvailableRecords(JTable table) {
		tableModel = (ExamTableModel) table.getModel();
		this.table = table;
	}

	public JPanel addNumberAvailableRecordsPanel() {
		JPanel numberAvailableRecordsPanel = new JPanel();
		numberAvailableRecordsPanel.setLayout(new FlowLayout());

		JLabel numberAvailableRecords = new JLabel(
				"Number of the available records");
		numberAvailableRecordsLabel = new JLabel(String.valueOf(tableModel
				.getStudentList().size()));

		Color darkGreen = new Color(17, 111, 21);
		numberAvailableRecords.setForeground(darkGreen);
		numberAvailableRecordsLabel.setForeground(Color.BLUE);
		numberAvailableRecordsLabel.setFont(new Font("Calibri", Font.BOLD, 20));

		numberAvailableRecordsPanel.add(numberAvailableRecords);
		numberAvailableRecordsPanel.add(numberAvailableRecordsLabel);

		return numberAvailableRecordsPanel;
	}

	public void addTableListener(List<Student> list) {
		table.addContainerListener(new ContainerListener() {

			@Override
			public void componentRemoved(ContainerEvent e) {
				/*
				 * model = (ExamTableModel) table.getModel();
				 * numberAvailableRecordsLabel.setText(String.valueOf(model
				 * .getStudentList().size()));
				 */
				// pageToggle.addButtonActionListener(table);
				numberAvailableRecordsLabel.setText(String.valueOf(list.size()));
			}

			@Override
			public void componentAdded(ContainerEvent e) {
				/*
				 * model = (ExamTableModel) table.getModel();
				 * numberAvailableRecordsLabel.setText(String.valueOf(model
				 * .getStudentList().size()));
				 */
				// pageToggle.addButtonActionListener(table);
				numberAvailableRecordsLabel.setText(String.valueOf(list.size()));
			}
		});
	}

}
