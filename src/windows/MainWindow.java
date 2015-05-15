package windows;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.JTableHeader;

import page.PageToggle;
import student.Student;
import table.ChangerExams;
import table.ChangerRecords;
import table.ColumnModel;
import table.ExamTableModel;
import table.GroupableTableHeader;
import XML.FileChooserListener;
import XML.FileSaver;
import constants.MenuName;
import constants.Path;

public class MainWindow {
	private JComboBox<Integer> numberExams;
	private int numberTable = 1;
	private JComboBox<Integer> chooseTable;
	private ExamTableModel tableModel;
	private JTable table;
	private List<Student> studentList;
	private JMenuItem addPeopleItem;
	private PageToggle pageToggle;
	private JMenuItem openFileItem;

	public MainWindow() {

		JFrame frame = new JFrame(MenuName.TITLE);
		frame.setSize(650, 400);
		frame.setLocationRelativeTo(null);
		// frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.setVisible(true);

		numberExams = new JComboBox<Integer>(MenuName.NUMBER);
		numberExams.setSelectedItem(2);
		Vector<Integer> chooseTableVector = new Vector<Integer>();
		chooseTable = new JComboBox(chooseTableVector);

		createMenu(frame);
		createTable((Integer) numberExams.getSelectedItem(), frame,
				chooseTableVector);
		createToolBar(frame, chooseTableVector);

	}

