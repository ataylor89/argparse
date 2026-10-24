package argparse;

public class Option {

    private String shortName;
    private String longName;
    private boolean hasArg;
    private Class type;
    private String description;

    public Option(String shortName, String longName, boolean hasArg, String description) {
        this.shortName = shortName;
        this.longName = longName;
        this.hasArg = hasArg;
        this.type = String.class;
        this.description = description;
    }

    public Option(String shortName, String longName, boolean hasArg, Class type, String description) {
        this.shortName = shortName;
        this.longName = longName;
        this.hasArg = hasArg;
        this.type = type;
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

    public void setType(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
