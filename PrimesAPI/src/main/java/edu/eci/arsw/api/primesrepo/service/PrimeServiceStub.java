package edu.eci.arsw.api.primesrepo.service;

import com.sun.org.apache.xpath.internal.functions.FuncUnparsedEntityURI;
import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@Service
public class PrimeServiceStub implements PrimeService
{
    List<FoundPrime> foundPrimeList;

    public PrimeServiceStub(){
        foundPrimeList = new CopyOnWriteArrayList<>();
        FoundPrime a1  = new FoundPrime();
        FoundPrime a2  = new FoundPrime();
        FoundPrime a3  = new FoundPrime();

        a1.setPrime("1"); a1.setUser("Carlos");
        a2.setPrime("2"); a2.setUser("Costa");
        a3.setPrime("3"); a3.setUser("Sebas");

        foundPrimeList.add(a1);foundPrimeList.add(a2);foundPrimeList.add(a3);

    }
    @Override
    public void addFoundPrime( FoundPrime foundPrime )
    {
        for(int i=0;i<foundPrimeList.size();i++){
            if(foundPrime.getPrime().equals(foundPrimeList.get(i).getPrime())){
                System.out.println("nop");
            }
        }
        foundPrimeList.add(foundPrime);
    }

    @Override
    public List<FoundPrime> getFoundPrimes()
    {
        return foundPrimeList;
    }

    @Override
    public FoundPrime getPrime( String prime )
    {
        for (FoundPrime fp : foundPrimeList){
            if (fp.getPrime().equals(prime)){
                return fp;
            }
        }
        return new FoundPrime();
    }
}
