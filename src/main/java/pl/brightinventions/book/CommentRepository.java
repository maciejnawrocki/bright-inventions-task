package pl.brightinventions.book;

import org.springframework.data.repository.CrudRepository;
import pl.brightinventions.book.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {}
