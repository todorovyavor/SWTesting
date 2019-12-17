package BankAccount;

public class BankAccount {
    private String bank_name;
    private String bank_name_en;
    private String bank_iban;
    private String bank_bic;


    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public void setBank_name_en(String bank_name_en) {
        this.bank_name_en = bank_name_en;
    }

    public void setBank_iban(String bank_iban) {
        this.bank_iban = bank_iban;
    }

    public void setBank_bic(String bank_bic) {
        this.bank_bic = bank_bic;
    }

    public BankAccount(String bank_name_en, String bank_name, String bank_iban, String bank_bic) {
        this.bank_name = bank_name;
        this.bank_name_en = bank_name_en;
        this.bank_iban = bank_iban;
        this.bank_bic = bank_bic;
    }

}
