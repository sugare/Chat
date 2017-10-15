import java.awt.*;
import java.awt.event.*;

public class ChatClient extends Frame {

	TextField tf = new TextField();
	TextArea ta = new TextArea();

	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	public void launchFrame() {
		setLocation(400, 300);
		this.setSize(300, 300);

		add(tf, BorderLayout.SOUTH);
		add(ta, BorderLayout.NORTH);
		pack();
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		tf.addActionListener(new TFListener());
		setVisible(true);
	}
	
	private class TFListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			String s = tf.getText().trim();
			ta.setText(s);
			tf.setText("");
			
		}
		
	}

}







