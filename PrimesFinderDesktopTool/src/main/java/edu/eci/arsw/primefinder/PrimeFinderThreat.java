package edu.eci.arsw.primefinder;

import edu.eci.arsw.math.MathUtilities;

import java.math.BigInteger;
import java.util.Collection;

public class PrimeFinderThreat extends Thread{
    private BigInteger a,b;
    private PrimesResultSet prs;
    public PrimeFinderThreat(BigInteger a,BigInteger b,PrimesResultSet prs){
        super();
        this.a=a;
        this.b=b;
        this.prs=prs;
        System.out.println(a + " "+b);
    }

    public void run() {
        PrimeFinder.findPrimes(a,b,prs);
    }


}
