package com.prototypetodolist.heroku.entity;

public class ToDo {
	private int id;
	
	private String activity;
	
	private String status;

	public ToDo(int id, String activity, String status) {
		this.id = id;
		this.activity = activity;
		this.status = status;
	}
	
	public ToDo() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", activity=" + activity + ", status=" + status + "]";
	}
	
}
