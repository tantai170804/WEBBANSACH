package me.huu_thinh.main.service;

import java.util.ArrayList;
import java.util.List;

import me.huu_thinh.main.dao.BookDAO;
import me.huu_thinh.main.dto.BookViewDTO;
import me.huu_thinh.main.model.Book;

public class BookService {
	
	private BookDAO bookDAO;
	
	public BookService() {
		this.bookDAO = new BookDAO();
	}
	
	public static class Result {
		private final boolean success;
		private final List<String> errors;

		public Result(boolean success, List<String> errors) {
			this.success = success;
			this.errors = errors;
		}

		public boolean isSuccess() {
			return success;
		}

		public List<String> getErrors() {
			return errors;
		}
	}

	public Book findById(int id) {
		if (id <= 0)
			return null;
		return BookDAO.findById(id);
	}

	public Result update(Book b) {
		List<String> errors = validate(b, true);
		if (!errors.isEmpty())
			return new Result(false, errors);

		boolean ok = BookDAO.update(b);
		if (!ok) {
			errors.add("Cập nhật thất bại!");
			return new Result(false, errors);
		}
		return new Result(true, errors);
	}

	private List<String> validate(Book b, boolean isUpdate) {
		List<String> errors = new ArrayList<>();
		if (b == null) {
			errors.add("Dữ liệu không hợp lệ.");
			return errors;
		}

		if (isUpdate && b.getBookId() <= 0)
			errors.add("ID sách không hợp lệ.");

		if (b.getBookCode() == null || b.getBookCode().trim().isEmpty())
			errors.add("Mã sách không được để trống.");

		if (b.getTitle() == null || b.getTitle().trim().isEmpty())
			errors.add("Tên sách không được để trống.");

		if (b.getPrice() < 0)
			errors.add("Giá không hợp lệ.");
		if (b.getQuantityInStock() < 0)
			errors.add("Số lượng không hợp lệ.");

		return errors;
	}

	public int countAllForAdmin() {
		return BookDAO.countAllForAdmin();
	}

	public List<BookViewDTO> getPageForAdmin(int page, int size) {
		int safePage = Math.max(page, 1);

		int safeSize = size;
		if (safeSize < 1)
			safeSize = 5;
		if (safeSize > 50)
			safeSize = 50;

		int offset = (safePage - 1) * safeSize;
		return BookDAO.getPageForAdmin(offset, safeSize);
	}

	public boolean delete(int id) {
	    if (id <= 0) return false;
	    return BookDAO.delete(id);
	}


	public int calcTotalPages(int totalItems, int size) {
		int safeSize = size;
		if (safeSize < 1)
			safeSize = 5;
		if (safeSize > 50)
			safeSize = 50;

		int pages = (int) Math.ceil(totalItems * 1.0 / safeSize);
		return Math.max(pages, 1);
	}

	public int clampPage(int page, int totalPages) {
		if (page < 1)
			return 1;
		if (page > totalPages)
			return totalPages;
		return page;
	}

	public int normalizeSize(int size) {
		if (size < 1)
			return 5;
		if (size > 50)
			return 50;
		return size;
	}

	public static class CreateResult {
		private final boolean success;
		private final List<String> errors;

		public CreateResult(boolean success, List<String> errors) {
			this.success = success;
			this.errors = errors;
		}

		public boolean isSuccess() {
			return success;
		}

		public List<String> getErrors() {
			return errors;
		}
	}

	public CreateResult create(Book b) {
		List<String> errors = validateBook(b);
		if (!errors.isEmpty()) {
			return new CreateResult(false, errors);
		}

		boolean ok = BookDAO.insert(b);
		if (!ok) {
			errors.add("Tạo sách thất bại!");
			return new CreateResult(false, errors);
		}
		return new CreateResult(true, errors);
	}

	private List<String> validateBook(Book b) {
		List<String> errors = new ArrayList<>();
		if (b == null) {
			errors.add("Dữ liệu không hợp lệ.");
			return errors;
		}
		if (b.getBookCode() == null || b.getBookCode().trim().isEmpty()) {
			errors.add("Mã sách không được để trống.");
		}
		if (b.getTitle() == null || b.getTitle().trim().isEmpty()) {
			errors.add("Tên sách không được để trống.");
		}
		if (b.getPrice() < 0) {
			errors.add("Giá không hợp lệ.");
		}
		if (b.getQuantityInStock() < 0) {
			errors.add("Số lượng không hợp lệ.");
		}
		return errors;
	}

	public List<Book> getAllBook() {
		return bookDAO.getAll();
	}
}
