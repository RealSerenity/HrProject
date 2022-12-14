package com.rserenity.hrproject.ui.rest;

import com.rserenity.hrproject.business.dto.OffDayRequestDto;
import com.rserenity.hrproject.business.services.OffDayRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "http://localhost:8080")
public class OffDayRequestController {

    @Autowired
    OffDayRequestServices offDayRequestServices;

    @GetMapping("/getAll")
    public List<OffDayRequestDto> getAll() {
        return offDayRequestServices.getRequests();
    }

    @PostMapping("/createRequest/user/{id}/{date}")
    public OffDayRequestDto createRequest(@PathVariable(value = "id") Long userId, @PathVariable(value = "date") String dateString) throws Throwable {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateString, dtf);
        // for localization
//        dtf = dtf.withLocale(Locale.ROOT);
//        LocalDate date = LocalDate.parse(dateString, dtf);
        return offDayRequestServices.createRequest(userId, date);
    }

    @DeleteMapping("/deleteRequest/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteRequest(@PathVariable Long id) throws Throwable {
        return offDayRequestServices.deleteRequest(id);
    }

    @GetMapping("/getRequests/user/{id}")
    public List<OffDayRequestDto> getRequestsById(@PathVariable Long id) throws Throwable {
//        return offDayRequestServices.getRequestsOfUserId(id);
        return offDayRequestServices.findAllofIdOrderByDateDesc(id);
    }

    @GetMapping("/getRequest/{id}")
    public OffDayRequestDto getRequestById(@PathVariable(value = "id") Long requestId) throws Throwable {
        return offDayRequestServices.getRequestById(requestId).getBody();
    }

    @PutMapping("/approveRequest/{id}")
    public OffDayRequestDto approveRequest(@PathVariable(value = "id") Long requestId) throws Throwable {
        return offDayRequestServices.approveRequest(requestId).getBody();
    }

    @PutMapping("/denyRequest/{id}")
    public OffDayRequestDto denyRequest(@PathVariable(value = "id") Long requestId) throws Throwable {
        return offDayRequestServices.denyRequest(requestId).getBody();
    }

}
