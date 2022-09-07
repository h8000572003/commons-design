package io.github.h800572003.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 拿鐵
 */
@Slf4j
public class Latte implements ICoffee{
    private Americano americano;

    public Latte(Americano americano) {
        this.americano = americano;
    }

    @Override
    public void make() {
        log.info("加入牛奶");
        this.americano.make();
    }
}
