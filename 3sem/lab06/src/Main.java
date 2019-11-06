import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Неверное количество аргументов(требуется одна строка)");
            System.out.println("Программа завершена");
            return;
        }
        System.out.println(args[0].replaceAll("(\\B)ing(\\b)", "$1ed$2"));
//        //StringBuilder
//        StringBuilder text = new StringBuilder(args[0]);
//        Pattern pattern = Pattern.compile("ing(\\s|$)");
//        Matcher matcher = pattern.matcher(text);
//        while(matcher.find()){
//            int start = matcher.start();
//            text.replace(start, start + 3, "ed");
//            matcher.reset();
//        }
//
//        System.out.println(text);
    }
}
