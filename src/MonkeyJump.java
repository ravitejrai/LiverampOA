import java.util.*;

public class MonkeyJump {
	public static void main(String[] args) {
		int[] stones = new int[] {1, -1, 0, 2, 3, 5};
		System.out.println(solution(stones, 3));
	}

	public static int solution(int[] stones, int d) {
		// jump across the river without stone
		if (d >= stones.length) return 0;

		Map<Integer, Integer> map = new HashMap<>();

		int lastStoneTime = -1;
		for (int i = 0; i < stones.length; i++) {
			if (stones[i] != -1) {
				map.put(stones[i], i);
				lastStoneTime = Math.max(lastStoneTime, stones[i]);
			}
		}

		// no stones will show up and d < width of river(stones.length)
		if (lastStoneTime == -1) return -1;

		int reachable = -1 + d;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < lastStoneTime; i++) {
			if (map.containsKey(i)) {
				// Deal with 3 cases
				int stone = map.get(i);
				if (stone > reachable) pq.offer(stone); // store unused stone
				else if (reachable - d < stone) { // between reachable -d & reachable
					reachable = stone + d; // update reachable
					if (reachable >= stones.length) return i;
					while (!pq.isEmpty()) { // use priority queue to get nearest leaf
						int stone_pos = pq.peek();
						if (stone_pos <= reachable) {
							reachable = stone_pos + d; // update reachable
							if (reachable >= stones.length) return i;
							pq.poll();
						} else break;
					}
				}
			}
			
		}

		return -1;
	}
}