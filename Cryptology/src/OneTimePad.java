import java.util.ArrayList;
import java.util.HashMap;

public class OneTimePad {
	final static int SPACE = 0x20;
	static HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	static HashMap<Integer, String> key = new HashMap<Integer, String>();

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
		for (String c : l) {
			ll.add("" + (char) (int) Integer.valueOf(c));
		}
		// System.out.println(ll);
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
		for (String s : hex) {
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
		// System.out.println(list);
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

	static HashMap<Integer, Boolean> toMap(int[] list) {
		HashMap<Integer, Boolean> out = new HashMap<Integer, Boolean>();
		for (int i = 0; i < 500; i++) {
			out.put(i, false);
		}
		for (int i = 0; i < list.length; i++) {
			out.put(list[i], true);
		}
		return out;
	}

	static String genSpaceStr(int[] spaces) {
		String out = "";
		HashMap<Integer, Boolean> in = toMap(spaces);
		for (int i = 0; i < 200; i++) {
			if (in.get(i)) {
				out += "20";
			} else {
				out += "00";
			}
		}
		return out;
	}

	static ArrayList<Integer> findSpaceCandidate(String x, int first, int second) {
		ArrayList<String> list = asciiToChar(hexToDec(x, 1));
		ArrayList<String> spaces = new ArrayList<String>();
		ArrayList<Integer> indices = new ArrayList<Integer>();
		spaces.add("A");
		spaces.add("B");
		spaces.add("C");
		spaces.add("D");
		spaces.add("E");
		spaces.add("F");
		spaces.add("G");
		spaces.add("H");
		spaces.add("I");
		spaces.add("J");
		spaces.add("K");
		spaces.add("L");
		spaces.add("M");
		spaces.add("N");
		spaces.add("O");
		spaces.add("P");
		spaces.add("Q");
		spaces.add("R");
		spaces.add("S");
		spaces.add("T");
		spaces.add("U");
		spaces.add("V");
		spaces.add("W");
		spaces.add("X");
		spaces.add("Y");
		spaces.add("Z");
		spaces.add("a");
		spaces.add("b");
		spaces.add("c");
		spaces.add("d");
		spaces.add("e");
		spaces.add("f");
		spaces.add("g");
		spaces.add("h");
		spaces.add("i");
		spaces.add("j");
		spaces.add("k");
		spaces.add("l");
		spaces.add("m");
		spaces.add("n");
		spaces.add("o");
		spaces.add("p");
		spaces.add("q");
		spaces.add("r");
		spaces.add("s");
		spaces.add("t");
		spaces.add("u");
		spaces.add("v");
		spaces.add("w");
		spaces.add("x");
		spaces.add("y");
		spaces.add("z");
		// spaces.add(" ");
		// System.out.print("x" + first + "+x" + second + ": ");
		// System.out.println(list);
		System.out.print("x" + first + "+x" + second + ": ");
		for (int i = 0; i < list.size(); i++) {
			if (spaces.contains(list.get(i)))
				// System.out.print(list.get(i));
				indices.add(i);
			else if ((int) list.get(i).charAt(0) == 0)
				System.out.print("double " + i + " ");
			// System.out.print(i + " ");
		}
		System.out.println(indices);
		// System.out.println();
		return indices;
	}

	public static HashMap<Integer, String> extractKey(String mix,
			int[] positions) {
		ArrayList<String> split = splitHex(mix);
		HashMap<Integer, Boolean> in = toMap(positions);
		for (int i = 0; i < split.size(); i++) {
			if (in.get(i)) {
				key.put(i, split.get(i));
			}
		}
		return key;
	}

	public static String buildKey() {
		String temp = "";
		for (int i = 0; i < 200; i++) {
			if (key.get(i) != null) {
				temp += key.get(i);
			} else {
				temp += "00";
			}
		}
		return temp;
	}

	public static void main(String[] args) {
		for (int i = 46; i <= 46; i++) {
			for (int j = 97; j <= 122; j++) {
				String m1 = decToHex(i);
				String m2 = decToHex(j);
				String x = xor(m1, m2);
				// System.out.print(asciiToChar(hexToDec(x)) + " ");
			}
			// System.out.println();
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
				"466d06ece998b7a2fb1d464fed2ced7641ddaa3cc31c9941cf110abbf409ed39598005b3399ccfafb61d0315fca0a314be138a9f32503bedac8067f03adbf3575c3b8edc9ba7f537530541ab0f9f3cd04ff50d66f1d559ba520e89a2cb2a83",
				"32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f904" };

		int len = cipher.length;
		// String x01 = xor(cipher[0], cipher[1]);
		// String x02 = xor(cipher[0], cipher[2]);
		// String x03 = xor(cipher[0], cipher[3]);
		// String x04 = xor(cipher[0], cipher[4]);
		// String x05 = xor(cipher[0], cipher[5]);
		// String x06 = xor(cipher[0], cipher[6]);
		// String x07 = xor(cipher[0], cipher[7]);
		// String x08 = xor(cipher[0], cipher[8]);
		// String x09 = xor(cipher[0], cipher[9]);
		// String x2 = xor(cipher[2], cipher[3]);
		// String x3 = xor(cipher[4], cipher[5]);
		// String x4 = xor(cipher[6], cipher[7]);
		// String x5 = xor(cipher[8], cipher[9]);
		// findSpaceCandidate(x01, 0, 1);
		// findSpaceCandidate(x02, 0, 2);
		// findSpaceCandidate(x03, 0, 3);
		// findSpaceCandidate(x04, 0, 4);
		// findSpaceCandidate(x05, 0, 5);
		// findSpaceCandidate(x06, 0, 6);
		// findSpaceCandidate(x07, 0, 7);
		// findSpaceCandidate(x08, 0, 8);
		// findSpaceCandidate(x09, 0, 9);

		int[] space_0 = { 2, 6, 13, 17, 24, 27, 32, 40, 51, 54, 58, 63, 74, 84,
				89, 91, 103, 106, 111 };
		String spaces_0 = genSpaceStr(space_0);
		String key_0 = xor(cipher[0], spaces_0);
		extractKey(key_0, space_0);

		// String x12 = xor(cipher[1], cipher[2]);
		// String x13 = xor(cipher[1], cipher[3]);
		// String x14 = xor(cipher[1], cipher[4]);
		// String x15 = xor(cipher[1], cipher[5]);
		// String x16 = xor(cipher[1], cipher[6]);
		// String x17 = xor(cipher[1], cipher[7]);
		// String x18 = xor(cipher[1], cipher[8]);
		// String x19 = xor(cipher[1], cipher[9]);
		// findSpaceCandidate(x12, 1, 2);
		// findSpaceCandidate(x13, 1, 3);
		// findSpaceCandidate(x14, 1, 4);
		// findSpaceCandidate(x15, 1, 5);
		// findSpaceCandidate(x16, 1, 6);
		// findSpaceCandidate(x17, 1, 7);
		// findSpaceCandidate(x18, 1, 8);
		// findSpaceCandidate(x19, 1, 9);

		int[] space_1 = { 5, 11, 26, 39, 47, 55, 64, 70, 73, 80, 82, 93 };
		String spaces_1 = genSpaceStr(space_1);
		String key_1 = xor(cipher[1], spaces_1);
		extractKey(key_1, space_1);

		// String x23 = xor(cipher[2], cipher[3]);
		// String x24 = xor(cipher[2], cipher[4]);
		// String x25 = xor(cipher[2], cipher[5]);
		// String x26 = xor(cipher[2], cipher[6]);
		// String x27 = xor(cipher[2], cipher[7]);
		// String x28 = xor(cipher[2], cipher[8]);
		// String x29 = xor(cipher[2], cipher[9]);
		// findSpaceCandidate(x23, 2, 3);
		// findSpaceCandidate(x24, 2, 4);
		// findSpaceCandidate(x25, 2, 5);
		// findSpaceCandidate(x26, 2, 6);
		// findSpaceCandidate(x27, 2, 7);
		// findSpaceCandidate(x28, 2, 8);
		// findSpaceCandidate(x29, 2, 9);
		int[] space_2 = { 8, 20, 31, 35, 38, 53, 57, 63, 65, 69, 72, 78, 83,
				89, 94 };
		String spaces_2 = genSpaceStr(space_2);
		String key_2 = xor(cipher[2], spaces_2);
		extractKey(key_2, space_2);

		// String x34 = xor(cipher[3], cipher[4]);
		// String x35 = xor(cipher[3], cipher[5]);
		// String x36 = xor(cipher[3], cipher[6]);
		// String x37 = xor(cipher[3], cipher[7]);
		// String x38 = xor(cipher[3], cipher[8]);
		// String x39 = xor(cipher[3], cipher[9]);
		// findSpaceCandidate(x34, 3, 4);
		// findSpaceCandidate(x35, 3, 5);
		// findSpaceCandidate(x36, 3, 6);
		// findSpaceCandidate(x37, 3, 7);
		// findSpaceCandidate(x38, 3, 8);
		// findSpaceCandidate(x39, 3, 9);

		int[] space_3 = { 26, 28, 33, 44, 54, 60, 63, 68, 71, 82, 91, 94, 96,
				103 };
		String spaces_3 = genSpaceStr(space_3);
		String key_3 = xor(cipher[3], spaces_3);
		extractKey(key_3, space_3);

		// String x45 = xor(cipher[4], cipher[5]);
		// String x46 = xor(cipher[4], cipher[6]);
		// String x47 = xor(cipher[4], cipher[7]);
		// String x48 = xor(cipher[4], cipher[8]);
		// String x49 = xor(cipher[4], cipher[9]);
		// findSpaceCandidate(x45, 4, 5);
		// findSpaceCandidate(x46, 4, 6);
		// findSpaceCandidate(x47, 4, 7);
		// findSpaceCandidate(x48, 4, 8);
		// findSpaceCandidate(x49, 4, 9);

		int[] space_4 = { 3, 14, 17, 21, 23, 27, 30, 34, 39, 44, 46, 50, 54,
				69, 78, 83, 85, 90, 111, 114 };
		String spaces_4 = genSpaceStr(space_4);
		String key_4 = xor(cipher[4], spaces_4);
		extractKey(key_4, space_4);

		// String x56 = xor(cipher[5], cipher[6]);
		// String x57 = xor(cipher[5], cipher[7]);
		// String x58 = xor(cipher[5], cipher[8]);
		// String x59 = xor(cipher[5], cipher[9]);
		// findSpaceCandidate(x56, 5, 6);
		// findSpaceCandidate(x57, 5, 7);
		// findSpaceCandidate(x58, 5, 8);
		// findSpaceCandidate(x59, 5, 9);

		int[] space_5 = { 4, 9, 37, 42, 48, 53, 76, 81, 100, 105, 116 };
		String spaces_5 = genSpaceStr(space_5);
		String key_5 = xor(cipher[5], spaces_5);
		extractKey(key_5, space_5);

		// String x67 = xor(cipher[6], cipher[7]);
		// String x68 = xor(cipher[6], cipher[8]);
		// String x69 = xor(cipher[6], cipher[9]);
		// findSpaceCandidate(x67, 6, 7);
		// findSpaceCandidate(x68, 6, 8);
		// findSpaceCandidate(x69, 6, 9);
		int[] space_6 = { 5, 9, 13, 19, 22, 39, 44, 51, 55, 69, 73, 79, 85, 88,
				102 };
		String spaces_6 = genSpaceStr(space_6);
		String key_6 = xor(cipher[6], spaces_6);
		extractKey(key_6, space_6);

		// String x78 = xor(cipher[7], cipher[8]);
		// String x79 = xor(cipher[7], cipher[9]);
		// findSpaceCandidate(x78, 7, 8);
		// findSpaceCandidate(x79, 7, 9);

		int[] space_7 = { 2, 6, 10, 14, 20, 26, 30, 38, 46, 49, 51, 57, 61, 69,
				73, 82, 87, 93, 114 };
		String spaces_7 = genSpaceStr(space_7);
		String key_7 = xor(cipher[7], spaces_7);
		extractKey(key_7, space_7);

		// String x89 = xor(cipher[8], cipher[9]);
		// findSpaceCandidate(x89, 8, 9);

		int[] space_8 = { 1, 15, 16, 27, 41, 43, 55, 62, 64, 78, 89, 102 };
		String spaces_8 = genSpaceStr(space_8);
		String key_8 = xor(cipher[8], spaces_8);
		extractKey(key_8, space_8);

		int[] space_9 = { 0, 4, 12, 29, 36, 45, 52, 59, 66, 67, 75, 77 };
		String spaces_9 = genSpaceStr(space_9);
		String key_9 = xor(cipher[9], spaces_9);
		extractKey(key_9, space_9);

		String test = buildKey();
		System.out.println(test);
		String see = xor(test, cipher[10]);
		System.out.println(asciiToChar(hexToDec(see, 1)));
	}
}
