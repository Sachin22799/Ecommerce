package com.ecommerce.ui.utils;

public enum SecurityQuestion {
	  QUESTION_1("What is  your best friend name?"),
	  QUESTION_2("What is your favorite color?"),
	  QUESTION_3("What is your favorite food?"),
	  QUESTION_4("what is your city name?"),
	  QUESTION_5("What is your favorite movie?"),
	  QUESTION_6("What is your first school name");

	  private final String question;

	  SecurityQuestion(String question) {
	    this.question = question;
	  }
     public String toString() {
     	return this. question;
	
}
	}