	void createMenu(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu(MenuName.FILE);
		JMenu tableMenu = new JMenu(MenuName.TABLE);

		JMenuItem searchPeopleItem = new JMenuItem(MenuName.SERCH_PEOPLE);
		addPeopleItem = new JMenuItem(MenuName.ADD_PEOPLE);
		JMenuItem deletePeopleItem = new JMenuItem(MenuName.DELETE_PEOPLE);

		JMenuItem newFileItem = new JMenuItem(MenuName.NEW_FILE);
		openFileItem = new JMenuItem(MenuName.OPEN_FILE);
		JMenuItem saveFileItem = new JMenuItem(MenuName.SAVE_AS);
		JMenuItem exitItem = new JMenuItem(MenuName.EXIT);

		openFileItem.setIcon(new ImageIcon(Path.OPEN_ICON.getPath()));
		saveFileItem.setIcon(new ImageIcon(Path.SAVE_ICON.getPath()));
		searchPeopleItem.setIcon(new ImageIcon(Path.SEARCH_ICON.getPath()));
		addPeopleItem.setIcon(new ImageIcon(Path.ADD_ICON.getPath()));
		deletePeopleItem.setIcon(new ImageIcon(Path.DELETE_ICON.getPath()));

		menuBar.add(fileMenu);
		menuBar.add(tableMenu);

		// fileMenu.add(newFileItem);
		fileMenu.add(openFileItem);
		fileMenu.add(saveFileItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);

		tableMenu.add(addPeopleItem);
		tableMenu.add(deletePeopleItem);
		tableMenu.add(searchPeopleItem);

		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		searchPeopleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchDialog(frame, table, studentList, pageToggle);
			}
		});

		deletePeopleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchDialog(frame, table, studentList, pageToggle)
						.startRemove(true);
			}
		});

	}

	@SuppressWarnings("serial")
	void createTable(int numberExams, JFrame frame,
			Vector<Integer> chooseTableVector) {
		List<Integer> d = new ArrayList<Integer>();
		d.add(9);
		d.add(5);

		List<Integer> d2 = new ArrayList<Integer>();
		d2.add(8);
		d2.add(9);

		List<String> d1 = new ArrayList<String>();
		d1.add("Прога");
		d1.add("Матан");

		tableModel = new ExamTableModel(numberExams);
		studentList = tableModel.getStudentList();
		Student student = new Student("Джум", "Владислав", "Егорович", 321702,
				d1, d);
		Student student1 = new Student("Зверуго", "Алексей", "Викторович",
				321701, d1, d2);
		Student student2 = new Student("2", "Максим", "", 321701, d1, d2);
		Student student3 = new Student("3", "Максим", "", 321702, d1, d2);
		Student student4 = new Student("4", "Максим", "", 321702, d1, d2);
		Student student5 = new Student("5", "Максим", "", 321701, d1, d2);

		tableModel.addDate(student);
		tableModel.addDate(student1);
		tableModel.addDate(student2);
		tableModel.addDate(student3);
		tableModel.addDate(student4);
		tableModel.addDate(student5);

		table = new JTable(tableModel) {
			protected JTableHeader createDefaultTableHeader() {
				return new GroupableTableHeader(columnModel);
			}
		};

		chooseTableVector.add(1);
		chooseTable.setSelectedIndex(0);

		new ColumnModel(table, numberExams);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		frame.add(scrollPane);

		table.requestFocusInWindow();

	}

	void createToolBar(JFrame frame, Vector<Integer> chooseTableVector) {
		JToolBar fileToolBar = new JToolBar();
		JToolBar peopleToolBar = new JToolBar();
		pageToggle = new PageToggle();
		JPanel transitionPanel = pageToggle.addPanel(table);

		fileToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		peopleToolBar.setLayout(new BoxLayout(peopleToolBar, BoxLayout.Y_AXIS));

		fileToolBar.setFloatable(false);
		peopleToolBar.setFloatable(false);

		frame.add(fileToolBar, BorderLayout.NORTH);
		frame.add(peopleToolBar, BorderLayout.LINE_START);
		frame.add(transitionPanel, BorderLayout.SOUTH);

		JLabel numberExamsLabel = new JLabel("Number of Exams");

		JButton newFileButton = new JButton(new ImageIcon(
				Path.NEW_FILE_ICON.getPath()));
		JButton saveButton = new JButton(
				new ImageIcon(Path.SAVE_ICON.getPath()));
		JButton openFileButton = new JButton(new ImageIcon(
				Path.OPEN_ICON.getPath()));
		JButton addPeopleButton = new JButton(new ImageIcon(
				Path.ADD_ICON.getPath()));
		JButton searchPeopleButton = new JButton(new ImageIcon(
				Path.SEARCH_ICON.getPath()));
		JButton deletePeopleButton = new JButton(new ImageIcon(
				Path.DELETE_ICON.getPath()));

		pageToggle.addButtonActionListener(table);
		saveButton.addActionListener(new FileSaver(studentList));
		openFileButton.addActionListener(new FileChooserListener(studentList,
				pageToggle, table));
		deletePeopleButton.setActionCommand("Remove");

		openFileItem.addActionListener(new FileChooserListener(studentList,
				pageToggle, table));

		// fileToolBar.add(newFileButton);
		fileToolBar.add(openFileButton);
		fileToolBar.add(saveButton);
		// fileToolBar.add(chooseTable);
		fileToolBar.add(numberExamsLabel);
		fileToolBar.add(numberExams);

		peopleToolBar.add(addPeopleButton);
		peopleToolBar.add(deletePeopleButton);
		peopleToolBar.add(searchPeopleButton);

		addPeopleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ExamDialog(frame, table, (Integer) numberExams
						.getSelectedItem(), studentList, pageToggle);
			}
		});

		addPeopleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ExamDialog(frame, table, (Integer) numberExams
						.getSelectedItem(), studentList, pageToggle);
			}
		});

		searchPeopleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchDialog(frame, table, studentList, pageToggle);
			}
		});

		deletePeopleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SearchDialog(frame, table, studentList, pageToggle)
						.startRemove(true);
			}
		});

		numberExams.addActionListener(new ChangerExams(tableModel,
		/* numberRecords, */pageToggle, numberExams, studentList, table));

		// pageToggle.addTableListener(studentList, table);

		pageToggle.getLeftStartButton().doClick();
	}
}
