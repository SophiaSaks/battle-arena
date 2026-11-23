package arena;

public enum LoggColors {
    GREEN_INFO("\u001B[32m"),
    YELLOW_WARNING("\u001B[33m"),
    RESET("\u001B[0m");

    private final String ansi;

    public String getAnsi(){
        return ansi;
    }

    LoggColors(String ansi) {
        this.ansi = ansi;
    }
}
