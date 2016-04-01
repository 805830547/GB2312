package gb2312;

import java.util.ArrayList;

public class MumConvert {
	
	public String integerToBinary(String integer) {
		 char [] gbChars = integer.toCharArray();
		 StringBuilder resultString = new StringBuilder();
		 for (char gbChar : gbChars) {
			 //judge chat is space use ' ' rather than " "
			 if (gbChar !=' ') {
				 String aString = Integer.toBinaryString(Integer.parseInt(String.valueOf(gbChar)));
				 StringBuilder stringBuilder = new StringBuilder();
				 for (int i = 0; i < 4-aString.length(); i++) {
					 stringBuilder.append("0");
				 }
				 stringBuilder.append(aString);
				 resultString.append(stringBuilder.toString());
			 }
			 resultString.append(" ");
		 }
		return resultString.toString();
	}
	
	public String binaryToInteger(String binary) {
		char[] gbChars = binary.toCharArray();

		ArrayList<Integer> loc = new ArrayList<Integer>();
		loc.add(0);
		for (int i = 0; i < gbChars.length; i++) {
			// judge chat is space use ' ' rather than " "
			if ((gbChars[i] == ' ') && ((i + 2) < gbChars.length)&&(gbChars[i+1] != ' ')) {
				loc.add(i + 1);
			}
		}

		StringBuilder resultString = new StringBuilder();

		try {
			int cut = 0;
			for (int j = 0; j < loc.size(); j++) {
				String d = binary.substring(loc.get(j), loc.get(j)+4);
				 
				int temp = Integer.valueOf(d, 2);
				
				resultString.append(Integer.toString(temp));
				if (cut == 3) {
					cut = 0;
					resultString.append(" ");
				} else {
					cut++;
				}
			}
			return resultString.toString();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			return "只能输入数字组";
		}

	}

}
