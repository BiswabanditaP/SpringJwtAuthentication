package com.biswa.JWTAuthentication.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;


import com.biswa.JWTAuthentication.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class StatusMessages {

	   private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	
	   private Optional<User> data;


	   private StatusMessages() {
	       timestamp = LocalDateTime.now();
	   }
	   public StatusMessages(HttpStatus status,Optional<User> user ,String message) {
	       this();
	       this.status = status;
	       this.data=user;
	       this.message=message;
	   }


	    public StatusMessages(HttpStatus status, String message) {
	       this();
	       this.status = status;
	       this.message = message;
	      
	   }
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public Optional<User> getData() {
		return data;
	}
	public void setData(Optional<User> data) {
		this.data = data;
	}
	

	@Override
	public String toString() {
		return "StatusMessages [status=" + status + ", timestamp=" + timestamp + ", message=" + message + ", data=" + data + "]";
	}
	
	
	   
}
