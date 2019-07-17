package main;

import data.Data;
import perceptron.Perceptron;

public class Iris_Main {
    //TODO -1 oder 0? In Pycharm nachsehen!

    public static void main(String[] args) {
        new Data();


        Perceptron pc = new Perceptron(0.01, 0, 4);


        /*
        for(double[] d : Data.iris){
            System.out.println(pc.predict(d));

            for(int i = 0; i<1000; i++){
                pc.learn(d);
            }
        }
         */
        for (int i = 0; i < 100; i++) {
            for (double[] d : Data.refDataList) {
                for (int j = 0; j < 100; j++)
                    pc.learn(d);
            }

        }

        for (double[] d : Data.refDataList) {

            System.out.println(pc.predict(d));
        }

        int errors = 0;
        for (double[] d : Data.refDataList) {
            boolean x = pc.isPredictionRight(d);
            if (!x)
                errors++;
            System.out.println(x);
        }


        pc.test();
        System.out.println("Errors:" + errors);


//weights: [-8.706985611718444, 12.811764155280432, 0.8892512942671809, 0.8627842087391915]
    }


}

