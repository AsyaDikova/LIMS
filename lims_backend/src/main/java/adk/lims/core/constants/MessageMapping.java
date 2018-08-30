package adk.lims.core.constants;

public final class MessageMapping {

    public static final class Analysis {
        public static final String PROBLEM_WITH_SAVING_ANALYSIS = "Analysis is not correct saved. Please try again.";
        public static final String SUCCESS_SAVING_ANALYSIS = "Success saved analysis.";
        public static final String MODEL_FIELD = "Min price is 1.00, Min TAT is 1";
    }

    public static final class Patient {
        public static final String PROBLEM_WITH_SAVING_PATIENT = "Patient is not correct saved. Please try again.";
    }

    public static final class AnalysisResult {
        public static final String PROBLEM_WITH_SAVING_ANALYSIS_RESULT = "Analysis Result is not correct saved. Please try again.";
        public static final String ANALYSIS_RESULT_SUCCESS = "Success create analysis result.";
        public static final String ERROR_MESSAGE = "Analysis and patient are required.";
    }

    public static final class Consultation {
        public static final String PROBLEM_WITH_CREATE_CONSULTATION = "Problem with create consultation.";
        public static final String SUCCESSFUL_CREATE_CONSULTATION = "Successful create consultation.";
        public static final String ERROR_MESSAGE = "Analysis, patient are required, date of consultation must be after today";
    }

    public static final class Employee {
        public static final String PROBLEM_WITH_REGISTER_EMPLOYEE = "You are not register employee with email: %s";
        public static final String SUCCESSFUL_CREATE_EMPLOYEE = "Successful register employee with email: %s and password: %s";
        public static final String ERROR_MESSAGE = "Email, password, firstName, lastName, phoneNumber are required.";
    }

    private MessageMapping(){}
}
