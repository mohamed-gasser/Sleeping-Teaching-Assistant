
package ta;

import java.util.concurrent.Semaphore;


public class TeachingAssistant_wakeup implements wakeup_interface{
     private boolean flag;
    // Used to send the signal.
    public synchronized void working_with_TeachingAssistant() {
        this.flag = true;
        this.notify();
    }

    // Will wait until it receives a signal before continuing.
    public synchronized void release() throws InterruptedException{
        while(!this.flag) wait();
        this.flag = false;
    }

    
}