package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.model.Beach;

import java.util.Collection;

public interface BeachServicenterface {


    public abstract Beach addBeach(String name);

    Collection<Beach> getAll();

    Beach getById(String name);

    void deleteById(String name);
}
