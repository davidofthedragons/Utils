package encryption.tests;

import java.util.*;

public class EncryptionTest {

	//static Random rand;
	static String chars = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-=_+<>?,./\\|[]{}`~\n ;:'\"";
	
	public static HashMap<String, String> constructMap(int seed, boolean dec) {
		Random rand = new Random(seed);
		HashMap<String, String> map = new HashMap<String, String>(chars.length());
		ArrayList<String> usedVals = new ArrayList<String>();
		System.out.println("constructMap(" + seed + ", " + dec + ");");
		for(int i=0; i<chars.length(); i++) {
			String key;
			if(!dec) {
				key = Integer.toString(rand.nextInt(chars.length()));
				if(key.length() == 1) key = "0" + key;
				while(map.containsValue(key)) {
					key = Integer.toString(rand.nextInt(chars.length()));
					if(key.length() == 1) key = "0" + key;
				}
				map.put(Character.toString(chars.charAt(i)), key);
				//System.out.println(chars.charAt(i) + ":" + key);
				//System.out.println(chars.charAt(i) + ":" + map.get(Character.toString(chars.charAt(i))));
				usedVals.add(key);
			}
			else if(dec) {
				while(map.containsKey((key = Integer.toString(rand.nextInt(chars.length())))));
				if(key.length()==1) key = "0" + key;
				map.put(key, Character.toString(chars.charAt(i)));
				usedVals.add(key);
			}
			//
		}
		return map;
	}
	
	public static String encrypt(String s, int seed) {
		HashMap<String, String> map = constructMap(seed, false);
		s = s.toLowerCase();
		String r = "";
		for(int i=0; i<s.length(); i++) {
			r += map.get(Character.toString(s.charAt(i)));
			System.out.println(s.charAt(i) + ":" + map.get(Character.toString(s.charAt(i))));
		}
		
		return r;
	}
	
	public static String decrypt(String s, int seed) {
		Random rand = new Random(seed);
		HashMap<String, String> map  = constructMap(seed, true);
		String r = "";
		for(int i=0; i<s.length(); i+=2) {
			String string = s.substring(i, i+2);
			r += map.get(string);
			System.out.println(string + ":" + map.get(string));
		}
		return r;
	}
	
	public static void main(String[] args) {
		String s = "Why won't this work?";
		int key = 779;
		String r = encrypt(s, key);
		String d = decrypt(r, key);
		System.out.println(s);
		System.out.println(r);
		System.out.println(d);
		
		//HashMap<String, String> map = constructMap(key);
		//System.out.println(map.get(" "));
	}

}
