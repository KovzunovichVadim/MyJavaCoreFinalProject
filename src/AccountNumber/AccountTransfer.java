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
    public Map<String, Integer> getAccountNumber(String account) {
        return super.getAccountNumber(account);
    }

    public boolean transferMoney(Map<String, Integer> account) {
        if (account.containsKey(srcAccount)) {
            if (account.get(srcAccount) - amountTransfer >= 0 && amountTransfer >= 0) {
                account.replace(srcAccount, account.get(srcAccount) - amountTransfer);
                account.replace(dstAccount, account.get(dstAccount) + amountTransfer);
            } else {
                return false;
            }
        }
        return true;
    }

    public String getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(String srcAccount) {
        this.srcAccount = srcAccount;
    }

    public String getDstAccount() {
        return dstAccount;
    }

    public void setDstAccount(String dstAccount) {
        this.dstAccount = dstAccount;
    }

    public int getAmountTransfer() {
        return amountTransfer;
    }

    public void setAmountTransfer(int amountTransfer) {
        this.amountTransfer = amountTransfer;
    }
}
