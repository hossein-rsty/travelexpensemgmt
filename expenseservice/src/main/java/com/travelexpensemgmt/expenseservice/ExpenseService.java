package com.travelexpensemgmt.expenseservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @project: travelexpensemgmt
 * @author: Hossein Rostamiraeini
 */
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     *
     * @param expense Object to be saved in DB
     * @return the saved Object
     */
    public Expense saveExpense(Expense expense){
        final ExpenseDbModel savedExpense = expenseRepository.save(createExpenseDbModel(expense));
        return createExpense(savedExpense);
    }

    /**
     *
     * @return a List of all Expense Objects existing in DB
     */
    public List<Expense> getExpenses(){
        List<ExpenseDbModel> expenses = expenseRepository.findAll();
        return expenses
                .stream()
                .map(this::createExpense)
                .collect(Collectors.toList());
    }

    /**
     *
     * @param expenseId The id of the expense to be returned
     * @return A Expense with the given ID
     */
    public Expense getExpenseById(String expenseId) {
        final ExpenseDbModel expenseDbModel = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found by id: " + expenseId));
        return createExpense(expenseDbModel);
    }
    /**
     * Delete a Expense by Id
     *
     * @param expenseId the unique Id of a Expense which is requiered for delete
     * @return the deleted Expense
     */
    public Expense deleteExpenseById(String expenseId){
        try {
            final Expense expense = getExpenseById(expenseId);
            expenseRepository.deleteById(expenseId);
            return expense;
        } catch (Exception e) {
            throw new IllegalArgumentException("Delete failed with error: " + e.getMessage());
        }
    }

    /**
     *
     * @param expense existing expense object to be edited
     * @return the updated Expense Object
     */
    public Expense updateById(Expense expense){
        if(expenseRepository.existsById(expense.getExpenseId())){
            final ExpenseDbModel updatedExpense = expenseRepository.save(editExpenseDbModel(expense));
            return createExpense(updatedExpense);
        }else{
            throw new IllegalArgumentException("Expense not found by id: " + expense.getExpenseId());
        }
    }
    public Expense setAssignedDoc(String expenseId, String docId){
        Expense expense = getExpenseById(expenseId);
        expense.setAssignedDocId(docId);
        final ExpenseDbModel savedExpense = expenseRepository
                .save(editExpenseDbModel(expense));
        return createExpense(savedExpense);
    }

    /**
     * INTERNAL
     * @param expense to be converted to a DB-Model Object
     * @return the converted object
     */
    private ExpenseDbModel createExpenseDbModel(Expense expense) {
        return ExpenseDbModel.builder()
                .id(null)
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .currency(expense.getCurrency())
                .description(expense.getDescription())
                .assignedDocId(expense.getAssignedDocId())
                .build();
    }

    /**
     * INTERNAL
     * @param expense to be converted to a DB-Model Object for editing purposes (The ID will be given too)
     * @return the converted object
     */
    private ExpenseDbModel editExpenseDbModel(Expense expense) {
        return ExpenseDbModel.builder()
                .id(expense.getExpenseId())
                .category(expense.getCategory())
                .amount(expense.getAmount())
                .currency(expense.getCurrency())
                .description(expense.getDescription())
                .assignedDocId(expense.getAssignedDocId())
                .build();
    }

    /**
     * INTERNAL
     * @param expenseDbModel to be converted to an Expense Object
     * @return the converted object
     */
    private Expense createExpense(ExpenseDbModel expenseDbModel) {
        return Expense.builder()
                .expenseId(expenseDbModel.getId())
                .category(expenseDbModel.getCategory())
                .amount(expenseDbModel.getAmount())
                .currency(expenseDbModel.getCurrency())
                .description(expenseDbModel.getDescription())
                .assignedDocId(expenseDbModel.getAssignedDocId())
                .build();
    }
}
