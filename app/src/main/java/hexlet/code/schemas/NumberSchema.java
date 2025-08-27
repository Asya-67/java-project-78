package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {
    @Override
    protected boolean isValueEmpty(Integer value) {
        return value == null;
    }
    public NumberSchema required() {
        requiredFlag = true;
        addCheck("required", value -> value != null);
        return this;
    }
    public NumberSchema positive() {
        addCheck("positive", value ->
                (!requiredFlag && isValueEmpty(value)) || (value != null && value > 0)
        );
        return this;
    }
    public NumberSchema range(int min, int max) {
        addCheck("range", value ->
                (!requiredFlag && isValueEmpty(value))
                        || (value != null && value >= min && value <= max)
        );
        return this;
    }
}
