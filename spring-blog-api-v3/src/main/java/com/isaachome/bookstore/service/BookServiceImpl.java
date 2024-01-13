package com.isaachome.bookstore.service;

import com.isaachome.bookstore.dto.BookDTO;
import com.isaachome.bookstore.entity.Book;
import com.isaachome.bookstore.repos.BookRepos;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepos postRepos;
    private final ModelMapper modelMapper;

    @Override
    public BookDTO getPostById(Long postId) {
        postRepos.findById(postId);
        return null;
    }

    @Override
    public BookDTO createBook(BookDTO dto) {
        Book savedBook = postRepos.save(convertToEntity(dto));
        return convertToDto(savedBook);
    }

    private Book convertToEntity(BookDTO dto){
         return modelMapper.map(dto, Book.class);
    }
    private BookDTO convertToDto(Book book){
        return  modelMapper.map(book, BookDTO.class);
    }
}