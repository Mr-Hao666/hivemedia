package cn.honghuroad.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZengXiong
 * @Description 快递100物流信息查询
 * @Date 2019/01/10 13:35
 */
public class KuaiDi100Util {
    public Logger logger = LoggerFactory.getLogger(KuaiDi100Util.class);

    private KuaiDi100Util() {
    }

    private static final KuaiDi100Util instance = new KuaiDi100Util();

    public static KuaiDi100Util getInstance() {
        return instance;
    }

    /**
     * 查询快递信息(该API不支持EMS、顺丰、圆通、中通和韵达等几家快递公司)
     *
     * @param key 密钥
     * @param com 公司名称
     * @param nu 快递单号
     * @return 请求结果
     */
    public String getKuaiDiInfo4Free(String key, String com, String nu) {
        String content = "";
        try {
            URL url = new URL("http://www.kuaidi100.com/api?id=" + key + "&com=" + com + "&nu=" + nu + "&valicode=" + 0);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    // String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, "UTF-8");
                    content += newContent;
                }
            }
            urlStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("快递查询错误");
        }
        return content;
    }

    /**
     * 查询快递信息
     *
     * @param param 请求参数
     * @return 请求结果
     */
    public List<Map<Object, Object>> getKuaiDiInfo4Firm(String param) {
        List<Map<Object, Object>> orderList = Lists.newArrayList();
        //客户授权KeynrrRQoWm242
        //实时查询customerE6D1CBE913C906C0999107429D4F5795
        //电子面单secret5257d01c1587491db5659b1c710e1713
        //短信接口用户ID112226dffbbc4ea881640c032a45bf88
        String customer = "E6D1CBE913C906C0999107429D4F5795";
        String key = "nrrRQoWm242";
        String sign = MD5Util.encode(param + key + customer);
        HashMap<String, String> params = Maps.newHashMap();
        params.put("param", param);
        params.put("sign", sign);
        params.put("customer", customer);
        String resp;
        try {
            resp = HttpRequestUtil.postData("http://poll.kuaidi100.com/poll/query.do", params, "utf-8").toString();
            //格式化数据
            JSONObject result = JSON.parseObject(resp);
            String state = (String) result.get("state");
            JSONArray dataJsonArr = (JSONArray) result.get("data");
            if (CollectionUtils.isNotEmpty(dataJsonArr)) {
                for (int i = 0; i < dataJsonArr.size(); i++) {
                    JSONObject job = (JSONObject) dataJsonArr.get(i);
                    Map<Object, Object> map = Maps.newHashMap();
                    SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                    map.put("state", state);
                    String time=(String)job.get("time");
                    Date date = format.parse(time);
                    map.put("time", date.getTime());
                    map.put("context", job.get("context"));
                    orderList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("快递查询错误");
        }
        return orderList;
    }

    public static void main(String[] args) {
        String emsCode = "shunfeng";
        String emsCompCode = "289333261834";
        String param = "{\"com\":\"" + emsCode + "\",\"num\":\"" + emsCompCode + "\"}";
        List<Map<Object, Object>> result = KuaiDi100Util.getInstance().getKuaiDiInfo4Firm(param);
        System.out.println(result.toString());
    }
}
