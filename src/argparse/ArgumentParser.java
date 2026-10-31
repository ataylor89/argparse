package argparse;

import java.util.ArrayList;
import java.util.List;

public class ArgumentParser {

    public ParseResult parse(Options options, String[] args) throws ParseException {
        List<String> positionalArgs = new ArrayList<>();
        int index = 0;
        boolean skipNext = false;
        while (index < args.length) {
            if (args[index].startsWith("-")) {
                String name = args[index].startsWith("--") ? args[index].substring(2) : args[index].substring(1);
                Option option = options.getOption(name);
                if (option.hasArg()) {
                    if (index + 1 < args.length) {
                        option.setValue(args[index + 1]);
                        skipNext = true;
                    }
                }
                else {
                    option.setValue("true");
                }
            }
            else {
                positionalArgs.add(args[index]);
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
