package cn.ich.firstcodeversiontwodemo.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HexUtil {

    private static final char[] HEX_CHAR_TABLE = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    private static final Map<Character, Byte> MAP = new HashMap<>();

    static {
        for (int i = 0; i < HEX_CHAR_TABLE.length; i++) {
            char c = HEX_CHAR_TABLE[i];
            MAP.put(c, (byte) i);
        }
    }

    /**
     * 将byte数组转化为字符串
     * @param array
     * @return
     */
    public static String toHexString(byte[] array) {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append(HEX_CHAR_TABLE[(b & 0xf0) >> 4]);
            sb.append(HEX_CHAR_TABLE[b & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * 将字符串转成byte数组
     * @param hexString 传入的HEX数据例如 1A 2B 3C
     * @return
     */
    public static byte[] hexStrToBinaryStr(String hexString) {
        if (TextUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        int index = 0;
        byte[] bytes = new byte[len / 2];
        while (index < len) {
            String sub = hexString.substring(index, index + 2);
            bytes[index/2] = (byte)Integer.parseInt(sub,16);
            index += 2;
        }
        return bytes;
    }

    /**
     * 拆分数据
     * @param hex 传入的HEX数据
     * @param splitNum  选择几个数组为一组
     * @return
     */
    public static List<String> splitGetData(String hex,int splitNum){
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < hex.length(); i += splitNum){
            stringList.add(hex.substring(i,i + splitNum));
        }
        return stringList;
    }
    /**
     * 16求和
     * @param hexData
     * @return
     */
    public static String hexSum(String hexData) {
        if (hexData == null || hexData.equals("")) {
            return "00";
        }
        hexData = hexData.replaceAll(" ", "");
        int total = 0;
        int len = hexData.length();
        if (len % 2 != 0) {
            return "00";
        }
        int num = 0;
        while (num < len) {
            String s = hexData.substring(num, num + 2);
            total += Integer.parseInt(s, 16);
            num = num + 2;
        }
        return hexInt(total);
    }

    private static String hexInt(int total) {
        int a = total / 256;
        int b = total % 256;
        if (a > 255) {
            return hexInt(a) + format(b);
        }
        return format(a) + format(b);
    }

    private static String format(int hex) {
        String hexa = Integer.toHexString(hex);
        int len = hexa.length();
        if (len < 2) {
            hexa = "0" + hexa;
        }
        return hexa;
    }
}
