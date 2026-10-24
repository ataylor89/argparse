package argparse;

public class Option {

    private String shortName;
    private String longName;
    private boolean hasArg;
    private String description;
    private Object value;

    public Option(String shortName, String longName, boolean hasArg, String description) {
        this.shortName = shortName;
        this.longName = longName;
        this.hasArg = hasArg;
        this.description = description;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public void hasArg(boolean hasArg) {
        this.hasArg = hasArg;
    }

    public boolean hasArg() {
        return hasArg;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
