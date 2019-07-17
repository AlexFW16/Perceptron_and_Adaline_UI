package perceptron;

import java.util.ArrayList;
import java.util.Random;

public class Perceptron {

    /*
    Instanciates a Perceptron.
    The sample length is meant to be the length of the features, not the length of the full supervised sample!
    E.g.: [x = 1, y = 1, classifier = 1] -> sample length = 2
     */

    //Learning-rate
    private double learningRate;
    //Threshold
    private double threshold;
    //Bias
    private double w_0;
    private ArrayList<Double> weights = new ArrayList<>();

    public void test(){

        System.out.println("learningRate: " + learningRate);
        System.out.println("Theta: " + threshold);
        System.out.println("weights: " +  weights.toString());
        System.out.println("w_0: " + w_0);
    }

    public Perceptron(double learningRate, double threshold, int sample_length) {
        this.learningRate = learningRate;
        this.threshold = threshold;

        //Initializes the variables with random doubles between 0.0 and 1.0
        Random rnd = new Random();
        for(int i = 0; i<sample_length; i++){
            this.weights.add(rnd.nextDouble());
        }
        //Sets the bias to 1
        w_0 = 1;

    }

    //Returns true if the prediction was right
    public boolean isPredictionRight(double[] sample) {

        return predict(sample) == sample[sample.length - 1];
    }
        //Appends the Heaviside function on the net_input to predict the classification
    public int predict(double[] sample){
       return heavisideFunction(net_input(sample));
    }

    //Learning-Algorithm | fit()
    public void learn(double[] data){

        //Adjusts the bias
        w_0 += learningRate * (data[data.length - 1] - predict(data));

        //DEBUG
    //   System.out.println("w_0: " + w_0);
       for(int i = 0; i<weights.size(); i++){

           double delta_w = learningRate * (data[data.length -1] - predict(data)) * data[i];
           //DEBUG
        // System.out.println("(" + (data[data.length - 1] + " - " + predict(data) + ") * " + data[i] + " | delta_w (" + i + ") " + delta_w));

           //Adjusts the weights
           weights.set(i, weights.get(i) + delta_w);

       }

    }


    //Calculates the netto-sum | w*x
    private double net_input(double[] sample){

        //Initializes the sum with w_0, thus it must not be added afterwards
        double sum = w_0;
        for(int i = 0; i<weights.size(); i++){
            sum += sample[i] * weights.get(i);
        }
        return sum;
    }

    private int heavisideFunction(double x){
        return x < threshold ? 0 : 1;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public double getThreshold() {
        return threshold;
    }

    public double getW_0() {
        return w_0;
    }

    public ArrayList<Double> getWeights() {
        return weights;
    }
}
