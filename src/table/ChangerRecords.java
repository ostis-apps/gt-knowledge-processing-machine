package table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;

import page.PageToggle;

public class ChangerRecords implements ActionListener{
	private ExamTableModel tableModel;
	private JComboBox<Integer> numberRecords;
	private JButton button;
	private JTable table;
	
	public ChangerRecords(JTable table,
			JComboBox<Integer> numberRecords, JButton button) {
		super();
		this.table = table;
		this.numberRecords = numberRecords;
		this.button = button;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tableModel = (ExamTableModel) table.getModel();
		tableModel.setNumberRecords((Integer) numberRecords
				.getSelectedItem());
		// leftStartButton.doClick();
		button.doClick();
	}

}
