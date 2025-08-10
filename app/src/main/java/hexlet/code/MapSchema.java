package hexlet.code.schemas;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    @Override
    protected boolean isValueEmpty(Map<?, ?> value) {
        return value == null;
    }
    public MapSchema required() {
        requiredFlag = true;
        addCheck("required", value -> value != null && value instanceof Map);
        return this;
    }
    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> {
            if (value == null) {
                return !requiredFlag;
            }
            return value.size() == size;
        });
        return this;
    }
}
