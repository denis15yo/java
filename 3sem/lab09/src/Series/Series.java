package Series;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    private static final int N = 5;

    double first;
    double step;

    Series(){
        first = 0;
        step = 0;
    }
    Series(double first, double step) {
        this.first = first;
        this.step = step;
    }

    public abstract double calcElementByIndex(int index);
    public double calcSum(){
        double sum = 0;
        for(int i = 1; i <= N; ++i){
            sum += calcElementByIndex(i);
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 1; i <= N; ++i){
            str.append(calcElementByIndex(i)).append(" ");
        }
        return str.toString();
    }

    public void saveToFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(fileName, false);
        writer.write("Elements: " + toString() + "\n");
        writer.append("Sum = ").append(String.valueOf(calcSum())).append("\n");
        writer.flush();
    }
}
