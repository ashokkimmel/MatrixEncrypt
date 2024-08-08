import java.lang.Exception;
import static java.lang.Math.sqrt;
import static java.lang.Math.round;


public class Matrix {
    public static void printArray(double[] message) {
        boolean keep_going = true;
        System.out.print('[');
        System.out.print(message[0]);
        for(int i = 1; keep_going; i++) {
            try {
                System.out.print(", ");
                System.out.print(message[i]);
            } catch (Exception e) {
                System.out.print("]\n");
                keep_going = false;
            }
        }
    }


    private int[][] _values;
    private int _denominator;

    public void set(int[] values) throws Exception {
        this._denominator = 1;
        int size = values.length;
        int length = (int) sqrt(size);
        _values = new int[length][length];

        if ((length * length) != size) {
            throw new Exception("The matrix is not a square length.");
        } else if (length == 0) {
            throw new Exception("The matrix cannot have length 0.");

        }else {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    this._values[i][j] = values[i * length + j];
                }
            }

        }
    }


    private void subtract_row(double multiplier, int subtractor_row, int changing_row, double[][] matrix_values) {
        for (int i = 0; i < matrix_values[0].length; i++) {
            matrix_values[changing_row][i] -= matrix_values[subtractor_row][i] * multiplier;
        }
    }
    private void divide_row(int rownum, int divisor, double[][] matrix_values) {
        for (int i = 0; i < matrix_values[0].length; i++) {
            matrix_values[rownum][i] /= divisor;
        }
    }

    private int discriminant(double[][] matrix) {
        double disc = 1;
        for (int i = 0;i<matrix.length;i++) {
            disc *= matrix[i][i];
            for (int j = i+1; j<matrix.length; j++) {
                subtract_row(matrix[j][i]/ matrix[i][i], i, j, matrix);
            }
        }

        return  (int) round(disc);
    }


    public void set_inverse(Matrix old_key) {
        int old_length = old_key._values.length;
        double[][] gaussian_inverse = new double[old_length][old_length * 2];
        for (int i = 0; i < old_length; i++) {
            for (int j = 0; j < old_length * 2; j++) {
                if (i == (j - old_length)) {
                    gaussian_inverse[i][j] = 1;
                } else if (j< old_length) {
                    gaussian_inverse[i][j] = old_key._values[i][j];
                }
            }
        }
        this._denominator = discriminant(gaussian_inverse);
        for (int i = 0; i < old_length; i++) {
            divide_row(i, (int) gaussian_inverse[i][i], gaussian_inverse);

            for (int j = i+1; j < old_length; j++) {
                subtract_row(gaussian_inverse[j][i], i, j, gaussian_inverse);
            }
        }
        for (int i = old_length -1; i >=0 ; i--) {
            divide_row(i, (int) gaussian_inverse[i][i], gaussian_inverse);
            for (int j = i - 1; j >= 0; j--) {
                subtract_row(gaussian_inverse[j][i], i, j, gaussian_inverse);

            }


        }
        for (int i = 0; i < old_length; i++) {
            printArray(gaussian_inverse[i]);
        }
        //System.out.println(this._denominator);
        for (int i = 0; i < old_length; i++) {
            for (int j = 0; j < old_length; j++) {
                this._values[i][j] = (int) round(gaussian_inverse[i][j+ old_length] * this._denominator);

            }
        }
    }


    public int[] multiply(int[] multipliable) throws Exception {
        if (multipliable.length > this._values.length) {
            throw new Exception("Message should not be longer than key");
        }
        int[] encrypted_list = new int[this._values.length];
        for (int i = 0; i < this._values.length; i++) {
            int product = 0;
            for (int j = 0; j < this._values.length; j++) {
                int currentval;
                try {
                    currentval = multipliable[j];
                } catch (Exception e) {
                    currentval = 32;
                }
                //System.out.println(currentval);

                product += this._values[i][j] * currentval;
            }
            encrypted_list[i] = product / this._denominator;
        }



            return encrypted_list;
    }
    public int[] multiply(char[] multipliable) throws Exception {
        if (multipliable.length > this._values.length) {
            throw new Exception("Message should not be longer than key");
        }
        int[] encrypted_list = new int[this._values.length];
        for (int i = 0; i < this._values.length; i++) {
            int product = 0;
            for (int j = 0; j < this._values.length; j++) {
                int currentval;
                try {
                    currentval = multipliable[j];
                } catch (Exception e) {
                    currentval = 32;
                }
                //System.out.println(currentval);

                product += this._values[i][j] * currentval;
            }
            encrypted_list[i] = product / this._denominator;
        }



        return encrypted_list;
    }

}
