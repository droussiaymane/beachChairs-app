package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import at.ac.fhsalzburg.swd.spring.model.Beach;

import java.util.Collection;

public interface ChairManagementServiceInterface {


    public abstract BeachChair addChair(Beach beach, Integer korbId, String status);

    boolean deleteChair(Integer korbId);

    BeachChair getByChairId(Integer korbId);

    Collection<BeachChair> getAll();
}
