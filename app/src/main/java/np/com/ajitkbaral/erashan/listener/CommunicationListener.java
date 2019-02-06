package np.com.ajitkbaral.erashan.listener;

import np.com.ajitkbaral.erashan.entity.Product;

public interface CommunicationListener {
    void communicate(String key, String message, Product product);
}
