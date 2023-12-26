package Model;

import AccountNumber.AccountTransfer;
import File.OperationWithFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountTransactions {
    private static final int NUMBER_OF_OPERATION_ONE = 1;
    private static final int NUMBER_OF_OPERATION_TWO = 2;
    private static final int ARRAY_SIZE_TRANSFER = 3;
    private static final int ARRAY_SIZE_ADD_MONEY = 2;
    private int number;
    private final String regex = "\\d{5}-\\d{5}\\s*\\|\\s*\\d{5}-\\d{5}\\s*\\|\\s*\\d+|\\d{5}-\\d{5}\\s*\\|\\s*\\d+";
    private String fileContentInput;
    private AccountTransfer accountTransfer;
    private Map<String, Integer> account = new HashMap<>();
    private Set<String> keys;
    private StringBuilder stringBuilder = new StringBuilder();

    public void numberOperation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of operation:\n" +
                "   1. Calling the operation of parsing translation files from input\n" +
                "   2. Calling the operation to display a list of all transfers from report file");
        number = scanner.nextInt();

        if (number == NUMBER_OF_OPERATION_ONE) {
            for (String filename : OperationWithFile.getFileInputNames()) {
                if (filename.contains(".txt")) {
                    fileContentInput = OperationWithFile.fileInputReading(filename);
                    List<String> arrayLists = Arrays.asList(fileContentInput.split("\\r*\\n"));
                    for (String arrayList : arrayLists) {
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(arrayList);
                        if (matcher.find()) {
                            String[] transferInfo = matcher.group().replace(" ", "").split("\\|");
                            if (transferInfo.length == ARRAY_SIZE_TRANSFER) {
                                accountTransfer = new AccountTransfer(transferInfo[0], transferInfo[1], Integer.parseInt(transferInfo[2]));
                                account = accountTransfer.getAccountNumber(OperationWithFile.fileAccountNumberReading());
                                if (accountTransfer.transferMoney(account)) {
                                    OperationWithFile.fileReportWriting(LocalDateTime.now() + " | " +
                                            filename + " | перевод с " + accountTransfer.getSrcAccount() +
                                            " на " + accountTransfer.getDstAccount() + " " +
                                            accountTransfer.getAmountTransfer() + " | успешно обработан");
                                } else {
                                    OperationWithFile.fileReportWriting(LocalDateTime.now() + " | " +
                                            filename + " | перевод с " + accountTransfer.getSrcAccount() +
                                            " на " + accountTransfer.getDstAccount() + " " +
                                            accountTransfer.getAmountTransfer() +
                                            " | ошибка во время обработки, неверная сумма перевода");
                                }
                            }
                        }
                        keys = account.keySet();
                        for (String key : keys) {
                            stringBuilder.append(key).append(" | ").append(account.get(key)).append("\n");
                        }
                        OperationWithFile.fileAccountNumberWriting(stringBuilder.toString());
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                    OperationWithFile.fileArchiveWriting(filename, fileContentInput);
                    OperationWithFile.deleteFileInput(filename);
                }
            }
        } else if (number == NUMBER_OF_OPERATION_TWO) {
            System.out.println(OperationWithFile.fileReportReading());
        }
    }
}
