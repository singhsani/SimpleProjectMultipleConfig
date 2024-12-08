package com.restFullApi.restFullApi.DAOService;

import com.restFullApi.restFullApi.Model.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserService {
    private static List<Users> userList=new ArrayList<>();
    private static int userCount=0;
    static{
        userList.add(new Users("Ram",++userCount, LocalDate.now().minusYears(30)));
        userList.add(new Users("Pawan",++userCount, LocalDate.now().minusYears(10)));
        userList.add(new Users("Shyam",++userCount, LocalDate.now().minusYears(20)));
        userList.add(new Users("Ramesh",++userCount, LocalDate.now().minusYears(15)));
    }
    public List<Users> findAll(){
        return userList;
    }
    public Users finById(Integer id){
        return userList.stream().filter(x-> Objects.equals(x.getId(), id)).findFirst().orElse(null);
    }
    public Users save(Users users){
        users.setId(++userCount);
        userList.add(users);
        return users;
    }

    public void deleteByUserId(Integer id) {
        Predicate<? super Users> predicate=users -> users.getId().equals(id);
        userList.removeIf(predicate);
    }
}
