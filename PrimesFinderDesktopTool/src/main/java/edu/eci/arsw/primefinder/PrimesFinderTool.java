package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {
    public static Object monitor = new Object();
    public static boolean pausa = false;
    public static AtomicInteger activos = new AtomicInteger(4);

    public static void main(String[] args) {
        int maxPrim=1000;

        PrimesResultSet prs=new PrimesResultSet("john");
        //System.out.println("Prime numbers found:");
        //PrimeFinder.findPrimes(new BigInteger("1"), new BigInteger("100"), prs);
        PrimeFinderThreat thr1 =     new PrimeFinderThreat(new BigInteger("1"),new BigInteger("2"),prs);
        PrimeFinderThreat thr2 = new PrimeFinderThreat(new BigInteger("3"),new BigInteger("5"),prs);
        PrimeFinderThreat thr3 = new PrimeFinderThreat(new BigInteger("6"),new BigInteger("8"),prs);
        PrimeFinderThreat thr4 = new PrimeFinderThreat(new BigInteger("9"),new BigInteger("10"),prs);

        thr1.start();thr2.start();thr3.start();thr4.start();
        /*
        try {
            thr1.join();
            thr2.join();
            thr3.join();
            thr4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        while(activos.get()>0){
            try {
                //check every 10ms if the idle status (10 seconds without mouse
                //activity) was reached.
                Thread.sleep(10);
                if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                    System.out.println("Idle CPU ");
                    pausa = true;
                }
                else{
                    System.out.println("User working again!");
                    pausa = false;
                    synchronized (monitor){
                        monitor.notifyAll();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Prime numbers found:");

        System.out.println(prs.getPrimes());

        System.exit(0);
    }
}


