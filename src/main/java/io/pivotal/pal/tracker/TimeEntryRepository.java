package io.pivotal.pal.tracker;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TimeEntryRepository {
    TimeEntry create(TimeEntry timeEntry);

    TimeEntry find(long id);

    List<TimeEntry> list();

    ResponseEntity<TimeEntry> delete(long id);

    TimeEntry update(long id, TimeEntry timeEntry);
}
