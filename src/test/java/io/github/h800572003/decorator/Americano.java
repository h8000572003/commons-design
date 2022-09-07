package io.github.h800572003.decorator;

import lombok.extern.slf4j.Slf4j;

/**
 * 美式咖啡
 */
@Slf4j
public class Americano implements ICoffee{


    @Override
    public void make() {
        log.info("加入黑咖啡");
        log.info("加水");
    }
}
