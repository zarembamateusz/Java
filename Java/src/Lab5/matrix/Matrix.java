package Lab5.matrix;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Matrix {
    int size_horizontal;
    int size_vertical;
    int[][] matrix = new int[size_horizontal][size_vertical];

    Matrix(int[][] _matrix) {
        this.Set(_matrix);
    }
    Matrix(String path) throws IOException {
        LinkedList<LinkedList<String>> macierz = new LinkedList<LinkedList<String>>();
        File file = new File(path);

        try(Scanner in_ = new Scanner(file);) {
            int h=0;
            String tekst= "";
            while (in_.hasNextLine()){
                macierz.add(new LinkedList<String>());
                tekst=in_.nextLine();
                int j=0;
                while (j<tekst.length()){
                    if(!",".equals(""+tekst.charAt(j)))
                        macierz.get(h).add("" + tekst.charAt(j));
                    j++;
                }
                h++;
            }
        }
        int maxSize=0;
        for(int i=0;i<macierz.size();i++){
            if(macierz.get(i).size()>=maxSize)
                maxSize=macierz.get(i).size();
        }
        int[][] tmp= new int[macierz.size()][maxSize];
        for(int j=0;j<macierz.size();j++){
            for(int k=0;k<macierz.get(j).size();k++)
                tmp[j][k]=Integer.parseInt(macierz.get(j).get(k));
        }
        this.Set(tmp);
    }


    private void Set(int[][] matrix_){
        this.matrix = matrix_;
        this.size_horizontal = matrix_.length;
        this.size_vertical = matrix_[0].length;
    }

    public Matrix Add(Matrix matrix_1)throws MatrixDimensionsException  {
        if(matrix_1.size_horizontal!=this.size_horizontal||matrix_1.size_vertical!=this.size_vertical)
            throw new MatrixDimensionsException();

        int[][] output_matrix = new int[size_horizontal][size_vertical];
        for (int i = 0; i < this.size_horizontal; i++) {
            for (int j = 0; j < this.size_vertical; j++)
                output_matrix[i][j] = this.matrix[i][j] + matrix_1.matrix[i][j];
        }
        return new Matrix(output_matrix);
    }

    public Matrix Sub(Matrix matrix_1)throws MatrixDimensionsException  {
        if(matrix_1.size_horizontal!=this.size_horizontal||matrix_1.size_vertical!=this.size_vertical){
            throw new MatrixDimensionsException();
        }
        int[][] output_matrix = new int[size_horizontal][size_vertical];

        for (int i = 0; i < this.size_horizontal; i++) {
            for (int j = 0; j < this.size_vertical; j++) {
                output_matrix[i][j] = this.matrix[i][j] - matrix_1.matrix[i][j];
            }
        }
        return new Matrix(output_matrix);
    }

    public Matrix Mul(Matrix matrix_1)throws MatrixDimensionsException {
        if(this.size_horizontal!=matrix_1.size_vertical){
            throw new MatrixDimensionsException();
        }
        int[][] output_matrix = new int[size_horizontal][size_vertical];
        for (int current_row = 0; current_row < size_horizontal; current_row++) {
            for (int current_col = 0; current_col < size_vertical; current_col++) {
                int current_element = 0;
                for (int k = 0; k < size_vertical; k++)
                    current_element += this.matrix[current_row][k] * matrix_1.matrix[k][current_col];

                output_matrix[current_row][current_col] = current_element;
            }
        }
        return new Matrix(output_matrix);
    }

    public void Print() {
        for (int i = 0; i < this.size_horizontal; i++) {
            for (int j = 0; j < this.size_vertical; j++) {
                System.out.print(this.matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}

