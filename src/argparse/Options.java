package argparse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Options {

    private Map<String, Option> options;

    public Options() {
        options = new HashMap<>();
    }

    public void setOptions(Map<String, Option> options) {
        this.options = options;
    }

    public Map<String, Option> getOptions() {
        return options;
    }

    public void addOption(Option option) throws ParseException {
        validate(option);
        String shortName = option.getShortName();
        String longName = option.getLongName();
        options.put(shortName, option);
        options.put(longName, option);
    }

    private void validate(Option option) throws ParseException {
        String shortName = option.getShortName();
        String longName = option.getLongName();
        if (shortName == null || longName == null || shortName.isBlank() || longName.isBlank()) {
            throw new ParseException("Option shortName and longName are required");
        }
        Class type = option.getType();
        if (type != null && !Types.supports(type)) {
            throw new ParseException(type.getName() + " is not a supported option type");
        }
        Class subType = option.getSubType();
        if (subType != null && !Types.supports(subType)) {
            throw new ParseException(subType.getName() + " is not a supported option type");
        }
    }

    public void addOption(String shortName, String longName, boolean hasArg, String delimiter, Class type, Class subType, String description) throws ParseException {
        addOption(new Option(shortName, longName, hasArg, delimiter, type, subType, description));
    }

    public void addOption(String shortName, String longName, boolean hasArg, Class type, String description) throws ParseException {
        addOption(new Option(shortName, longName, hasArg, type, description));
    }

    public void addOption(String shortName, String longName, boolean hasArg, String description) throws ParseException {
        addOption(new Option(shortName, longName, hasArg, description));
    }

    public boolean hasOption(String name) {
        return options.containsKey(name);
    }

    public Option getOption(String name) {
        return options.get(name);
    }

    public int getCount() {
        return (int) options.values().stream().distinct().count();
    }

    public List<Option> getDistinct() {
        return options.values().stream().distinct().toList();
    }
}
