public class Encryption {
    public static void main(String[] args) {
        Matrix mymatrix = new Matrix();
        try {
            mymatrix.set(new int[] {1,2,3,4,5,6,77,8,9,105,11,12,13,14,15,120});
//            mymatrix.set(new int[] {1,2,2,3});
        }catch (Exception e) {
            e.printStackTrace();
        }
        char[] mylst = {43,53};
        double d = (double) 389283 /3892310;
        int i = 3892310 * 4;
        System.out.println(i*d);
        try {
            int[] encryptedlst = mymatrix.multiply(mylst);
            mymatrix.set_inverse(mymatrix);
            int[] decryptedlst = mymatrix.multiply(encryptedlst);
//            Matrix.printArray(decryptedlst);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}