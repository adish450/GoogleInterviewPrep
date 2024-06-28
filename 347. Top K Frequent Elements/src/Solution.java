class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        ArrayList<Integer> [] list = new ArrayList[nums.length + 1];
        //freq map
        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // build array of list with indexing of frequency
        for (Integer key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (list[freq] == null) {
                list[freq] = new ArrayList<Integer>(); 
            }
            list[freq].add(key);
        }

        int count = 0;
        int[] ans = new int[k];
        for (int i = list.length - 1 ; i >= 0 ; i--) { // traverse from last as indexing is the answer
            if (list[i] != null) {
                for (Integer i1 : list[i]) {
                    ans[count++] = (int)i1;
                    if (count == k) {
                        break;
                    }
                }
            }
            if (count == k) {
                break;
            } 
        }

        return ans;
    }
}