package table;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

public class ColumnModel {
	public ColumnModel(JTable table, int numberExams) {
	
	TableColumnModel tableColumnModel = table.getColumnModel();

	ColumnGroup examColumn = new ColumnGroup("Ёкзамены");
	int number = 0;
	for (int numberExamsIterator = 2; numberExamsIterator < numberExams * 2 + 2; numberExamsIterator++) {
		ColumnGroup examNumber = new ColumnGroup(String.valueOf(++number));
		examColumn.add(examNumber);
		examNumber.add(tableColumnModel.getColumn(numberExamsIterator));
		examNumber.add(tableColumnModel.getColumn(++numberExamsIterator));
	}

	GroupableTableHeader header = (GroupableTableHeader) table
			.getTableHeader();
	header.addColumnGroup(examColumn);
	}
}
