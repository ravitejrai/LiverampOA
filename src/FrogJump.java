/*
A small frog wants to get to the other side of a pond. The frog is initially located on
one bank of the pond(position 0) and wants to get to the other bank(position X).
The frog can jump any (integer) distance between 1 and D. If X > D then the frog
cannot jump right across the pond. Luckily, there are leaves failing from a tree onto
the surface of the pond, and the frog can jump onto the leaves.
You are given a zero-indexed array A consisting of N integers. This array represents
failing leaves. Initially there are no leaves. A[K] represents the position where a leaf
will fall in second K.
The goal is to find the earliest time when the frog can get to the other side of the 
pond. The frog can jump from and to positions 0 and X(the banks of the pond) and 
every position with a leaf.
For example, you are given integers X = 7, D = 3 and array A such that:
A[0] = 1
A[1] = 3
A[2] = 1
A[3] = 4
A[4] = 2
A[5] = 5
Initially, the frog cannot jump cross the pond in a single jump. However, in second
3, after a leaf falls at position 4, it becomes possible for the frog to cross. This is the 
earliest moment when the frog can jump across the pond(by jumps of length 1, 3 and 3)
Write a function:
	int solution(int A[], int N, int X, int D);
that, given a zero-indexed array A consiting of N integers, and integers X and D,
returns the earliest time when frog can jump to the other side of the pond. If
the frog can leap across the pond in just one jump, the function should return 0. If
the frog can get to the other side of the pond just after the very first leaf fails, the
fucntion should also return 0. If the frog is never able to jump to the other side of
the pond, the function shoud return -1.
*/

/*
links:
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=191886&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D28%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
https://instant.1point3acres.com/thread/194823
*/

import java.util.*;

public class FrogJump {
	public static void main(String[] args) {
		int[] arr = new int[] {1,7,2,4,7};
		System.out.println(solution(arr, arr.length, 8, 3));
	}

	public static int solution(int[] arr, int n, int x, int d) {

		/* 
		use reachable to store the position frog can reach currently,
			the reachable is updated when a new leaf comes down in 3 situation,
		1. leaf_pos <= reachable-d
			clearly this leaf will not change the reachable's max
		2. leaf_pos > reachable
			we can't leverage this leaf now bc we can't reach it, but maybe in the future we can reach it,
			so I use priority queue to store it.
		3. reachable-d < leaf_pos <= reachable
			clearly this leaf will extend our reachable to leaf_pos+d
			during this process, if we have any unused leaf(in case 2) in the range [reachable+1, leaf_pos+d]
			lets say k, then we can extend the reachable to k+d
		So for each leaf, we do above update for reachable, then check if x is already reachable
		*/

		if (x <= d) return 0;
		// if no leave falls or no jump, return -1
		if (arr == null || arr.length == 0 || d == 0) return -1;
		int reachable = d;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < arr.length; i++) {
			
			// Deal with 3 cases
			if (arr[i] > reachable) pq.offer(arr[i]); // case 2
			else if (reachable - d < arr[i]) { // case 3
				reachable = arr[i] + d;
				if (reachable >= x) return i;
				while (!pq.isEmpty()) { // use priority queue to get nearest leaf
					int leaf_pos = pq.peek();
					if (leaf_pos <= reachable) {
						reachable = leaf_pos + d; // update reachable
						if (reachable >= x) return i;
						pq.poll();
					} else break;
				}
			}
			// case 1 do nothing
		}

		return -1;
	}
}




