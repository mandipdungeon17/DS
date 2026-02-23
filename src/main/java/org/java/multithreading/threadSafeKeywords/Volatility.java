package org.java.multithreading.threadSafeKeywords;

/*
* Volatile keyword is used to indicate that a variable's value will be modified by different threads.
* In the below example, we have a flag variable which is set to true by one thread and read by another thread.
* The flag variable is declared as volatile. If we remove the volatile keyword, the getFlag method will never return.
* The reason is that the flag variable is cached by the thread, and it will never be updated by the other thread.
* If we declare the flag variable as volatile, the flag variable will not be cached by the thread,
* and it will be updated by the other threads.
* The volatile keyword is used to prevent the caching of the variable by the thread.
 */
public class Volatility {
    private volatile boolean flag = false;
//    private boolean flag = false;

    public void setFlagTrue(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Setting flag to true");
        this.flag = true;
    }

    public boolean getFlag(){
        System.out.println("Getting flag");
        while(!flag){
            // do nothing
        }
        return this.flag;
    }

    /*
    * If we declare the methods as synchronized, then the volatile keyword is not required.
    * Because the synchronized keyword will not allow the caching of the variable by the thread.
    * The synchronized keyword is used to prevent the caching of the variable by the thread.
    * The volatile keyword is used to prevent the caching of the variable by the thread.

    public class Volatility {
    private boolean flag = false;

    public synchronized void setFlagTrue(){
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Setting flag to true");
        this.flag = true;
    }

    public synchronized boolean getFlag(){
        System.out.println("Getting flag");
        while(!flag){
            // do nothing
        }
        return this.flag;
    }
     */

    public static void main(String[] args) throws InterruptedException {
        Volatility volatility = new Volatility();
        Thread thread = new Thread(volatility::setFlagTrue);
        Thread thread1 = new Thread(volatility::getFlag);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
    }
}
