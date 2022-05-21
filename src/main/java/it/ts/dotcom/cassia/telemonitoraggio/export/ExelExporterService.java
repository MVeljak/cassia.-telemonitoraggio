package it.ts.dotcom.cassia.telemonitoraggio.export;

import com.google.common.base.CaseFormat;
import it.ts.dotcom.cassia.telemonitoraggio.dto.ExportlDataViewDto;
import lombok.Builder;
import lombok.Setter;
import lombok.Value;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.lang.reflect.Field;
import java.sql.Date;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ExelExporterService<T> {

    @Setter
    private List<T> listRecords;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet;

    @Setter
    private Map<Integer, String> exportExel;

    public ExelExporterService(List<T> listRecords ){
        this.listRecords = listRecords;
        this.workbook = new XSSFWorkbook();
    }

    public void writeHeader(String sheetName) {
        sheet = workbook.createSheet(sheetName);

        Row row = sheet.createRow(0);

//        CellStyle style = creteStyle(16, true);
        StyleBuilder styleBuilder = StyleBuilder.builder()
                .heght(16)
                .bold(true)
                .build();

        CellStyle style = creteStyle(styleBuilder);

        for(Map.Entry<Integer, String> map: exportExel.entrySet()){
            Integer columnNumber = map.getKey();
            String columnName = map.getValue();
            createCell(row, columnNumber, columnName, style);
        }

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        CreationHelper createHelper = workbook.getCreationHelper();
        short format = createHelper.createDataFormat().getFormat("d-m-yy h:mm");
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        }
        else if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Instant){
            style.setDataFormat(format);
            cell.setCellValue(java.util.Date.from((Instant) value));
        } else if (value instanceof Date){
            style.setDataFormat(format);
            cell.setCellValue((Date) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    public void write() throws NoSuchFieldException, IllegalAccessException {
        int rowCount = 1;


        StyleBuilder styleBuilder = StyleBuilder.builder()
                .heght(14)
                .build();
        CellStyle cellStyle = creteStyle(styleBuilder);

        Map<Integer, Map<Integer, Object>> extracted = extrctGlobal();

        extracted.forEach((key, value) -> {
            //System.out.printf("Record: %s\n", key);
            for (Map.Entry<Integer, Object> map : value.entrySet()) {
               // System.out.printf("\t%s:\t%s\n", map.getKey(), map.getValue());
                Row row = sheet.createRow(key);
                createCell(row, map.getKey(), map.getValue(), cellStyle);
            }

        });

//        for (T record : listRecords) {
//            Row row = sheet.createRow(rowCount++);
//            int columnCount = 0;
//
////            createCell(row, columnCount++, record.getId(), style);
////            createCell(row, columnCount++, record.getStudentName(), style);
////            createCell(row, columnCount++, record.getExamYear(), style);
////            createCell(row, columnCount++, record.getScore(), style);
//
//        }
    }

    private CellStyle creteStyle(int heght, boolean bold) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(heght);
        font.setBold(bold);
        style.setFont(font);
        return style;
    }

    private CellStyle creteStyle(StyleBuilder builder) {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(builder.bold);

        font.setFontHeight(builder.heght);

        style.setFont(font);
        return style;
    }

    @Builder
    private static class StyleBuilder{
        int heght;
        @Builder.Default
        boolean bold = false;
    }

//    public void generate(HttpServletResponse response) throws IOException {
//        writeHeader();
//        write();
//        ServletOutputStream outputStream = response.getOutputStream();
//        workbook.write(outputStream);
//        workbook.close();
//
//        outputStream.close();
//
//    }

    private Map<Integer, Object> extractSingleRowVAlue(T t) throws NoSuchFieldException, IllegalAccessException {
        Map<Integer, Object> valueToExport = new HashMap<>();
        for(Map.Entry<Integer, String> map: exportExel.entrySet()){
            Integer columnNumber = map.getKey();
            String columnName = map.getValue();

            String formatColumnName = formatColumnName(columnName);

            Field field = new ValueGenerator(ExportlDataViewDto.class)
                    .getFieldValue(formatColumnName);

            Object fieldValue = Optional.ofNullable(
                    field.get(t)
            ).orElse("");

            valueToExport.put(columnNumber,fieldValue);
        }
        return valueToExport;


    }

    public Map<Integer, Map<Integer, Object>> extrctGlobal() throws NoSuchFieldException, IllegalAccessException {
        Map<Integer, Map<Integer, Object>> result = new HashMap<>();
        int index = 0;
        for (T record : listRecords) {
            Map<Integer, Object> extracted = extractSingleRowVAlue(record);
            result.put(index++, extracted);
        }

        return  result;
    }


    public static String formatColumnName(String oldValue){
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL,
                oldValue.toLowerCase().replace(" ", "_"));
    }

    @Value
    private static class ValueGenerator{

        Class<?> clazz;
        public Field getFieldValue(String columnValue) throws NoSuchFieldException, IllegalAccessException {
            Field fieldID = clazz.getDeclaredField(columnValue);
            fieldID.setAccessible(true);
            return fieldID;
        }


    }
}
