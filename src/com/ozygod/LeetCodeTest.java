package com.ozygod;

public class LeetCodeTest {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }

    public static void main(String[] args) {
//        int[] demo = new int[]{1,1,1,2,2,2,3};
//        test001 test001 = new test001();
//        int len = test001.removeDuplicates(demo);
//        System.out.println("size:"+len);
////        demo[1]=demo[2];
//        for (int i =0; i < len; i++) {
//            System.out.println("content:" + demo[i]);
//        }
//        System.out.println("=================");
//        for (int i =0; i < demo.length; i++) {
//            System.out.println("content:" + demo[i]);
//        }

        String s = "A man, a plan, a canal: Panama";
        System.out.println(LeetCodeTest.isPalindrome(s));

        StringBuffer ss = new StringBuffer();
        ss.append("-42");
        System.out.println(LeetCodeTest.myAtoi(ss.toString()));
        int tmp = Integer.parseInt("-912834723");
        System.out.println(tmp < Integer.MIN_VALUE/10);
    }

    public static int myAtoi(String s) {
        if (s==null || s.length() == 0 || " ".equals(s)) return 0;
        StringBuffer buf = new StringBuffer();
        String sign = "";
        boolean flag = false;
        int limit = -Integer.MAX_VALUE;
        for(int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if(buf.length() == 0 && key == ' ') {
                continue;
            }
            if(buf.length() == 0 && (key == '-' || key == '+')) {
                if(sign.length() > 0) {
                    return 0;
                }
                sign = String.valueOf(key);
                if (key == '-') {
                    flag = true;
                    limit = Integer.MIN_VALUE;
                }
                continue;
            }
            int digit = Character.digit(key, 10);
            if(buf.length() == 0 && digit < 0) {
                return 0;
            }
            if (digit < 0) {
                break;
            }
            int result = buf.length() == 0 ? 0 : Integer.parseInt(buf.toString());
            result *= -1;
            if(result < limit/10) {
                return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result *= 10;
            if(result < limit + digit) {
                return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            buf.append(key);
        }
        if (buf.length() == 0) {
            return 0;
        }
        return Integer.parseInt(sign+buf.toString());
    }

    public static boolean isPalindrome(String s) {
        if(s == "") return true;
        s = s.toLowerCase();
        int lt = 0, gt = s.length()-1;
        while(lt < gt) {
            while(!Character.isLetterOrDigit(s.charAt(lt))) if(++lt>gt) break;
            while(!Character.isLetterOrDigit(s.charAt(gt))) if(--gt<lt) break;
            if(lt>gt) break;
            if(s.charAt(lt++) != s.charAt(gt--)) {
                return false;
            }
        }
        return true;
    }
}
