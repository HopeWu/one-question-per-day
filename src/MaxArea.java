public class MaxArea {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = left, rightMax = right;
        int currentMaxArea = area(left, right, height);

        int prev = 0;
        while( left < right) {
            if (height[left] < height[right]) {
                left += 1;
                if(height[left] > height[left - 1]){
                    // check area when the new is higher
                    int _area = area(left, right, height);
                    if (_area > currentMaxArea){
                        currentMaxArea = _area;
                        leftMax = left;
                        rightMax = right;
                    }
                }
            }else{
                right -= 1;
                if(height[right] > height[right + 1]){
                    // check area when the new is higher
                    int _area = area(left, right, height);
                    if (_area > currentMaxArea){
                        currentMaxArea = _area;
                        leftMax = left;
                        rightMax = right;
                    }
                }
            }
        }
        return currentMaxArea;
    }

    int area(int left, int right, int[] height){
        return min(height[left], height[right]) * (right - left);
    }

    int min(int a, int b){
        if (a < b) return a;
        return b;
    }
}
