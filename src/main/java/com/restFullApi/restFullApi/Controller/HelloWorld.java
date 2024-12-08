package com.restFullApi.restFullApi.Controller;

import com.restFullApi.restFullApi.DAOService.UserService;
import com.restFullApi.restFullApi.DTO.HelloBeanResponse;
import com.restFullApi.restFullApi.ExceptionHandler.UserNotFoundException;
import com.restFullApi.restFullApi.Model.PostDetail;
import com.restFullApi.restFullApi.Model.Users;
import com.restFullApi.restFullApi.UserRepository.PostRepo;
import com.restFullApi.restFullApi.UserRepository.UserRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RestController
@RequestMapping("/baseUrl")
public class HelloWorld {
    private MessageSource messageSource;
    private UserRepo userRepo;
    private PostRepo postRepo;
    public HelloWorld(MessageSource messageSource,UserRepo userRepo,PostRepo postRepo){
        this.messageSource=messageSource;
        this.userRepo=userRepo;
        this.postRepo=postRepo;
    }

    @GetMapping(path="/hello-world")
    public String printHelloWorld(){
        return "Hello World";
    }

    @GetMapping(path="/hello-world-i18n")
    public String printHelloWorldi18n(){
        Locale locale= LocaleContextHolder.getLocale();
       return messageSource.getMessage("good.morning.messages",null,"Hello",locale);
    }


    @GetMapping(path="/hello-world-bean")
    public HelloBeanResponse printHelloBean(){
        return new HelloBeanResponse("Hello world");
    }
    // path variable
    @GetMapping(path="/hello/{name}")
    public HelloBeanResponse printPathVariable(@PathVariable String name){
        return new HelloBeanResponse("hello "+name);
    }
    @Autowired
    UserService userService;
    @GetMapping(path="/getAllUser")
    public List<Users> getAllUsers(){
        return userRepo.findAll();
    }

    /**
     * url= http://localhost:8080/user
     * EntityModel
     * WebMvcLinkBuilder
     * @param id
     * @return
     */
    @GetMapping(path="/user/{id}")
    public EntityModel<Users> getUsersById(@PathVariable Integer id){
        Users users=userRepo.findById(id).get();
        if(Objects.isNull(users)){
            throw new UserNotFoundException("id : "+ id);
        }
        EntityModel<Users> entityModel=EntityModel.of(users);
        WebMvcLinkBuilder webMvcLinkBuilder=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        entityModel.add(webMvcLinkBuilder.withRel("all_user"));
        return entityModel;
    }
    @PostMapping(path = "/user")
    public ResponseEntity<Users> createUser(@Validated  @RequestBody Users users){
        Users users1= userRepo.save(users);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(users1.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping(path="/user/{id}")
    public void deleteUserById(@PathVariable Integer id){
        userRepo.deleteById(id);
    }

    @PostMapping(path = "/user/{id}/post")
    public ResponseEntity<Objects> createPost(@PathVariable int id,@Valid @RequestBody PostDetail postDetail){
        Users users1= userRepo.findById(id).get();
        postDetail.setUsers(users1);
        PostDetail post=postRepo.save(postDetail);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
