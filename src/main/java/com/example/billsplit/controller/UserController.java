package com.example.billsplit.controller;

import com.example.billsplit.request.output.decorated.user.UserDecoratedOutput;
import com.example.billsplit.service.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{id}")
    ResponseEntity<UserDecoratedOutput> getUser(@PathVariable(name = "id", required = true) Long id) {
        UserDecoratedOutput userDecoratedOutput = userService.getUserForID(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDecoratedOutput);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDecoratedOutput> postUser(@RequestParam(name = "name", required = true) String name,
                                                 @RequestParam(name = "groupID", required = true) Long groupID) {
        UserDecoratedOutput userDecoratedOutput = userService.postUser(name, groupID);
        return ResponseEntity.status(HttpStatus.OK).body(userDecoratedOutput);
    }

    @DeleteMapping()
    @RequestMapping("/delete/{id}")
    ResponseEntity deleteUser(@PathVariable(name = "id", required = true) Long id) {
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
