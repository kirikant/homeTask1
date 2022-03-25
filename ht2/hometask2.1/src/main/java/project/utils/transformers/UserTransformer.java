package project.utils.transformers;

import project.dto.User;
import project.entity.UserEntity;
import project.utils.api.ITransformer;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer implements ITransformer<UserEntity,User> {
    private static UserTransformer transformer = new UserTransformer();

    private UserTransformer() {
    }

    public static UserTransformer getTransformer() {
        return transformer;
    }


    public User entityToDto(UserEntity userEntity) {
        String birthDay = "";
        if (userEntity.getBirthDay()!=null) birthDay=userEntity.getBirthDay().toString();
        return new User(userEntity.getId()
                ,userEntity.getLogin(),
                userEntity.getPassword(),
                userEntity.getName(),
                birthDay);
    }

    public UserEntity dtoToEntity(User user) {
        String birthDay = "";
        if (user.getBirthDay()!=null) birthDay=user.getBirthDay().toString();
        return new UserEntity(user.getLogin(),
                user.getPassword(),
                user.getName(),
                birthDay);
    }

    public List<User> listEntityToListDto(List<UserEntity> userEntities){
        ArrayList<User> users = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            users.add(entityToDto(userEntity));
        }
        return users;
    }
}
