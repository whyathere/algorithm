package util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class GsdUtil {




    /**
     * 获取验签
     * @param salt 盐
     * @param data 待生成验签的信息
     * @return
     */
    public final static String createSign(String salt, String data){
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes("UTF-8"), "HmacSHA512");
            Mac mac = Mac.getInstance("HmacSHA512");
            mac.init(secretKeySpec);
            byte[] endyptedBytes = mac.doFinal(data.getBytes("UTF-8"));
            StringBuffer buffer = new StringBuffer();
            for (byte endyptedByte:endyptedBytes){
                buffer.append(String.format("%1$02x", Byte.toUnsignedInt(endyptedByte)));
            }
            String sign = buffer.toString();
            return sign;
        } catch (Throwable ex) {
            throw new RuntimeException(String.format("获取验签失败！salt: %s, data: %s", salt, data), ex);
        }
    }

}
