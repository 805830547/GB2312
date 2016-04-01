package gb2312;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
/**
 * 
 * @author LY
 *
 *两个字节来存储汉字：第一个字节存储区号，第二字节存储每个区上的位号
 *1.区号和位号都必须加上20H(十六进制)，即十进制32；
 *2.GB2312为了标识一个字节存储到底是汉字中某一个字节还是ASCII字符，
 *用数字符号作为判断，负号则做为汉字
 *每个字节都加128，即将两个字节的最高位由0改1，其余7位不变(取余)
 */
public class GB2312 {

	public final static int DRI16 = 16;
	public final static int DRI55 = 55;
	public final static int DRI56 = 56;
	public final static int DRI87 = 87;
	
	public final static int MIN_POS = 1;
	public final static int MAX_POS = 94;
	/**
	 * show GB2312 Code table
	 */
	public void showGB2312String() {
		byte [] gbBytes = new byte [2];
		for (int d = DRI16; d <= DRI55; d++) {
			int high_pos = d + 32;
			high_pos += 128; 
			
			gbBytes[0] = (byte) high_pos;
			for (int p = MIN_POS; p <= MAX_POS; p++) {
				int low_pos = p + 32;
				low_pos += 128;
				gbBytes[1] = (byte) low_pos;
				
				String ccString = null;
				try {
					ccString = new String(gbBytes, "GB2312");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (!(d == DRI55 && p >= 90)) {
					System.out.println("第"+(d*100+p)+"个汉字是：");
					System.out.println(ccString);
				}
			}
		}
	}
	/**
	 * convert Code(****) to Chinese character
	 * @param code
	 */
//	public void convertGB2312CodeToGB2312String(int gb2312code) {
//		String ccString = null;
//		byte [] gbBytes = new byte [2];
//		gbBytes[0] = (byte) (gb2312code/100+32+128);
//		gbBytes[1] = (byte) (gb2312code%100+32+128);
//		try {
//			ccString = new String(gbBytes, "GB2312");
//			System.out.println(ccString);
//		} catch (Throwable e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public String convertGB2312CodeToGB2312String(String gb2312code) {
		char[] gbChars = gb2312code.toCharArray();
		ArrayList<Integer> loc = new ArrayList<Integer>();
		loc.add(0);
		for (int i = 0; i < gbChars.length; i++) {
			// judge chat is space use ' ' rather than " "
			if ((gbChars[i] == ' ') && ((i + 1) < gbChars.length)) {
				loc.add(i + 1);
			}
		}
		StringBuilder resultString = new StringBuilder();
		try {
			for (int j = 0; j < loc.size(); j++) {
				int k = loc.get(j);
				int high = Integer.parseInt(String.valueOf(gbChars[k])) * 10
						+ Integer.parseInt(String.valueOf(gbChars[k + 1]));
				int low = Integer.parseInt(String.valueOf(gbChars[k + 2])) * 10
						+ Integer.parseInt(String.valueOf(gbChars[k + 3]));

				String ccString = null;
				byte[] gbBytes = new byte[2];
				gbBytes[0] = (byte) (high + 32 + 128);
				gbBytes[1] = (byte) (low + 32 + 128);
				ccString = new String(gbBytes, "GB2312");
				resultString.append(ccString);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			return "只能输入数字组";
		}

		return resultString.toString();
	}
	
	/**
	 * convert Chinese string to GB2312Code
	 * @param gb2312String
	 */
	public String convertGB2312StringToGB2312Code(String gb2312String) {
		byte [] gbBytes = new byte [gb2312String.length()*2];
		StringBuilder stringBuilder = new StringBuilder();
		try {
			gbBytes = gb2312String.getBytes("GB2312");
			
			for (int len = 0; len < gb2312String.length(); len++) {
				int high = (int) (gbBytes[len*2]-32+128);
				int low = (int) (gbBytes[len*2+1]-32+128);
				stringBuilder.append(String.valueOf(high*100+low));
				stringBuilder.append(" ");
			}
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append("呃呃呃！居然出现了BUG，这样的话就很尴尬了。【老脸一红】");
		}
		return stringBuilder.toString();
		
	}
}
