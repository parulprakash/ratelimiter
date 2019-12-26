package com.parul.ratelimiter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Driver {
    static RateLimiter rateLimiter = new RateLimiterImpl(3, new CustomTimeUnit(TimeUnit.SECONDS, 10));
    static List<User> users = new ArrayList<User>();
    public static void main(String[] args) {


        for(int i = 0; i < 5; ++i) {

            User user = new User(i);
            users.add(user);

        }
        System.out.println(callFunction());
        System.out.println(callFunction());
        System.out.println(callFunction());
        System.out.println(callFunction());
        System.out.println(callFunction());
        System.out.println(callFunction());
        System.out.println(callFunction());
        System.out.println(callFunction());

        try {
            Thread.sleep(5000);
            System.out.println(callFunction());
            System.out.println(callFunction());
            Thread.sleep(5000);
            System.out.println(callFunction());
            System.out.println(callFunction());
            System.out.println(callFunction());
        } catch (InterruptedException e ) {

        }
        System.out.println("BYE BYE");


    }

    public static boolean callFunction(){

        System.out.println(new Date());
        return ((RateLimiterImpl)rateLimiter).isRequestAllowed(users.get(0));

    }
}
