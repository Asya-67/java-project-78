package hexlet.code.schemas;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {
    private final Map<String, Predicate<String>> checks = new LinkedHashMap<>();

    private boolean requiredFlag = false;

    public StringSchema required() {
        requiredFlag = true;
        checks.put("required", value -> value != null && !value.isEmpty());
        return this;
    }
    public StringSchema minLength(int length) {
        checks.put("minLength", value ->
                (!requiredFlag && (value == null || value.isEmpty()))
                        || (value != null && value.length() >= length)
        );
        return this;
    }
    public StringSchema contains(String substring) {
        checks.put("contains", value ->
                (!requiredFlag && (value == null || value.isEmpty()))
                        || (value != null && value.contains(substring))
        );
        return this;
    }

    public boolean isValid(String value) {
        if (!requiredFlag && (value == null || value.isEmpty())) {
            return true;
        }
        return checks.values().stream().allMatch(p -> p.test(value));
    }
}
