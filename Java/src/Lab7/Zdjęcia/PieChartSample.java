package Lab7.Zdjęcia;

import io.indico.Indico;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PieChartSample extends Application {

    @Override public void start(Stage stage) throws IOException, IndicoException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Prawdopodobieństwa");
        stage.setWidth(500);
        stage.setHeight(500);

        Indico indico = new Indico("4888ed2cfd3d06ec25cf616684770600");
        File f = new File("C:\\Users\\Mateusz\\Desktop\\foto");

        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("jpg");
            }
        });
        int j = 0;
        String[] foty = new String[matchingFiles.length];
        while (matchingFiles.length > j) {
            foty[j] = matchingFiles[j].getAbsolutePath();
            j++;
        }



        BatchIndicoResult multiple = indico.imageRecognition.predict(foty);
        List<Map<String, Double>> results = multiple.getImageRecognition();
        String folderDir = "C:\\Users\\Mateusz\\Desktop\\foto";
        String output = "";
        LinkedList<String> nazwy =new LinkedList<>();
        LinkedList<ObservableList<PieChart.Data>> prawdowpodobienstwa =new LinkedList<>();

        int i = 0;
        for (Map<String, Double> map : results) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            double chance = 0;
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                pieChartData.add(new PieChart.Data(pair.getKey().toString(),(double)pair.getValue()));
                if ((double) pair.getValue() > chance) {
                    chance = (double) pair.getValue();
                    output = pair.getKey().toString();
                }
                it.remove();
            }
            String directoryPath = folderDir + "\\" + output;
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File activeFile = matchingFiles[i];
            activeFile.renameTo(new File(directoryPath + "\\" + activeFile.getName()));
            nazwy.add(activeFile.getName());
            prawdowpodobienstwa.add(pieChartData);
            i++;
        }

        int y=0;
        for(int k=0;k<nazwy.size();k++) {
            if (nazwy.get(k).equals("bocian")){
                y = k;
                break;
            }

        }


        final PieChart chart = new PieChart(prawdowpodobienstwa.get(y));
        chart.setTitle("bocian");

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}