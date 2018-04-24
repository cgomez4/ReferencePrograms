import java.util.*;

public class robotmaze{
int curRow;
int curCol;
int steps;
    // object for robot
    public robotmaze(int curRow, int curCol, int steps){
        this.curRow = curRow;
        this.curCol = curCol;
        this.steps = steps;
    }

    // creates board
    public static char[][] createBoard(int row, int column, char[] rowGen){
        char board[][] = new char[row][column];
        int rg = 0;
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column; j++)
            {
                board[i][j] = rowGen[rg];
                rg++;
               // System.out.print(board[i][j]);
            }
            // System.out.println("");
        }
       // System.out.println("");
       return board;
    }

    public static void solve(int row, int column, char[][] board){
        int curRow = 0;
        int curCol = 0;
        int steps = 0;
        robotmaze robot = new robotmaze(curRow, curCol, steps);
        ArrayDeque<robotmaze> q = new ArrayDeque<>();
        boolean visited[][] = new boolean[row][column];

        // find the robot!
        for(int i = 0; i < row; i++)
        {
            for(int j = 0; j < column; j++)
            {
                if(board[i][j] == 'R')
                {
                    robot.curRow = i;
                    robot.curCol = j;
                    i = row+1;
                    j = column+1;
                }
                else if(board[i][j] == 'B')
                {
                    visited[i][j] = true;
                }
                
            }
        }
        
        q.offer(robot);
        visited[robot.curRow][robot.curCol] = true;
        int tempx, tempy;
        while(!q.isEmpty())
        {
            robot = q.poll();

            if(board[robot.curRow][robot.curCol] == 'D')
            {
                System.out.println(robot.steps);
                return;
            }
            System.out.println(robot.curRow + ", " + robot.curCol);
            
            // for(int i = 0; i < row; i++)
            // {
            //     for(int j = 0; j < column; j++)
            //     {
            //         System.out.print(board[i][j]);
            //     }
            //     System.out.println("");
            // }
            // System.out.println("");
            tempx = robot.curCol;
            tempy = robot.curRow;

            // robot moves down
            if(robot.curRow + 1 < row && visited[robot.curRow+1][robot.curCol] == false)
            {   
                
                
                robot.curRow++;
               
                robot.steps++;
                q.offer(robot);
                visited[robot.curRow][robot.curCol] = true;
            }

            // robot moves up
            if(robot.curRow - 1 >= 0 && visited[robot.curRow-1][robot.curCol] == false)
            {
               
                robot.curRow--;
               
                robot.steps++;
                q.offer(robot);
                visited[robot.curRow][robot.curCol] = true;
            }

            robot.curRow = tempy;
            robot.curCol = tempx;
            // robot moves left
            if(robot.curCol - 1 >= 0 && visited[robot.curRow][robot.curCol-1] == false)
            {
                
                robot.curCol--;
                
                robot.steps++;
                q.offer(robot);
                visited[robot.curRow][robot.curCol] = true;
            }

            robot.curRow = tempy;
            robot.curCol = tempx;
            // robot moves right
            if(robot.curCol + 1 < column && visited[robot.curRow][robot.curCol+1] == false)
            {
                
                robot.curCol++;
               
                robot.steps++;
                q.offer(robot);
                visited[robot.curRow][robot.curCol] = true;
            }
        }
     
        System.out.println(robot.steps);
    }

    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        int boards = scanner.nextInt();
        for(int i = 0; i < boards; i++)
        {

            int row = scanner.nextInt();
            int column = scanner.nextInt();
            char[][] board = new char[row][column];
            String rowGen = "";
            for(int j = 0; j < row; j++)
            {    
                rowGen += scanner.next();
            }
            char bd[] = new char[rowGen.length()];
            bd = rowGen.toCharArray();
            // creates board 
            board = createBoard(row, column, bd);
            // finds shortest path
            solve(row, column, board);
        }
    }
}