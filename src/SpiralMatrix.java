import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[3][4];
        // fill the array
        int value = 1;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = 0; j < arr[0].length; ++j) {
                arr[i][j] = value++;
            }
        }

        SpiralMatrix spiralMatrix = new SpiralMatrix();
        List<Integer> result = spiralMatrix.spiralOrder(arr);
        System.out.println(result);
    }

    /*
        0: right, 1: down, 2: left, 3: up
    */
    int direction = 0;

    int row = 0, col = 0;

    public List<Integer> spiralOrder(int[][] matrix) {
        int numberOfRows = matrix.length;
        int numberOfColumns = matrix[0].length;
        int availableRows = numberOfRows;
        int availableColumns = numberOfColumns;

        List<Integer> result = new ArrayList<Integer>();

        while (availableRows > 0 && availableColumns > 0) {
            for (int i = 0; i < availableColumns; ++i) {
                result.add(matrix[row][col]);
                // before update the last one, change the direction
                this.updateDirection();

                this.updateRowCol();
            }

            availableRows -= 1;

            for (int i = 0; i < availableRows; ++i) {
                result.add(matrix[row][col]);
                // before update the last one, change the direction
                if (i == availableRows - 1)
                    this.updateDirection();
                this.updateRowCol();
            }
            availableColumns -= 1;
        }

        return result;
    }

    private void updateDirection() {
        direction = (direction + 1) % 4;
    }

    /*
        0: right, 1: down, 2: left, 3: up
    */
    private void updateRowCol() {
        if (direction == 0) {
            col += 1;
        }
        if (direction == 1) {
            row += 1;
        }
        if (direction == 2) {
            col -= 1;
        }
        if (direction == 3) {
            row -= 1;
        }
    }
}
