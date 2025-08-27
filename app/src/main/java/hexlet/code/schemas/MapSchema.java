package hexlet.code.schemas;
import java.util.Map;
import java.util.HashMap;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<String, BaseSchema<?>> shapeSchemas = new HashMap<>();
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

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        this.shapeSchemas.clear();
        this.shapeSchemas.putAll(schemas);
        addCheck("shape", value -> {
            if (value == null) {
                return !requiredFlag;
            }
            Map<?, ?> mapValue = (Map<?, ?>) value;
            for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
                String key = entry.getKey();
                BaseSchema<?> schema = entry.getValue();
                Object val = mapValue.get(key);

                @SuppressWarnings("unchecked")
                BaseSchema<Object> objSchema = (BaseSchema<Object>) schema;

                if (!objSchema.isValid(val)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
