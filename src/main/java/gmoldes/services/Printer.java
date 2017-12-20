package gmoldes.services;

import java.awt.print.PrinterJob;
import java.io.File;
import java.util.Map;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

public class Printer {

    private static final String DEFAULT_PRINTER = "KONICA MINOLTA";

    public static void print(String pathToPDF, Map<String, String> printAttributes) throws Exception {

        PrintService serviceForPrint = null;
        PDDocument PDFDocumentLoaded = PDDocument.load(new File(pathToPDF));

        PrintRequestAttributeSet datts = new HashPrintRequestAttributeSet();
        MediaSizeName DINA4 = MediaSize.ISO.A4.getMediaSizeName();
        datts.add(DINA4);
        if(printAttributes.get("sides").equals("DUPLEX")) {
            datts.add(Sides.DUPLEX);
        } else{
            datts.add(Sides.ONE_SIDED);
        }
        if(printAttributes.get("chromacity").equals("MONOCHROME")) {
            datts.add(Chromaticity.MONOCHROME);
        }else{
            datts.add(Chromaticity.COLOR);
        }
        if(printAttributes.get("quality").equals("HIGH")) {
            datts.add(PrintQuality.HIGH);
        }else{
            datts.add(PrintQuality.NORMAL);
        }
        if(printAttributes.get("orientation").equals("LANDSCAPE")) {
            datts.add(OrientationRequested.LANDSCAPE);
        }else{
            datts.add(OrientationRequested.PORTRAIT);
        }
        datts.add(new Copies(1));
        datts.add(new JobName("GMappJob", null));

        PrintService[] printServices = findPrintService(datts);
        if(printServices.length == 0){
            System.out.println("No printer can print the document ...");
        }
        else {
            for(PrintService printService : printServices){
                if(printService.getName().contains(DEFAULT_PRINTER)){
                    serviceForPrint = printService;
                }else {
                    serviceForPrint = ServiceUI.printDialog(null, 100, 100, printServices, null, null, datts);
                }
            }
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(PDFDocumentLoaded));
            job.setPrintService(serviceForPrint);
            job.print();
        }
        PDFDocumentLoaded.close();
    }

    private static PrintService[] findPrintService(PrintRequestAttributeSet datts) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, datts);
        for (PrintService printService : printServices) {
            System.out.println(printService.getName());
        }
        return printServices;
    }
}
