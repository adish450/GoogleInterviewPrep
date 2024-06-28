class Solution {

    private class Pair {
        int freq;
        int num;

        Pair (int freq, int num) {
            this.freq = freq;
            this.num = num;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {

        // freq vs number map

        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<>() {
            @Override
            public int compare (Pair p1, Pair p2) {
                if (p1.freq - p2.freq < 0) {
                    return 1;
                } else if (p1.freq - p2.freq > 0) {
                    return -1;
                } else {
                    if (p1.num - p2.num < 0) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });

        HashMap<Integer, Pair> elementMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            // check if the element already exists
            if (elementMap.get(num) != null) {
                Pair rp = elementMap.get(num);
                pq.remove(rp);
                rp.freq++;
                pq.add(rp);
            } else {
                // add element
                Pair np = new Pair(1, num);
                elementMap.put(num, np);
                pq.add(np);
            }
        }

        int ans [] = new int[k];

        for (int i = 0 ; i < k ; i++) {
            Pair rp = pq.remove();
            ans[i] = rp.num;
        }

        return ans;
        
    }
}