package project.utils.api;

import project.entity.UserEntity;

import java.util.List;

public interface ITransformer<Entity,Dto> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto Dto);
    List<Dto> listEntityToListDto(List<Entity> entities);
}
