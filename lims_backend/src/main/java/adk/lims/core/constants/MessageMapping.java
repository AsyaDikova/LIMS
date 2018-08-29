package adk.lims.core.constants;

public final class MessageMapping {

    public static final class Analysis {
        public static final String PROBLEM_WITH_SAVING_ANALYSIS = "Analysis is not correct saved. Please try again.";
        public static final String SUCCESS_SAVING_ANALYSIS = "Success saved analysis.";
    }

    public static final class Patient {
        public static final String PROBLEM_WITH_SAVING_PATIENT = "Patient is not correct saved. Please try again.";
    }

    public static final class AnalysisResult {
        public static final String PROBLEM_WITH_SAVING_ANALYSIS_RESULT = "Analysis Result is not correct saved. Please try again.";
        public static final String ANALYSIS_RESULT_SUCCESS = "Success create analysis result.";
    }

    public static final class Consultation {
        public static final String PROBLEM_WITH_CREATE_CONSULTATION = "Problem with create consultation.";
        public static final String SUCCESSFUL_CREATE_CONSULTATION = "Successful create consultation.";
    }

    public static final class Employee {
        public static final String PROBLEM_WITH_REGISTER_EMPLOYEE = "You are not register employee with email: %s";
        public static final String SUCCESSFUL_CREATE_EMPLOYEE = "Successful register employee with email: %s and password: %s";
    }

    private MessageMapping(){}
}
