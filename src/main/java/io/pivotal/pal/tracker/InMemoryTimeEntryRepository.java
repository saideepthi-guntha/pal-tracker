package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository {


    public ResponseEntity responseEntity;
    HashMap<Long,TimeEntry> timeEntryData = new HashMap<Long,TimeEntry>();




    @Override
    public TimeEntry create(TimeEntry timeEntry){

        long id = timeEntryData.size() + 1;
        timeEntry.setId(id);
        timeEntryData.put(id, timeEntry);
        return timeEntryData.get(id);

    }

    @Override
    public TimeEntry find(long id){
        return timeEntryData.get(id);
    }

    @Override
    public List<TimeEntry> list(){
        return new ArrayList<>(timeEntryData.values());
    }

    @Override
    public ResponseEntity<TimeEntry> delete(long id){

        timeEntryData.remove(id);
        return responseEntity;
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry){
        timeEntry.setId(id);
        timeEntryData.put(id,timeEntry);
        return timeEntryData.get(id);
    }

}
