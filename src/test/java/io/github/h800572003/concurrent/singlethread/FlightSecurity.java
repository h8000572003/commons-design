package io.github.h800572003.concurrent.singlethread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class FlightSecurity {

    private int count = 0;

    private String boardingPass;
    private String idCard;

    public synchronized void  pass(Passenger passenger) {
        try {
            count++;
            this.idCard = passenger.getIdCard();
            TimeUnit.SECONDS.sleep(1);
            this.boardingPass=passenger.getBoardingPass();
            this.check();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            count--;
            log.info("count:{} passenger:{}", count, passenger);

        }

    }

    private void check() {
        if (!StringUtils.equals(boardingPass,idCard)) {
            throw new RuntimeException("no same boardingPass:"+boardingPass+",idCard:"+idCard);
        }
    }

}
