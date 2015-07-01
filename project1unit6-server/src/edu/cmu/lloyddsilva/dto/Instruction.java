package edu.cmu.lloyddsilva.dto;

import java.io.Serializable;

public class Instruction implements Serializable {
	
	private static final long serialVersionUID = 3089679778626846261L;
	
	private InstructionType command;
	private Object request;
	private String comment;
	private Object response;
	
	public Instruction() {
		super();
	}
	
	public Instruction(InstructionType command) {
		super();
		this.command = command;
	}

	public Instruction(InstructionType command, Object request, String comment, Object response) {
		super();
		this.command = command;
		this.request = request;
		this.comment = comment;
		this.response = response;
	}

	public InstructionType getCommand() {
		return command;
	}

	public void setInstruction(InstructionType command) {
		this.command = command;
	}

	public Object getRequest() {
		return request;
	}

	public void setRequest(Object request) {
		this.request = request;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RequestData [command=").append(command)
				.append(", request=").append(request).append(", comment=")
				.append(comment).append(", response=").append(response)
				.append("]");
		return builder.toString();
	}
	
	
}
