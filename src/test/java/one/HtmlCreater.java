package one;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by Devin on 2018/7/4.
 */
public class HtmlCreater {


    /**
     * @author:Devin
     * @date:2018/7/4
     * @description:通过paraMap来解析成需要跳转的html页面，用于入金时向前端返回跳转页面信息
     **/
    public final static String createHtmlByMap(String url, String method, Map<String, String> paraMap){
        if (StringUtils.isBlank(url) || StringUtils.isBlank(method)){
            throw new NullPointerException(String.format("url和method不能为空！url: %s, method: %s", url, method));
        }
        String head = "<html>\n"
                + " <head>\n"
                + "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
                + "  <title> </title>\n"
                + " </head>\n"
                + " <body>\n"
                + "  <form method=\"" + method + "\" name=\"AutoForm\" id=\"AutoForm\" action=\"" + url + "\">\n"
                + "   <table>\n"
                + "    <tbody>\n"
                + "     <tr>\n"
                + "      <td>";
        StringBuffer inputs = new StringBuffer();
        if (!MapUtils.isEmpty(paraMap)){
            Set<Map.Entry<String, String>> entries = paraMap.entrySet();
            for (Map.Entry<String, String> entry:entries){
                inputs.append("\n\t\t  <input name=\"" + entry.getKey() + "\" id=\"" + entry.getKey() + "\" type=\"hidden\" value=\"" + entry.getValue() + "\" />");
            }
        }
        String foot = "\n\t  </td>\n"
                + "     </tr>\n"
                + "    </tbody>\n"
                + "   </table>\n"
                + "  </form>\n"
                + "  <script type=\"text/javascript\">document.AutoForm.submit();</script>\n"
                + " </body>\n"
                + "</html>";
        String html = head + inputs.toString() + foot;
        return html;
    }

}
