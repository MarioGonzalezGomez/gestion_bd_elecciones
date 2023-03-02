package com.mggcode.gestion_bd_elecciones.service;

import com.mggcode.gestion_bd_elecciones.model.Partido;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;



@Service
public class ExcelExportService {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public void writePartidoToExcel(List<Partido> partidos, HttpServletResponse response) throws IOException {
        // Inicializar un nuevo libro de trabajo
        workbook = new XSSFWorkbook();
        writeHeader();
        write(partidos);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    private void writeHeader() {
        sheet = workbook.createSheet("Partidos");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "CODIGO", style);
        createCell(row, 1, "SIGLAS", style);
        createCell(row, 2, "CODIGO PADRE", style);
        createCell(row, 3, "LITERAL", style);
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(List<Partido> partidos) {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Partido record : partidos) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getCodigo(), style);
            createCell(row, columnCount++, record.getSiglas(), style);
            createCell(row, columnCount++, record.getCodigoPadre(), style);
            createCell(row, columnCount++, record.getNombreCompleto(), style);
        }
    }


}
