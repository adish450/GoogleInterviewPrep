class Solution {

    private class Pair {
        int freq;
        int num;

        Pair(int freq, int num) {
            this.freq = freq;
            this.num = num;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {

        // freq vs number map
        HashMap<Integer, Integer> elementMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // check if the element already exists
            if (elementMap.get(num) != null) {
                Integer freq = elementMap.get(num);
                freq++;
                elementMap.put(num, freq);
            } else {
                // add element
                elementMap.put(num, 1);
            }
        }

        int ans[] = new int[k];
        int count = 0;
        if (elementMap.size() <= k) {
        	// if size is less than k, it means we can take all the elements of the map as it is, as all will be part of the answer.
            for (Integer key : elementMap.keySet()) {
                ans[count++] = key;
            }
        } else {
            // create a min heap
            PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<>() {
                @Override
                public int compare(Pair p1, Pair p2) {
                    if (p1.freq - p2.freq < 0) {
                        return -1;
                    } else if (p1.freq - p2.freq > 0) {
                        return 1;
                    } else {
                        if (p1.num - p2.num < 0) {
                            return -1;
                        } else {
                            return 1;
                        }
                    }
                }
            });

            for (Integer key : elementMap.keySet()) {
                Pair np = new Pair(elementMap.get(key), key);
                pq.add(np);
                if (pq.size() > k) {
                	// this solution only works because we are using a min heap which has exactly k elements in it,
                    //when we traverse the map and add a new element,
                    //if the size exceeds k the min element is no longer needed and we remove it.
                    pq.poll(); 
                }
            }

            for (int i = 0; i < k; i++) {
                Pair rp = pq.poll();
                ans[i] = rp.num;
            }
        }

        return ans;

    }
}