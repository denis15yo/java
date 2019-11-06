import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

public class MyMath {
    private static double[][] cloneTwoDimensionalArray(double[][] a){
        double[][] b =  a.clone();
        for(int i = 0; i < b.length; ++i){
            b[i] = a[i].clone();
        }

        return b;
    }

    public static double[] solveSystemOfEquations(double[][] matrixOfSystem, double[] freeMemberColumn) throws SystemCanNotBeSolvedException{
        final double EPS = 0.000001;

        double[][] a = cloneTwoDimensionalArray(matrixOfSystem);
        double[] b = freeMemberColumn.clone();

        int n = matrixOfSystem.length;
        double[] x = new double[n];

        //прямой ход метод Гаусса
        for(int i = 0; i < n ; ++i){
            if(Math.abs(a[i][i]) < EPS){
                throw new SystemCanNotBeSolvedException("На главной диагонали получился 0. Система не может быть решена методом Гаусса");
            }

            //делаем нули под главной диагональю
            for(int j = i + 1; j < n ; ++j){
                double c = -a[j][i] / a[i][i];
                for(int k = i; k < n ; ++k){
                    a[j][k] += (a[i][k] * c);
                }
                b[j] += (b[i] * c);
            }
        }

        //находим поочередно неизвестные
        for(int i = n - 1; i >= 0; --i){
            float temp = 0;
            for(int j = i + 1; j < n; ++j){
                temp += (a[i][j] * x[j]);
            }

            x[i] = (b[i] - temp) / a[i][i];
        }

        return x;
    }

    public static double[] multiplyMatrixByVector(double[][] a, double[] x) throws MultiplyMatrixByVectorException{
        int n = a[0].length;

        for(int i = 1; i < a.length; ++i){
            if(a[i].length != n){
                throw new MultiplyMatrixByVectorException("Некорректные размеры матрицы и вектора");
            }
        }
        if(x.length != n){
            throw new MultiplyMatrixByVectorException("Некорректные размеры матрицы и вектора");
        }

        double[] b = new double[n];
        for(int i = 0; i < a.length; ++i){
            double temp = 0;
            for(int j = 0; j < n; ++j){
                temp += a[i][j] * x[j];
            }
            b[i] = temp;
        }

        return b;
    }
}
