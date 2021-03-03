package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {
    @Autowired
    private TimeEntryRepository timeEntryRepository;
    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository=timeEntryRepository;
    }
    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate){
        TimeEntry entry = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(entry, HttpStatus.CREATED);
    }
    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId) {
        TimeEntry entry = timeEntryRepository.find(timeEntryId);
        HttpStatus status;
        if(entry==null){
            status=HttpStatus.NOT_FOUND;
        }else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<TimeEntry>(entry, status);
    }
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> entryList = timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(entryList, HttpStatus.OK);
    }
    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,@RequestBody TimeEntry timeEntryToUpdate){
        TimeEntry entry = timeEntryRepository.update(timeEntryId,timeEntryToUpdate);
        HttpStatus status;
        if(entry==null){
            status=HttpStatus.NOT_FOUND;
        }else {
            status = HttpStatus.OK;
        }
        return new ResponseEntity<TimeEntry>(entry, status);
    }
    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<Void> delete(@PathVariable long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
