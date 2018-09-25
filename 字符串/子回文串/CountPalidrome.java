public class CountPalidrome {
    /**
     * 数子回文串的个数
     */
    public int[] manacher(String s) {
        char[] chars = new char[2 * s.length() + 1];
        int[] len = new int[2 * s.length() + 1];
        chars[0] = '#';
        int p=1;
        for (char c : s.toCharArray()) {
            chars[p++] = c;
            chars[p++] = '#';
        }

        len[0] = 1;
        int id=0,max=0;
        for (int i = 1; i < chars.length; i++) {
            if (i >= max) {
                for (int j = 0; i + j < chars.length && i - j >= 0; j++) {
                    if (chars[i + j] == chars[i - j]) {
                        len[i]++;
                    } else break;
                }
            } else {
                int i1 = 2 * id - i;
                if (i + len[i1] - 1 < max) {
                    len[i] = len[i1];
                } else {
                    len[i] = max - i + 1;
                    for (int j = max-i+1; i + j < chars.length && i - j >= 0; j++) {
                        if (chars[i - j] == chars[i + j]) {
                            len[i]++;
                        } else break;
                    }
                }

            }
            if (i + len[i] - 1 > max) {
                max = i + len[i] - 1;
                id = i;
            }
        }
        return len;
    }

    public int calculate(int[] len) {
        int count = 0;
        for (int i : len) {
            count += i / 2;
        }
        return count;
    }

    public static void main(String[] args) {
        CountPalidrome countPalidrome = new CountPalidrome();
        int[] res = countPalidrome.manacher("aabbac");
        int count = countPalidrome.calculate(res);
        System.out.print(count);
    }
}
