public class PalindromicSubsequence {
    public int dp(String s) {
        /**
         * 求最长回文子序列
         * dp
         *
         * m[i][j]=the length of longest palindromic subsequence from position i to j
         *
         * m[i][j]=1 if i==j; 2+m[i+1][j-1] if [i]==[j]; max(m[i][j-1],m[i+1][j]) if [i]!=[j]; 0 if i>j
         * max一定是m[0][s.length()-1]，为什么？考虑定义！
         */
        int max = 1;
        int[][] m = new int[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            m[i][i] = 1;
        }

        char[] chars = s.toCharArray();
        //矩阵的求解顺序要相反，至于为什么，试一下便知
        for (int i = s.length() - 2; i >= 0; i--) {
            for (int j = i + 1; j < s.length(); j++) {
                m[i][j] = chars[i] == chars[j] ? 2 + m[i + 1][j - 1] : Math.max(m[i + 1][j], m[i][j - 1]);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
        return m[0][s.length()-1];
    }

    public static void main(String[] args) {
        PalindromicSubsequence palindromicSubsequence = new PalindromicSubsequence();
        System.out.println(palindromicSubsequence.dp("bbcbab"));
    }
}
