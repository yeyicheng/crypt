import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;

public class OneTimePad {
	final static int SPACE = 0x20;
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

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

	static char asciiToChar(String s) {
		return (char) (int) Integer.valueOf(s);
	}
	
	static ArrayList<String> asciiToChar(ArrayList<String> l) {
		ArrayList<String> ll = new ArrayList<String>();
		for (String c : l){
			ll.add(""+(char)(int) Integer.valueOf(c));
		}
		System.out.println(ll);
		return ll;
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

	static ArrayList<String> hexToDec(String c, int op) {
		ArrayList<String> dec = new ArrayList<String>();
		ArrayList<String> hex = splitHex(c);
		for (String s : hex){
			dec.add(Integer.valueOf(s, 16).toString());
		}
		return dec;
	}
	
	static String hexToDec(String c) {
		return Integer.valueOf(c, 16).toString();
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

	static String binToHex(String s) {
		String out = "";
		String seg = "";
		for (int i = 0; i < s.length(); i++) {
			if (i % 4 != 3) {
				seg += s.charAt(i);
			} else {
				seg += s.charAt(i);
				if (seg.equals("0000"))
					out += "0";
				else if (seg.equals("0001"))
					out += "1";
				else if (seg.equals("0010"))
					out += "2";
				else if (seg.equals("0011"))
					out += "3";
				else if (seg.equals("0100"))
					out += "4";
				else if (seg.equals("0101"))
					out += "5";
				else if (seg.equals("0110"))
					out += "6";
				else if (seg.equals("0111"))
					out += "7";
				else if (seg.equals("1000"))
					out += "8";
				else if (seg.equals("1001"))
					out += "9";
				else if (seg.equals("1010"))
					out += "a";
				else if (seg.equals("1011"))
					out += "b";
				else if (seg.equals("1100"))
					out += "c";
				else if (seg.equals("1101"))
					out += "d";
				else if (seg.equals("1110"))
					out += "e";
				else if (seg.equals("1111"))
					out += "f";
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

	static String xor(String s1, String s2) {
		String out = "";
		s1 = hexToBin(s1);
		s2 = hexToBin(s2);
		if (s1.length() < s2.length()) {
			for (int i = 0; i < s1.length(); i++) {
				out += s1.charAt(i) ^ s2.charAt(i);
			}
			// out += s2.substring(s1.length(), s2.length());
		} else {
			for (int i = 0; i < s2.length(); i++) {
				out += s1.charAt(i) ^ s2.charAt(i);
			}
			// out += s1.substring(s2.length(), s1.length());
		}
		out = binToHex(out);
		return out;
	}

	static ArrayList<String> splitHex(String s) {
		String temp = "";
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++) {
			if (i % 2 == 1) {
				temp += s.charAt(i);
				list.add(temp);
				temp = "";
				continue;
			}
			temp += s.charAt(i);
		}
		if (temp.length() > 0)
			list.add(temp);
//		System.out.println(list);
		return list;
	}

	static ArrayList<Integer> findSpace(ArrayList<String> s1,
			ArrayList<String> s3, Integer num) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (s1.size() > s3.size()) {
			for (int i = 0; i < s3.size(); i++) {
				int a = Integer.valueOf(s1.get(i), 16);
				int b = Integer.valueOf(s3.get(i), 16);
				if (Math.abs(a - b) == SPACE) {
					list.add(i);
					System.out.println("i: " + i + ", a: " + a + ", b: " + b
							+ ", hex: " + s1.get(i));
				}
			}
		}
		if (OneTimePad.map.get(num) == null)
			OneTimePad.map.put(num, list);
		else {
			ArrayList a = OneTimePad.map.get(num);
			a.addAll(list);
			OneTimePad.map.put(num, a);
		}
		return list;
	}
	
	static ArrayList<Integer> findSpaceCandidate(String x, int first, int second) {
		ArrayList<String> list = asciiToChar(hexToDec(x, 1));
		ArrayList<String> spaces = new ArrayList<String>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		spaces.add("A");spaces.add("B");spaces.add("C");spaces.add("D");spaces.add("E");
		spaces.add("F");spaces.add("G");spaces.add("H");spaces.add("I");spaces.add("J");
		spaces.add("K");spaces.add("L");spaces.add("M");spaces.add("N");spaces.add("O");
		spaces.add("P");spaces.add("Q");spaces.add("R");spaces.add("S");spaces.add("T");
		spaces.add("U");spaces.add("V");spaces.add("W");spaces.add("X");spaces.add("Y");
		spaces.add("Z");spaces.add("a");spaces.add("b");spaces.add("c");spaces.add("d");
		spaces.add("e");spaces.add("f");spaces.add("g");spaces.add("h");spaces.add("i");spaces.add("j");
		spaces.add("k");spaces.add("l");spaces.add("m");spaces.add("n");spaces.add("o");
		spaces.add("p");spaces.add("q");spaces.add("r");spaces.add("s");spaces.add("t");
		spaces.add("u");spaces.add("v");spaces.add("w");spaces.add("x");spaces.add("y");
		spaces.add("z");spaces.add(" ");
		System.out.print("x" + first + "+x" + second + ": ");
		System.out.println(list);
		for (int i = 0; i < list.size(); i++){
			if (spaces.contains(list.get(i)))
				indices.add(i);
//				System.out.print(i + " ");
		}
//		System.out.println();
		return indices;
	}

	public static void main(String[] args) {
		for (int i = 65; i <= 90; i++){
			for (int j = 97; j <= 122; j++){
				String m1 = decToHex(i);
				String m2 = decToHex(j);
				String x = xor(m1, m2);
//				System.out.print((char)i + "+");
//				System.out.print((char)j + "=");
//				System.out.print(x + "-");
//				System.out.print(asciiToChar(hexToDec(x)) + " ");
			}
//			System.out.println();
		}
		
		String[] cipher = {
				"315c4eeaa8b5f8aaf9174145bf43e1784b8fa00dc71d885a804e5ee9fa40b16349c146fb778cdf2d3aff021dfff5b403b510d0d0455468aeb98622b137dae857553ccd8883a7bc37520e06e515d22c954eba5025b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301fbcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e",
				"234c02ecbbfbafa3ed18510abd11fa724fcda2018a1a8342cf064bbde548b12b07df44ba7191d9606ef4081ffde5ad46a5069d9f7f543bedb9c861bf29c7e205132eda9382b0bc2c5c4b45f919cf3a9f1cb74151f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab3cdadb8356f",
				"32510ba9a7b2bba9b8005d43a304b5714cc0bb0c8a34884dd91304b8ad40b62b07df44ba6e9d8a2368e51d04e0e7b207b70b9b8261112bacb6c866a232dfe257527dc29398f5f3251a0d47e503c66e935de81230b59b7afb5f41afa8d661cb",
				"32510ba9aab2a8a4fd06414fb517b5605cc0aa0dc91a8908c2064ba8ad5ea06a029056f47a8ad3306ef5021eafe1ac01a81197847a5c68a1b78769a37bc8f4575432c198ccb4ef63590256e305cd3a9544ee4160ead45aef520489e7da7d835402bca670bda8eb775200b8dabbba246b130f040d8ec6447e2c767f3d30ed81ea2e4c1404e1315a1010e7229be6636aaa",
				"3f561ba9adb4b6ebec54424ba317b564418fac0dd35f8c08d31a1fe9e24fe56808c213f17c81d9607cee021dafe1e001b21ade877a5e68bea88d61b93ac5ee0d562e8e9582f5ef375f0a4ae20ed86e935de81230b59b73fb4302cd95d770c65b40aaa065f2a5e33a5a0bb5dcaba43722130f042f8ec85b7c2070",
				"32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd2061bbde24eb76a19d84aba34d8de287be84d07e7e9a30ee714979c7e1123a8bd9822a33ecaf512472e8e8f8db3f9635c1949e640c621854eba0d79eccf52ff111284b4cc61d11902aebc66f2b2e436434eacc0aba938220b084800c2ca4e693522643573b2c4ce35050b0cf774201f0fe52ac9f26d71b6cf61a711cc229f77ace7aa88a2f19983122b11be87a59c355d25f8e4",
				"32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd90f1fa6ea5ba47b01c909ba7696cf606ef40c04afe1ac0aa8148dd066592ded9f8774b529c7ea125d298e8883f5e9305f4b44f915cb2bd05af51373fd9b4af511039fa2d96f83414aaaf261bda2e97b170fb5cce2a53e675c154c0d9681596934777e2275b381ce2e40582afe67650b13e72287ff2270abcf73bb028932836fbdecfecee0a3b894473c1bbeb6b4913a536ce4f9b13f1efff71ea313c8661dd9a4ce",
				"315c4eeaa8b5f8bffd11155ea506b56041c6a00c8a08854dd21a4bbde54ce56801d943ba708b8a3574f40c00fff9e00fa1439fd0654327a3bfc860b92f89ee04132ecb9298f5fd2d5e4b45e40ecc3b9d59e9417df7c95bba410e9aa2ca24c5474da2f276baa3ac325918b2daada43d6712150441c2e04f6565517f317da9d3",
				"271946f9bbb2aeadec111841a81abc300ecaa01bd8069d5cc91005e9fe4aad6e04d513e96d99de2569bc5e50eeeca709b50a8a987f4264edb6896fb537d0a716132ddc938fb0f836480e06ed0fcd6e9759f40462f9cf57f4564186a2c1778f1543efa270bda5e933421cbe88a4a52222190f471e9bd15f652b653b7071aec59a2705081ffe72651d08f822c9ed6d76e48b63ab15d0208573a7eef027",
				"466d06ece998b7a2fb1d464fed2ced7641ddaa3cc31c9941cf110abbf409ed39598005b3399ccfafb61d0315fca0a314be138a9f32503bedac8067f03adbf3575c3b8edc9ba7f537530541ab0f9f3cd04ff50d66f1d559ba520e89a2cb2a83" };

		int len = cipher.length;
		String x01 = xor(cipher[0], cipher[1]);
		String x02 = xor(cipher[0], cipher[2]);
		String x12 = xor(cipher[1], cipher[2]);
		String x2 = xor(cipher[2], cipher[3]);
		String x3 = xor(cipher[4], cipher[5]);
		String x4 = xor(cipher[6], cipher[7]);
		String x5 = xor(cipher[8], cipher[9]);
		System.out.println(findSpaceCandidate(x01, 0, 1));
		System.out.println(findSpaceCandidate(x02, 0, 2));
		findSpaceCandidate(x12, 1, 2);
		findSpaceCandidate(x2, 2, 3);
		findSpaceCandidate(x2, 2, 3);
		findSpaceCandidate(x3, 4, 5);
		findSpaceCandidate(x4, 6, 7);		
		findSpaceCandidate(x5, 8, 9);

//		System.out.println(asciiToChar(hexToDec(x1, 1)));
//		splitHex(x1);
		for (int i = 0; i < len; i++) {
//			findSpace(splitHex(cipher[i]), splitHex(xored), i);
//			System.out.println("---------------");
		}
	}
}
