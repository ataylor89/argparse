package argparse;

public class ArgumentParser {

    public ParseResult parse(Options options, String[] args) {
        ParseResult result = new ParseResult();
        int index = 0;
        boolean skipNext = false;
        while (index < args.length) {
            if (args[index].startsWith("-")) {
                String name = args[index].startsWith("--") ? args[index].substring(2) : args[index].substring(1);
                if (options.hasOption(name)) {
                    Option option = options.getOption(name);
                    if (option.hasArg()) {
                        if (index + 1 < args.length) {
                            String value = args[index + 1];
                            option.setValue(value);
                            skipNext = true;
                        }
                    }
                    else {
                        Boolean value = Boolean.TRUE;
                        option.setValue(value);
                    }
                    result.getOptions().put(name, option);
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
