package com.parul.ratelimiter;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/* This is the implementation of RateLimiter.
*  It stores the requests for each user in a queue and maintains a map userRequestsMap.
*  For each user there will be a separate queue.
*  Whenever a request is made and isAllowedRequest() function will be called.
*  It will determine whether the request is allowed or not. Also it will trigger removing
*  expired requests from the queue.
* */
public class RateLimiterImpl implements RateLimiter {

    private int requests;
    private CustomTimeUnit unit;
    private Map<Integer, Queue<Date>> userRequestsMap = new HashMap<Integer, Queue<Date>>();
    private static final Logger logger = Logger.getLogger(RateLimiterImpl.class.getName());

    public RateLimiterImpl(int requests, CustomTimeUnit unit) {
        this.requests = requests;
        this.unit = unit;
    }

    public static RateLimiter create(int requests, CustomTimeUnit unit) {
        return (new RateLimiterImpl(requests, unit));
    }

    /* This method takes the user object and creates a queue for user if it does not exist.
    *  New queue for each user will be added to map.
    *  If queue is already there, then expired entries from the queue is removed.
    *  After that if queue is empty or has size less than requests , latest request time will be entered to the queue.
    *  If queue is full then request will not be fulfilled.
    * * */
    public boolean isRequestAllowed(User user) throws RateLimiterException{
        Date currentTime =  new Date();
        Integer userId = user.getUserId();
        Queue<Date> requestQueue = userRequestsMap.get(userId);
        if(requestQueue == null) {
            requestQueue = new LinkedList<Date>();
            userRequestsMap.put(userId, requestQueue);
        }  else {
            removeObsoleteRequests(requestQueue, currentTime);
        }
        if(requestQueue.size() < requests) {
            requestQueue.add(currentTime);
            logger.log(Level.INFO,"Request for the user " + userId + " has been added to queue");
            return true;
        } else {
            logger.log(Level.WARNING,"Rate limit has reached");
            throw new RateLimiterException("Maximum limit to request has been reached");
        }
    }

    /**************PRIVATE METHODS*************/

    /*This method will remove the expired requests from the queue*/
    private void removeObsoleteRequests(Queue<Date> requestQueue, Date time) {
        if(requestQueue.isEmpty())
            return;
        long currentTimeInMillis = time.getTime();
        while(!requestQueue.isEmpty()) {
            Date firstRequest = requestQueue.peek();
            long timeInMillis = firstRequest.getTime();
            if (currentTimeInMillis - timeInMillis < unit.getRequestRateInMillis()) {
                break;
            }
            requestQueue.poll();
        }
    }
}
