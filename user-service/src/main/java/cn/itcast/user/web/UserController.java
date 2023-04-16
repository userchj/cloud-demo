package cn.itcast.user.web;

import cn.itcast.user.config.Proper;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
//@RefreshScope
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @Autowired
    private Proper proper;

    @GetMapping("prop")
    public Proper Prop(){
        return proper;
    }
    @GetMapping("/now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(proper.getDateformat()));
    }
    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
                          @RequestHeader(value = "truth",required = false) String truth) {
        System.out.println("truth :"+truth);
        return userService.queryById(id);
    }
}
