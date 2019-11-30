package util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 字符串工具类
 *
 * @author Only You
 * @version 1.0
 * @date 2016年1月6日
 */
public class StringUtil {

    public static final String EMPTY = "";

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true 不为空, false 为空
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !"null".equals(str) && str.trim().length() != 0;
    }

    /**
     * 是否要显示金额
     *
     * @param str
     * @return
     */
    public static boolean isShowMoney(String str) {
        return str != null && !"null".equals(str) && str.trim().length() != 0 && !str.equals("0");
    }

    public static boolean isShowMoney(double str) {
        return str != 0;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return true 为空，false 不为空
     */
    public static boolean isEmpty(String str) {
        return str == null || "null".equals(str) || str.trim().length() == 0;
    }

    public static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm", Locale.CHINA);

    public static String getCurrentTime() {
        return dateformat.format(new Date());
    }

    public static final SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public static String formatDateTime(long millseconds) {
        return sdformat.format(new Date(millseconds));
    }

    public static String getCurrentDateTime() {
        return sdformat.format(new Date());
    }

    /**
     * 判断集合是否为空
     */
    public static <T> boolean isCollectionsNotEmpty(Collection<T> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * 判断MAP是否为空
     */
    public static <K, V> boolean isMapNotEmpty(Map<K, V> map) {
        return map != null && map.size() > 0;
    }

    /**
     * 判断List是否为空
     */
    public static boolean isListEmpty(List<?> array) {
        return array != null && array.size() == 0;
    }

    /**
     * 判断JSON数组是否为空
     */
    public static boolean isJSONArrayEmpty(JSONArray array) {
        return array == null || array.length() == 0;
    }

    public static boolean isObjectNull(Object object) {
        if (object != null && object.getClass().isArray()) {
            // 如果是数组类型
            throw new UnsupportedOperationException("isObjectNotNull not supported operation :" + object);
        }
        return object == null;
    }

    public static boolean isObjectNotNull(Object object) {
        if (object != null && object.getClass().isArray()) {
            // 如果是数组类型
            throw new UnsupportedOperationException("isObjectNotNull not supported operation :" + object);
        }
        return object != null;
    }

    /**
     * 判断JSON数据不空为
     */
    public static boolean isJSONArrayNotEmpty(JSONArray array) {
        return array != null && array.length() > 0;
    }

    /**
     * 判断JSON数组是否为空
     */
    public static boolean isJSONObjectEmpty(JSONObject object) {
        return object == null || object.length() == 0;
    }

    /**
     * 判断JSON数据不空为
     */
    public static boolean isJSONObjectNotEmpty(JSONObject object) {
        return object != null && object.length() > 0;
    }

    public static boolean isIntArrayNotEmpty(int[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断List数据不空为
     */
    public static boolean isListNotEmpty(List<?> array) {
        return array != null && array.size() > 0;
    }

    /**
     * 判断long数组不为空
     *
     * @param array
     * @return
     */
    public static boolean isLongArrayNotEmpty(long[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断float数组不为空
     *
     * @param array
     * @return
     */
    public static boolean isFloatArrayNotEmpty(float[] array) {
        return array != null && array.length > 0;
    }

    /**
     * 判断double数组不为空
     *
     * @param array
     * @return
     */
    public static boolean isDoubleArrayNotEmpty(double[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isNotBlank(String str) {
        return (str != null) && (str.length() != 0);
    }

    public static boolean isBlank(String str) {
        return (str == null) || (str.length() == 0);
    }

    public static boolean isNotTrimBlank(String str) {
        return (str != null) && (str.trim().length() != 0);
    }

    public static boolean isTrimBlank(String str) {
        return (str == null) || (str.trim().length() == 0);
    }

    /**
     * 判断是否为网址
     *
     * @param urlString url
     * @return
     */
    public static boolean isHttpUrl(String urlString) {
        if (isEmpty(urlString)) {
            return false;
        }
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(urlString);
        return matcher.matches();

    }

    /**
     * 判断是否为淘口令
     *
     * @param result
     * @return
     */
    public static boolean isPromoterId(String result) {
        if (isEmpty(result)) {
            return false;
        }
        String regex = "^[0-9A-Z]{8}$";
        Pattern httpPattern = Pattern.compile(regex);
        if (httpPattern.matcher(result).matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是身份证
     *
     * @param idNo
     * @return
     */
    public static boolean isIdNo(String idNo) {
        if (isTrimBlank(idNo)) {
            return false;
        }
        Pattern p = Pattern.compile("^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[X,x]))$");
        Matcher matcher = p.matcher(idNo);
        return matcher.find();
    }

    /**
     * 判断是否为手机号
     *
     * @param mobile
     * @return
     */
    private boolean isMobileNo(String mobile) {
        Pattern pattern = Pattern
                .compile("^((1[358][0-9])|(14[57])|(17[0678]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }


    /**
     * 判断是否为邮箱号
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (isTrimBlank(email)) {
            return false;
        }
        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 在HTML特殊字符的处理
     *
     * @param source
     * @return
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtil.isEmpty(source) ? source : source.replaceAll("&lt;", "<")
                .replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&")
                .replaceAll("&quot;", "\"")
                .replaceAll("&copy;", "©")
                .replaceAll("&yen;", "¥")
                .replaceAll("&divide;", "÷")
                .replaceAll("&times;", "×")
                .replaceAll("&reg;", "?")
                .replaceAll("&sect;", "§")
                .replaceAll("&pound;", "￡")
                .replaceAll("&cent;", "￠");
    }

    /**
     * 验证用户名是否合法
     *
     * @param id
     * @return
     */
    public static boolean isNotUserName(String id) {
        if (isTrimBlank(id)) {
            return false;
        }
        // 字母开头，由字母，数字和下划线组成的长度为2到16的字符串
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{2,16}$");
        Matcher m = p.matcher(id);
        return !m.find();
    }

    /**
     * 判断是否为密码
     *
     * @param password
     * @return
     */
    public static boolean isNotPassWord(String password) {
        if (isTrimBlank(password)) {
            return false;
        }
        // 就是以大小写字母开头，由大小写字母，数字和下划线组成的长度为6到18的字符串
        Pattern p = Pattern.compile("^[a-zA-Z0-9_]{6,18}$");
        Matcher m = p.matcher(password);
        return !m.find();
    }

    /**
     * 判断银行卡号是否合法
     *
     * @param bankCard
     * @return
     */
    public static boolean isNotBank(String bankCard) {
        if (isTrimBlank(bankCard)) {
            return false;
        }
        // 一共16或19位，都是数字。
        Pattern p = Pattern.compile("^\\d{16}$|^\\d{19}$");
        Matcher m = p.matcher(bankCard);
        return !m.find();
    }

    /**
     * 只允许字母、数字和汉字
     *
     * @param str
     * @return
     */
    public static boolean isStringFilter(String str) {
        if (isTrimBlank(str)) {
            return false;
        }
        Pattern p = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5]");
        Matcher m = p.matcher(str);
        return !m.find();
    }

    /**
     * @param context
     * @param resId
     * @param str
     * @return
     */
    public static String isStringFormat(Context context, int resId, String str) {
        return String.format(context.getResources().getString(resId), str);
    }

    /**
     * 从Raw文件中读取
     *
     * @param context
     * @param resId
     * @return
     */
    public static String getFromRaw(Context context, int resId) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 直接从assets读取
    public static String getFromAssets(Context context, String fileName) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换成大写
     *
     * @param s
     * @return
     */
    public static String toUpperCase(String s) {
        return s.toUpperCase(Locale.getDefault());
    }

    /**
     * 判断当前应用程序处于前台还是后台
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    /*
     * 若真实姓名为 张三
     * 则返回 **三
     */
    public static String realName(String realName) {
        if (realName == null) return null;
        char[] charArray = realName.toCharArray();
        String x = "";
        for (int i = 0; i < charArray.length - 1; i++) {
            x += "*";
        }
        return charArray[0] + x;
    }

    /**
     * 将字节自动转换为KB 或M
     *
     * @param byteLength 字节长度
     * @return
     */
    public static String byteChange(double byteLength) {
        String str = "";
        double kb = byteLength / 1024;//转换为KB
        double m = kb / 1024;//转换为M
        if (m < 1) {
            //保留两位小数，并四色五入
            BigDecimal b = new BigDecimal(kb);
            kb = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            str = kb + "KB";
        } else {
            BigDecimal b = new BigDecimal(m);
            m = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            str = m + "M";
        }
        return str;
    }

    /**
     * 保留两位小数
     *
     * @param number
     * @return
     */
    public static String saveFloatTwo(float number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }


    public static String saveFloatTwo(double number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }

    /**
     * @param number
     * @return
     */
    public static String saveFloatTwo(String number) {
        try {
            double money = Double.parseDouble(number);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(money);
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * @param number
     * @return
     */
    public static String saveLevelPrice(String number) {
        try {
            double m = Double.parseDouble(number);
            if (number.contains(".0")) {
                int index = number.lastIndexOf(".");
                String price = number.substring(0, index);
                return price;
            } else if (m <= 1) {
                double money = Double.parseDouble(number);
                DecimalFormat df = new DecimalFormat("0.0");
                return df.format(money);
            }
            double money = Double.parseDouble(number);
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format(money);
        } catch (Exception e) {
            return "0.00";
        }
    }

    public static String subTimeDay(String time) {
        if (StringUtil.isEmpty(time))
            return "";
        else {
            if (time.length() >= 10)
                return time.substring(0, 10);
            else return time;
        }
    }

    /**
     * 是否是车牌
     *
     * @param value
     * @return
     */
    public static boolean matcherVehicleNumber(String value) {
        String regex = "^[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼川贵云藏陕甘青宁新渝]?[A-Z][A-HJ-NP-Z0-9学挂港澳练]{5}$";
        return testRegex(regex, value.toUpperCase());
    }

    private static boolean testRegex(String regex, String inputValue) {
        return Pattern.compile(regex).matcher(inputValue).matches();
    }

    /**
     * 是否是车架号
     *
     * @param param
     * @return
     */
    public static boolean isIdentificationCode(String param) {
        if (param.toUpperCase().matches("^[A-Z0-9]{6,17}$")) {
            return true;
        }
        return false;
    }

    /**
     * 是否为发动机号
     *
     * @param param
     * @return
     */
    public static boolean isVehicleEngineNo(String param) {
        if (param.toUpperCase().matches("^[A-Z0-9]+$")) {
            return true;
        }
        return false;
    }

    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     *
     * @param cardNum 待检验的原始卡号
     * @return 返回是否包含
     * @author fenggaopan 2015年7月21日 上午9:49:40
     */
    public static boolean judgeContainsStr(String cardNum) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }

    /**
     * 定义script的正则表达式
     */
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";
    /**
     * 定义空格回车换行符
     */
    private static final String REGEX_SPACE = "\\s*|\t|\r|\n";

    /**
     * 定义空格回车换行符
     *
     * @param htmlStr
     * @return
     */
    public static String delHTMLTag(String htmlStr) {
        // 过滤script标签
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        // 过滤style标签
        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        // 过滤html标签
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        // 过滤空格回车标签
        Pattern p_space = Pattern.compile(REGEX_SPACE, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");
        return htmlStr.trim(); // 返回文本字符串
    }

    /**
     * 判断电话号码长度是否为11位
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (phone.length() == 11) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 转换成待空格的电话号码
     *
     * @param text
     * @return
     */
    public static String replaceForPhone(String text) {
        String newStr = text.replace(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newStr.length(); i += 4) {
            if (i == 0) {
                sb.append(newStr.length() > 3 ? newStr.substring(0, 3) : newStr);
                i += 3;
                continue;
            } else if (i > 0) {
                sb.append(" ");
                if (i + 4 <= newStr.length()) {
                    sb.append(newStr.substring(i, i + 4));
                } else {
                    sb.append(newStr.substring(i, newStr.length()));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 转换成待分隔的卡号
     *
     * @param text
     * @return
     */
    public static String replaceForCardNo(String text) {
        String newStr = text.replace(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newStr.length(); i += 4) {
            if (i > 0) {
                sb.append(" ");
            }
            if (i + 4 <= newStr.length()) {
                sb.append(newStr.substring(i, i + 4));
            } else {
                sb.append(newStr.substring(i, newStr.length()));
            }
        }
        return sb.toString();
    }

    /**
     * 验证URL地址
     *
     * @param url
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * 是否有汉字
     *
     * @param ChineseCharactersStr
     * @return
     */
    public static boolean isContainsChinese(String ChineseCharactersStr) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(ChineseCharactersStr);
        if (m.find())
            return true;
        else
            return false;
    }

    /**
     * 获取利用反射获取类里面的值和名称
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                map.put(fieldName, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     *
     * @param str
     * @param strStart
     * @param strEnd
     * @return
     */
    public static String subString(String str, String strStart, String strEnd) {
        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);
        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            // "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
            return "";
        }
        if (strEndIndex < 0) {
            // "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
            return "";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    public static String toWhat(int what) {
        //A0001
        int length = String.valueOf(what).length();
        StringBuilder sb = new StringBuilder();
        sb.append("A");
        for (int i = 0; i < 4 - length; i++) {
            sb.append("0");
        }
        sb.append(what);
        String result = "==========" + sb.toString() + "==========";
        return result;
    }


    /**
     * 替换、过滤特殊字符
     *
     * @param str
     * @return
     * @throws
     */
    public static String StringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!");//替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}