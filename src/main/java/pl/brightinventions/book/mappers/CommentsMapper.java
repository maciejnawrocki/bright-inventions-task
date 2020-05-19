package pl.brightinventions.book.mappers;

import org.mapstruct.Mapper;
import pl.brightinventions.book.dto.CommentDto;
import pl.brightinventions.book.dto.InboundCommentDto;
import pl.brightinventions.book.entity.Comment;

@Mapper(componentModel = "spring")
public interface CommentsMapper {
  Comment mapToEntity(InboundCommentDto commentDto);

  CommentDto mapToDto(Comment comment);
}
