package argparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ParseResult {

    private List<String> positionalArgs;
    private Options options;

    public ParseResult(List<String> positionalArgs, Options options) {
        this.positionalArgs = positionalArgs;
        this.options = options;
    }

    public void setPositionalArgs(List<String> positionalArgs) {
        this.positionalArgs = positionalArgs;
    }

    public List<String> getPositionalArgs() {
        return positionalArgs;
    }

    public void setOptions(Options options) {
        this.options = options;        
    }

    public Options getOptions() {
        return options;
    }

    public String getPositionalArg(int index) {
        if (index < positionalArgs.size()) {
            return positionalArgs.get(index);
        }
        return null;
    }

    public Option getOption(String longName) {
        if (!options.hasOption(longName)) {
            throw new NoSuchElementException("Unknown option: " + longName);
        }
        return options.getOption(longName);
    }

    public String getOptionValue(String longName) {
        Option option = getOption(longName);
        return option.getValue();
    }

    @SuppressWarnings("unchecked")
    public <T> T getParsedOptionValue(String longName) {
        Option option = getOption(longName);
        String value = option.getValue();
        Class type = option.getType();
        if (type.equals(List.class)) {
            String delimiter = option.getDelimiter();
            Class subType = option.getSubType();
            return (T) Types.parseList(value, delimiter, subType);
        }
        else {
            return (T) Types.parse(value, type);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Positional args\n");
        for (int i = 0; i < positionalArgs.size(); i++) {
            String arg = positionalArgs.get(i);
            builder.append("Arg ").append(i).append(": ").append(arg);
            if (i + 1 < positionalArgs.size()) {
                builder.append("\n");
            }
        }
        builder.append("\n\n").append("Options\n");
        List<Option> optionList = options.getDistinct();
        int size = optionList.size();
        for (int i = 0; i < size; i++) {
            String name = optionList.get(i).getLongName();
            String value = optionList.get(i).getValue();
            builder.append(name).append(": ").append(value);
            if (i + 1 < size) {
                builder.append("\n");
            }
        };
        return builder.toString();
    }
}
