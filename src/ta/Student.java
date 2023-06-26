
package ta;
import java.util.concurrent.Semaphore;
import java.lang.*;
import java.util.concurrent.Semaphore;


public class Student  implements Runnable{
   public static boolean finish[];
   
   private int programTime;
   
   public static int NoChair;

   public TeachingAssistant_wakeup wakeup;


   private Semaphore chairs;


   private Semaphore available;


   private Thread t;


   public Student(int program, TeachingAssistant_wakeup w, Semaphore c, Semaphore a)
    {
        programTime = program;    
        wakeup = w;
        chairs = c;
        available = a;
        t = Thread.currentThread();
    }


    @Override
    public void run()
    {
        int idThread=Integer.parseInt(Thread.currentThread().getName());

        while(!finish[idThread-1])
        {
            try
            {

                System.out.println("Student " + idThread + " has started programming for " + programTime + " seconds.");
               t.sleep(programTime * 1000);
                

               System.out.println("Student " + idThread  + " is checking to see if TA is available.");
               if (available.tryAcquire())
               {
                   try
                   {
                       // Wakeup the TA.
                       wakeup.working_with_TeachingAssistant();
                       System.out.println("Student " + idThread  + " has woke up the TA.");
                       System.out.println("Student " + idThread  + " has started working with the TA.");
                       t.sleep(4950);
                       System.out.println("Student " + idThread  + " has stopped working with the TA.");
                   }
                   catch (InterruptedException e)
                   {
                       // Something bad happened.
                       continue;
                   }
                   finally
                   {
                       available.release();
                       finish[idThread-1]=true;
                   }
               }
               else
               {
                   // Check to see if any chairs are available.
                   System.out.println("Student " + idThread  + " could not see the TA.  Checking for available chairs.");
                   if (chairs.tryAcquire())
                   {
                       try
                       {
                           // Wait for TA to finish with other student.
                           System.out.println("Student " + idThread  + " is sitting outside the office.  "
                                   + "He is #" + ((Student.NoChair - chairs.availablePermits())) + " in line.");
                           
                           available.acquire();
                          
                           System.out.println("Student " + idThread  + " has started working with the TA.");
                           t.sleep(5000);
                           System.out.println("Student " + idThread  + " has stopped working with the TA.");
                           available.release();
                           finish[idThread-1]=true;
                       }
                       catch (InterruptedException e)
                       {
                           // Something bad happened.
                           continue;
                       }
                   }
                   else
                   {
                       System.out.println("Student " + idThread  + " could not see the TA and all chairs were taken.  Back to programming!");
                       finish[idThread-1]=false;
                   }
               }
            }
            catch (InterruptedException e)
            {
                break;
            }
        }
        
    }
    
    
}
