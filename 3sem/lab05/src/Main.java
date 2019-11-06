import java.io.File;

public class Main {
    public static void main(String[] args) {
        try{
            if(args.length != 2){
                throw new IllegalArgumentException("Неверное число аргументов.");
            }
        }
        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
            System.out.println("Программа завершена.");
            return;
        }

        double x;
        double eps;

        try{
            x = Double.parseDouble(args[0]);
            eps = Double.parseDouble(args[1]);
        }
        catch(NumberFormatException e){
            System.out.println("Вы ввели неверное число. Программа завершена.");
            return;
        }

        if(eps < 0){
            eps = -eps;
            System.out.println("Введена отрицательная погрешность. Её значение взято по модулю");
        }

        SumOfSeries sumOfSeries = new SumOfSeries(x, eps);
        try{
            sumOfSeries.calc();
        }
        catch(SeriesDivergesException e){
            System.out.println(e.getMessage());
            System.out.println("Программа завершена.");
            return;
        }

        double sum = sumOfSeries.getSum();
        Report report = sumOfSeries.getReport();

        File file = new File ("/Users/denisshilovich/java/3sem/lab05/reportSum.xls");
        Exporter.exportToExcel(report, file);

        System.out.println("Сумма ряда = " + String.format("%.6f", sum));
        System.out.println("Отчет находится в lab05/reportSum.xls");
    }
}
