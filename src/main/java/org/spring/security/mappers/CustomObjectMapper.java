package org.spring.security.mappers;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.spring.security.beans.User;
import org.spring.security.model.UserModel;

//@Mapper(componentModel = "spring",nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CustomObjectMapper {
    CustomObjectMapper customObjectMapper= Mappers.getMapper(CustomObjectMapper.class);


    @Mappings({
            @Mapping(source = "id",target = "id",qualifiedByName = "StringToObjectId"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "password",target = "password"),
            @Mapping(source = "blocked",target = "blocked"),
            @Mapping(source = "stdInClass",target = "stdInClass"),
            @Mapping(source = "roles",target = "roles"),
            @Mapping(source = "bookCollection",target = "bookCollection"),
    })
    User getUserFromUserModel(UserModel userModel);

   /* @Named("StringToObjectId")
    default ObjectId stringToObjectId(String id){
        return new ObjectId(id);
    }
*/
    @Mappings({
            @Mapping(source = "id",target = "id",qualifiedByName= "ObjectIdToString"),
            @Mapping(source = "name",target = "name"),
            @Mapping(source = "email",target = "email"),
            @Mapping(source = "password",target = "password"),
            @Mapping(source = "blocked",target = "blocked"),
            @Mapping(source = "stdInClass",target = "stdInClass"),
            @Mapping(source = "roles",target = "roles"),
            @Mapping(source = "bookCollection",target = "bookCollection"),
    })
    UserModel getUserModelFromUser(User user);

    /*@Named("ObjectIdToString")
    default String objectIdToString(ObjectId objectId){
        return objectId.toString();
    }

*/

}
