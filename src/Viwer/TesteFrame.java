package Viwer;
import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TesteFrame extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteFrame frame = new TesteFrame();
					//frame.add(new MainViwer());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TesteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setContentPane(new MainViwer());
				
	}

}
