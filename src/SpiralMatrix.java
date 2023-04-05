import java.util.List;

public class SpiralMatrix {
    /*
        0: right, 1: down, 2: left, 3: up
    */
    int direction = 1;

    public List<Integer> spiralOrder(int[][] matrix) {
        return null;
    }

    void nextDirection(){
        direction = (direction+1)%4;
    }
}
