// link: http://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/


// Find the Minimum length Unsorted Subarray, sorting which makes the complete array sorted

/*Given an unsorted array arr[0..n-1] of size n, find the minimum length subarray arr[s..e] such that sorting this subarray makes the whole array sorted.

Examples:
1) If the input array is [10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60], your program should be able to find that the subarray lies between the indexes 3 and 8.

2) If the input array is [0, 1, 15, 25, 6, 7, 30, 40, 50], your program should be able to find that the subarray lies between the indexes 2 and 5.
Recommended: Please solve it on “PRACTICE” first, before moving on to the solution.

Solution:
1) Find the candidate unsorted subarray
a) Scan from left to right and find the first element which is greater than the next element. Let s be the index of such an element. In the above example 1, s is 3 (index of 30).
b) Scan from right to left and find the first element (first in right to left order) which is smaller than the next element (next in right to left order). Let e be the index of such an element. In the above example 1, e is 7 (index of 31).

2) Check whether sorting the candidate unsorted subarray makes the complete array sorted or not. If not, then include more elements in the subarray.
a) Find the minimum and maximum values in arr[s..e]. Let minimum and maximum values be min and max. min and max for [30, 25, 40, 32, 31] are 25 and 40 respectively.
b) Find the first element (if there is any) in arr[0..s-1] which is greater than min, change s to index of this element. There is no such element in above example 1.
c) Find the last element (if there is any) in arr[e+1..n-1] which is smaller than max, change e to index of this element. In the above example 1, e is changed to 8 (index of 35)

3) Print s and e.*/


import java.util.*;

public class MinLenUnsortedSubarr {
	public static void main(String[] args) {
		int[] arr = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
		int[] arrNew = {0, 1, 15, 25, 6, 7, 30, 40, 50};
		System.out.println(solution2(arr));
		System.out.println(solution2(arrNew));
		System.out.println(solution1(arr));
		System.out.println(solution1(arrNew));
	}

	// O(nlogn)
	public static int solution1(int[] arr) {
		if (arr == null || arr.length == 0) return 0;

		@SuppressWarnings("unused")
		int res = Integer.MAX_VALUE;

		int[] sorted = arr.clone();
		Arrays.sort(sorted);
		int left = 0, right = arr.length-1;
		while (left < arr.length) {
			if (sorted[left] != arr[left]) break;
			
			left++;
		}
		if (left == arr.length) return 0;
		while (sorted[right] == arr[right]) right--;

		System.out.println("part of solution 1");
		return (right - left + 1);
	}

	// O(n)
	public static String solution2(int[] arr) {
		if (arr == null || arr.length == 1) return null; //earlier this was return 0 ;
		int left = 0, right = arr.length - 1;

		// find ordered part from start, final arr[left] mean left max
		for (; left < arr.length-1; left++) {
			if (arr[left] > arr[left+1]) break;
		}

		if (left == arr.length-1) return null; //earlier this was return 0 ;

		// find ordered part from end, final arr[left] mean right min
		for (; right > 0; right--) {
			if (arr[right] < arr[right-1]) break;
		}

		// find min and max between left and right
		int max = arr[left], min = arr[left];
		for (int i = left + 1; i <= right; i++) {
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}

		// update left if any arr[0 ->left-1] > min
		for (int i = 0; i < left; i++) {
			if (min < arr[i]) {
				left = i;
				break;
			}
		}

		// update right if any[right+1 -> len] < max
		for (int i = arr.length-1; i > right; i--) {
			if (max > arr[i]) {
				right = i;
				break;
			}
		}

		System.out.println("part of solution 2");
		return (left + "-" + right); //earlier this was return ( right - left + 1) ;
	}
}