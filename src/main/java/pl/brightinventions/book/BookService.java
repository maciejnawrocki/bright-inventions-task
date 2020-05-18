package pl.brightinventions.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.brightinventions.book.entity.Book;
import pl.brightinventions.book.entity.Comment;
import pl.brightinventions.book.exception.BookServiceException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final CommentRepository commentRepository;

  Page<Book> getAllBooks(int page, int size) {
    return bookRepository.findAll(PageRequest.of(page, size));
  }

  Book addBook(Book book) {
    return bookRepository.save(book);
  }

  Book updateBook(Long id, Book book) {
    if(bookRepository.findById(id).isEmpty()){
      throw new BookServiceException("Book with id " + id + " does not exist");
    }
    book.setId(id);
    return bookRepository.save(book);
  }

  void removeBook(long id) {
    bookRepository.deleteById(id);
  }

  Comment addComment(long id, Comment comment) {
    Optional<Book> byId = bookRepository.findById(id);
    Book book =
        byId.orElseThrow(
            () -> new BookServiceException("Book with id " + id + " does not exist"));
    comment.setBook(book);
    book.getComments().add(comment);
    bookRepository.save(book);
    return commentRepository.save(comment);
  }
}
