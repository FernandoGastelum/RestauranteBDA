/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 * La clase ReporteBO se encarga de generar reportes en formato PDF que contienen tablas de datos.
 * Utiliza Apache PDFBox para la creación y configuración de los documentos PDF.
 */
public class ReporteBO {
    /**
     * Constructor por defecto para la clase ReporteBO.
     */
    public ReporteBO() {
    }
    
    /**
     * Genera un archivo PDF que contiene una tabla basada en el modelo de datos proporcionado.
     * Permite al usuario seleccionar la ubicación de guardado a través de un cuadro de diálogo.
     *
     * @param model El modelo de datos (DefaultTableModel) que contiene los datos a incluir en la tabla.
     * @param nombreRestaurante El nombre del restaurante que se mostrará como encabezado en el PDF.
     */
    public void generarPDFConTabla(DefaultTableModel model, String nombreRestaurante) {
    try {
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage(PDRectangle.A0);
        documento.addPage(pagina);

        // Contenido de la página
        PDPageContentStream contenido = new PDPageContentStream(documento, pagina);
        contenido.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);

        // Configuración del encabezado
        float margin = 50;
        float yStart = pagina.getMediaBox().getHeight() - margin;
        float tableWidth = pagina.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;

        // Encabezado
        contenido.beginText();
        contenido.newLineAtOffset(margin, yStart);
        contenido.showText(nombreRestaurante);
        contenido.endText();

        // Fecha de generación
        contenido.beginText();
        contenido.newLineAtOffset(margin, yStart - 15);
        contenido.showText("Fecha de generación: " + LocalDate.now());
        contenido.endText();

        // Configuración de la tabla
        yPosition -= 40; // Espacio para el encabezado
        float rowHeight = 30; // Altura de fila
        float cellMargin = 5; // Margen dentro de la celda
        int columns = model.getColumnCount();
        float columnWidth = tableWidth / (float) columns;

        // Dibujar encabezados de columna
        for (int i = 0; i < columns; i++) {
            String header = model.getColumnName(i);
            drawCell(contenido, header, margin + i * columnWidth, yPosition, columnWidth, rowHeight);
        }
        yPosition -= rowHeight;

        // Dibujar las filas con datos (invirtiendo nombre y fechaHora)
        for (int row = 0; row < model.getRowCount(); row++) {
            String fechaHora = model.getValueAt(row, 0) != null ? model.getValueAt(row, 0).toString() : ""; // Obtener fechaHora
            String nombre = model.getValueAt(row, 1) != null ? model.getValueAt(row, 1).toString() : ""; // Obtener nombre

            for (int col = 0; col < columns; col++) {
                String celda;
                if (col == 0) {
                    celda = fechaHora; // Primer campo es fechaHora
                } else if (col == 1) {
                    celda = nombre; // Segundo campo es nombre
                } else {
                    celda = model.getValueAt(row, col) != null ? model.getValueAt(row, col).toString() : ""; // Otros campos
                }
                drawCell(contenido, celda, margin + col * columnWidth, yPosition, columnWidth, rowHeight);
            }
            yPosition -= rowHeight;

            // Línea de separación entre filas
            contenido.moveTo(margin, yPosition);
            contenido.lineTo(margin + tableWidth, yPosition);
            contenido.stroke();
        }

        // Dibujar las líneas de columna
        float tableBottomY = yPosition + rowHeight;
        for (int i = 0; i <= columns; i++) {
            float xPosition = margin + i * columnWidth;
            contenido.moveTo(xPosition, yStart);
            contenido.lineTo(xPosition, tableBottomY);
            contenido.stroke();
        }

        // Agregar número de página
        contenido.beginText();
        contenido.newLineAtOffset(pagina.getMediaBox().getWidth() - margin - 50, 20);
        contenido.showText("Página 1"); // Cambia esto si quieres calcular el número de páginas dinámicamente
        contenido.endText();

        contenido.close();

        // Cuadro de diálogo para seleccionar la ubicación de guardado
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos PDF", "pdf"));

        // Muestra el diálogo y obtiene la opción seleccionada
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            // Asegurarse de que la extensión sea .pdf
            if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }
            // Guardar el PDF en la ubicación seleccionada
            documento.save(fileToSave);
            JOptionPane.showMessageDialog(null, "PDF generado exitosamente en: "+ fileToSave.getAbsolutePath(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Operación de guardado cancelada.");
        }

        documento.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    /**
     * Dibuja una celda en el PDF con un borde y el texto alineado dentro de ella.
     *
     * @param contenido El flujo de contenido de la página donde se dibujará la celda.
     * @param text El texto que se mostrará dentro de la celda.
     * @param x La coordenada X de la celda.
     * @param y La coordenada Y de la celda.
     * @param width El ancho de la celda.
     * @param height La altura de la celda.
     * @throws IOException Si ocurre un error al escribir en el flujo de contenido.
     */
    private void drawCell(PDPageContentStream contenido, String text, float x, float y, float width, float height) throws IOException {
        contenido.setLineWidth(1f);
        contenido.moveTo(x, y);
        contenido.lineTo(x + width, y);
        contenido.lineTo(x + width, y - height);
        contenido.lineTo(x, y - height);
        contenido.closePath();
        contenido.stroke();

        // Ajustar el texto a la celda
        contenido.beginText();
        contenido.newLineAtOffset(x + 5, y - height + 5); // Ajustar el texto dentro de la celda
        contenido.showText(text);
        contenido.endText();
    }
}
