package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {
    @Test
    void testBasic() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(10)).isTrue();

        schema.required();
        assertThat(schema.isValid(null)).isFalse();
    }
    @Test
    void testRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number()
                .required()
                .positive()
                .range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }
}
