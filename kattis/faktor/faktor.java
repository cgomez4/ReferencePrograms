import java.util.*;
public class faktor{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int first = scanner.nextInt();
        int second = scanner.nextInt();
        int answer = first * (second-1) + 1;
        System.out.println(answer);
    }
}