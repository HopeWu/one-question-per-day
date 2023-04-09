import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets
 * [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * ref: https://leetcode.com/problems/3sum/
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> results = threeSum.threeSum(nums);

        System.out.println(results.size());
        System.out.println(results);
    }

    /**
     * The brutal-force solution is to find all 3-element combinations and then reduce duplicates, which takes
     * O(n*n*n) time. Here, the solution takes O(n*n) time.
     * Steps for this method:
     * <p>
     * 1. find all 2-element combinations and save them with their occurrence in a dict;
     * 2. iterate the array and try to pair each with the dict and count if there is a match.
     * 3. reduce duplicates
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        /*
        remember each sums by two and their occurrence
         */
        return solution1(nums);
    }

    /**
     * the most naive way, brutal force, taking O(n*n*n) time
     * @param nums
     * @return
     */
    private List<List<Integer>> solution1(int[] nums) {
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        /*
        iterate each combination of three
         */
        List<Integer> three = null;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                for (int k = j + 1; k < length; k++) {
                    if(nums[i]+nums[j]+nums[k] == 0){
                        three = new ArrayList<>();
                        three.add(nums[i]); three.add(nums[j]); three.add(nums[k]);
                        if(! isContained(result, three)){
                            result.add(three);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * check whether results consists of three
     * @param results
     * @param three
     * @return
     */
    private boolean isContained(List<List<Integer>> results, List<Integer> three) {
        for(List<Integer> triplet: results){
            if(isEqual(triplet, three)) return true;
        }
        return false;
    }

    /**
     * check whether the triplet and three are the same thing
     * @param triplet
     * @param three
     * @return
     */
    private boolean isEqual(List<Integer> triplet, List<Integer> three) {
        HashMap<Integer, Boolean> visits = new HashMap<>();
        boolean found;
        for(int x: triplet){
            found = false;
            for(int y: three){
                if (x == y && !visits.getOrDefault(y, false)){
                    visits.put(y, true);
                    found = true;
                    break;
                }
            }
            if(!found) return false;
        }
        return true;
    }

    /**
     * a better solution that takes O(n*n) time
     * @param nums
     * @return
     */
    private List<List<Integer>> solution2(int[] nums) {
        int length = nums.length;
        /*
        initialize the map with the pair values we need
         */
        HashMap<Integer, List<List<Integer>>> pairSums = new HashMap<>();
        List<List<Integer>> pairs = null;
        for (int i = 0; i < length; i++) {
            pairs = new ArrayList<>();
            pairSums.put(0-nums[i], pairs);
        }

        /*
        add the sums we need
         */
        int sum;
        List<Integer> pair;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                /*
                only add the sums we need
                 */
                sum = nums[i] + nums[j];
                if(pairSums.containsKey(sum)){
                    pair = new ArrayList<>();
                    pair.add(i); pair.add(j);
                    pairSums.get(sum).add(pair);
                }
            }
        }
        System.out.println(pairSums);

        List<List<Integer>> results = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            List<Integer> rest;
            /*
            if there is a counter part
             */
            if(pairSums.get(-nums[i]).size() != 0){
                /*
                pair them
                 */
                List<Integer> oneTriplet;

                List<List<Integer>> list = pairSums.get(-nums[i]);

                for (List<Integer> counterPairs : list) {
                    oneTriplet = new ArrayList<>();
                    // make sure don't use the same element again
                    if (counterPairs.contains(i)) continue;

                    for (Integer index : counterPairs) {
                        oneTriplet.add(nums[index]);
                    }
                    oneTriplet.add(nums[i]);
                    // check for duplicated
                    if(!isContained(results, oneTriplet))
                        results.add(oneTriplet);
                }
            }
        }

        return results;
    }
}
