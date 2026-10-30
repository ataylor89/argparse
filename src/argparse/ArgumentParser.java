package argparse;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {

    public ParseResult parse(Options options, String[] args) {
        List<String> positionalArgs = new ArrayList<>();
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
                    option.setValue(value);
                }
            }
            else {
                String positionalArg = args[index];
                positionalArgs.add(positionalArg);
            }
            index++;
            if (skipNext) {
                index++;
                skipNext = false;
            }
        }
        return new ParseResult(positionalArgs, options);
    }
}
