package dive.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * url编码
 *
 * @author dawn
 * @date 2019/1/22 0:43
 */
public class UrlUtil {

    /**
     * url编码
     *
     * @param url url
     * @return 编码后
     */
    public static String urlEncode(String url) {
        Objects.requireNonNull(url, "url");
        try {
            return URLEncoder.encode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
