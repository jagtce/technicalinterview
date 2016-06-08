package com.sample.util;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;
import java.util.Set;

/** Java Program to find duplicate entries in an array. 
 * 
 *  There are 3 solutions of this problem 
 *  First Implementation-using Set data structure
 *  Second Implementation-using HashMapt data structure. 
 *  Third Implementation- using the array  
 * 
 *  @author Jagadeesh */
public class DeDup {

	/**
	 * Main method to execute the program
	 * @param arg
	 */
	public static void main(String arg[]) {
		int[] randomIntegers = { 1, 2, 34, 34, 25, 1, 45, 3, 26, 85, 4, 34, 86,
				25, 43, 2, 1, 10000, 11, 16, 19, 1, 18, 4, 9, 3, 20, 17, 8, 15,
				6, 2, 5, 10, 14, 12, 13, 7, 8, 9, 1, 2, 15, 12, 18, 10, 14, 20,
				17, 16, 3, 6, 19, 13, 5, 11, 4, 7, 19, 16, 5, 9, 12, 3, 20, 7,
				15, 17, 10, 6, 1, 8, 18, 4, 14, 13, 2, 11 };

		DeDup dedup = new DeDup();
		dedup.removeDuplicatesArraySet(randomIntegers);
		dedup.removeDuplicatesArrayMap(randomIntegers);
		dedup.removeDuplicatesArrayCopy(randomIntegers);

	}

	/**
	 * First Implementation of identifying the duplicates. This method uses the java.util.Set collection to identify 
	 * the duplicate  entries from the array.
	 * 
	 * Pros:
	 * This method utilizes the full advantages of collection. Set by itself is the collection provided by Java to store
	 * unique entries. This reduces the data scan of the entire list to find the duplicate.
	 * LinkedHashSet helps to retain the original insertion order.
	 * 
	 * Cons:
	 * Using collections will require additional space in the memory to hold the data structure.
	 * 
	 * UseCases:
	 * This approach can be taken when we need a faster execution of code at the cost of memory usage
	 * 
	 * @param randomIntegers
	 * @return Integer[]
	 */
	public Integer[] removeDuplicatesArraySet(int[] randomIntegers) {
		//Create a Set
		Set<Integer> set = new LinkedHashSet<Integer>();
		
		//Loop through the array and add the elements to the set
		for (int i = 0; i < randomIntegers.length; i++) {
			set.add(randomIntegers[i]);
		}
		//Convert the set to array using the collection api
		Integer[] uniqueList = set.toArray(new Integer[set.size()]);
		return uniqueList;
	}
	
	/**
	 * Second Implementation of identifying the duplicates. This method uses the java.util.HashMap collection to identify  
	 * the duplicate entries from the array.The approach is similar to the first implementation, but with a different data
	 * structure
	 * 
	 * Pros:
	 * This method utilizes the full advantages of collection. HashMap will efficiently identify the element with the hash
	 * value created for each entry(store and retrieve). This reduces the data scan of the entire list to find the duplicate
	 * 
	 * Cons:
	 * HashMap requires two elements to be strored, a Key and a Value which takes more memory compared to Set.
	 * Does not maintain the order of the elements
	 * 
	 * UseCases:
	 * This approach can be taken when we need a faster execution of code at the cost of memory usage and without maintaining
	 * the order of the elements
	 * 
	 * @param randomIntegers
	 * @return Integer[]
	 */
	public Integer[] removeDuplicatesArrayMap(int[] randomIntegers) {
		//Create a HashMap
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		
		//Loop through the input array and before adding to the list, check if the list already contains
		for (int i = 0; i < randomIntegers.length; i++) {
			Integer count=hm.get(randomIntegers[i]);
			// build hash table with count
			if (count==null) {
				hm.put(randomIntegers[i],1);
			}else{
				hm.put(randomIntegers[i],++count);
			}
			
			//Reteieve duplicate elements from the map based on the count value
			Set<Entry<Integer, Integer>> entrySet = hm.entrySet();
			for (Entry<Integer, Integer> entry : entrySet) {
				 if (entry.getValue() > 1) { 
					hm.remove(entry);
				 } 
			}
			
		}
		//Create a new Set
		Set<Integer> uniqueList = hm.keySet();
		//Create an array from the Set
		Integer[] unique = uniqueList.toArray(new Integer[uniqueList.size()]);
		return unique;
	}

	/**
	 * Third Implementation of identifying the duplicates. This method does not use any of the collection api to identify  
	 * the duplicate entries from the array. It uses the plain array data structure with the brute force algorithm. It will
	 * compare each element against the other element and check the equality
	 * 
	 * Pros:
	 * Not much memory is used. Since the array has fixed size the memory allocation need not be reserved unlike the collection
	 * data structure
	 * 
	 * Cons:
	 * The complexity increases when the no of elements increases. The looping takes more time depending on the no of elements
	 * This is not the best approach when the performance and efficiency is concerned
	 * 
	 * UseCases:
	 * This approach can be taken when we have memory constraints to store a large data structure in heap.
	 * 
	 * the order of the elements
	 * 
	 * @param randomIntegers
	 * @return int[]
	 */
	public int[] removeDuplicatesArrayCopy(int[] randomIntegers) {
		int end = randomIntegers.length;
		/*loop through the */ 
		for (int i = 0; i < end; i++) {
			for (int j = i + 1; j < end; j++) {
				if (randomIntegers[i] == randomIntegers[j]) {
					randomIntegers[j] = randomIntegers[end - 1];
					end--;
					j--;
				}
			}
		}

		int[] uniqueList = new int[end];
		System.arraycopy(randomIntegers, 0, uniqueList, 0, end);
		return uniqueList;
	}

}
