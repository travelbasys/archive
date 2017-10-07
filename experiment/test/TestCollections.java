package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCollections {

	public static void main(String[] args) {

		// 1.list 
		// Can be accessed by index
		// with no duplicates allowed
		// List is an ordered sequence of elements
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(0);
		
		for (int i = 0; i < list.size(); i++) {
			Integer element = list.get(i);
//			System.out.println(element);
		}
		
		
		// 2.set
		// Can not be accessed by index
		// with no duplicates allowed
		// List is an unordered sequence of elements
		Set<Integer> set = new HashSet<Integer>();
		set.add(0);
		set.add(0);
		
		for(Integer object : set) {
			Integer element = (Integer) object;
//			System.out.println(element);
		}
		
		
		// 3.Array
		// Array is static in size that is fixed length data structure
		// Array can contains primitive data types (like int , float , double)
	    // insert elements using the assignment operator.
		int[] array = new int[100];
		
		array[0] = 5;
		array[1] = 5;
		array[2] = 5;
		array[3] = 5;
		array[4] = 5;
		
		for (int i = 0; i < array.length; i++) {
			Integer element = array[i];
//			System.out.println(element);
		}
		
		
		//Mutiple Array
		int[][] multiArray = new int[2][2];
		
		multiArray[0] [0] = 1;
		multiArray[0] [1] = 2;
		multiArray[1] [0] = 3;
		multiArray[1] [1] = 4;
		
//		int[] singleArray = multiArray[1];
		
		for (int i = 0; i < multiArray.length; i++) {
			for (int j = 0; j < multiArray[i].length; j++) {
				Integer element = multiArray[i][j];
				System.out.println(element);
			}

		}
		
		// 4. Map
		//  maps keys to values. A map cannot contain duplicate keys
	      HashMap<Integer, String> hmap = new HashMap<Integer, String>();
	      hmap.put(2000, "TEST"); 
	      
	      Set setIterator = hmap.entrySet();
	      Iterator iterator = setIterator.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry mentry = (Map.Entry)iterator.next();
	         Integer key =  (Integer) mentry.getKey();
	         String value = (String) mentry.getValue();
	         
//	         System.out.println(key + ":" + value);
	      }
	      
	      
	      String value = hmap.get(2000);
//	      System.out.println(value);
	      
	}
}
