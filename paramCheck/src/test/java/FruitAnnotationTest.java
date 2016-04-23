import me.annotation.FruitColor;
import me.annotation.FruitName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by chn on 15/12/15.
 */
public class FruitAnnotationTest {

    class Apple {
        @FruitName
        public String name;

        @FruitColor
        public String color;

    }

    @Test
    public void test2() {
        Apple apple = new Apple();
        assertEquals("--", apple.name);
        assertEquals("GREEN", apple.color);
    }
}
