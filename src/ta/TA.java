
package ta;

import java.util.concurrent.Semaphore;
import java.util.Random;
import java.util.*;
        
public class TA {      
  
    public static void main(String[] args) throws InterruptedException {
        
        GUI g=new GUI();
        Thread tg=new Thread(g);
        tg.start();
     

            int numberofStudents=g.NOS ;
            int numberofchairs=g.NOC ;
            int numberofavailable=g.NOTA ;
          
         Student.NoChair=numberofchairs;
         Student.finish=new boolean[numberofStudents];
        // Create semaphores.
        TeachingAssistant_wakeup wakeup = new TeachingAssistant_wakeup();
        Semaphore chairs = new Semaphore(numberofchairs);
        Semaphore available = new Semaphore(numberofavailable);
        
        
        // Used for randomly generating program time.
       Random studentWait = new Random();
        
        // Create student threads and start them.
        for (int i = 1; i <=numberofStudents; i++)
        {
            String ti=String.valueOf(i);
            Thread student = new Thread(new Student(studentWait.nextInt(20), wakeup, chairs, available),ti);
            student.start();
        }
        
        for (int i =1 ; i <= numberofavailable; i++)
        {
            String ti=String.valueOf(i);
            Thread ta = new Thread(new TeachingAssistant(wakeup, chairs, numberofchairs),ti);
            ta.start();
        }
        
        
    }
    
}


