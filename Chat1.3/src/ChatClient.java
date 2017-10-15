import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class ChatClient extends Frame {
	
	Socket s = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	private boolean bConnected = false;
	
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
				disconnect();
				System.exit(0);
			}
			
		});
		tf.addActionListener(new TFListener());
		setVisible(true);
		this.connect();
		new Thread(new RecvThread()).start();
	}
	
	
	
	public  void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
System.out.println("connected");
			bConnected = true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void disconnect() {
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private class TFListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = tf.getText().trim();
			//ta.setText(str);
			tf.setText("");
			
			try {
				dos.writeUTF(str);
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
	}

	private class RecvThread implements Runnable {

		@Override
		public void run() {
			try {
				while(bConnected) {
					String str = dis.readUTF();
					ta.setText(ta.getText() + str + '\n');
					
				}
			} catch (SocketException e) {
				System.out.println("ÍË³öÁË£¬bye");
			} catch (EOFException e) {
				System.out.println("bye-bye");
			
			} 
			catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	
}







