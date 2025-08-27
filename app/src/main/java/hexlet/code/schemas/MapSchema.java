package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        requiredFlag = true;
        addCheck("required", value -> value != null && value instanceof Map);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> (!requiredFlag && isValueEmpty(value)) || (value != null && value.size() == size));
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", value -> {
            if (value == null) {
                return !requiredFlag;
            }
            for (Map.Entry<String, ? extends BaseSchema<?>> entry : schemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<Object> schema = (BaseSchema<Object>) entry.getValue();
                Object val = value.get(key);
                if (!schema.isValid(val)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
