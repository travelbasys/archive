package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class TestHashmap {

	
	public static void main(String[] args) {
		
		HashMap<Integer, String> testHashMap = new HashMap<Integer, String>();
		
		testHashMap.put(0, "Musterman 0");
		testHashMap.put(1, "Musterman 1");
		testHashMap.put(2, "Musterman 2");
		
		testHashMap.remove(1);
		
//		testHashMap.put(3, null);
//		System.out.println("testHashMap.get(2)): "+ testHashMap.get(2));	
//		System.out.println("testHashMap.size(): " +  testHashMap.size());
//		System.out.println("testHashMap.size(): " +  testHashMap.size());
//		
//		for (int i = 0; i < testHashMap.keySet().size(); i++) {
//			System.out.println(testHashMap.get(2));	
//		}
//
//		System.out.println("testHashMap.entrySet().size: " + testHashMap.entrySet().size());
//		
//		for (int i = 0; i < testHashMap.entrySet().size(); i++) {
//			System.out.println(testHashMap.entrySet());	
//		}

		Set<Entry<Integer, String>> entrySet = testHashMap.entrySet();
		for (Entry<Integer, String> entry : entrySet) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		
		System.out.println("*****************");
		
		for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<Integer, String> entry = (Entry<Integer, String>) iterator.next();
			
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
		}
		
	}
}
