package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class Data {

    public static ArrayList<double[]> refDataList;

    //
    {
        try {
            InputStream data = new FileInputStream(new File("C:\\Users\\User\\Desktop\\iris.data"));
            DataHandler handler = new DataHandler();
            ArrayList<String> dataList = handler.read(data);
            refDataList = new ArrayList<>();


            dataList.forEach(element -> {
                String[] contentArray = element.split(",");
                double[] contentArrayDouble = new double[contentArray.length];

                for (int i = 0; i < contentArray.length; i++) {

                    contentArrayDouble[i] = Double.parseDouble(contentArray[i]);
                }

                refDataList.add(contentArrayDouble);

            });
            Collections.shuffle(refDataList);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Iris data-collection

    //setos = 1, versicolor = -1
    public static double[][] iris = {
            {5.1, 3.5, 1.4, 0.2, 1},
            {5.2, 2.7, 3.9, 1.4, -1},
            {4.9, 3.0, 1.4, 0.2, 1},
            {4.9, 2.4, 3.3, 1. - 1, 0},
            {4.7, 3.2, 1.3, 0.2, 1},
            {6.5, 2.8, 4.6, 1.5, -1},
            {4.6, 3.1, 1.5, 0.2, 1},
            {5.0, 3.6, 1.4, 0.2, 1},
            {7.0, 3.2, 4.7, 1.4, -1},
            {6.3, 3.3, 4.7, 1.6, -1},
            {5.4, 3.9, 1.7, 0.4, 1},
            {6.9, 3.1, 4.9, 1.5, -1},
            {4.6, 3.4, 1.4, 0.3, 1},
            {5.7, 2.8, 4.5, 1.3, -1},
            {5.0, 3.4, 1.5, 0.2, 1},
            {4.4, 2.9, 1.4, 0.2, 1},
            {6.4, 3.2, 4.5, 1.5, -1},
            {5.5, 2.3, 4.0, 1.3, -1},
            {4.9, 3.1, 1.5, 0.1, 1},
            {6.6, 2.9, 4.6, 1.3, -1}
    };


}
