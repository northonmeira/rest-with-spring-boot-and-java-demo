package br.com.northon.demo.unittests.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.northon.demo.data.vo.v1.BookVO;
import br.com.northon.demo.model.Book;

public class MockBook {
	
	public Book mockEntity() {
		return this.mockEntity(0);
	}
	
	public BookVO mockVo() {
		return this.mockVO(0);
	}
	
	public List<Book> mockEntityList() {
		List<Book> books = new ArrayList<Book>();
		for (int i = 0; i < 14; i++) {
			books.add(this.mockEntity(i));
		}
		return books;
	}
	
	public List<BookVO> mockVOList() {
		List<BookVO> books = new ArrayList<BookVO>();
		for (int i = 0; i < 14; i++) {
			books.add(this.mockVO(i));
		}
		return books;
	}
	
	public Book mockEntity(Integer number) {
		return new Book(number.longValue(), "author" + number, new Date(), number.doubleValue(), "Title"+number);
	}
	
	public BookVO mockVO(Integer number) {
		return new BookVO(number.longValue(), "author" + number, new Date(), number.doubleValue(), "Title"+number);
	}

}
