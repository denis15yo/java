package myUtil;

import essenses.AgeBounds;
import essenses.Toy;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Exporter {
    public static void exportToXML(List<Toy> toys, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("<shop>\n");
        fileWriter.flush();
        toys.forEach(t -> {
            try {
                exportToyToXML(t, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.write("</shop>\n");
        fileWriter.flush();
    }

    public static void exportToyToXML(Toy toy, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("<toy>\n");
        fileWriter.write("<name>" + toy.getName() + "</name>\n");
        fileWriter.write("<cost>" + toy.getCost() + "</cost>\n");
        fileWriter.flush();
        exportAgeBoundsToXML(toy.getAgeBounds(), file);
        fileWriter.write("</toy>\n");
        fileWriter.flush();
    }

    public static void exportAgeBoundsToXML(AgeBounds ageBounds, File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write("<ageBounds>\n");
        fileWriter.write("<minAge>" + ageBounds.getMin() + "</minAge>\n");
        fileWriter.write("<maxAge>" + ageBounds.getMax() + "</maxAge>\n");
        fileWriter.write("</ageBounds>\n");
        fileWriter.flush();
    }
}
