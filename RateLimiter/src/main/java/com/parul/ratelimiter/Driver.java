package com.parul.ratelimiter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Driver {
    static RateLimiter rateLimiter = new RateLimiterImpl(2, new CustomTimeUnit(TimeUnit.SECONDS, 5));
    static List<User> users = new ArrayList<User>();
    public static void main(String[] args) {
         for(int i = 0; i < 5; ++i) {
         User user = new User(i);
            users.add(user);
         }
        callFunction(users.get(0));
        callFunction(users.get(0));
        callFunction(users.get(0));
        callFunction(users.get(1));
        callFunction(users.get(1));
        callFunction(users.get(1));
        try {
            Thread.sleep(5000);
            callFunction(users.get(0));
            callFunction(users.get(1));
            callFunction(users.get(0));
            callFunction(users.get(1));
            callFunction(users.get(0));
            callFunction(users.get(1));
        } catch (InterruptedException e ) {

        }
        System.out.println("BYE BYE");


    }

    public static void callFunction(User user){
        System.out.println(new Date());
        try {
            (rateLimiter).isRequestAllowed(user);
            System.out.println("Successfully requested");
        } catch(RateLimiterException e) {
            System.out.println(e);
        }

    }
}
