package argparse;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Types {

    private static final Set<Class> supportedTypes;

    static {
        supportedTypes = new HashSet<>(Set.of(
            String.class,
            Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Double.class,
            Float.class,
            Boolean.class,
            List.class));
    }

    public static boolean supports(Class type) {
        return supportedTypes.contains(type);
    }

    public static <T> T parse(String arg, Class<T> type) throws ParseException {
        if (type.equals(String.class)) {
            return type.cast(arg);
        }
        else if (type.equals(Byte.class)) {
            return type.cast(Byte.valueOf(arg));
        }
        else if (type.equals(Short.class)) {
            return type.cast(Short.valueOf(arg));
        }
        else if (type.equals(Integer.class)) {
            return type.cast(Integer.valueOf(arg));
        }
        else if (type.equals(Long.class)) {
            return type.cast(Long.valueOf(arg));
        }
        else if (type.equals(Double.class)) {
            return type.cast(Double.valueOf(arg));
        }
        else if (type.equals(Float.class)) {
            return type.cast(Float.valueOf(arg));
        }
        else if (type.equals(Boolean.class)) {
            return type.cast(Boolean.valueOf(arg));
        }
        else {
            throw new ParseException(
                String.format("The type %s is not supported by the Type.parse method", type.getName()));
        }
    }

    public static <T> List<T> parseList(String arg, String delimiter, Class<T> type) throws ParseException {
        if (arg == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        try {
            String[] tokens = arg.split(delimiter);
            for (String token : tokens) {
                Method method = type.getMethod("valueOf", String.class);
                result.add(type.cast(method.invoke(null, token)));
            }
        } catch (Exception e) {
            throw new ParseException("Unable to parse list of type " + type.getName());
        }
        return result;
    }
}
