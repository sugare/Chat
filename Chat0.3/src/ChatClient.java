import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.org.apache.bcel.internal.generic.AALOAD;

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
		
		// WindowAdapter可放在任意位置，最合适的还是内部类，因为仅仅是一个关闭的作用，日后无需更多的变更
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
/*		class aa extends WindowAdapter {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		}*/
		
		setVisible(true);
	}
	
}

/*class aa extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}*/