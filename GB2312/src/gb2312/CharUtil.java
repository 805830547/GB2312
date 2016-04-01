package gb2312;

public class CharUtil { 
    // 根据Unicode编码完美的判断中文汉字和符号 
    private static boolean isChinese(char c) { 
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c); 
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ) { 
            return true; 
        } 
        return false; 
    } 

    // 完整的判断中文汉字和符号 
    public static boolean isChinese(String strName) { 
        char[] ch = strName.toCharArray(); 
        for (int i = 0; i < ch.length; i++) { 
            char c = ch[i]; 
            if (isChinese(c)) { 
                continue; 
            } else {
            	return false;
			}
        } 
        return true; 
    } 
} 
