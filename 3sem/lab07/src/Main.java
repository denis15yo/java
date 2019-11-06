import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc;
        try{
            sc = new Scanner(new File("input.txt"));
        }
        catch(FileNotFoundException e){
            System.out.println("Файл не найден. Программа завершена");
            return;
        }

        int n = 0;
        try{
            n = sc.nextInt();
            sc.nextLine();
        }
        catch(InputMismatchException e){
            System.err.println("Некорректное число n в файле. Программа заверешена");
            return;
        }
        double[][] a = new double[n][n];
        double[] b = new double[n];

        try{
            for(int i = 0; i < n; ++i){
                String[] stringNumbers = sc.nextLine().split("\\s+\\|?\\s*");
                if(stringNumbers.length != n + 1){
                    throw new IncorrectAmountOfDataException("В одной из строк некорректное количество данных");
                }
                for(int j = 0; j < stringNumbers.length - 1 ; ++j){
                    a[i][j] = Double.parseDouble(stringNumbers[j]);
                }
                b[i] = Double.parseDouble(stringNumbers[stringNumbers.length - 1]);
            }
        }
        catch(NumberFormatException e){
           System.err.println("Некорректный формат числа в файле. Программа завершена");
           return;
        }
        catch(IncorrectAmountOfDataException e){
            System.err.println(e.getMessage());
            System.err.println("Программа заверешена");
            return;
        }
        if(sc.hasNext()){
            System.out.println("В файле слишком много данных. Взяты первые необходимые.");
        }

        try{
            double[] x = MyMath.solveSystemOfEquations(a, b);

            System.out.println("Решение:");
            System.out.println(Arrays.toString(x));

            System.out.println("B:");
            System.out.println(Arrays.toString(MyMath.multiplyMatrixByVector(a, x)));
        }
        catch (SystemCanNotBeSolvedException | MultiplyMatrixByVectorException e){
            System.err.println(e.getMessage());
            System.err.println("Программа завершена");
        }

    }
}
