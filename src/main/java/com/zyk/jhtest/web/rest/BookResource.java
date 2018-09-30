package com.zyk.jhtest.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.zyk.jhtest.common.model.BaseDto;
import com.zyk.jhtest.service.BookService;
import com.zyk.jhtest.web.rest.errors.BadRequestAlertException;
import com.zyk.jhtest.web.rest.util.HeaderUtil;
import com.zyk.jhtest.service.dto.BookDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Book.
 */
@RestController
@RequestMapping("/api")
public class BookResource {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private static final String ENTITY_NAME = "book";

    private final BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    @Timed
    public BaseDto createBook(@RequestBody BookDTO bookDTO) throws URISyntaxException {
        bookDTO = bookService.save(bookDTO);
        return BaseDto.createBaseDTO(bookDTO);
    }


}
