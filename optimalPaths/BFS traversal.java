// Different implementation using BFS
 
// Camilo Gomez
// ca506812
import java.util.*;

public class pacman{
int score;
int curRow;
int curCol;
int numPaths;
String path;
    // object for pacman
    public pacman(int score, int curRow, int curCol, int numPaths, String path){
        this.score = score;
        this.curRow = curRow;
        this.curCol = curCol;
        this.numPaths = numPaths;
        this.path = path;
    }

    public static int dynSolve(int table[][], int row, int column, int numPaths){
        String path = "";
        int score = 0;
        numPaths = 0;
        int curRow = 0;
        int curCol = 0;

        // initialize pacman object
        pacman pacman = new pacman(score, curRow, curCol, numPaths, path);
        ArrayDeque<pacman> queue = new ArrayDeque<pacman>();
        boolean[][] visited = new boolean[row+1][column+1];
        queue.offer(pacman);
        while(!queue.isEmpty())
        {
            // dequeues top element of queue
            pacman = queue.poll();
            
            // if we find exit, return the path
            if(table[pacman.curRow][pacman.curCol] == 0)
            {
                pacman.numPaths++;
                System.out.println(pacman.score + " " + pacman.numPaths);
                System.out.println(pacman.path);
                return pacman.numPaths;
            }

            for(int i = 0; i < row*column; i++)
            {
                // pacman moves down
                if(pacman.curRow + 1 < row && visited[pacman.curRow+1][pacman.curCol] == false)
                {
                    // compares pacman's right value with the one below it. if equal, go down
                    if(pacman.curCol + 1 < column && table[pacman.curRow+1][pacman.curCol] >= table[pacman.curRow][pacman.curCol+1])
                    {
                        pacman.curRow++;
                        pacman.score += table[pacman.curRow][pacman.curCol];
                        pacman.path += "D";
                        queue.offer(pacman);
                        visited[pacman.curRow][pacman.curCol] = true;  
                    }
                    // If pacman was all the way to the right, just go down
                    if(pacman.curCol + 1 >= column)
                    {
                        pacman.curRow++;
                        pacman.score += table[pacman.curRow][pacman.curCol];
                        pacman.path += "D";
                        queue.offer(pacman);
                        visited[pacman.curRow][pacman.curCol] = true;
                    }
                }

                // pacman moves right
                if(pacman.curCol + 1 < column && visited[pacman.curRow][pacman.curCol+1] == false)
                {      
                    // compares pacman's right value with the one below it. if equal, go down
                    if(pacman.curRow + 1 < row && table[pacman.curRow+1][pacman.curCol] < table[pacman.curRow][pacman.curCol+1])
                    {   
                        pacman.curCol++;
                        pacman.score += table[pacman.curRow][pacman.curCol];
                        pacman.path += "R";
                        queue.offer(pacman);
                        visited[pacman.curRow][pacman.curCol] = true;   
                    } 
                    // Pacman is at the bottom so just go right.
                    if(pacman.curRow + 1 >= row)
                    {
                        pacman.curCol++;
                        pacman.score += table[pacman.curRow][pacman.curCol];
                        pacman.path += "R";
                        queue.offer(pacman);
                        visited[pacman.curRow][pacman.curCol] = true;       
                    }  
                }
            }
        }
        System.out.println(pacman.score + " " + pacman.numPaths);
        System.out.println(pacman.path);
        return pacman.numPaths;
    }

    public static int paths(int table[][], int row, int column){
        int totalpaths = 0;
        int numpaths = 0;
        //while(we traversed all max paths)
        {
            totalpaths += dynSolve(table, row, column, numpaths);
        } 
        
        return totalpaths;
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int incP = 0;
        String P = scanner.next();
        // P = 0, E = 0
        int table[][] = new int[row][column];
        // Initialize pacman and end location
        table[0][0] = -1;
        table[row-1][column-1] = 0;
        // Creates the table
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column; j++)
            {
                // I had to create an IncP to start at (1, 0)
                if(i == 0 && incP == 0 && column != 1)
                {
                    j = 1;
                    incP = 1;
                }
                if(column == 1 && incP == 0)
                {
                    i = 1;
                    incP = 1;
                }
                if(i == row-1 && j == column-1)
                {
                    break;
                }
                table[i][j] = scanner.nextInt();
            }
        }

        //dynSolve(table, row, column); 
        int numpaths = 0;
        numpaths = paths(table, row, column);

        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column; j++)
            {
                System.out.print("(" + table[i][j] + "), ");
            }
            System.out.println("");
        }
    }
}
