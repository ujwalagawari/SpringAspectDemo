package com.cg.app.model;
import org.springframework.stereotype.Service;

@Service
public class Calculator {

	public Integer add(int firstNum, int secondNum){
		return firstNum + secondNum;
	}

}
