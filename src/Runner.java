import javax.swing.SwingUtilities;

import windows.MainWindow;

public class Runner {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainWindow();
			}
		});
	}
}
