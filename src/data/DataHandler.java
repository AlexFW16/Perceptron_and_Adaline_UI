package data;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataHandler {

    public ArrayList<String> read(InputStream stream) {

        Scanner sc = new Scanner(stream);
        ArrayList<String> dataList = new ArrayList<>();

        sc.forEachRemaining(dataList::add);
        return dataList;

    }


}

