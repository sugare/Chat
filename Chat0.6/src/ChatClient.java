import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

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
		this.connect();
	}
	
	public  void connect() {
		try {
			Socket s = new Socket("127.0.0.1", 8888);
System.out.println("connected");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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







