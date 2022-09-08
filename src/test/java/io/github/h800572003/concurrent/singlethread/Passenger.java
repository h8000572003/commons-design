package io.github.h800572003.concurrent.singlethread;

import lombok.ToString;

@ToString
public class Passenger extends Thread {
    private String boardingPass;
    private String idCard;


    private FlightSecurity flightSecurity;

    public Passenger(String name, String boardingPass, String idCard, FlightSecurity flightSecurity) {
        super(name);
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.flightSecurity = flightSecurity;
    }

    @Override
    public void run() {
        flightSecurity.pass(this);
    }


    public String getBoardingPass() {
        return boardingPass;
    }

    public String getIdCard() {
        return idCard;
    }
}
