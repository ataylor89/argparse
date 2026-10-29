package argparse;

import java.lang.reflect.Method;
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

    public static boolean is(String arg, Class type) {
        if (!supports(type)) {
            throw new IllegalArgumentException(type.getName() + " not supported");
        }
        else if (arg == null) {
            return true;
        }
        else if (type.equals(String.class)) {
            return true;
        }
        else if (type.equals(Byte.class)) {
            try {
                Byte.parseByte(arg);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (type.equals(Short.class)) {
            try {
                Short.parseShort(arg);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (type.equals(Integer.class)) {
            try {
                Integer.parseInt(arg);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (type.equals(Long.class)) {
            try {
                Long.parseLong(arg);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (type.equals(Double.class)) {
            try {
                Double.parseDouble(arg);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (type.equals(Float.class)) {
            try {
                Float.parseFloat(arg);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        else if (type.equals(Boolean.class)) {
            return arg.equalsIgnoreCase("true") || arg.equalsIgnoreCase("false");
        }
        else if (type.equals(List.class)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void validate(String arg, Class type) {
        if (!is(arg, type)) {
            throw new IllegalArgumentException("Argument is not of type " + type.getName());
        }
    }

    public static <T> T parse(String arg, T defaultValue, String delimiter, Class<T> type) {
        validate(arg, type);

        if (arg == null) {
            return defaultValue;
        }
        else if (type.equals(String.class)) {
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
        else if (type.equals(List.class)) {
            return type.cast(List.of(arg.split(delimiter)));
        }
        else {
            return type.cast(arg);
        }
    }

    public static <T> T parse(String arg, T defaultValue, Class<T> type) {
        return parse(arg, defaultValue, null, type);
    }

    public static <T> T parse(String arg, Class<T> type) {
        return parse(arg, null, null, type);
    }

    public static <T> List<T> parseList(String arg, List<T> defaultValue, String delimiter, Class<T> type) {
        validate(arg, List.class);

        if (arg == null) {
            return defaultValue;
        }

        return Arrays.stream(arg.split(delimiter)).map(token -> {
            try {
                Method method = type.getMethod("valueOf", String.class);
                return type.cast(method.invoke(null, token));
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to parse list of type " + type.getName());
            }
        }).toList();
    }

    public static <T> List<T> parseList(String arg, String delimiter, Class<T> type) {
        return parseList(arg, null, delimiter, type);
    }
}
