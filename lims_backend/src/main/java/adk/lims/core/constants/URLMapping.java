package adk.lims.core.constants;

public final class URLMapping {
    public static final String REGISTER = "/register";
    public static final String CREATE = "/create";
    public static final String ADD = "/add";
    public static final String IS_EXIST = "isExist";

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

    public static final class Analysis {
        public static final String ANALYZES_BASE = "/analyzes";
        public static final String ANALYZES_ONE = "/analyzes/{id}";
        public static final String ANALYSIS_ADD = "/analysis/add";
        public static final String ANALYSIS_NAMES_LIST = "analysis/namesList";

    }

    public static final class DaySchedule {
        public static final String DAY_SCHEDULE_BASIC = "/daySchedule";
        public static final String DAY_SCHEDULE_FREE_HOURS = "/freeHours";
        public static final String DAY_SCHEDULE_EMPLOYEE = "/employee";
    }

    private URLMapping(){}
}
