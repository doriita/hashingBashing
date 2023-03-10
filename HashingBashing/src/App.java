import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
        //Skriver ut hello world
        System.out.println("Hello, World!");
        char[][]testmatix= {
                {'a','d','s','f'},
                {'f','r','e','d'},
                {'f','r','a','e'},
                {'r','u','t','a'}};
        System.out.println(findAllSubWords(testmatix));
    }
    public static Set<String> findAllSubWords(char[][] matrix) {
        Set<String> subWords = new HashSet<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // sök horisontellt
                StringBuilder stringBuilder = new StringBuilder();
                for (int k = j; k < cols; k++) {
                    stringBuilder.append(matrix[i][k]);
                    subWords.add(stringBuilder.toString());
                }

                // sök vertikalt
                stringBuilder = new StringBuilder();
                for (int k = i; k < rows; k++) {
                    stringBuilder.append(matrix[k][j]);
                    subWords.add(stringBuilder.toString());
                }

                // sök diagonalt (nedåt och höger)
                stringBuilder = new StringBuilder();
                for (int k = i, l = j; k < rows && l < cols; k++, l++) {
                    stringBuilder.append(matrix[k][l]);
                    subWords.add(stringBuilder.toString());
                }

                // sök diagonalt (uppåt och höger)
                stringBuilder = new StringBuilder();
                for (int k = i, l = j; k >= 0 && l < cols; k--, l++) {
                    stringBuilder.append(matrix[k][l]);
                    subWords.add(stringBuilder.toString());
                }

                // sök diagonalt (nedåt och vänster)
                stringBuilder = new StringBuilder();
                for (int k = i, l = j; k < rows && l >= 0; k++, l--) {
                    stringBuilder.append(matrix[k][l]);
                    subWords.add(stringBuilder.toString());
                }

                // sök diagonalt (uppåt och vänster)
                stringBuilder = new StringBuilder();
                for (int k = i, l = j; k >= 0 && l >= 0; k--, l--) {
                    stringBuilder.append(matrix[k][l]);
                    subWords.add(stringBuilder.toString());

                }
            }
        }
        Set<String> reversedSubwords = new HashSet<>();
        for (String subsequence : subWords) {
            reversedSubwords.add(new StringBuilder(subsequence).reverse().toString());
        }
        subWords.addAll(reversedSubwords);


        return subWords;
    }public static void wordlistToMap(){

    }
    public static void  findWords(){
        
    }

    
}
