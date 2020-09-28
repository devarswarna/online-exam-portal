package com.manipal.exception;

public class QBankAlreadyAttemptedException extends RuntimeException {
	public QBankAlreadyAttemptedException(String message) {
		super(message);
	}
}
