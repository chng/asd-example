import me.annotation.ObjectNotNull;
import org.junit.Test;

/**
 * Created by chn on 15/12/15.
 */
public class AnnotationTest {

    @Test
    public void test() {
        method(1);
        method(Integer.valueOf(1));
    }
    public void method(@ObjectNotNull int a) {
    }
    public void method(@ObjectNotNull Integer a) {

    }
}
