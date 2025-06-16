package Controller.Fight.Log;

public interface Logger {
    void printLog(String message);
    void addLog(String message);
    String getLog();
}
