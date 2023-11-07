package com.luscious.foodweb.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController1 {

    @GetMapping("/valid")
    public String getIndex1(
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) {



        if (!name.equals(""))
            return "mainpage/index1";

        return "mainpage/index2";

    }


}
