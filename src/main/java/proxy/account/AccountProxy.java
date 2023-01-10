package proxy.account;

import model.account.AccountResponse;

public interface AccountProxy {
    /**
     * 创建离岸账户
     * @param name 账户名称
     * @param operationId 操作id，需幂等
     * @return
     */
    AccountResponse createAccount(String name, String operationId);
}
