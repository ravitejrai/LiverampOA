/*
Problem description:
Give 4 digits, find the maximum valid time that can be displayed on a 
digital clock (in 24-hour format)  using those digits. For example, given digits
 1,8,3,2, the maximum valid time is "23:18". Note that "28:31" is not a valid 
 time.
Write a function:
	class Solution { public String solution(int A, int B, int C, int D); }
that, given 4 digits A,B,C,D, returns the maximum valid time in string 
format "HH:MM" or "NOT POSSIBLE" if it is not possible to display a valid time.
For example, given 1,8,3,2, the function should return "23:18"
Given 2,4,0,0, the function should return "20:40"
Given 3,0,7,0, the function should return "07:30"
Given 9,1,9,7, the function should return "NOT POSSIBLE"
Assume that:
- A, B, C, and D are integers within the range [0..9].
In your solution, focus on correctness. The performance of your solution will 
not be the focus of the assessment.
*/

/*
links:
https://instant.1point3acres.com/thread/214710
*/

import java.util.ArrayList;
import java.util.List;

public class MaxTimeCombination {
	public static void main(String[] args) {

		System.out.println(solution(2, 4, 0, 0));
		System.out.println(solution(3, 0, 7, 0));
	}

	public static String solution(int A, int B, int C, int D) {
		// brute force permutation
		int[] temp = new int[] {A, B, C, D};

		List<List<Integer>> permutation = permute(temp);
		int h = Integer.MIN_VALUE;
		int m = Integer.MIN_VALUE;
		boolean exists = false;

		/*		System.out.println("Permutations:" + permutation);
		for (int i = 0; i < permutation.size(); i++) {
		if (permutation.get(i).get(0) > 0  && permutation.get(i).get(0) < 3 ){
			List <Integer> output = permutation.get(i);
			System.out.println(output);		
		}

		}*/


		for (int i = 0; i < permutation.size(); i++) {
			//if (permutation.get(i).get(0) > 0  && permutation.get(i).get(0) < 3 ){
			List<Integer> k = permutation.get(i);
			//System.out.println("Sorted :" + k);
			int hh = k.get(0)*10 + k.get(1);
			if (hh < 24) {
				exists = true;
				if (hh > h) {
					h = hh;
				} 
			} 
			int mm = k.get(2)*10 + k.get(3);

			if ( mm < 60) {
				exists = true;
				if (mm > m) {
					m = mm;
				} 
			} 
		}

		return (exists ? String.format("%02d:%02d", h, m) : "NOT POSSIBLE");
	}

	public static List<List<Integer>> permute(int[] num) {
		List<List<Integer>> result = new ArrayList<>();

		//start from an empty list
		result.add(new ArrayList<>());

		for (int i = 0; i < num.length; i++) {
			//list of list in current iteration of the array num
			List<List<Integer>> current = new ArrayList<>();

			for (List<Integer> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, num[i]);

					List<Integer> temp = new ArrayList<>(l);
					current.add(temp);

					//System.out.print(temp + " ");

					//l.remove(num[i]); 
					l.remove(j);
				}

			}

			result = new ArrayList<>(current);
		}

		return result;
	}
}
