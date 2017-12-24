package gmoldes.domain.dto;

public class ProvisionalContractDataDTO {

    private String employerFullName;
    private String employeeFullName;
    private String ccc;
    private String contractType;
    private String dateFrom;
    private String dateTo;
    private String durationDays;
    private String workDayType;
    private String numberHoursPerWeek;
    private String daysWeekToWork;
    private String laboralCategory;

    public ProvisionalContractDataDTO(String employerFullName, String employeeFullName, String ccc, String contractType,
                                      String dateFrom, String dateTo, String durationDays, String workDayType,
                                      String numberHoursPerWeek, String daysWeekToWork, String laboralCategory) {
        this.employerFullName = employerFullName;
        this.employeeFullName = employeeFullName;
        this.ccc = ccc;
        this.contractType = contractType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.durationDays = durationDays;
        this.workDayType = workDayType;
        this.numberHoursPerWeek = numberHoursPerWeek;
        this.daysWeekToWork = daysWeekToWork;
        this.laboralCategory = laboralCategory;
    }

    public String getEmployerFullName() {
        return employerFullName;
    }

    public void setEmployerFullName(String employerFullName) {
        this.employerFullName = employerFullName;
    }

    public String getEmployeeFullName() {
        return employeeFullName;
    }

    public void setEmployeeFullName(String employeeFullName) {
        this.employeeFullName = employeeFullName;
    }

    public String getCcc() {
        return ccc;
    }

    public void setCcc(String ccc) {
        this.ccc = ccc;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDurationDays() {
        return durationDays;
    }

    public void setDurationDays(String durationDays) {
        this.durationDays = durationDays;
    }

    public String getWorkDayType() {
        return workDayType;
    }

    public void setWorkDayType(String workDayType) {
        this.workDayType = workDayType;
    }

    public String getNumberHoursPerWeek() {
        return numberHoursPerWeek;
    }

    public void setNumberHoursPerWeek(String numberHoursPerWeek) {
        this.numberHoursPerWeek = numberHoursPerWeek;
    }

    public String getDaysWeekToWork() {
        return daysWeekToWork;
    }

    public void setDaysWeekToWork(String daysWeekToWork) {
        this.daysWeekToWork = daysWeekToWork;
    }

    public String getLaboralCategory() {
        return laboralCategory;
    }

    public void setLaboralCategory(String laboralCategory) {
        this.laboralCategory = laboralCategory;
    }

    public static ProvisionalContractDataDTO.DataBuilder create() {
        return new ProvisionalContractDataDTO.DataBuilder();
    }

    public static class DataBuilder {

        private String employerFullName;
        private String employeeFullName;
        private String ccc;
        private String contractType;
        private String dateFrom;
        private String dateTo;
        private String durationDays;
        private String workDayType;
        private String numberHoursPerWeek;
        private String daysWeekToWork;
        private String laboralCategory;

        public ProvisionalContractDataDTO.DataBuilder withEmployerFullName (String employerFullName) {
            this.employerFullName = employerFullName;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withEmployeeFullName(String employeeFullName) {
            this.employeeFullName = employeeFullName;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withCcc(String ccc) {
            this.ccc = ccc;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withContractType(String contractType) {
            this.contractType = contractType;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withDateFrom(String dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withDateTo(String dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withDurationDays(String durationDays) {
            this.durationDays = durationDays;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withWorkDayType(String workDayType) {
            this.workDayType = workDayType;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withNumberHoursPerWeek(String numberHoursPerWeek) {
            this.numberHoursPerWeek = numberHoursPerWeek;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withDaysWeekToWork(String daysWeekToWork) {
            this.daysWeekToWork = daysWeekToWork;
            return this;
        }

        public ProvisionalContractDataDTO.DataBuilder withLaboralCategory(String laboralCategory) {
            this.laboralCategory = laboralCategory;
            return this;
        }

        public ProvisionalContractDataDTO build() {
            return new ProvisionalContractDataDTO(this.employerFullName, this.employeeFullName, this.ccc, this.contractType, this.dateFrom,
            this.dateTo, this.durationDays, this.workDayType, this.numberHoursPerWeek, this.daysWeekToWork, this.laboralCategory);
        }
    }
}
