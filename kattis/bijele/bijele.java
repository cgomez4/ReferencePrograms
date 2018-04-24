import java.util.*;
public class bijele{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int king = scanner.nextInt();
        int queen = scanner.nextInt();
        int rooks = scanner.nextInt();
        int bishops = scanner.nextInt();
        int knights = scanner.nextInt();
        int pawns = scanner.nextInt();
        king = 1 - king;
        queen = 1 - queen;
        rooks = 2 - rooks;
        bishops = 2 - bishops;
        knights = 2 - knights;
        pawns = 8 - pawns;
        System.out.print(king + " " + queen + " " + rooks + " " +
         bishops + " " + knights + " " + pawns);
    }
}