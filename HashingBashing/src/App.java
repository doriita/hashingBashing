import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class App {
    public int matrixSize;
    public Set<String> subWords = new HashSet<>();
    public Map<String, Integer> wordMap = new HashMap<>();

    private void run(int matrixSize) throws IOException {
        this.matrixSize = matrixSize;
        char[][] puzzle = createMatrix();
        wordlistToMap();
        findAndPrintMatches();

    }



    /**

     This method finds all subwords in a 2D char matrix by searching
     horizontal, vertical, diagonal, it then reverses all found words and adds them to the set and returns the
     set containing all subwords.
     @param matrix takes a 2D char array to find the subwords within.
     @ return a Set of Strings representating the found words in the 2D array with duplicates filtered out..
     */

    public Set<String> findAllSubWords(char[][] matrix) {

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
    }





    public void wordlistToMap() throws IOException {
        String fileName = "C:\\Users\\pette\\Documents\\GitHub\\hashingBashing\\HashingBashing\\src\\wordlist.txt";
        File file = new File(fileName);

        try (

                Stream linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                if (line.toString().length() <= matrixSize)
                    wordMap.put(line.toString(), null);
            });
        }


    }


    public void findAndPrintMatches() {

        System.out.println("\nThe found words were: \n");

        List<String> matchingWords = new ArrayList<>();
        for (String subWord : subWords) {
            if (wordMap.containsKey(subWord.toLowerCase())) {
                matchingWords.add(subWord);

            }

        }
        Collections.sort(matchingWords);
        System.out.println(matchingWords);


    }


    public char[][] createMatrix() {

        char[][] newPuzzle = new char[matrixSize][matrixSize];
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random random = new Random();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                newPuzzle[i][j] = alphabet[random.nextInt(26)];

            }
        }
        printMatrix(newPuzzle);
        findAllSubWords(newPuzzle);

        return newPuzzle;
    }
    /**

     This method prints a 2D char matrix to the console widow.
     @param matrix takes a 2D char array to be printed.
     */

    public void printMatrix(char[][] matrix) {
        System.out.println("The Matrix:");
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }


    public static void main(String[] args) throws Exception {
        App game = new App();
        game.run(4);
        //System.out.println(game.wordMap.size());

        /*char[][] puzzle = game.createMatrix();
        game.printMatrix(puzzle);
        game.findAllSubWords(puzzle);

        game.wordlistToMap();

        System.out.println(game.wordMap.size());
        System.out.println(game.subWords.size());
        game.findAndPrintMatches();*/

    }


}
