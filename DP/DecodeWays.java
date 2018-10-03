import com.sun.deploy.panel.ITreeNode;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DecodeWays {
    int count = 0;
    String string = null;

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int len = s.length();

        int[] dp = new int[len + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= len; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            int temp = Integer.parseInt(s.substring(i - 2, i));
            if (temp > 9 && temp < 27) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }

    /**
     *
     * 楼下两个怕是假的dp……
     *
     */

    public void dp(String val, String str) {
        if (val.length() > 0 && (val.charAt(0) == '0' || Integer.parseInt(val) > 26)) {
            return;
        }

        if (str.length() == 0) {
            count++;
            return;
        }

        dp(str.substring(0, 1), str.substring(1));
        if (str.length() >= 2 && str.charAt(0) <= '2') {
            dp(str.substring(0, 2), str.substring(2));
        }
    }

    public void dp1(int ptr) {
        if (ptr >= string.length() || string.charAt(ptr) == '0') {
            if (ptr == string.length()) {
                count++;
            }
            return;
        }


        dp1(ptr + 1);
        if (ptr + 2 <= string.length() && Integer.parseInt(string.substring(ptr, ptr + 2)) <= 26) {
            dp1(ptr + 2);
        }
    }



    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        long curr = System.currentTimeMillis();
//        int res = decodeWays.numDecodings("1787897759966261825913315262377298132516969578441236833255596967132573482281598412163216914566534565");
        int res = decodeWays.numDecodings("102");
        long last = System.currentTimeMillis();
        System.out.println("res = " + res);
        System.out.println("time = " + (last - curr));
    }
}
