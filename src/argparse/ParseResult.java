package argparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Option getOption(String name) throws ParseException {
        return options.getOption(name);
    }

    public String getOptionValue(String name, String defaultValue) throws ParseException {
        Option option = getOption(name);
        String value = option.getValue();
        return value == null ? defaultValue : value;
    }

    public String getOptionValue(String name) throws ParseException {
        return getOptionValue(name, null);
    }

    /*
     * This is a generic method, which I learned about by reading the Java tutorials on generics.
     *
     * As far as I know, there are two use cases for generics:
     *
     * (1) Generic types (generic classes and generic interfaces, which have one or more type parameters)
     * (2) Generic methods (which have one or more type parameters)
     *
     * The rule of thumb is that, to use a generic type or generic method, you pass it one or more type arguments.
     *
     * When you use a generic class or a generic interface, you pass it a type argument as soon as you declare
     * a variable of that type. For example, in the statement "List<Integer> primes", you pass the generic List class
     * a type argument as soon as you declare a variable of that type.
     *
     * When you use a generic method, you often pass it a type argument when you invoke the method.
     * For example, when the generic method Types.parse is called, we pass it a type argument. Same with Types.parseList.
     *
     * But sometimes, the type argument gets passed further on downstream.
     * For example, the method ParseResult#getParsedOptionValue(String), which accepts one argument, a String,
     * does not accept a type argument. Instead, it passes a type argument to Types.parse or Types.parseList.
     *
     * It took me a long time to wrap my head around the subject :)
     * I think I was able to finally understand it - to a degree - by reading the Java tutorials on generics,
     * and by writing my own generic types or generic methods to practice what I learned.
     */
    @SuppressWarnings("unchecked")
    public <T> T getParsedOptionValue(String name, T defaultValue) throws ParseException {
        Option option = getOption(name);
        String value = option.getValue();
        Class type = option.getType();
        if (value == null) {
            return defaultValue;
        }
        else if (type.equals(List.class)) {
            String delimiter = option.getDelimiter();
            Class subType = option.getSubType();
            return (T) Types.parseList(value, delimiter, subType);
        }
        else {
            return (T) Types.parse(value, type);
        }
    }

    public <T> T getParsedOptionValue(String name) throws ParseException {
        return getParsedOptionValue(name, null);
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
