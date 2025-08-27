package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {
    @Test
    void testBehaviorWithoutRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("abc")).isTrue();
    }

    @Test
    void testNullEmptyValid() {
        Validator v = new Validator();
        StringSchema schema = v.string().required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("abc")).isTrue();
    }

    @Test
    void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().minLength(5);

        assertThat(schema.isValid("abcd")).isFalse();
        assertThat(schema.isValid("abcde")).isTrue();
        assertThat(schema.isValid("abcdef")).isTrue();
    }

    @Test
    void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string().required().contains("abc");

        assertThat(schema.isValid("abcdef")).isTrue();
        assertThat(schema.isValid("abcc def")).isTrue();
        assertThat(schema.isValid("ABCC")).isFalse();
    }

    @Test
    void testChecks() {
        Validator v = new Validator();
        StringSchema schema = v.string()
                .required()
                .minLength(5)
                .contains("abc");

        assertThat(schema.isValid("what does the abc means")).isTrue();
        assertThat(schema.isValid("abc")).isFalse();
        assertThat(schema.isValid("what does the def means")).isFalse();
    }

    @Test
    void testMultipleMinLengthOverridesPrevious() {
        Validator v = new Validator();
        StringSchema schema = v.string()
                .minLength(10)
                .minLength(4);

        assertThat(schema.isValid("Hex")).isFalse();
        assertThat(schema.isValid("Hexlet")).isTrue();
    }

    @Test
    void testMultipleContainsOverridesPrevious() {
        Validator v = new Validator();
        StringSchema schema = v.string()
                .contains("abc")
                .contains("cat");

        assertThat(schema.isValid("the abc is here")).isFalse();
        assertThat(schema.isValid("black cat")).isTrue();
    }

    @Test
    void testNullEmptyAfterContains() {
        Validator v = new Validator();
        StringSchema schema = v.string().contains("abc");

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
    }
}
