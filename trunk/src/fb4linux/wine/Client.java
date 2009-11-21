package fb4linux.wine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.adobe.flexbuilder.utils.osnative.OSNative;

public class Client {

	private String server;
	private int port;
	public Client(String server,int port){
		this.server=server;
		this.port=port;
	}
	public Object call(String clazz,String method,Object[] args){
		try {
			Socket cl=new Socket(server,port);
			ObjectOutputStream out = new ObjectOutputStream(cl.getOutputStream());
			
			
			CMD cmd=new CMD(clazz,method,args);
			out.writeObject(cmd);
			
			ObjectInputStream in = new ObjectInputStream(cl.getInputStream());
			Object robj=in.readObject();
			cl.close();
			return robj;
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		Client cl=new Client("localhost",9880);
		Object[] cargs=new Object[1];
		cargs[0]=1.3f;
		System.out.println(cl.call("fb4linux.wine.TestC", "getfloat", cargs));
		Object[] oargs=new Object[3];
		oargs[0]=1;
		oargs[1]=2.3f;
		oargs[2]="hello";
		System.out.println(cl.call("fb4linux.wine.TestC", "getOther", oargs));
		Object[] oarg3=new Object[1];
		oarg3[0]=false;
		System.out.println(cl.call("fb4linux.wine.TestC", "getBoolean", oarg3));
		//oarg3[0]="flashplayer";
		//System.out.println(cl.call("com.adobe.flexbuilder.utils.osnative.OSNative", 
		//		"GlobalMemoryStatusEx", null));
		cargs[0]=true;
		System.out.println(cl.call("com.adobe.flexide.embeddedplayer.OWL", 
				"initialize", cargs));
		
	}

}
