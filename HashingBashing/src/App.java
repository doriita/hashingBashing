import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;

public class App {

    public Set<String> subWords = new HashSet<>();
    public Map<String, Integer> wordMap = new HashMap<>();

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
                wordMap.put(line.toString(), null);
            });
        }


    }


    public List<String> findWords() {
        List<String> matchingWords = new ArrayList<>();
        for (String subWord : subWords) {
            if (wordMap.containsKey(subWord)) {
                matchingWords.add(subWord);

            }

        }
        return matchingWords;

    }


    public char[][] createMatrix(int matrixSize) {
        char[][] newPuzzle = new char[matrixSize][matrixSize];
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        Random random = new Random();
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                newPuzzle[i][j] = alphabet[random.nextInt(26)];

            }
        }
        return newPuzzle;


    }

    public void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }


    //public class mainTest {
    public static void main(String[] args) throws Exception {
        App game = new App();

        char[][] puzzle = game.createMatrix(16);
        game.printMatrix(puzzle);
        game.findAllSubWords(puzzle);

        game.wordlistToMap();
        //System.out.println(game.wordMap);
        for (String str : game.subWords) {
            System.out.println(str);

        }
        System.out.println(game.wordMap.size());
        System.out.println(game.subWords.size());
        System.out.println(game.findWords());
        System.out.println(game.findWords().size());
    }
}
