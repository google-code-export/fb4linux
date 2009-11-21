package fb4linux.wine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.Serializable;

public class CMD implements Serializable{
	public String callObj;
	public String callMethod;
	public Object[] callArgs;
	public String baseHome;
	public CMD(String callObj,String callMethod,Object[] callArgs){
		this.callObj=callObj;
		this.callMethod=callMethod;
		this.callArgs=callArgs;
		
	}
	
	public Object invock(){
		
		
		if(callObj.compareTo("com.adobe.flexbuilder.utils.osnative.OSNative")==0){
			if(callMethod.compareTo("queryWindowsRegistry")==0){
				//System.out.println(callObj+"."+callMethod+"("+callArgs[0]+")");
				if(((String)callArgs[0]).compareTo("HKEY_LOCAL_MACHINE\\SOFTWARE\\MozillaPlugins\\@adobe.com/FlashPlayer")==0 ||
						((String)callArgs[0]).compareTo("HKEY_CURRENT_USER\\SOFTWARE\\MozillaPlugins\\@adobe.com/FlashPlayer")==0){
					return baseHome+"/dll/NPSWF32.dll";
				}
			}
		}
		
		try {
			Class ownerClass=Class.forName(callObj);
			 Method method;
			if(callArgs !=null){
				Class[] argsClass = new Class[callArgs.length];
				
				for (int i = 0, j = callArgs.length; i < j; i++) {
		        	Class aclazz = callArgs[i].getClass();
		        	//System.out.println(aclazz.getName());
		        	if (aclazz.getName() == "java.lang.Integer")
		        		argsClass[i] = int.class;
		        	else if (aclazz.getName() == "java.lang.Float")
		        		argsClass[i] = float.class;
		        	else if (aclazz.getName() == "java.lang.Boolean")
		        		argsClass[i] = boolean.class;
		        	else
		        		argsClass[i] = aclazz;
		        }
				method = ownerClass.getMethod(callMethod, argsClass);
			}else
				method = ownerClass.getMethod(callMethod);

	        return method.invoke(null, callArgs);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
