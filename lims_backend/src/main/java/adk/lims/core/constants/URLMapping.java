package adk.lims.core.constants;

public final class URLMapping {
    public static final String REGISTER = "/register";
    public static final String CREATE = "/create";
    public static final String ADD = "/add";

    public static final class Patient {
        public static final String PATIENT_BASE = "/patient";
    }

    public static final class Employee{
        public static final String EMPLOYEE_BASE = "/employee";
    }

    public static final class AnalysisResult {
        public static final String ANALYSIS_RESULT_BASE = "analysisResult";
        public static final String ALL_MY_RESULT = "/myResults/all";
        public static final String ONE_MY_RESULT = "/myResults/{id}";
    }

    public static final class Consultation {
        public static final String CONSULTATION_BASE = "/consultation";
    }

    private URLMapping(){}
}
