/*
A network consisting of M cities and M-1 roads connecting them is given. Cities are labeled with distinct integers within the range[0..(M-1)]
Roads connect cities in such a way that each pair of distinct cities is connected either by a direct road or along a path consisting of direct roads. There is exactly one way to reach any city from any other city. In other words, cities and direct roads from a tree. The number of direct roads that must be traversed is called the distance between these two cities.
Write a function:
	class Solution{ public int[] solution(int[] T)}
that, given a non-empty zero-indexed array T consisting of M integers describing a network of M cities and M-1 roads, returns an array consisting of M-1 integers, specifying the number of cities positioned at each distance 1, 2, ..., M-1.
Array T describes a network of cities as follows:
- if T[P] = Q and P = Q, then P is the capital;
- if T[P] = Q and P != Q, then there is a direct road between cities P and Q
For example, given the following array T consisting of ten elements:
T[0] = 9 T[1] = 1 T[2] = 4
T[3] = 9 T[4] = 0 T[5] = 4
T[6] = 8 T[7] = 9 T[8] = 0 T[9] = 1
the function should return [1,3,2,3,0,0,0,0,0], as explained above.
Assume that:
- M is an integer within the range [1..100,000]
- each element of array T is an integer within the range [0..M-1]
- there is exactly one (possibly indirect) connection between any two distinct cities
Complexity:
- expected worst-case time complexity is O(M)
- expected worst-case space complexity is O(M), beyond input storage(not counting the storage required for input arguments)
Elements of input arrays can be modified.
*/

import java.util.*;

public class NumberOfCities {
	public static void main(String[] args) {
		int[] map = {9,1,4,9,0,4,8,9,0,1};
		int[] result = solution(map);
		for (int i = 0; i < result.length; i++) System.out.println(result[i]);
	}

	public static int[] solution (int[] map) {
		if (map == null || map.length == 0) return null;

		Map<Integer, List<Integer>> links = new HashMap<>();
		
		int capital = 0;
		
		for (int i = 0; i < map.length; i++) {
			int cur = map[i];
			if (cur == i) {
				capital = cur;
				continue;
			}
			if (links.containsKey(cur)) links.get(cur).add(i);
			else {
				List<Integer> level = new ArrayList<>();
				level.add(i);
				links.put(cur,level);
				links.get(capital);
			}
		} 
		int[] result = new int[map.length - 1];
		helper(map, capital, links, result);
		return result;
	}

	public static void helper(int[] map, int capital, Map<Integer, List<Integer>> links, int[] result) {
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> start = links.get(capital);
		for (int i = 0; i < start.size(); i++) queue.offer(start.get(i));

		int j = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			result[j] = size;
			j++;
			for (int i = 0; i < size; i++) {
				int cur = queue.poll();
				if (!links.containsKey(cur)) continue;
				else {
					List<Integer> neighbour = links.get(cur);
					for (int m : neighbour) queue.offer(m);
				}
			}
		}
	}
}