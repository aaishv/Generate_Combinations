package com.finra.finratest.controller;

import com.finra.finratest.helper.BuildCombinations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "api")
public class CombinationGeneratorController {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/{number}", produces = "application/json")
    public ResponseEntity<Object> getAllCombinations(@PathVariable String number)throws Exception{
        ResponseEntity<Object> res=(number.length()==7 || number.length()==10)?new BuildCombinations().possibleCombination(number):ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid!");
          return res;
    }
}