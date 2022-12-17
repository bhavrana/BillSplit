package com.example.billsplit.service.expense.validate;

import com.example.billsplit.domain.Currency;
import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.CurrencyRepository;
import com.example.billsplit.repository.UserGroupRepository;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.input.expense.AddExpenseInput;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddExpenseValidationService {

    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;


    public AddExpenseValidationService(UserGroupRepository userGroupRepository, UserRepository userRepository, CurrencyRepository currencyRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }

    public void validateExpense(AddExpenseInput addExpenseInput) {

        // Validate that the user exists;
        Optional<Uzer> user = userRepository.findById(addExpenseInput.getUserID());
        if(!user.isPresent()) {
            throw new BillSplitException("User doesn't exist");
        }

        // Validate that the group exists
        Optional<UserGroup> userGroupOptional = userGroupRepository.findById(addExpenseInput.getGroupID());
        if(!userGroupOptional.isPresent()) {
            throw new BillSplitException("User group doesn't exist");
        }

        // Validate if currency exists
        Optional<Currency> currency = currencyRepository.findById(addExpenseInput.getCurrency());
        if(!currency.isPresent()) {
            throw new BillSplitException("Currency doesn't exist");
        }

        UserGroup userGroup = userGroupOptional.get();
        for(Uzer u : userGroup.getUsers()) {
            if(u.getId() == addExpenseInput.getUserID()) {
                // user exists in group
                return;
            }
        }
        throw new BillSplitException("User doesn't exist in group");
    }
}
