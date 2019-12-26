package com.parul.ratelimiter;

/* Interface RateLimiter contains the methods required to implement
*  rate limiting modules.  Rate limiting module stops a particular requestor from making too many
*  http requests within a particular period of time.
*  Methods can be overriden to implement different algorithms of rate limiting module.
* */
public interface RateLimiter {
    boolean isRequestAllowed(User user);
}