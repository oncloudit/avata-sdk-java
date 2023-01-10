import config.ConfigCache;
import constant.ErrorMessage;
import exception.SdkException;
import proxy.account.impl.AccountClient;
import model.mt.MtClient;
import proxy.nft.impl.NftClient;
import model.order.OrderClient;
import model.records.RecordsClient;
import proxy.tx.impl.TxClient;
import util.Strings;

public class Client {
    public NftClient nftClient;
    public AccountClient accountClient;
    public MtClient mtClient;
    public OrderClient orderClient;
    public RecordsClient recordsClient;
    public TxClient txClient;

    /**
     * SDK initialization method
     */
    private Client(Builder builder) {
        this.accountClient = new AccountClient();
        this.nftClient = new NftClient();
        this.mtClient = new MtClient();
        this.orderClient = new OrderClient();
        this.recordsClient = new RecordsClient();
        this.txClient = new TxClient();
    }

    public static class Builder {
        private String doMain;
        private String apiKey;
        private String apiSecret;
        private long httpTimeout;

        public Builder setDoMain(String doMain) {
            if (Strings.isEmpty(doMain)) {
                throw new SdkException(ErrorMessage.UNKNOWN_ERROR);
            }
            this.doMain = doMain;
            return this;
        }

        public Builder setHttpTimeout(long httpTimeout) {
            if (httpTimeout == 0) {
                throw new SdkException(ErrorMessage.UNKNOWN_ERROR);
            }
            this.httpTimeout = httpTimeout;
            return this;
        }

        public Builder setApiKey(String apiKey) {
            if (Strings.isEmpty(apiKey)) {
                throw new SdkException(ErrorMessage.UNKNOWN_ERROR);
            }
            this.apiKey = apiKey;
            return this;
        }

        public Builder setApiSecret(String apiSecret) {
            if (Strings.isEmpty(apiSecret)) {
                throw new SdkException(ErrorMessage.UNKNOWN_ERROR);
            }
            this.apiSecret = apiSecret;
            return this;
        }

        public Client init() {
            ConfigCache.initCache(doMain, httpTimeout, apiKey, apiSecret);
            return new Client(this);
        }
    }

    public Boolean setDoMain(String doMain) {
        if (doMain.isEmpty()) {
            return false;
        }
        ConfigCache.get().setDoMain(doMain);
        return true;
    }
}
