package org.spring.security.mappers;/*
package org.spring.security.mappers;

import org.bson.types.ObjectId;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.spring.security.beans.Book;
import org.spring.security.beans.Chapter;
import org.spring.security.beans.User;
import org.spring.security.model.BookModel;
import org.spring.security.model.ChapterModel;
import org.spring.security.model.UserModel;

@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface BookMapper {
    BookMapper bookMapper =Mappers.getMapper(BookMapper.class);
    @Mappings({
            @Mapping(source = "id",target = "id",qualifiedByName = "StringToObjectId1"),
    })
    Book getBookFromBookModel(BookModel bookModel);

    @Mappings({
            @Mapping(source = "id",target = "id",qualifiedByName = "ObjectIdToString1"),
    })
    BookModel getBookModelFromBook(Book book);

    @Mappings({
            @Mapping(source = "id",target = "id",qualifiedByName = "StringToObjectId1"),
    })
    Chapter getChapterFromChaperModel(ChapterModel chapterModel);

    @Mappings({
            @Mapping(source = "id",target = "id",qualifiedByName = "ObjectIdToString1"),
    })
    ChapterModel getChapterModelFromChapter(Chapter chapter);

    @Named("ObjectIdToString1")
    default String objectIdToString(ObjectId objectId){
        return objectId.toString();
    }

    @Named("StringToObjectId1")
    default ObjectId stringToObjectId(String id){
        return new ObjectId(id);
    }

}
*/
