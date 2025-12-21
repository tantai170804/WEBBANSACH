package me.huu_thinh.main.model;

public class BookCategory {

	private int categoryId;
	private String name;
	private String description;
	public BookCategory(int category_id, String name, String description) {
		super();
		this.categoryId = category_id;
		this.name = name;
		this.description = description;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
}
