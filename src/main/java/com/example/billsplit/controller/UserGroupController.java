package com.example.billsplit.controller;

import com.example.billsplit.request.input.userexpense.UserGroupInput;
import com.example.billsplit.request.output.decorated.usergroup.UserGroupDecoratedOutput;
import com.example.billsplit.request.output.decorated.usergroup.UserGroupSettlementDecoratedOutput;
import com.example.billsplit.service.usergroup.UserGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/group")
public class UserGroupController {

    private final UserGroupService userGroupService;

    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserGroupDecoratedOutput> postUserGroup(@RequestBody(required = true) UserGroupInput userGroupInput) {
        UserGroupDecoratedOutput userGroupDecoratedOutput = userGroupService.createUserGroup(userGroupInput);
        return ResponseEntity.status(HttpStatus.OK).body(userGroupDecoratedOutput);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{groupID}")
    ResponseEntity<UserGroupDecoratedOutput> getUserGroup(@PathVariable(name = "groupID", required = true) Long groupID) {
        UserGroupDecoratedOutput userGroupDecoratedOutput = userGroupService.getUserGroup(groupID);
        return ResponseEntity.status(HttpStatus.OK).body(userGroupDecoratedOutput);
    }

    @DeleteMapping
    @RequestMapping("/delete/{groupID}")
    ResponseEntity deleteUserGroup(@PathVariable(name = "groupID", required = true) Long groupID) {
        userGroupService.deleteUserGroup(groupID);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/payment/{groupID}")
    ResponseEntity<UserGroupSettlementDecoratedOutput> getUserGroupPaymentGraph(@PathVariable(name = "groupID", required = true)
                                                                                        Long groupID) {
        UserGroupSettlementDecoratedOutput userGroupSettlementDecoratedOutput =
                userGroupService.getUserGroupPaymentGraph(groupID);
        return ResponseEntity.status(HttpStatus.OK).body(userGroupSettlementDecoratedOutput);
    }

}
