import java.util.*;

public class rushmore{

    public static ArrayList[] storeg;

    public static Boolean Solve(String start, String end) {

        // If they have different lengths, they're obviously not equal
        String hold = "";
        if (start.length() != end.length()) 
        {
            hold = "no";
            return false;
        }
        
        // Go through each letter
        for (int i=0; i<start.length(); i++) 
        {
            boolean[] visited = new boolean[26];

            DFS(start.charAt(i)-'a', visited);

            if (!visited[end.charAt(i)-'a'])
            {
                hold = "no";
                return false;
            }
                
        }

        // We tried all combinations and it didn't fail
        hold = "yes";
        return true;
    }

    // recursively goes through each alphabet to see if they're equal
    public static void DFS(int v1, boolean[] used) {

        used[v1] = true;
        int i;
        for (i=0; i<storeg[v1].size(); i++)
        {
            if (!used[((ArrayList<Integer>)storeg[v1]).get(i)])
            {
                DFS(((ArrayList<Integer>)storeg[v1]).get(i), used);
            }
        }
    }

    public static void main(String[] args) {

        Scanner stdin = new Scanner(System.in);
        storeg = new ArrayList[26];
        for (int i=0; i<26; i++) storeg[i] = new ArrayList<Integer>();
        int n = stdin.nextInt();
        int numCases = stdin.nextInt();

        // reads in both alphabets.
        for (int i=0; i<n; i++) 
        {
            int v1 = stdin.next().charAt(0) - 'a';
            int v2 = stdin.next().charAt(0) - 'a';
            storeg[v1].add(v2);
        }

        // reads in both words and checks if equal.
        for (int loop=0; loop<numCases; loop++) 
        {
            String firstWord = stdin.next();
            String secondWord = stdin.next();
            if(Solve(firstWord, secondWord))
            {
                System.out.println("yes");
            }
            else
            {
                System.out.println("no");
            }
        }
    }

   
}