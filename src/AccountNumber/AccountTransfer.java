package AccountNumber;

import java.util.Map;

public class AccountTransfer extends AccountNumber{
    /*
     * srcAccount - debit from account
     * dstAccount - transfer to account
     * amountTransfer - transfer quantity
     */
    private String srcAccount;
    private String dstAccount;
    private int amountTransfer;

    public AccountTransfer(String srcAccount, String dstAccount, int amountTransfer) {
        this.srcAccount = srcAccount;
        this.dstAccount = dstAccount;
        this.amountTransfer = amountTransfer;
    }

    @Override
    public Map<String, Integer> getAccountNumber() {
        return super.getAccountNumber();
    }

    public boolean transferMoney() {
        if (getAccountNumber().containsKey(srcAccount)) {
            if (getAccountNumber().get(srcAccount) - amountTransfer >= 0) {
                getAccountNumber().replace(srcAccount, getAccountNumber().get(srcAccount) - amountTransfer);
                getAccountNumber().replace(dstAccount, getAccountNumber().get(dstAccount) + amountTransfer);
            } else {
                return false;
            }
        }
        return true;
    }
}
