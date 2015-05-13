package table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTable;

import page.PageToggle;

public class ChangerRecords implements ActionListener{
	private ExamTableModel tableModel;
	private JComboBox<Integer> numberRecords;
	private PageToggle pageToggle;
	private JTable table;
	
	public ChangerRecords(JTable table,
			JComboBox<Integer> numberRecords, PageToggle pageToggle) {
		super();
		this.table = table;
		this.numberRecords = numberRecords;
		this.pageToggle = pageToggle;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tableModel = (ExamTableModel) table.getModel();
		tableModel.setNumberRecords((Integer) numberRecords
				.getSelectedItem());
		// leftStartButton.doClick();
		pageToggle.getLeftStartButton().doClick();
	}

}
