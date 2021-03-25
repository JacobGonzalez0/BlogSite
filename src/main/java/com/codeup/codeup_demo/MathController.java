package com.codeup.codeup_demo;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping(path = "/add/{numOne}/and/{numTwo}")
    @ResponseBody
    public int add(@PathVariable int numOne, @PathVariable int numTwo){
        return numOne + numTwo;
    }

    @GetMapping(path = "/subtract/{numOne}/from/{numTwo}")
    @ResponseBody
    public int subtract(@PathVariable int numOne, @PathVariable int numTwo){
        return numOne - numTwo;
    }

    @GetMapping(path = "/multiply/{numOne}/and/{numTwo}")
    @ResponseBody
    public int multiply(@PathVariable int numOne, @PathVariable int numTwo){
        return numOne * numTwo;
    }

    @GetMapping(path = "/divide/{numOne}/by/{numTwo}")
    @ResponseBody
    public int divide(@PathVariable int numOne, @PathVariable int numTwo){
        return numOne / numTwo;
    }
    
}
