package com.luscious.foodweb.student;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/space/student")
public class SpaceController {
    @GetMapping
    public String sayHello(){
        return "The universe just created. I am Arceus";
    }

    @PostMapping
    public String sayBye(){
        return "This universe will be destroyed";
    }

}
