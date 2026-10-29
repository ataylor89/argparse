package argparse;

import java.util.HashMap;
import java.util.Map;

public class Options {

    private Map<String, Option> options;

    public Options() {
        options = new HashMap<>();
    }

    public void addOption(Option option) {
        validate(option);
        String shortName = option.getShortName();
        String longName = option.getLongName();
        options.put(shortName, option);
        options.put(longName, option);
    }

    public void addOption(String shortName, String longName, boolean hasArg, Class type, String description) {
        Option option = new Option(shortName, longName, hasArg, type, description);
        addOption(option);
    }

    public void addOption(String shortName, String longName, boolean hasArg, String description) {
        addOption(shortName, longName, hasArg, String.class, description);
    }

    public boolean hasOption(String name) {
        return options.containsKey(name);
    }

    public Option getOption(String name) {
        return options.get(name);
    }

    private void validate(Option option) {
        String shortName = option.getShortName();
        String longName = option.getLongName();
        if (shortName == null || longName == null || shortName.isBlank() || longName.isBlank()) {
            throw new IllegalArgumentException("shortName and longName are required");
        }
    }
}
