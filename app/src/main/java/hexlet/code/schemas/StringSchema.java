package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        requiredFlag = true;
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value ->
                (!requiredFlag && isValueEmpty(value)) || (value != null && value.length() >= length)
        );
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value ->
                (!requiredFlag && isValueEmpty(value)) || (value != null && value.contains(substring))
        );
        return this;
    }
}
