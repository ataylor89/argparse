package argparse;

public class ArgumentParser {

    public ParseResult parse(Options options, String[] args) {
        ParseResult result = new ParseResult();
        int index = 0;
        boolean skipNext = false;
        while (index < args.length) {
            if (args[index].startsWith("-")) {
                String name = args[index].startsWith("--") ? args[index].substring(2) : args[index].substring(1);
                String value = null;
                if (options.hasOption(name)) {
                    Option option = options.getOption(name);
                    if (option.hasArg()) {
                        if (index + 1 < args.length) {
                            value = args[index + 1];
                            skipNext = true;
                        }
                    }
                    else {
                        value = "true";
                    }
                    String delimiter = option.getDelimiter();
                    Class type = option.getType();
                    Types.validate(value, delimiter, type);
                    String longName = option.getLongName();
                    result.getOptions().put(longName, value);
                }
            }
            else {
                String positionalArg = args[index];
                result.getPositionalArgs().add(positionalArg);
            }
            index++;
            if (skipNext) {
                index++;
                skipNext = false;
            }
        }
        return result;
    }
}
