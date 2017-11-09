package Lab5.matrix;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {

        try {
            Matrix jeden = new Matrix("C:\\Users\\Mateusz\\Desktop\\test.txt");
            Matrix dwa = new Matrix("C:\\Users\\Mateusz\\Desktop\\test2.txt");
            Matrix out_ = jeden.Add(dwa);
            out_.Print();
        }catch (IOException a){

        }catch (MatrixDimensionsException b){

        }


    }
}
