package AccountNumber;
import java.util.HashMap;
import java.util.Map;

public class AccountNumber {
    private Map<String, Integer> accountNumbers = new HashMap<>();

    public Map<String, Integer> getAccountNumber(String account){
        try {
            String[] numbers = account.split("\\r*\\n");
            for (String number:numbers){
                String[] keyValue = number.replace(" ","").split("\\|");
                if (!accountNumbers.containsKey(keyValue[0])){
                    accountNumbers.put(keyValue[0], (Integer.parseInt(keyValue[1])));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return accountNumbers;
    }
}
