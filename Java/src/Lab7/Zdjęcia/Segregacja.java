package Lab7.Zdjęcia;

import io.indico.Indico;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Segregacja {


    private void segreguj() throws IndicoException, IOException {

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
            System.out.println(matchingFiles[i]);
            File activeFile = matchingFiles[i];
            activeFile.renameTo(new File(directoryPath + "\\" + activeFile.getName()));
            nazwy.add(activeFile.getName());
            prawdowpodobienstwa.add(pieChartData);
            i++;
            System.out.println(chance + ":" + output);
        }

    int y=0;
    for(int k=0;k<nazwy.size();k++) {
        if (nazwy.get(k).equals("bocian")){
        y = k;
        break;
        }

    }

    }




}
