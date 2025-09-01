public class Message {

    public static void main(String[] args) {
        Matrix mymatrix =  new Matrix();
        int[] mylist = {1,2,3,4};
        try {
            mymatrix.set(mylist);
        }catch (Exception e) {

        }
        int[] newlist;
        try {
            newlist = mymatrix.multiply(new int[] {1});
        } catch (Exception e) {
            e.printStackTrace();
            newlist = new int[] {};
        }
    //    System.out.println(newlist);
    }
}
