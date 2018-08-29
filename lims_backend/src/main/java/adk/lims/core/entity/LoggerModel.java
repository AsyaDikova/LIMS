package adk.lims.core.entity;

public class LoggerModel {
    private String method;
    private String requestUrl;

    public LoggerModel() {
    }

    public LoggerModel(String method, String requestUrl) {
        this.method = method;
        this.requestUrl = requestUrl;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
}
