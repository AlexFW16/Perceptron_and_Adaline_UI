package main;

import perceptron.Perceptron;

public class Main_test {

    public static void main(String[] args){

        //Sample data with linear parting line
     double[] d1 = {1, 1, 1};
     double[] d2 = {0.5, 0.4, 0};
     double[] d3 = {0.5, 0.6, 1};
     double[] d4 = {0.2, 0.1, 0};
     double[] d5 = {0.2, 0.4, 1};
     double[] d6 = {0.1, 0.1, 1};
     double[] d7 = {0.1, 0.05, 0};
     double[] d8 = {0.7, 0.1, 0};
     double[] d9 = {0.7, 0.8, 1};

        double[][] all = {d1, d2, d3, d4, d5, d6, d7, d8, d9};

        //Creating Perceptron
        Perceptron pc = new Perceptron(0.01, 0, 2);
        pc.test();

        System.out.println("Learning...");

        for(double[] d : all){
            for (int i = 0; i<10000; i++) {
                pc.learn(d);
            }
        }

        System.out.println("Predicting after learning:");

        System.out.println("d1:" + pc.predict(d1));
        System.out.println("d2:" + pc.predict(d2));
        System.out.println("d3:" + pc.predict(d3));
        System.out.println("d4:" + pc.predict(d4));
        System.out.println("d5:" + pc.predict(d5));
        System.out.println("d5:" + pc.predict(d6));
        System.out.println("d5:" + pc.predict(d7));
        System.out.println("d5:" + pc.predict(d8));
        System.out.println("d5:" + pc.predict(d9));

        double[] test = {0.8, 0.6};
        System.out.println("NEW: " + pc.predict(test));


/*
        pc.learn(d1);
        pc.test();
        System.out.println("D1: " + pc.predict(d1));
*/
    }


}
