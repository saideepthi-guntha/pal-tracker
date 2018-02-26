package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {


    private final CounterService counter;
    private final GaugeService gauge;
    private TimeEntryRepository timeEntryRepository;

    private ResponseEntity responseEntity;

    public TimeEntryController(CounterService counter, GaugeService gauge, TimeEntryRepository timeEntryRepository) {
        this.counter = counter;
        this.gauge = gauge;
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){

        TimeEntry timeEntryCreate = timeEntryRepository.create(timeEntry);
        counter.increment("TimeEntry.created");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity<>(timeEntryCreate,HttpStatus.CREATED);
}

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry !=null){
            counter.increment("TimeEntry.read");
            return new ResponseEntity<>(timeEntry,HttpStatus.OK);
        }

       else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable long id,@RequestBody TimeEntry timeEntry){
        TimeEntry updatedEntry = timeEntryRepository.update(id, timeEntry);
        if (updatedEntry!=null){
            counter.increment("TimeEntry.updated");
            return new ResponseEntity<>(updatedEntry,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        counter.increment("TimeEntry.deleted");
        gauge.submit("timeEntries.count", timeEntryRepository.list().size());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        counter.increment("TimeEntry.listed");
        return new ResponseEntity<>(timeEntryRepository.list(),HttpStatus.OK);
    }

}

