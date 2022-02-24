package cn.itcast.user.web;

import cn.itcast.user.config.ConfigProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
//@RefreshScope  //用于nacos配置热更新@Value注入的变量
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //    @Value("${pattern.dateformat}")
//    private String nowDate;
    @Autowired
    private ConfigProperties configProperties;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        return userService.queryById(id);
    }

    @GetMapping("now")
    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(configProperties.dateFormat));
    }

    @GetMapping("test")
    public String test() {
        return configProperties.toString();
    }
}
