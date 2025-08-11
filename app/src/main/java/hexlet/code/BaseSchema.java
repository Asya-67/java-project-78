package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean requiredFlag = false;

    protected final void addCheck(String name, Predicate<T> predicate) {
        checks.put(name, predicate);
    }

    public final boolean isValid(T value) {
        if (!requiredFlag && isValueEmpty(value)) {
            return true;
        }
        return isValidObject(value);
    }

    protected final boolean isValidObject(T value) {
        return checks.values().stream().allMatch(p -> p.test(value));
    }

    protected abstract boolean isValueEmpty(T value);
}
