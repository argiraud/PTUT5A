package fr.polyrecrute.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lol")
@Tag(name = "Test2Controller", description = "the Test2Controller API")
public class Test2Controller {

    @RequestMapping(value = "")
    public String hello() {
        //userEntityDao.save(new UserEntity("login", "password", 0));
        return "lol";

    }
}