package pl.brightinventions.book;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.brightinventions.book.entity.Book;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {}
