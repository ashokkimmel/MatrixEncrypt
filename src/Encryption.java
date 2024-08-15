public class Encryption {
    public static void main(String[] args) {
        char[] mylst = "I'm not surprised that I had to tell you the answer.".toCharArray();
        Matrix.printArray(mylst);
        try {
            Matrix mymatrix = new Matrix(new int[] {4, 2, 3, 7, 4, 5, 6, 8, 6, 5, 4, 9, 8, 4, 2, 4});
            char[] encryptedlst = mymatrix.encrypt(mylst);
            System.out.println(encryptedlst);
            char[] decryptedlst = mymatrix.decrypt(encryptedlst);
            System.out.println(decryptedlst);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}