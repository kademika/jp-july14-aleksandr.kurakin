package com.kademika.day12.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kurakinaleksandr on 25.09.14.
 */
public class IdGenerator {
    AtomicLong id = new AtomicLong(0);

    public long getNextId(){
        return  id.addAndGet(1);
    }
}
