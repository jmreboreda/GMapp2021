package gmoldes.forms;

import gmoldes.utilities.Utilities;

public class TimeRecord {

    private String nameOfMonth;
    private String yearNumber;
    private String enterpriseName;
    private String quoteAccountCode;
    private String employeeName;
    private String employeeNIF;
    private String numberHoursPerWeek;


    public TimeRecord(String nameOfMonth,
                      String yearNumber,
                      String enterpriseName,
                      String quoteAccountCode,
                      String employeeName,
                      String employeeNIF,
                      String numberHoursPerWeek) {

        this.nameOfMonth = nameOfMonth;
        this.yearNumber = yearNumber;
        this.enterpriseName = enterpriseName;
        this.quoteAccountCode = quoteAccountCode;
        this.employeeName = employeeName;
        this.employeeNIF = employeeNIF;
        this.numberHoursPerWeek = numberHoursPerWeek;
    }

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    public String getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(String yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getQuoteAccountCode() {
        return quoteAccountCode;
    }

    public void setQuoteAccountCode(String quoteAccountCode) {
        this.quoteAccountCode = quoteAccountCode;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeNIF() {
        return employeeNIF;
    }

    public void setEmployeeNIF(String employeeNIF) {
        this.employeeNIF = employeeNIF;
    }

    public String getNumberHoursPerWeek() {
        return numberHoursPerWeek;
    }

    public void setNumberHoursPerWeek(String numberHoursPerWeek) {
        this.numberHoursPerWeek = numberHoursPerWeek;
    }

    @Override
    public String toString(){

        return Utilities.replaceWithUnderscore(this.getEnterpriseName())
                + "_Registro_Horario_"
                + this.getNameOfMonth() + "_"
                + this.getYearNumber() + "_"
                + Utilities.replaceWithUnderscore(this.employeeName);
    }
}
