package fb4linux.wine;

public class TestC {
	public static int getVersion(int version){
		return version+1;
	}
	public static float getfloat(float mv){
		return mv+1;
	}
	public static String getOther(int a1,float a2,String a3){
		return "this is a return:"+a1+a2+a3;
	}
	public static boolean getBoolean(boolean flags){
		return ! flags;
	}
}
