public class OneTimePad {

	static String stringToAscii(String s) {
		String out = "";
		for (int i = 0; i < s.length(); i++) {
			out += charToAscii(s.charAt(i));
			out += " ";
		}
		return out;
	}

	static int charToAscii(char c) {
		return (int) c;
	}

	static String decToHex(String s) {
		String out = "";
		String[] in = s.split(" ");
		for (int i = 0; i < in.length; i++) {
			out += decToHex(Integer.valueOf(in[i]));
		}
		return out;
	}

	static String decToHex(int c) {
		int q = 1, r = c;
		String hex = "";
		while (q != 0) {
			q = c / 16;
			r = c % 16;
			c = q;
			switch (r) {
			case 10:
				hex = "a" + hex;
				break;
			case 11:
				hex = "b" + hex;
				break;
			case 12:
				hex = "c" + hex;
				break;
			case 13:
				hex = "d" + hex;
				break;
			case 14:
				hex = "e" + hex;
				break;
			case 15:
				hex = "f" + hex;
				break;
			default:
				hex = r + hex;
			}
		}
		return hex;
	}

	static String hexToBin(String s) {
		String out = "";
		for (int i = 0; i < s.length(); i++) {
			out += hexToBin(s.charAt(i));
		}
		return out;
	}

	static String hexToBin(char c) {
		switch (c) {
		case '1':
			return "0001";
		case '2':
			return "0010";
		case '3':
			return "0011";
		case '4':
			return "0100";
		case '5':
			return "0101";
		case '6':
			return "0110";
		case '7':
			return "0111";
		case '8':
			return "1000";
		case '9':
			return "1001";
		case 'a':
			return "1010";
		case 'b':
			return "1011";
		case 'c':
			return "1100";
		case 'd':
			return "1101";
		case 'e':
			return "1110";
		case 'f':
			return "1111";
		default:
			return "0000";
		}
	}

	static String binToHex(String s){
		String out = "";
		String seg = "";
		for (int i = 0; i < s.length(); i++){
			if (i % 4 != 3){
				seg += s.charAt(i);
			} else {
				seg += s.charAt(i);
				if (seg.equals("0000")) out += "0";
				else if (seg.equals("0001")) out += "1";
				else if (seg.equals("0010")) out += "2";
				else if (seg.equals("0011")) out += "3";
				else if (seg.equals("0100")) out += "4";
				else if (seg.equals("0101")) out += "5";
				else if (seg.equals("0110")) out += "6";
				else if (seg.equals("0111")) out += "7";
				else if (seg.equals("1000")) out += "8";
				else if (seg.equals("1001")) out += "9";
				else if (seg.equals("1010")) out += "a";
				else if (seg.equals("1011")) out += "b";
				else if (seg.equals("1100")) out += "c";
				else if (seg.equals("1101")) out += "d";
				else if (seg.equals("1110")) out += "e";
				else if (seg.equals("1111")) out += "f";
				seg = "";
			}
		}
		return out;
	}
	
	static String decrypt(String m, String c) {
		if (m.length() != c.length())
			return "";
		String out = "";
		for (int i = 0; i < m.length(); i++) {
			out += m.charAt(i) ^ c.charAt(i);
		}
		return out;
	}
	
	static String encrypt(String m, String key) {
		if (m.length() != key.length())
			return "";
		String out = "";
		for (int i = 0; i < m.length(); i++) {
			out += m.charAt(i) ^ key.charAt(i);
		}
		return out;
	}
	
	public static void main(String[] args) {
		String m = hexToBin(decToHex(stringToAscii("attack at dawn")));
		String c = hexToBin("09e1c5f70a65ac519458e7e53f36");
		String key = decrypt(m, c);
		String m1 = hexToBin(decToHex(stringToAscii("attack at dusk")));
		System.out.println(m);
		System.out.println(c);
		System.out.println(key);
		System.out.println(m1);
		System.out.println(binToHex(encrypt(m1, key)));
		System.out.println(binToHex(c));
	}
}
