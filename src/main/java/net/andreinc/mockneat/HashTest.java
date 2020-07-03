package net.andreinc.mockneat;

import java.util.HashSet;
import java.util.Set;

class A {
    @Override
    public int hashCode() {
        return 0;
    }
}

public class HashTest {
    public static void main(String[] args) {
        Set<A> set = new HashSet<>();

        set.add(new A());
        set.add(new A());
        set.add(new A());

        System.out.println(set.size());
    }
}