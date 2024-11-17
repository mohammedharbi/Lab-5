package com.example.eventsystem;

import com.example.eventsystem.ApiResopnse.ApiResponse;
import com.example.eventsystem.Model.EventSystem;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event-system")
public class EventSystemController {

    ArrayList<EventSystem> events = new ArrayList<>();
    //• Create a new event (ID , description , capacity, startDate , endDate)
    @PostMapping("/add")
    public ApiResponse addEvent(@RequestBody EventSystem event) {
        events.add(event);
        return new ApiResponse("add successful");
    }
    //• Display all event .
    @GetMapping("/get")
    public ArrayList getEvents() {
        return events;
    }
    //• Update a event
    @PutMapping("/update/{index}")
    public ApiResponse updateEvent(@PathVariable int index, @RequestBody EventSystem event) {
        events.set(index, event);
        return new ApiResponse("update successful");
    }
    //• Delete a event
    @DeleteMapping("/Delete/{index}")
    public ApiResponse deleteEvent(@PathVariable int index) {
        events.remove(index);
        return new ApiResponse("delete successful");
    }
    //• Change capacity
    @PutMapping("/update-capacity/{index}/{capacity}")
    public ApiResponse updateEventCapacity(@PathVariable int index, @PathVariable int capacity) {
        events.get(index).setCapacity(capacity);
        return new ApiResponse("capacity updated successful");
    }
    //• Search for a event by given id
    //Hint ( use @JsonFormat(pattern="yyyy-MM-dd") and LocalDateTime )
    @GetMapping("/get-event-id/{ID}")
    public ArrayList getEventById(@PathVariable int ID) {
        ArrayList<EventSystem> tempevent = new ArrayList<>();

        for (EventSystem event : events) {
            if (event.getID() == ID) {tempevent.add(event);}
        }
        return tempevent;
    }
}
