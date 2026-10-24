package argparse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseResult {
    
    private List<String> positionalArgs;
    private Map<String, Option> options;

    public ParseResult() {
        this.positionalArgs = new ArrayList<>();
        this.options = new HashMap<>();
    }

    public void setPositionalArgs(List<String> positionalArgs) {
        this.positionalArgs = positionalArgs;
    }

    public List<String> getPositionalArgs() {
        return positionalArgs;
    }

    public void setOptions(Map<String, Option> options) {
        this.options = options;        
    }

    public Map<String, Option> getOptions() {
        return options;
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
        int counter = 0;
        int size = options.size();
        for (Map.Entry<String, Option> entry : options.entrySet()) {
            String name = entry.getKey();
            Option option = entry.getValue();
            Object value = option.getValue();
            builder.append(name).append(": ").append(value);
            if (counter + 1 < size) {
                builder.append("\n");
            }
            counter++;
        };
        return builder.toString();
    }
}
