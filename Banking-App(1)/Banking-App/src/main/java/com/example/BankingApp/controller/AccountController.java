package com.example.BankingApp.controller;

import com.example.BankingApp.dto.AccountDto;
import com.example.BankingApp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    //account rest api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
    //get Account REST API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto =accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }
    //deposite REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,
                                             @RequestBody Map<String, Double>request){

       AccountDto accountDto = accountService.deposit(id, request.get("amount"));
       return ResponseEntity.ok(accountDto);

    }
    //withdraw Rest Api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,
                                              @RequestBody Map<String, Double> request){
        double amount =request.get("amount");
        AccountDto accountDto = accountService.withdraw(id ,amount);
        return ResponseEntity.ok(accountDto);
    }
    //get all account Rest api
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
       List<AccountDto> accounts = accountService.getAllAccounts();
       return ResponseEntity.ok(accounts);
    }

    //delete  Account Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted successfully!");
    }
}
