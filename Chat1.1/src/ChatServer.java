import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	boolean started = false;
	ServerSocket ss = null;
	
	List<Client> clients = new ArrayList<ChatServer.Client>();
	
	public static void main(String[] args) {
		new ChatServer().start();
	}

	public void start() {
		try {
			ss = new ServerSocket(8888);
			started = true;
		} catch (BindException e) {
			System.out.println("Port Already use!!!");
			System.exit(0);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {

			while(started) {
				Socket s = ss.accept();
				Client c = new Client(s);
System.out.println("a client connected");
				new Thread(c).start();
				clients.add(c);
System.out.println(clients);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	class Client implements Runnable {
		
		private Socket s = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean bConnected = false;
		
		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				bConnected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		public void send(String str) {
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				while (bConnected) {
					String str = dis.readUTF();
System.out.println(str);
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						c.send(str);
					}
					/*
					for (Iterator<Client> it = clients.iterator(); it.hasNext();) {
						Client c = it.next();
						c.send(str);
					}
					*/
					
				}
			} catch (EOFException e) {
				System.out.println("Client Closed");
				
			} catch (IOException e) {
				e.printStackTrace();
				
			} finally {
				try {
					
					if (dis != null) dis.close();
					if (dos != null) dos.close();
					if (s != null) s.close();
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	
}
