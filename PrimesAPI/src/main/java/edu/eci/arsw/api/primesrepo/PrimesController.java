package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
    @Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public ResponseEntity<?> getPrimes()
    {
        List<FoundPrime> data = null;
        try{
            data = primeService.getFoundPrimes();
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping( method = POST )
    public ResponseEntity<?> postPrimes(@RequestBody FoundPrime fp)
    {
        try{
            primeService.addFoundPrime(fp);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping( value = "/primes/{primenumber}", method = GET )
    public ResponseEntity<?> getPrimesByPrime(@PathVariable("primenumber") String primerNumber)
    {
        FoundPrime data;
        try{
            data = primeService.getPrime(primerNumber);
            return new ResponseEntity<>(data, HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<>("ERROR 500",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //TODO implement additional methods provided by PrimeService



}
