public class MaxSubArray {
    public int maxSubArray(int[] nums) {
//        int largestSumSoFar = nums[0];
        int[] sumUpTo = new int[nums.length];
        sumUpTo[0] = nums[0];
        for (int i = 1; i < nums.length; ++i){
            sumUpTo[i] = sumUpTo[i-1] + nums[i];
//            if (largestSumSoFar < sumUpTo[i])
//                largestSumSoFar = sumUpTo[i];
        }
        int sum = sumUpTo[sumUpTo.length-1];
        int left = 0, right = nums.length-1;
        int _sum = sum, largestSumSoFar = sum;
        while(left < right){
            if(sumUpTo[left] < sum - sumUpTo[right-1]){
                // subtract the left part, always subtract the smaller part
                _sum -= nums[left];
                if (_sum > largestSumSoFar)
                    largestSumSoFar = _sum;
                left += 1;
            }else{
                // subtract the right part
                _sum -= nums[right];
                if (_sum > largestSumSoFar)
                    largestSumSoFar = _sum;
                right -= 1;
            }
        }
        return largestSumSoFar;
    }
}
