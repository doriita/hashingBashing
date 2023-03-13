import java.io.*;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Stream;
/**

 *The App class represents an application that generates a 2D matrix of characters with a given size,
 *searches for all possible subwords in the matrix, and then searches a given word list file to find any matches.
 *The matching words are then printed in alphabetical order to the console.
 */

public class App {
    public int matrixSize;
    public Set<String> subWords = new HashSet<>();
    public Map<String, Integer> wordMap = new HashMap<>();

    /**
     * This method runs the program with given matrix(two-dimensional array) size and file path.
     *
     * @param matrixSize the size of the matrix to be created
     * @param filePath   the file path of the word list to be searched. Notice that this path may differ from computer to computer.
     * @throws IOException if an input/output error occurs while reading the word list file
     */

    private void run(int matrixSize, String filePath) throws IOException {
        this.matrixSize = matrixSize;
        createMatrix();
        //read the wordlist from given filePath and store in a map
        wordlistToMap(filePath);
        //find and print out the matches between words in the wordList and the matrix
        findAndPrintMatches();

    }


    /**
     * This method finds all subwords in a 2D char matrix by searching
     * horizontal, vertical, diagonal, it then reverses all found words and adds them to the set and returns the
     * set containing all subwords.
     *
     * @param matrix takes a 2D char array to find the subwords within.
     * @ return a Set of Strings representating the found words in the 2D array with duplicates filtered out..
     */

    public Set<String> findAllSubWords(char[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // seraches horizontal
                StringBuilder stringBuilder = new StringBuilder();
                for (int k = j; k < cols; k++) {
                    stringBuilder.append(matrix[i][k]);
                    subWords.add(stringBuilder.toString());
                }

                // search vertical
                stringBuilder = new StringBuilder();
                for (int k = i; k < rows; k++) {
                    stringBuilder.append(matrix[k][j]);
                    subWords.add(stringBuilder.toString());
                }

                // search diagonal (down and right )
                stringBuilder = new StringBuilder();
                for (int k = i, l = j; k < rows && l < cols; k++, l++) {
                    stringBuilder.append(matrix[k][l]);
                    subWords.add(stringBuilder.toString());
                }

                // search diagonal (up and right)
                stringBuilder = new StringBuilder();
                for (int k = i, l = j; k >= 0 && l < cols; k--, l++) {
                    stringBuilder.append(matrix[k][l]);
                    subWords.add(stringBuilder.toString());
                }


            }
        }
        //reverses all words and adds them to the set
        Set<String> reversedSubwords = new HashSet<>();
        for (String subsequence : subWords) {
            reversedSubwords.add(new StringBuilder(subsequence).reverse().toString());
        }
        subWords.addAll(reversedSubwords);


        return subWords;
    }

    /**
     * This method reads lines from a file  and puts them  into a hashMap where the word is the key and
     * the value is null. It only adds all lines in the file that are shorter or equal to the length of the 2D array.
     *
     * @param filePath the filepath to the wordlist that should be added to the hashMap.
     * @ return a Set of Strings representating the found words in the 2D array with duplicates filtered out..
     * @ throws IOException if there is an error reading the file.
     */
    public void wordlistToMap(String filePath) throws IOException {
        File file = new File(filePath);
        //read lines from the file
        try (
                Stream linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                //tests lines VS length of matrix to only include words with amount of characters equals to size
                // of matrix or less.
                if (line.toString().length() <= matrixSize)
                    wordMap.put(line.toString(), null);
            });
        }


    }

    /**
     * This method iterates the Set subWords and checks if a subword exists in wordMap, if so the word is added to the
     * list matchingWords. The list matchingWords is then sorted in alphabetial order and printed to the console with one
     * word per row.
     */


    public void findAndPrintMatches() {
        //creates an empty list to store matching words
        List<String> matchingWords = new ArrayList<>();
        //iterates the subwords set and check for matches
        for (String subWord : subWords) {
            if (wordMap.containsKey(subWord.toLowerCase())) {
                //adding matches to the list
                matchingWords.add(subWord);

            }

        }
        //sorting the words in alphabetical order
        Collections.sort(matchingWords);
        System.out.println("\nThe found words were: \n");
        //iterating matching words and print one word per line.
        for (String word : matchingWords) {
            System.out.println(word);

        }


    }

    /**
     * This method creates a 2D char array of size matrixSize filled with randomly
     * generated uppercase letters from the English alphabet, prints the matrix, find all its sub-words and store them in a hashSet.
     */
    public void createMatrix() {
        //create a 2D char array with the size matrixSize matrizSize
        char[][] matrix = new char[matrixSize][matrixSize];
        //create a char array with letters of the english alphabet in uppercase
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        //create a Random object
        Random random = new Random();
        //loop through each row and column of the 2D array "newPuzzle" and fill each spot with a random letter from the char array "alphabet"
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = alphabet[random.nextInt(26)];

            }
        }
        //print matrix/2D char array
        printMatrix(matrix);
        //find all subwords of matrix and store them in class hash set "subWords"
        findAllSubWords(matrix);

    }

    /**
     * This method prints a 2D char matrix to the console window.
     *
     * @param matrix takes a 2D char array to be printed.
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
        game.run(16, "C:\\Users\\pette\\Documents\\GitHub\\hashingBashing\\HashingBashing\\src\\wordlist.txt");



    }


}
