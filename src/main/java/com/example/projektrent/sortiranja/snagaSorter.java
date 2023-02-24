package com.example.projektrent.sortiranja;
import java.util.Comparator;

import com.example.projektrent.entiteti.Hatchback;
import com.example.projektrent.entiteti.Vozilo;

class SnagaVozilaComparator implements Comparator<Vozilo<?>> {
    @Override
    public int compare(Vozilo<?> v1, Vozilo<?> v2) {
        return Integer.compare(v1.getSnaga(), v2.getSnaga());
    }
}

