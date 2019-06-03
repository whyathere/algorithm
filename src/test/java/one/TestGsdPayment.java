package one;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import sun.misc.BASE64Encoder;
import util.GsdUtil;
import util.StringUtil;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class TestGsdPayment {


    @Test
    public void test01() throws Exception {
        String MerchantCode = "10056"; //商户号（唯一）
        String MerchantId = "180069"; //商户ID（唯一）
        String MerchantUrl = "http://www.baidu.com"; //商户异步通知地址
        String OrderAmount = "100"; //订单金额
        String OrderCurrency = "CNY"; //货币类型： CNY
        String OrderNo = "20181ioi121222";    //订单号
        String PaymentType = "unionpay";    // 支付方式
        String ReturnUrl = "http://www.jingdong.com"; // 支付完成同步跳转地址
//        String SignType = "MD5"; // 此参数对签名无影响

        Map<String, String> params = new HashMap<>();

        params.put("MerchantCode",MerchantCode);
        params.put("MerchantId",MerchantId);
        params.put("MerchantUrl",MerchantUrl);
        params.put("OrderAmount",OrderAmount);
        params.put("OrderCurrency",OrderCurrency);
        params.put("OrderNo",OrderNo);
        params.put("PaymentType",PaymentType);
        params.put("ReturnUrl",ReturnUrl);
//        params.put("SignType",SignType);


        List<String> keys = new ArrayList<>(params.keySet());
        System.out.println(keys);
        Collections.sort(keys);
        System.out.println(keys);
        String MERCHANT_KEY = "9c5677fbbf14e232c623e40b97dc848e27045589";
        String SECURITY_SLAT = "A0bfgGHJ9l1nNP6Rt3xZ";

        StringBuffer keyBuffer = new StringBuffer();
        StringBuffer valueBuffer = new StringBuffer();
        StringBuffer dataBuffer = new StringBuffer("{");

/*        for (Map.Entry<String,String> entry : params.entrySet()){
            keyBuffer.append(entry.getKey()+"%#");
            valueBuffer.append(entry.getValue());
            dataBuffer.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
        }*/

        for (String key:keys){
            keyBuffer.append(key).append("%#");
            String value = params.get(key);
            valueBuffer.append(value);
            dataBuffer.append("\"").append(key).append("\":\"").append(value).append("\",");
        }
        System.out.println(keyBuffer);
        System.out.println(valueBuffer);

        String waitSign = keyBuffer.substring(0,keyBuffer.length()-2)+valueBuffer.toString()+MERCHANT_KEY;

//        String sign = Encrypt.SHA512(waitSign+SECURITY_SLAT);
        String sign = GsdUtil.createSign(SECURITY_SLAT,waitSign);
        //===========================

        dataBuffer.deleteCharAt(dataBuffer.length() - 1).append("}");
        String dataStr = dataBuffer.toString().replace("/", "\\/");

//        String data = buildDataText(params);
        String data = createData(dataStr);
        String url = "http://merchant.gsdpay.com/api/gateway/pay";

        Map<String,String> maps = new HashMap<>(2);
        maps.put("Sign",sign);
        maps.put("Data",data);
        String html = HtmlCreater.createHtmlByMap(url,"POST",maps);
        System.out.println(html);
    }




    private static String buildDataText(Map<String, String> reqData) {

        String jsonData = JSON.toJSONString(reqData);
        jsonData = jsonData.replace("/", "\\/");
        String base64Data = Base64.getEncoder().encodeToString(jsonData.getBytes(StandardCharsets.UTF_8));

        String suffix = base64Data.substring(base64Data.length() - 3);
        String dataStr = base64Data.substring(0, base64Data.length() - 3);

        dataStr = dataStr.replace("J", "%");
        dataStr = dataStr.replace("w", "*");
        dataStr = dataStr.replace("6", "@");
        dataStr = dataStr.replace("8", ",");
        dataStr = dataStr.replace("a", "6");
        dataStr = dataStr.replace("Q", "w");

        dataStr = new StringBuilder(dataStr).reverse().toString();

        int substrLength = (int)Math.floor((double)dataStr.length() / 3);
        if (dataStr.length() % 3 == 1) {
            String dataStrSub1 = dataStr.substring(0, substrLength);
            String dataStrSub2 = dataStr.substring(substrLength, substrLength * 2);
            String dataStrSub3 = dataStr.substring(substrLength * 2);
            dataStr = dataStrSub2 + dataStrSub1 + dataStrSub3;
        }
        else if (dataStr.length() % 3 == 2) {
            String dataStrSub1 = dataStr.substring(0, substrLength);
            String dataStrSub2 = dataStr.substring(substrLength, substrLength * 2 + 2);
            String dataStrSub3 = dataStr.substring(substrLength * 2 + 2);
            dataStr = dataStrSub2 + dataStrSub3 + dataStrSub1;
        }
        else {
            String dataStrSub1 = dataStr.substring(0, substrLength);
            String dataStrSub2 = dataStr.substring(substrLength, substrLength * 2);
            String dataStrSub3 = dataStr.substring(substrLength * 2);
            dataStr = dataStrSub3 + dataStrSub1 + dataStrSub2;
        }

        dataStr = dataStr + suffix;
        return dataStr;
    }

    /**
     * 生成Data信息
     * @param jsonData json格式字符串
     * @return
     */
    private String createData(String jsonData){
        try {
            byte[] base64DataBytes = Base64.getEncoder().encode(jsonData.getBytes("UTF-8"));
            String base64Data = new String(base64DataBytes, "UTF-8");
            String data1 = base64Data.substring(0, base64Data.length() - 3);
            String data2 = base64Data.substring(base64Data.length() - 3);
            data1 = data1.replace("J", "%").replace("w", "*").replace("6", "@").replace("8", ",");
            data1 = data1.replace("a", "6").replace("Q", "w");
            String reversaledData1 = StringUtil.reversalStr(data1);
            String splitedData1 = splitThree(reversaledData1);
            String data = splitedData1 + data2;
            return data;
        } catch (Throwable ex) {
            throw new RuntimeException(String.format("生成Data信息失败！jsonData: %s", jsonData), ex);
        }
    }

    /**
     * 将字符串分成三段，并按照特定规则重新组合
     * @param srcStr
     * @return
     */
    private final String splitThree(String srcStr){
        int remainder = srcStr.length() % 3;
        switch (remainder) {
            case 0:
                // 等分，返回 3 + 1 + 2
                int splitIndex = srcStr.length() * 2 / 3;
                String beginer = srcStr.substring(splitIndex);
                return beginer + srcStr.substring(0, splitIndex);
            case 1:
                // 分割 n 、n 、n + 1，返回 2 + 1 + 3
                splitIndex = srcStr.length() / 3;
                String str1 = srcStr.substring(splitIndex, splitIndex * 2);
                String str2 = srcStr.substring(0, splitIndex);
                String str3 = srcStr.substring(splitIndex * 2);
                return str1 + str2 + str3;
            case 2:
                // 分割 n、n + 2、n，返回 2 + 3 + 1
                splitIndex = srcStr.length() / 3;
                beginer = srcStr.substring(splitIndex);
                return beginer + srcStr.substring(0, splitIndex);
        }
        return null;
    }
}
