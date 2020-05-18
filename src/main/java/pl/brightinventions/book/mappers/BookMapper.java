package pl.brightinventions.book.mappers;

import org.mapstruct.Mapper;
import pl.brightinventions.book.dto.BookDto;
import pl.brightinventions.book.entity.Book;

@Mapper(
    componentModel = "spring",
    uses = {CommentsMapper.class})
public interface BookMapper {

  Book mapToEntity(BookDto bookDto);

  BookDto mapToDto(Book book);
}
