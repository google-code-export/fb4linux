package fb4linux.wine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;


public class WineBridge {
	private String baseHome;
	public WineBridge(String baseHome){
		this.baseHome=baseHome;
		ServerSocket server;
		try {
			server = new ServerSocket(9880);
			log(" Flash Builder 4 wine bridge listen port 9880");
			while(true){
				Socket client=server.accept();
				ObjectInputStream in = new ObjectInputStream(client.getInputStream());
				CMD cmd=(CMD)in.readObject();
				cmd.baseHome=baseHome;
				traceCMD(cmd);
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				Object retObj=cmd.invock();
				log("return:"+retObj);
				out.writeObject(retObj);
				//BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
				//String callCMD=in.readLine();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void traceCMD(CMD cmd){
		log(cmd.callObj);
		log(cmd.callMethod);
		if(cmd.callArgs!=null){
			int l=cmd.callArgs.length;
			for(int i=0;i<l;i++){
				log(cmd.callArgs[i]);
			}
		}
	}
	private void log(Object msg){
		System.out.println(msg);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length!=1)
			System.out.println("the usage:WineBridge <baseHome>");
		else{
			String baseDir=args[0];
			new WineBridge(baseDir);
		}
	}

}
