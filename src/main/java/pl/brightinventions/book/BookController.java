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
import pl.brightinventions.book.dto.InboundBookDto;
import pl.brightinventions.book.dto.InboundCommentDto;
import pl.brightinventions.book.mappers.BookMapper;
import pl.brightinventions.book.mappers.CommentsMapper;

import java.net.URI;

@RestController
@RequestMapping(BookController.BOOKS)
@RequiredArgsConstructor
class BookController {

  public static final String BOOKS = "/books";
  public static final String COMMENTS = "/comments";

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
  public ResponseEntity<BookDto> addBook(@RequestBody InboundBookDto bookDto) {
    BookDto book = bookMapper.mapToDto(bookService.addBook(bookMapper.mapToEntity(bookDto)));
    return ResponseEntity.created(URI.create(String.format("%s/%s", BOOKS, book.getId())))
        .body(book);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDto> updateBook(
      @PathVariable long id, @RequestBody InboundBookDto bookDto) {
    BookDto book = bookMapper.mapToDto(bookService.updateBook(id, bookMapper.mapToEntity(bookDto)));
    return ResponseEntity.ok().body(book);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> removeBook(@PathVariable long id) {
    bookService.removeBook(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/{id}" + COMMENTS)
  public ResponseEntity<CommentDto> addComment(
      @PathVariable long id, @RequestBody InboundCommentDto commentDto) {
    CommentDto comment =
        commentsMapper.mapToDto(bookService.addComment(id, commentsMapper.mapToEntity(commentDto)));
    return ResponseEntity.created(
            URI.create(String.format("%s/%s/%s/%s", BOOKS, id, COMMENTS, comment.getId())))
        .body(comment);
  }
}
