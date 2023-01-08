package cn.unminded.rtool.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

/**
 * 安全工具类，当前仅支持AES加解密操作
 */
public final class SecurityUtil {

    private SecurityUtil() {
        throw new UnsupportedOperationException();
    }

    public static final String UTF_8 = StandardCharsets.UTF_8.displayName();
    /**
     * 加密算法
     */
    private static final String AES = "AES";
    /**
     * 设置加密密码处理长度。
     */
    private static final int PWD_SIZE = 16;

    // SecureRandom.getInstanceStrong()
    private static final Random RANDOM = new Random();

    /**
     * 加密，返回字节数组
     * @param text
     * @param password
     * @return
     */
    public static byte[] encryptByAES(byte[] text, String password) {
        try {
            Cipher cipher = getCipher(password, AES, Cipher.ENCRYPT_MODE);
            return cipher.doFinal(text);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            // ignore;
        }

        return null;
    }

    /**
     * 解密，返回字节数组
     * @param text
     * @param password
     * @return
     */
    public static byte[] decryptByAES(byte[] text, String password) {
        try {
            Cipher cipher = getCipher(password, AES, Cipher.DECRYPT_MODE);
            return cipher.doFinal(text);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            // ignore;
        }

        return null;
    }

    /**
     * 对密文进行base64解码后再进行AES解密
     * @param text
     * @param password
     * @return
     */
    public static byte[] decryptByBase64AES(String text, String password) {
        byte[] decodeByBase64 = decodeByBase64(text);
        return decryptByAES(decodeByBase64, password);
    }

    /**
     * 先对明文进行AES加密，然后再进行base64编码
     * @param text
     * @param password
     * @return
     */
    public static String encryptByAESBase64(String text, String password) {
        byte[] encryptByAES = null;
        try {
            encryptByAES = encryptByAES(text.getBytes(UTF_8), password);
        } catch (UnsupportedEncodingException e) {
            // ignore;
        }
        if (Objects.isNull(encryptByAES)) {
            return null;
        }

        return encodeByBase64(encryptByAES);
    }

    /**
     * 获取密钥处理类
     * @param password 密码
     * @param algorithm 密码工作算法
     * @param mode 密码工作模式 加密还是解密{@link Cipher#ENCRYPT_MODE}{@link Cipher#DECRYPT_MODE}
     * @return
     */
    public static Cipher getCipher(String password, String algorithm, int mode) {
        Objects.requireNonNull(password);
        Objects.requireNonNull(algorithm);
        if (Cipher.ENCRYPT_MODE != mode && Cipher.DECRYPT_MODE != mode) {
            throw new IllegalArgumentException(String.format("输入的密码工作模式mode: %s不符合定义", mode));
        }

        Cipher cipher = null;
        try {
            // 1 获取密钥
            SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(UTF_8), algorithm);
            // 2 获取Cipher实例
            cipher = Cipher.getInstance(algorithm);
            // 查看数据块位数 默认为16（byte） * 8 =128 bit
//            System.out.println("数据块位数(byte)：" + cipher.getBlockSize());
            // 3 初始化Cipher实例。设置执行模式以及密钥
            cipher.init(mode, keySpec);
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            // ignore;
        }

        return cipher;
    }

    /**
     * 对 BASE64输出的密文进行BASE64 decodebuffer 得到密文字节数组
     * @param text 已经进行过base64加密的文本
     * @return 经base64解密后的字节数组
     */
    public static byte[] decodeByBase64(String text) {
        Objects.requireNonNull(text);
        return Base64.getDecoder().decode(text);
    }

    /**
     * 字节数组进行BASE64 encoder 得到 BASE6输出的密文
     * @param text 已经进行过加密的字节数组
     * @return 经base64编码后的字符串
     */
    public static String encodeByBase64(byte[] text) {
        Objects.requireNonNull(text);
        return Base64.getEncoder().encodeToString(text);
    }

    /**
     * 字节数组转成16进制字符串
     * @param bytes
     * @return
     */
    public static String byte2hex(byte[] bytes) { // 一个字节的数，
        StringBuffer sb = new StringBuffer(bytes.length * 2);
        String tmp = "";
        for (int n = 0; n < bytes.length; n++) {
            // 整数转成十六进制表示
            tmp = (java.lang.Integer.toHexString(bytes[n] & 0XFF));
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase(); // 转成大写
    }

    /**
     * 将hex字符串转换成字节数组
     * @param str
     * @return
     */
    private static byte[] hex2byte(String str) {
        if (str == null || str.length() < 2) {
            return new byte[0];
        }
        str = str.toLowerCase();
        int l = str.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = str.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }

        return result;
    }

    /**
     * 生成16位密钥
     * @return
     */
    public static String generatorKey() {
        int length = PWD_SIZE;
        int min = 32;
        int max = 126;
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int ch = min + RANDOM.nextInt(max - min);
            sb.append((char) ch);
        }

        return sb.toString();
    }

    /**
     * 生成密钥
     * @param args
     */
    public static void main(String[] args) {
        String key = generatorKey();
        System.out.println(key);
    }

}
