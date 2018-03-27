import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://github.com/junit-team/junit5-samples/tree/r5.1.0/junit5-gradle-consumer
 *
 */

@DisplayName("JunitTest")
//@Disabled
class JunitTest {

    @Test
    @DisplayName("test1")
    void test1() {
        assertEquals(new Sample().getNum(1), 1);
//        assertEquals(new Sample().getNum(1), 2);
    }
}
