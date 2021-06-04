package com.douzone.mysite.exception;

public class GuestBookRepositoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GuestBookRepositoryException() {

		super("guestbokRepository: 새엇앚");

	}

	public GuestBookRepositoryException(String message) {
		super(message);
	}

}
