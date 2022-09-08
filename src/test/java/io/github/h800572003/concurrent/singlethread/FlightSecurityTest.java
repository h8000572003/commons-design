package io.github.h800572003.concurrent.singlethread;

import java.util.stream.IntStream;

public class FlightSecurityTest {


    public static void main(String[] args) {
        FlightSecurity flightSecurity = new FlightSecurity();


        IntStream.range(0, 10).mapToObj(i -> "Tom" + i).forEach(i -> {
            String name = "Andy" + i + "";
            new Passenger(name, name, name, flightSecurity).start();
        });


    }
}
