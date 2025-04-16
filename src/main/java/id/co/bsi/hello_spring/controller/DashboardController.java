package id.co.bsi.hello_spring.controller;

import id.co.bsi.hello_spring.dto.response.DashboardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;


@RestController
@RequestMapping("/api/users")
public class DashboardController {

    @GetMapping("/{accountnum}")
    public ResponseEntity<DashboardResponse> getProfile(
            @RequestHeader(value = "token", required = true) String token,
            @PathVariable String accountnum
    ) {
        DashboardResponse response = new DashboardResponse();

        // Cek Token
        if (!token.equals("ertyhgvbanscyfvbsnc")) {
            response.setStatus("fail");
            response.setMessage("Unauthorized access");

            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        response.setStatus("success");
        response.setMessage("Success");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
