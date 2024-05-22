package br.com.northon.demo.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import br.com.northon.demo.controller.BookController;
import br.com.northon.demo.data.vo.v1.BookVO;
import br.com.northon.demo.exception.RequiredObjectIsNullException;
import br.com.northon.demo.exceptions.ResourceNotFoundException;
import br.com.northon.demo.mapper.DozerMapper;
import br.com.northon.demo.model.Book;
import br.com.northon.demo.repositorys.BookRepository;

@Service
public class BookService {

	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private PagedResourcesAssembler<BookVO> assembler;
	
	public BookVO findById(Long id) {
		logger.info("finding one book by id");
		
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));
		
		BookVO vo = DozerMapper.parseObject(entity, BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		
		return vo;
	}
	
	public PagedModel<EntityModel<BookVO>> findAll(Pageable pageable) {
		logger.info("finding all books");
		
		var booksPage = repository.findAll(pageable);
		
		var booksVoPage = booksPage.map(b -> DozerMapper.parseObject(b, BookVO.class));
		
		booksVoPage.stream().forEach(
				b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));
		
		Link link = linkTo(methodOn(BookController.class).findAll(
						pageable.getPageNumber(), pageable.getPageSize(), "asc")).withSelfRel();
		
		return assembler.toModel(booksVoPage, link);
	}
	
	public BookVO create(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("creating a book");
		
		var entity = DozerMapper.parseObject(book, Book.class);
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).create(book)).withSelfRel());
		
		return vo;
	}
	
	public BookVO update(BookVO book) {
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("updating a book");
		
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!")); 
		
		entity.setAuthor(book.getAuthor());
		entity.setTitle(book.getTitle());
		entity.setPrice(book.getPrice());
		entity.setLaunchDate(book.getLaunchDate());
		
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		
		vo.add(linkTo(methodOn(BookController.class).update(book)).withSelfRel());
		
		return vo;
	}
	
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID!"));  
		
		logger.info("deleting a book");
		
		repository.delete(entity);
	}
}
