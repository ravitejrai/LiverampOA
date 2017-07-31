import java.util.*;

/*
*/

public class MaxSubseqLen {
	public static void main (String[] args) {
		int[] arr = new int[] {6,2,3,2,3,5,5,5,5,5,5};

		System.out.println(maxSubseq2(arr));
	}

	public static int maxSubseq(int[] a){
       Arrays.sort(a); // nlogn
       int last=-1;
       int current=0;
       int max_len=0;
       int i=0;
       for(;i<a.length;i++){
           if(i>0&&a[i]!=a[i-1]){
               max_len=Math.max(max_len,last>=0?i-last:i-current);
               last=a[i]-a[i-1]==1?current:-1;
               current=i;
           }
       }
       max_len=Math.max(max_len,last>=0?i-last:i-current);
       return max_len;
   }

   public static int maxSubseq2(int[] arr) {
   		Map<Integer, Integer> map = new HashMap<>();

   		int res = Integer.MIN_VALUE;
   		int max = Integer.MIN_VALUE;
   		int min = Integer.MAX_VALUE;
   		for (int i = 0; i < arr.length; i++) {
   			int temp = 1;
   			if (map.containsKey(arr[i])) {
   				temp = map.get(arr[i]) + 1;
   			}

   			min = Math.min(min, arr[i]);
   			max = Math.max(max, arr[i]);
   			map.put(arr[i], temp);
   		}

      System.out.println(min + "," + max);
      if (max == min) return map.get(min);
   		for (int m = min; m < max; m++) {
          if (map.get(m) != null && map.get(m+1) != null) {
            res = Math.max(res, map.get(m) + map.get(m+1));
          }
   		}

   		return res;
   }
}