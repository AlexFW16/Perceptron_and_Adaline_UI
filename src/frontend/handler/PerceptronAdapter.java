package frontend.handler;

import perceptron.Perceptron;

public class PerceptronAdapter {

    private static PerceptronAdapter ourInstance = new PerceptronAdapter();
    private static Perceptron perceptron;
    public static PerceptronAdapter getInstance() {
        return ourInstance;
    }

    private PerceptronAdapter() {
    }

    public static Perceptron getPerceptron() {
        return perceptron;
    }

    public static void setPerceptron(Perceptron perceptron) {
        PerceptronAdapter.perceptron = perceptron;
    }
}
