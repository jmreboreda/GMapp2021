package gmoldes.utilities;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.AcroFields;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import gmoldes.forms.TimeRecord;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class CreateTimeRecordPDF {

    private static final String USER_HOME = System.getProperty("user.home");
    private static final String PATHIN = "/pdf_forms/DGM_002_Registro_Horario.pdf";

    public static void createPDF(TimeRecord timeRecord) throws IOException, DocumentException {

        final String PATHOUT = USER_HOME + "/Escritorio/" + timeRecord.toString() + ".pdf";
        PdfReader reader = new PdfReader(PATHIN);
        PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(PATHOUT));

        AcroFields timeRecordPDF = stamp.getAcroFields();
        HashMap map = timeRecordPDF.getFields();
        timeRecordPDF.setField("nameOfMonth",timeRecord.getNameOfMonth());
        timeRecordPDF.setField("yearNumber",timeRecord.getYearNumber());
        timeRecordPDF.setField("enterpriseName",timeRecord.getEnterpriseName());
        timeRecordPDF.setField("quoteAccountCode",timeRecord.getQuoteAccountCode());
        timeRecordPDF.setField("employeeName",timeRecord.getEmployeeName());
        timeRecordPDF.setField("employeeNIF",timeRecord.getEmployeeNIF());
        timeRecordPDF.setField("numberHoursPerWeek",timeRecord.getNumberHoursPerWeek() + " horas/semana");
        timeRecordPDF.setField("enterpriseSignature","Firmado: " + timeRecord.getEnterpriseName());
        timeRecordPDF.setField("employeeSignature","Firmado: " + timeRecord.getEmployeeName());

        stamp.setFormFlattening(true);
        stamp.close();
    }
}
