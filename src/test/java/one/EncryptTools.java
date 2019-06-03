/*
package one;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;
// NOTE: we use the 'fastjson' package to handle json data
// you can use the other json handle package
import com.alibaba.fastjson.*;

 public class EncryptTools {

    private String slat;
    private String seckey;
    private Map<String, String> respData;

    public EncryptTools(String merchant_key, String security_slat) {
        this.seckey = merchant_key;
        this.slat = security_slat;
    }

    public String createRequestData(Map<String, String> reqData) {
        try {
            var sign = this.sign(reqData);
            var data = this.buildDataText(reqData);

            return ("Sign=" + sign + "&" + "Data=" + data);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String sign(Map<String, String> reqData) throws Exception {
        var arrayList = new ArrayList<>(reqData.keySet());
        arrayList.sort((x, y)-> (x.compareTo(y)));
        var keysStrBuilder = new StringBuilder();
        var valuesStrBuilder = new StringBuilder();
        for (String val: arrayList) {
            keysStrBuilder.append(val).append("%#");
            valuesStrBuilder.append(reqData.get(val));
        }
        var keys_str = keysStrBuilder.toString();
        var values_str = valuesStrBuilder.toString();
        keys_str = keys_str.substring(0, keys_str.length() - 2);

        return hmacData(keys_str + values_str + this.seckey, this.slat, "HmacSHA512");
    }

    private static String hmacData(String data, String slat, String algorithm) throws Exception {

        var dataBytes = data.getBytes(StandardCharsets.UTF_8);
        var slatBytes = slat.getBytes(StandardCharsets.UTF_8);

        var key = new SecretKeySpec(slatBytes, algorithm);

        var hmac = Mac.getInstance(algorithm);
        hmac.init(key);

        var hash = hmac.doFinal(dataBytes);
        return byte2hex(hash);
    }

    private static String byte2hex(byte[] b) {
        var hs = new StringBuilder();

        for (byte val: b) {
            hs.append(String.format("%1$02x", Byte.toUnsignedInt(val)));
        }

        return hs.toString();
    }

    private String buildDataText(Map<String, String> reqData) {

        var jsonData = JSON.toJSONString(reqData);
        jsonData = jsonData.replace("/", "\\/");
        var base64Data = Base64.getEncoder().encodeToString(jsonData.getBytes(StandardCharsets.UTF_8));

        var suffix = base64Data.substring(base64Data.length() - 3);
        var dataStr = base64Data.substring(0, base64Data.length() - 3);

        dataStr = dataStr.replace("J", "%");
        dataStr = dataStr.replace("w", "*");
        dataStr = dataStr.replace("6", "@");
        dataStr = dataStr.replace("8", ",");
        dataStr = dataStr.replace("a", "6");
        dataStr = dataStr.replace("Q", "w");

        dataStr = new StringBuilder(dataStr).reverse().toString();

        var substrLength = (int)Math.floor((double)dataStr.length() / 3);
        if (dataStr.length() % 3 == 1) {
            var dataStrSub1 = dataStr.substring(0, substrLength);
            var dataStrSub2 = dataStr.substring(substrLength, substrLength * 2);
            var dataStrSub3 = dataStr.substring(substrLength * 2);
            dataStr = dataStrSub2 + dataStrSub1 + dataStrSub3;
        }
        else if (dataStr.length() % 3 == 2) {
            var dataStrSub1 = dataStr.substring(0, substrLength);
            var dataStrSub2 = dataStr.substring(substrLength, substrLength * 2 + 2);
            var dataStrSub3 = dataStr.substring(substrLength * 2 + 2);
            dataStr = dataStrSub2 + dataStrSub3 + dataStrSub1;
        }
        else {
            var dataStrSub1 = dataStr.substring(0, substrLength);
            var dataStrSub2 = dataStr.substring(substrLength, substrLength * 2);
            var dataStrSub3 = dataStr.substring(substrLength * 2);
            dataStr = dataStrSub3 + dataStrSub1 + dataStrSub2;
        }

        dataStr = dataStr + suffix;
        return dataStr;
    }

    public Boolean verify(Map<String, String> data) throws Exception {

        if (! data.containsKey("Sign") || ! data.containsKey("Data"))
            return false;

        this.respData = this.recoverData(data.get("Data"));

        if (this.respData == null) return false;

        return data.get("Sign").equals(this.sign(this.respData));
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> recoverData(String respData) {
        if (respData.matches("\\s")) return null;

        var suffix = respData.substring(respData.length() - 3);
        var dataStr = respData.substring(0, respData.length() - 3);

        var substrLength = (int)Math.floor((double)dataStr.length() / 3);
        if (dataStr.length() % 3 == 1)
        {
            var dataStrSub1 = dataStr.substring(0, substrLength);
            var dataStrSub2 = dataStr.substring(substrLength, substrLength * 2);
            var dataStrSub3 = dataStr.substring(substrLength * 2);
            dataStr = dataStrSub2 + dataStrSub1 + dataStrSub3;
        }
        else if (dataStr.length() % 3 == 2)
        {
            var dataStrSub1 = dataStr.substring(0, substrLength + 2);
            var dataStrSub2 = dataStr.substring(substrLength + 2, substrLength * 2 + 2);
            var dataStrSub3 = dataStr.substring(substrLength * 2 + 2);
            dataStr = dataStrSub3 + dataStrSub1 + dataStrSub2;
        }
        else
        {
            var dataStrSub1 = dataStr.substring(0, substrLength);
            var dataStrSub2 = dataStr.substring(substrLength, substrLength * 2);
            var dataStrSub3 = dataStr.substring(substrLength * 2);
            dataStr = dataStrSub2 + dataStrSub3 + dataStrSub1;
        }
        dataStr = new StringBuilder(dataStr).reverse().toString();

        dataStr = dataStr.replace('6', 'a');
        dataStr = dataStr.replace('w', 'Q');
        dataStr = dataStr.replace('%', 'J');
        dataStr = dataStr.replace('*', 'w');
        dataStr = dataStr.replace('@', '6');
        dataStr = dataStr.replace(',', '8');


        dataStr = dataStr + suffix;

        var base64Data = Base64.getDecoder().decode(dataStr);
        var base64Str = new String(base64Data).replace("\\/", "/");

        return JSON.parseObject(base64Str, Map.class);
    }

    public Map<String, String> getResponseData() {
        return this.respData;
    }
}
 */