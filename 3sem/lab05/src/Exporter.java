import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class Exporter {
    static void exportToExcel(Report report, File file) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("Report");
        Row row;
        Cell cell;

        CellStyle headerStyle = book.createCellStyle();
        headerStyle.setAlignment(headerStyle.ALIGN_CENTER);
        row = sheet.createRow(0);
        for(int i = 0; i < 3; ++i){
            cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
        }

        cell = row.getCell(0);
        cell.setCellValue("k");
        cell = row.getCell(1);
        cell.setCellValue("Term");
        cell = row.getCell(2);
        cell.setCellValue("Sum");

        String formattedDouble;
        CellStyle style = book.createCellStyle();
        style.setAlignment(style.ALIGN_RIGHT);
        int size = report.size();
        for(int i = 0; i < size; ++i){
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellValue(report.get(i).getK());
            cell = row.createCell(1);
            cell.setCellStyle(style);
            formattedDouble = String.format("%.6f", report.get(i).getTerm());
            cell.setCellValue(formattedDouble);
            cell = row.createCell(2);
            cell.setCellStyle(style);
            formattedDouble = String.format("%.6f", report.get(i).getSum());
            cell.setCellValue(formattedDouble);
        }

        sheet.autoSizeColumn(0);

        try {
            book.write(new FileOutputStream(file));
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
