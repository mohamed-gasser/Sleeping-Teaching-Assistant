
package ta;
import java.util.concurrent.Semaphore;
import java.util.concurrent.Semaphore;

public class TeachingAssistant implements Runnable {
     // Semaphore used to wakeup TA.
    private TeachingAssistant_wakeup wakeup;

    // Semaphore used to wait in chairs outside office.
    private Semaphore chairs;

    // Mutex lock (binary semaphore) used to determine if TA is available.
    private Semaphore available;

    // A reference to the current thread.
    private Thread t;
    
    private int numberofchairs;
    
    public TeachingAssistant(TeachingAssistant_wakeup w, Semaphore c,int chair)
    {
        t = Thread.currentThread();
        wakeup = w;
        chairs = c;
        this.numberofchairs = chair ; 
    }
    
    @Override
    public void run()       
    {
        long idThread=Integer.parseInt(Thread.currentThread().getName());
        while (true)
        {
            try
            {
                System.out.println("No students left.  The TA "+idThread+"is going to nap.");
                wakeup.release();
                System.out.println("The TA "+idThread+"was awoke by a student.");
                
                t.sleep(5000);
                
                // If there are other students waiting.
                if (chairs.availablePermits() != numberofchairs)
                {
                    do
                    {
                        chairs.release();
                        t.sleep(5000);
                      
                    }
                    while (chairs.availablePermits() != numberofchairs);                   
                }
                //System.exit(0);
            }
            catch (InterruptedException e)
            {
                // Something bad happened.
                continue;
            }
            boolean flag=false;
            for (int i = 0; i <Student.finish.length; i++) {
                if(Student.finish[i]!=true)
                    flag=true;    
            }
            if (!flag)
                System.exit(0);

                
        }
    }
}
