package argparse;

import java.util.HashMap;
import java.util.Map;

public class Options {

    private Map<String, Option> options;

    public Options() {
        options = new HashMap<>();
    }

    public void addOption(Option option) {
        String shortName = option.getShortName();
        String longName = option.getLongName();
        if (shortName == null && longName == null) {
            throw new IllegalArgumentException("Option not valid: shortName is null and longName is null");
        }
        if (shortName != null) {
            options.put(shortName, option);
        }
        if (longName != null) {
            options.put(longName, option);
        }
    }

    public void addOption(String shortName, String longName, boolean hasArg, String description) {
        Option option = new Option(shortName, longName, hasArg, description);
        addOption(option);
    }

    public boolean hasOption(String name) {
        return options.containsKey(name);
    }

    public Option getOption(String name) {
        return options.get(name);
    }
}
