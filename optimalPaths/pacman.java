// Camilo Gomez
// ca506812
import java.util.*;

public class pacman {
  int topValue = 0;
  int leftValue = 0;
  int currentValue = 0;
  long numPaths = 0;

  // object for pacman
  public pacman(int topValue, int leftValue, int currentValue, long numPaths) {
    this.topValue = topValue;
    this.leftValue = leftValue;
    this.currentValue = currentValue;
    this.numPaths = numPaths;
  }

  public static void dynSolve(int table[][], int row, int column) {
    long numPaths = 0;
    int topValue = 0;
    int leftValue = 0;
    int currentValue = 0;
    // initialize pacman array object
    pacman[][] pacman = new pacman[row + 1][column + 1];

    for (int i = 0; i <= row; i++) {
      for (int j = 0; j <= column; j++) {
        // initialize each object's starting at [0,0]
        pacman[i][j] = new pacman(topValue, leftValue, currentValue, numPaths);
        pacman[i][j].currentValue = table[i][j];
       
        if (j == 1 && i == 1)
          pacman[i][j].numPaths = 1;

        if (i > 0 && j > 0){
          pacman[i][j].topValue = pacman[i - 1][j].currentValue;
          pacman[i][j].leftValue = pacman[i][j - 1].currentValue;

          // if top is greater
          // do same as before except with object above cur object
          if (pacman[i][j].leftValue < pacman[i][j].topValue) {
            pacman[i][j].currentValue += pacman[i - 1][j].currentValue;
            pacman[i][j].numPaths = pacman[i - 1][j].numPaths;
          }

          // if left is greater
          // update current value with left object's current value
          // update maximum paths taken with the left object
          if (pacman[i][j].leftValue > pacman[i][j].topValue) {
            pacman[i][j].currentValue += pacman[i][j - 1].currentValue;
            pacman[i][j].numPaths = pacman[i][j - 1].numPaths;
          }

          // if equal
          // top value gets chosen over left in order to preserve alphabetic order
          // when updating value and path taken.
          if (pacman[i][j].leftValue == pacman[i][j].topValue) {
            pacman[i][j].currentValue += pacman[i - 1][j].currentValue;
            if (pacman[i][j].leftValue != 0 && pacman[i][j].topValue != 0) {
              pacman[i][j].numPaths =
                  (pacman[i - 1][j].numPaths + pacman[i][j - 1].numPaths) % (long) (1e9 + 7);
            } else {
              pacman[i][j].numPaths = 1;
            }
          }
        } else // can only go in one direction
        {
          // can only go right
          if (row == 1 && j > 1) {
            pacman[i][j].numPaths = 1;
            pacman[i][j].currentValue += pacman[i][j - 1].currentValue % (long) (1e9 + 7);
          }
          // can only go down
          if (column == 1 && i > 1) {
            pacman[i][j].numPaths = 1;
            pacman[i - 1][j].currentValue += pacman[i - 1][j].currentValue % (long) (1e9 + 7);
          }
        }
      }
    }
    char[] path = backTracePath(pacman, row, column);
    System.out.println(pacman[row][column].currentValue + " " + pacman[row][column].numPaths);
    // prints out the path
    for (int i = path.length - 2; i >= 0; i--) {
      if (i == path.length - 2 && path.length > 3) {
        if (path[i - 1] == 'R')
          System.out.print('R');
        if (path[i - 1] == 'D')
          System.out.print('D');
      } else {
        System.out.print(path[i]);
      }
    }
    System.out.println("");
  }

  // path reconstruction 
  public static char[] backTracePath(pacman[][] pacman, int row, int column) {
    int length = row + column - 1;
    int left = 0;
    int top = 0;
    char[] path = new char[length];
    int index = 0;
    boolean flag = false;
    while (column > 0 || row > 0) {
      if (row > 1 && column > 1) {
        top = pacman[row][column].topValue;
        left = pacman[row][column].leftValue;
      } else {
        if (row >= 1) {
          top = 0;
          left = pacman[row][column].leftValue;
        }
        if (column >= 1) {
          left = 0;
          top = pacman[row][column].topValue;
        }
      }

      if (top < left) {
        path[index] = 'R';
        column--;
      }
      if (left < top) {
        path[index] = 'D';
        row--;
      }
      if (left == top) {
        path[index] = 'R';
        column--;
      }
      index++;
      if (row == 0 || column == 0)
        break;
    }

    return path;
  }

  public static void main(String args[]) {
    Scanner scanner = new Scanner(System.in);
    int row = scanner.nextInt();
    int column = scanner.nextInt();
    int incP = 0;
    String P = scanner.next();
    int table[][] = new int[row + 1][column + 1];
    // Initialize pacman and end location
    // P = 0, E = 0
    table[1][1] = 0;
    table[row][column] = 0;
    // created an extra row and column for easier handling of top and left values.
    for (int i = 0; i < column; i++) {
      table[0][i] = 0;
    }
    for (int i = 0; i < row; i++) {
      table[i][0] = 0;
    }
    // Creates the table
    for (int i = 1; i <= row; i++) {
      for (int j = 1; j <= column; j++) {
        // I had to create an IncP to start at (1, 2)
        if (i == 1 && incP == 0 && column != 1) {
          j = 2;
          incP = 1;
        }
        // The table can only go down so start at (2, 1)
        if (column == 1 && incP == 0) {
          i = 2;
          incP = 1;
        }
        // we reached the end
        if (i == row && j == column) {
          break;
        }
        table[i][j] = scanner.nextInt();
      }
    }

    dynSolve(table, row, column);
  }
}