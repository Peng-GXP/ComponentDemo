package app.qimi.cn.lib_common.retrofit.https;

import android.content.Context;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * @author GaoXP
 * @time 2017/12/9.
 */

public class HttpsSSLSocketFactory {

    private static final String KEY_STORE_TYPE_BKS = "BKS";//证书类型 固定值
    private static final String KEY_STORE_TYPE_P12 = "BKS";//证书类型 固定值
    private static final String KEY_STORE_CLIENT_PATH = "client.bks";//客户端要给服务器端认证的证书
    private static final String KEY_STORE_PASSWORD = "123456";// 客户端证书密码
    public static final String TAG = "HttpsSSLSocketFactory";

    public static SSLContext getSSLContext(Context context) {
        try {
            // 服务器端需要验证的客户端证书
            KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
            // 客户端信任的服务器端证书
            KeyStore trustStore = KeyStore.getInstance(KEY_STORE_TYPE_BKS);
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore defaultKeystore = KeyStore.getInstance(keyStoreType);
            defaultKeystore.load(null, null);
            InputStream ksIn = context.getResources().getAssets().open(KEY_STORE_CLIENT_PATH);
//            InputStream tsIn = context.getResources().getAssets().open(KEY_STORE_TRUST_PATH);
            try {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());
//                trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    ksIn.close();
                } catch (Exception ignore) {
                }
                try {
//                    tsIn.close();
                } catch (Exception ignore) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());


            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[]{};
                }

                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }
            }};
//            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            sslContext.init(keyManagerFactory.getKeyManagers(), trustAllCerts, null);
            return sslContext;
        } catch (Exception e) {

        }
        return null;
    }
}

