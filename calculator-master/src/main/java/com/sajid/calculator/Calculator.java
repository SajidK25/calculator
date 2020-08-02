package com.sajid.calculator;
import org.springframework.stereotype.Service;

@Service
public class Calculator {
     int sum(int a, int b) {
          return a + b;
     }
     
     int subtract(int a, int b) {
          return a - b;
     }
}