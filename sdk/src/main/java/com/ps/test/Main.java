package com.ps.test;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().print();
    }

    Lock lock = new Lock();

    public void print() throws InterruptedException {
        lock.lock();
        doAdd();
        lock.unlock();
    }

    public void doAdd() throws InterruptedException {
        lock.lock();
        //do something
        lock.unlock();
    }

}

class Lock {
    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }
}
