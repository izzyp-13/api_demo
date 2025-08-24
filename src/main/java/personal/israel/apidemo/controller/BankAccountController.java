package personal.israel.apidemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.israel.apidemo.models.AddFunds;
import personal.israel.apidemo.models.BankAccount;
import personal.israel.apidemo.models.ResponseClass;
import personal.israel.apidemo.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    private final BankAccountRepository repository;

    public BankAccountController(BankAccountRepository repository) {
        this.repository = repository;
    }
    /**
     * Create a new school bankAccount
     */
    @PostMapping()
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount savedAccount = repository.save(bankAccount);
        return ResponseEntity.ok(savedAccount);
    }

    /**
     * Get a list of all school districts
     */
    @GetMapping()
    public ResponseEntity<List<BankAccount>> getAllBankAccounts() {
        return ResponseEntity.ok(repository.findAll());
    }

    /**
     * Get one school district by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getBankAccountById(@PathVariable Long id) {
        BankAccount bankAccount = repository.findById(id);
        if (bankAccount != null) {
            return ResponseEntity.ok(bankAccount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a bank account by ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseClass> deleteBankAccountById(@PathVariable Long id) {
        ResponseClass responseClass = new ResponseClass();
        BankAccount bankAccount = repository.findById(id);
        if (bankAccount != null) {
            Integer idInteger = Math.toIntExact(id);
            repository.deleteById(idInteger);
            responseClass.setMessage("Account deleted successfully");
        } else {
            responseClass.setMessage("Account not found");
        }
        return ResponseEntity.ok(responseClass);
    }

    /**
     * Add funds to an account
     */
    @PostMapping("/add-funds/{id}")
    public ResponseEntity<ResponseClass> addFunds(@PathVariable Long id,
                                                  @RequestBody AddFunds fundsAmount) {
        ResponseClass responseClass = new ResponseClass();
        BankAccount bankAccount = repository.findById(id);
        if (bankAccount != null) {
            bankAccount.setBalance(bankAccount.getBalance().add(fundsAmount.getAmount()));
            repository.save(bankAccount);
            responseClass.setMessage("Funds added successfully");
        } else {
            responseClass.setMessage("Account not found");
        }
        return ResponseEntity.ok(responseClass);
    }
}
