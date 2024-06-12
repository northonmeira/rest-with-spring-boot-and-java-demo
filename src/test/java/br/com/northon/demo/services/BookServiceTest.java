package br.com.northon.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.northon.demo.data.vo.v1.BookVO;
import br.com.northon.demo.exception.RequiredObjectIsNullException;
import br.com.northon.demo.model.Book;
import br.com.northon.demo.repositorys.BookRepository;
import br.com.northon.demo.unittests.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
class BookServiceTest {
	
	MockBook input;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	BookRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
	
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("[</api/book/v1/1>;rel=\"self\"]"));
		assertEquals("author1", result.getAuthor());
		assertEquals("Title1", result.getTitle());
		assertEquals(1D, result.getPrice());
	}

//	@Test
//	void testFindAll() {
//		List<Book> list = input.mockEntityList();
//		
//		when(repository.findAll()).thenReturn(list);
//		
//		var books = service.findAll();
//		
//		assertNotNull(books);
//		assertEquals(14, books.size());
//	}

	@Test
	void testCreate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		
		BookVO vo = input.mockVO(1);
		
		vo.setKey(1L);
		
		when(repository.save(entity)).thenReturn(persisted);
	
		var result = service.create(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("[</api/book/v1>;rel=\"self\"]"));
		assertEquals("author1", result.getAuthor());
		assertEquals("Title1", result.getTitle());
		assertEquals(1D, result.getPrice());
	}

	@Test
	void testUpdate() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		Book persisted = entity;
		
		BookVO vo = input.mockVO(1);
		
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(persisted));
	
		when(repository.save(entity)).thenReturn(persisted);
	
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("[</api/book/v1>;rel=\"self\"]"));
		assertEquals("author1", result.getAuthor());
		assertEquals("Title1", result.getTitle());
		assertEquals(1D, result.getPrice());
	}

	@Test
	void testDelete() {
		Book entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		service.delete(1L);
	}
	
	@Test
	void testUpdateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		
		String actualMessage = exception.getMessage();
		
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testCreateWithNullBook() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It is not allowed to persist a null object!";
		
		String actualMessage = exception.getMessage();
		
		
		assertTrue(actualMessage.contains(expectedMessage));
	}

}
