package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    @Override
    protected boolean isValueEmpty(String value) {
        return value == null || value.isEmpty();
    }

    public StringSchema required() {
        requiredFlag = true;
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }
    public StringSchema minLength(int length) {
        addCheck("minLength", value ->
                (!requiredFlag && (value == null || value.isEmpty()))
                        || (value != null && value.length() >= length)
        );
        return this;
    }
    public StringSchema contains(String substring) {
        addCheck("contains", value ->
                (!requiredFlag && (value == null || value.isEmpty()))
                        || (value != null && value.contains(substring))
        );
        return this;
    }
}
