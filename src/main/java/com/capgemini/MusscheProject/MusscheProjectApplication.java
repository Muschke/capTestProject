package com.capgemini.MusscheProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusscheProjectApplication {

	public static void main(String[] args) {

		//	SpringApplication.run(MusscheProjectApplication.class, args);

		String toTest = "Cabbefour007";

		System.out.println(matches(toTest));


	}

	public static Boolean matches(String input){
		return input.matches("^[A-Z]{1}[a-z]{8}\\d{0,3}$");
	}
}
