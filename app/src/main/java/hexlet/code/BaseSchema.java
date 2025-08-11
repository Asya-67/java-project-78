package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean requiredFlag = false;

    protected void addCheck(String name, Predicate<T> predicate) {
        checks.put(name, predicate);
    }

    public final boolean isValid(T value) {
        if (!requiredFlag && isValueEmpty(value)) {
            return true;
        }
        return checks.values().stream().allMatch(p -> p.test(value));
    }
    public boolean isValidObject(Object value) {
        try {
            @SuppressWarnings("unchecked")
            T castValue = (T) value;
            return isValid(castValue);
        } catch (ClassCastException e) {
            return false;
        }
    }
    protected abstract boolean isValueEmpty(T value);
}
