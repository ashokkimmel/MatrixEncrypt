import org.junit.Assert;
import org.junit.Test;
public class MatrixTest {

    @Test
    public void firstTest() {

        Assert.assertEquals(1,1);
// test 1- Test non-square matrix
        try {
            Matrix test1matrix = new Matrix();
            int[] y = {1, 2};
            test1matrix.set(y);
            Assert.fail("Should have caused an exception: The matrix is not a square length");
        } catch (Exception e) {

        }
// test 2 - Test empty matrix
        try {
            Matrix test2matrix = new Matrix();
            int[] y = {};
            test2matrix.set(y);
            Assert.fail("Should have caused an exception: The matrix is not a square length");
        } catch (Exception e) {

        }
// test 3 - test matrix creation
        try {
            Matrix test3matrix = new Matrix();
            int[] y = {1, 2, 3, 4};
            test3matrix.set(y);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
// test 4 - test setinverse function
        try {
            Matrix test4matrix1 = new Matrix();
            Matrix test4matrix2 = new Matrix();
            test4matrix2.set(new int[] {1, 2, 3, 4});
            test4matrix1.set_inverse(test4matrix2);


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }


    }

}