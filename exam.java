public class exam {
	
	public String filterAndConcat(String s1, String s2, String illegalChars, int minFreq) {
		String s = "";
		String illegalChar = "";
		try {
			for (int i = 0; i<illegalChars.length(); i++) {
				illegalChar = s1.substring(i);
				if (s1.contains(illegalChar) || s2.contains(illegalChar)){
					break;
				}
			}
		} catch(IllegalCharacterException e) {
			System.out.format("IllegalCharacterException: '%s' found in input string\n", illegalChar);
			return "";
		}finally{
			
		}
		return s;
	}

}
