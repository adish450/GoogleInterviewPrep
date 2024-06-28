class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        ArrayList<Integer> [] list = new ArrayList[nums.length + 1];
        //freq map
        HashMap<Integer, Integer> freqMap = new HashMap<Integer, Integer>();
        for (int i = 0 ; i < nums.length; i++) {
            int num = nums[i];
            if (freqMap.containsKey(num)) {
                freqMap.put(num, freqMap.get(num) + 1);
            } else {
                freqMap.put(num, 1);
            }
        }

        // build array of list with indexing of frequency
        for (Integer key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (list[freq] == null) {
                ArrayList<Integer> nl = new ArrayList<Integer>();
                nl.add(key);
                list[freq] = nl; 
            } else {
                list[freq].add(key);
            }
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