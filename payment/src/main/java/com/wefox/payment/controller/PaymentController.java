package com.wefox.payment.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Payment operations")
public class PaymentController {

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/start")
    @ApiOperation(value = "Reset payment application", notes = "(via GET)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful reset"),
            @ApiResponse(code = 500, message = "Server error")})
    public ResponseEntity<String> start() {
        //generate payment messages
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
