package io.github.h800572003.decorator;

import org.junit.jupiter.api.Test;

public class DecoratorTest {

    @Test
    void testMake(){
        Americano americano = new Americano();
        Latte latte = new Latte(americano);
        latte.make();
    }
}
