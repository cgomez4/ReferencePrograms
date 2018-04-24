// Camilo Gomez
// ca506812
import java.util.*;

public class countseq{

    public static long numOccurence(char[] firstWord, char[] secondWord, long numOccur){
        // same word, can only occur once
        if(firstWord.equals(secondWord))
        {
            numOccur = 1;
            return numOccur;
        }
        // words of size 0
        if(firstWord.length == 0 || secondWord.length == 0)
        {
            numOccur = 0;
            return numOccur;
        }
        
        long[][] table = new long[secondWord.length+1][firstWord.length+1];
        // creates the table
        for(int i = 0; i < firstWord.length + 1; i++)
        {
            table[0][i] = 1;
        }
        for(int j = 1; j < secondWord.length + 1; j++)
        {
            table[j][0] = 0;
        }
    
        for(int i = 1; i <= secondWord.length; i++)
        {
            for(int j = 1; j <= firstWord.length; j++)
            {
                // compares letters to see if they match
                // if match, add left of current index with it's top-left index
                // and store in current index
                if(secondWord[i-1] == firstWord[j-1])
                {
                    table[i][j] = table[i][j-1] + table[i-1][j-1];
                }
                else
                {
                    // else pass over the value from left index
                    table[i][j] = table[i][j-1];
                } 
            }
        }

	// Prints table for testing (after submission testing)
  	for(int i = 0; i <= secondWord.length; i++)
        {
            for(int j = 0; j <= firstWord.length; j++)
            {
		System.out.print(table[i][j] + ", ");
            }
		System.out.println("");
        }

        numOccur = table[secondWord.length][firstWord.length];
        return numOccur;
    }

    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int i;
        char[] fWord;
        char[] secWord;
        for(i = 0; i < n; i++)
        {
            String firstWord = scanner.next();
            String secondWord = scanner.next();
            // Converts to char array for easier handling 
            fWord = firstWord.toCharArray();
            secWord = secondWord.toCharArray();
            long count = 0;
            // Using longs to handle large numbers
            long numOccur = numOccurence(fWord, secWord, count);
            System.out.println(numOccur);
        }
    }
}
