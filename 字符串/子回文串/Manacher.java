public class Manacher {

    /**
     * 找最长子回文串
     * 讲道理，数回文串的那个代码更漂亮一点
     */

    int maxid=0,maxr=1;
    public String longestPalindrome(String s) {
        if(s.length()<=1)return s;

        String str = "#"+String.join("#", s.split(""))+"#";
        manacher(str);
        return str.substring(maxid-maxr+1,maxid+maxr).replaceAll("#","");
    }

    public void manacher(String s) {
        char[] chars = s.toCharArray();
        int[] r = new int[chars.length];

        int center=0,bound=0;
        r[0]=1;

        for (int i = 1; i < chars.length; i++) {
            if (i >= bound) {
                for (int j = 0; i + j < chars.length && i - j >= 0; j++) {
                    if (chars[i - j] == chars[i + j]) {
                        r[i]++;
                    } else break;
                }

                if (i + r[i] - 1 > bound) {
                    center = i;
                    bound = i + r[i] - 1;
                }

            } else {
                if (bound - i + 1 > r[2 * center - i]) {
                    r[i] = r[2 * center - i];
                } else {
                    r[i] = bound - i + 1;
                    for (int j = bound - i + 1; i + j < chars.length && i - j >= 0; j++) {
                        if (chars[i - j] == chars[i + j]) {
                            r[i]++;
                        } else break;
                    }
                    if (i + r[i] - 1 > bound) {
                        center = i;
                        bound = i + r[i] - 1;
                    }
                }

            }
            if (r[i] > maxr) {
                maxr = r[i];
                maxid = i;
            }
        }
    }
}
