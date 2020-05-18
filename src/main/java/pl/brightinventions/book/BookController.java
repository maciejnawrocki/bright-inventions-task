package pl.brightinventions.book;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.brightinventions.book.dto.BookDto;
import pl.brightinventions.book.dto.CommentDto;
import pl.brightinventions.book.entity.Book;
import pl.brightinventions.book.mappers.BookMapper;
import pl.brightinventions.book.mappers.CommentsMapper;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
class BookController {

  private final BookMapper bookMapper;
  private final CommentsMapper commentsMapper;
  private final BookService bookService;

  @GetMapping
  public ResponseEntity<Page<BookDto>> getBooks(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "50") int size) {
    Page<BookDto> responsePage = bookService.getAllBooks(page, size).map(bookMapper::mapToDto);
    return ResponseEntity.ok().body(responsePage);
  }

  @PostMapping
  public ResponseEntity<Book> addBook(@RequestBody BookDto bookDto) {
    Book book = bookService.addBook(bookMapper.mapToEntity(bookDto));
    return ResponseEntity.ok().body(book);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody BookDto bookDto) {
    Book book = bookService.updateBook(id, bookMapper.mapToEntity(bookDto));
    return ResponseEntity.ok().body(book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> removeBook(@PathVariable long id) {
    bookService.removeBook(id);
    return ResponseEntity.ok().body("Item successfully removed");
  }

  @PostMapping("/{id}/comments")
  public ResponseEntity<CommentDto> addComment(
      @PathVariable long id, @RequestBody CommentDto commentDto) {
    CommentDto comment =
        commentsMapper.mapToDto(bookService.addComment(id, commentsMapper.mapToEntity(commentDto)));
    return ResponseEntity.ok().body(comment);
  }
}
