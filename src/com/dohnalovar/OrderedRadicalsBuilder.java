package com.dohnalovar;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by dohnalovar on 6/24/2019
 */
public class OrderedRadicalsBuilder {

    private List<Radical> radicals;
    private List<Integer> primes = new ArrayList<>();

    public int getPrime(int index) {
        return primes.get(index);
    }

    public Radical getRadical(int index) {
        return radicals.get(index-1);
    }

    private static OrderedRadicalsBuilder ourInstance = new OrderedRadicalsBuilder();

    public static OrderedRadicalsBuilder getInstance() {
        return ourInstance;
    }

    private OrderedRadicalsBuilder() {
        //generate primes
        BigInteger i = BigInteger.valueOf(1);
        while ( (i = i.nextProbablePrime()).compareTo(BigInteger.valueOf(100000)) <= 0 ) {
            primes.add(i.intValue());
        }

        SortedSet<Radical> radicalSortedSet = new TreeSet<>();

        radicalSortedSet.add(new Radical(1,1));
        for (int j = 2; j <= 100000; j++) {
            //add primes immediatelly, their radical factor is the same
            if (primes.contains(j)) {
                radicalSortedSet.add(new Radical(j, j));
                continue;
            }
            radicalSortedSet.add(new Radical(j, rad(j)));
        }


        radicals = new ArrayList<>(radicalSortedSet);
    }

    private int rad(int number) {
        int result = 1;
        int i = 0;
        while (number > 1 && i < primes.size()) {
            int prime = primes.get(i);
            boolean primeNotCounted = true;
            while (number % prime == 0) {
                number = number / prime;
                if (primeNotCounted) {
                    result *= prime;
                    primeNotCounted = false;
                }
            }
            i++;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Radicals {\n" + radicals + "\n}";
    }
}
