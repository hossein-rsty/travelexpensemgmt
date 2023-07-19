package com.travelexpensemgmt.expenseservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@RestController
@Slf4j
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService)
    {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getExpenses() {

        try {
            final List<Expense> expenses = expenseService.getExpenses();
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping
    public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense) {
        try {
            return new ResponseEntity<>(expenseService.saveExpense(expense), HttpStatus.CREATED);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable("id") String id) {
        try {
            final Expense expense = expenseService.getExpenseById(id);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        } catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Methode delete a Expense by Id
    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<Expense> deleteExpensekbyId(@PathVariable("expenseId") String expenseId) {
        try {
            Expense expense = expenseService.deleteExpenseById(expenseId);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Method update the expenses attributes
    @PutMapping
    public ResponseEntity<Expense> updateById(@RequestBody Expense expense) {
        try {
            Expense updatedExpense = expenseService.updateById(expense);
            return new ResponseEntity<>(updatedExpense, HttpStatus.OK);
        }catch (IllegalArgumentException iae) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id},{dId}")
    public ResponseEntity<Expense> setAssignedDoc(@PathVariable("id") String id, @PathVariable("dId") String dId){
        try{
            final Expense expense = expenseService.setAssignedDoc(id, dId);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }catch(IllegalArgumentException iae){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
