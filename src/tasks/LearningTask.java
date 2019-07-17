package tasks;

import frontend.util.ValidationUtil;
import javafx.concurrent.Task;
import main.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class LearningTask extends Task {

    private File dataFile;
    private int epochs;
    private AtomicBoolean isCancelled = new AtomicBoolean(false);

    public void setDataFile(File dataFile) {
        this.dataFile = dataFile;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }



    @Override
    protected void cancelled() {
        this.isCancelled.set(true);
    }

    @Override
    public Void call() throws IOException {

            updateProgress(0, epochs);

            for(int i = 0; i<epochs; i++){
                learn(dataFile);
                updateProgress(i, epochs);
                if(isCancelled.get())
                    return null;
            }

            updateProgress(-1.0, 1);
            this.done();
        return null;
    }



    private void learn(File dataFile) throws IOException{
        String[] input;

        FileReader reader = new FileReader(dataFile);
        BufferedReader bufferedReader = new BufferedReader(reader);

        /*
        Puts each line of the given file into a double array
        In case a line is not valid, the learning process for this object is skipped
         */
        while (true){
            try {
                input = bufferedReader.readLine().split(Main.SEPARATOR);
            }
            catch (NullPointerException e){
                break;
            }


            if(ValidationUtil.validateDoubleStringArray(input)){

                double[] doubleInput = Arrays.stream(input)
                        .mapToDouble(Double::parseDouble)
                        .toArray();

                Main.perceptron.learn(doubleInput);
            }
            else
                break;
        }

        bufferedReader.close();

    }

}
