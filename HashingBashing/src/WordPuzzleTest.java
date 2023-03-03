import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class WordPuzzleTest {

	private WordPuzzle wordPuzzle = new WordPuzzle();

	private char[][] createMatrix() {
        char[][] matrix = { { 'D', 'I', 'D', 'P' },
					         { 'I', 'L', 'L', 'Z' },
					         { 'G', 'R', 'O', 'W' },
					         { 'S', 'I', 'S', 'T' } };

        return matrix;
		
	}
    private void printMatrix(char[][] matrix){
        // length returns number of rows
        for (int i = 0; i < matrix.length; i++){
            // here length returns number of columns corresponding to current row
            for (int j = 0; j < matrix[i].length; j++){
                // using tabs for equal spaces, looks better aligned
	            // matrix[i][j] will return each element placed at row ‘i',column 'j'     DIDP \t 
		        System.out.print( matrix[i][j]  + "\t");
	     }
	     System.out.println();
	   }
	}


	@Test
	void createAndPrint() {
        //char[][] matrix = new char[4][4];
		char[][] matrix  = createMatrix();
        printMatrix(matrix);
	}



} 
