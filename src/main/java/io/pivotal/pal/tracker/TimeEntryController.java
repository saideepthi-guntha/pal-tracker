package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    private ResponseEntity responseEntity;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){

        TimeEntry timeEntryCreate = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(timeEntryCreate,HttpStatus.CREATED);
}

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        TimeEntry timeEntry = timeEntryRepository.find(id);
        if(timeEntry !=null){
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
            return new ResponseEntity<>(updatedEntry,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id){
        timeEntryRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<>(timeEntryRepository.list(),HttpStatus.OK);
    }

}

