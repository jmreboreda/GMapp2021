package gmoldes.services;

import java.awt.print.PrinterJob;
import java.io.File;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class Printer {

        public static void print(String pathToPDF) throws Exception {

            PDDocument document = PDDocument.load(new File(pathToPDF));

            PrintRequestAttributeSet datts = new HashPrintRequestAttributeSet();
            datts.add(Sides.DUPLEX);
            datts.add(OrientationRequested.LANDSCAPE);
            datts.add(new Copies(2));
            datts.add(new JobName("RBJM job", null));


            PrintService printService = findPrintService("PDF");
            if(printService == null){
                System.out.println("No se ha encontrado KONICA MINOLTA");
            }
            else {

                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPageable(new PDFPageable(document));
                job.setPrintService(printService);
                job.print();
            }
        }

        private static PrintService findPrintService(String printerName) {
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
            for (PrintService printService : printServices) {
                System.out.println(printService.getName());
                if (printService.getName().trim().contains(printerName)) {
                    return printService;
                }
            }
            return null;
        }
}
