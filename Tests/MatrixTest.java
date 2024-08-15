import org.junit.Assert;
import org.junit.Test;
public class MatrixTest {

    @Test
    public void firstTest() {

        Assert.assertEquals(1,1);
// test 1- Test non-square matrix
        try {
            int[] y = {1, 2};
            Matrix test1matrix = new Matrix(y);
            Assert.fail("Should have caused an exception: The matrix is not a square length");
        } catch (Exception e) {

        }
// test 2 - Test empty matrix
        try {
            int[] y = {};
            Matrix test2matrix = new Matrix(y);
            Assert.fail("Should have caused an exception: The matrix is not a square length");
        } catch (Exception e) {

        }
// test 3 - test matrix creation
        try {
            int[] y = {1, 2, 3, 4};
            Matrix test3matrix = new Matrix(y);

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
// test 4 - test encrypt and decrypt
        try {

            Matrix test4matrix1 = new Matrix(new int[] {1, 2, 3, 4});


        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }


    }

}