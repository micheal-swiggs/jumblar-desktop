package com.jumblar.desktop

class Main{
	public static getN(){
		return 1024
	}
	public static getR(){
		return 8
	}
	public static getP(){
		return 1
	}
	public static getKeyLength(){
		return 64
	}
	
	private static String pCode = null;
	
	public static void setProtectionCode(String val){
		pCode = val;
	}
	
	public static String getProtectionCode(){
		return pCode;
	}
	
	private static boolean trayIconActive = false;
	public static void setTrayIconActive (boolean v){ 
		trayIconActive = v
	}
	public static boolean isTrayIconActive(){ return trayIconActive}
	
	public static void storePreferences(String username, String email){
		def d = System.getProperty("user.home") + "/.jumblar"
		def f = new File(d)
		if (!f.exists()){
			f.mkdirs()
		}
		f = new File(d+"/preferences")
		if (f.exists()){
			f.delete()
		}
		f.createNewFile()
		f << "${username}\n"
		f << "${email}\n"
	}
	
	public static String[] getPreferences(){
		def d = System.getProperty("user.home") + "/.jumblar/preferences"
		def f = new File(d)
		if (!f.exists()){
			return null
		}
		f.text.split("\n")
		
	}
}