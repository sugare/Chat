import java.awt.*;

public class ChatClient extends Frame {
	//����һ��TextArea,����һ��TextField
	TextArea ta = new TextArea();
	TextField tf = new TextField();
	
	public static void main(String[] args) {
		new ChatClient().launchFrame();
	}

	public void launchFrame() {
		setLocation(400, 300);
		this.setSize(300, 300);
		
		add(ta, BorderLayout.NORTH);
		add(tf, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

}
