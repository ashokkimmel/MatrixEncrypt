import java.lang.Exception;

import static java.lang.Math.*;


public class Matrix {
    public static void printArray(int[] message) {
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
    public static void printArray(char[] message) {
        boolean keep_going = true;
        System.out.print('[');
        int a = message[0];
        System.out.print(a);
        for(int i = 1; keep_going; i++) {
            try {
                a = message[i];
                System.out.print(", ");
                System.out.print(a);
            } catch (Exception e) {
                System.out.print("]\n");
                keep_going = false;
            }
        }
    }


    private int[][] _values;
    private int[][] _inversevalues;
    private int _denominator;

    private void subtract_row(int divisor, int subtractor_row, int changing_row, int[][] matrix_values) {
        int[] changingrowlst = new int[matrix_values[0].length];
        for (int i = 0; i < matrix_values[0].length; i++) {
            changingrowlst[i] =                                                                                                          ((matrix_values[changing_row][i] * matrix_values[subtractor_row][subtractor_row]) -                                                      (matrix_values[changing_row][subtractor_row] * matrix_values[subtractor_row][i])) / divisor;
        }
        matrix_values[changing_row] = changingrowlst;
    }

    private void matrixsort(int column, int[][] oldarray) throws Exception {
        if (oldarray[column][column] == 0) {
            for (int i = column+1; i < oldarray.length; i++) {
                if (oldarray[i][column] != 0) {
                    int[] temp = new int[oldarray[column].length];
                    for (int j = 0; j < oldarray[column].length; j++) {
                        temp[j] = -oldarray[column][j];
                    }
                    oldarray[column] = oldarray[i];
                    oldarray[i] = temp;
                    return;
                }

            }


            throw new Exception("Discrimanent = 0");

        }
    }

    private void old_set_inverse() throws Exception{
        int length = this._values.length;
        _inversevalues = new int[length][length];
        int[][] gaussian_inverse = new int[length][length * 2];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length * 2; j++) {
                if (i == (j- length)) {
                    gaussian_inverse[i][j] = 1;
                } else if (j < length) {
                    gaussian_inverse[i][j] = this._values[i][j];
                }
            }
        }
        int[] pivot_values = new int[length+1];
        pivot_values[0] = 1;
        for (int i = 0; i < length; i++) {
            matrixsort(i, gaussian_inverse);
            pivot_values[i+1] = gaussian_inverse[i][i];
            for (int j = 0; j < length; j++) {
                if (i != j) {
                    subtract_row(pivot_values[i], i, j, gaussian_inverse);
                }
            }




        }
        this._denominator = pivot_values[length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this._inversevalues[i][j] = gaussian_inverse[i][j + length];

            }
        }
    }
    public Matrix(int[] values) throws Exception {
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
        this.old_set_inverse();
    }

    public char[] demultiply(int[] multipliable) throws Exception {
        int[][] myvalues = this._inversevalues;
        if (multipliable.length > myvalues.length) {
            throw new Exception("Message should not be longer than key");
        }
        char[] encrypted_list = new char[myvalues.length];
        for (int i = 0; i < myvalues.length; i++) {
            int product = 0;
            for (int j = 0; j < myvalues.length; j++) {
                int currentval;
                try {
                    currentval = multipliable[j];
                } catch (Exception e) {
                    currentval = 32;
                }

                product += myvalues[j][i] * currentval;
            }
            encrypted_list[i] = (char) (product /this._denominator);
        }



            return encrypted_list;
    }
    public int[] enmultiply(char[] multipliable) throws Exception {
        int[][] myvalues = this._values;
        if (multipliable.length > myvalues.length) {
            throw new Exception("Message should not be longer than key");
        }
        int[] encrypted_list = new int[myvalues.length];
        for (int i = 0; i < myvalues.length; i++) {
            int product = 0;
            for (int j = 0; j < myvalues.length; j++) {
                int currentval;
                try {
                    currentval = multipliable[j];
                } catch (Exception e) {
                    currentval = 32;
                }

                product += myvalues[j][i] * currentval;
            }
            encrypted_list[i] = product;
        }



        return encrypted_list;
    }
    public char[] encrypt(char[] msg) {
        int matrixlength = this._values.length;
        char[] longmsg =  new char[(int) ceil((float) msg.length / matrixlength) * matrixlength];
        System.arraycopy(msg, 0, longmsg, 0, msg.length);
        for (int i = msg.length; i < longmsg.length; i++) {
            longmsg[i] = 32;
        }
        char[] multipliable = new char[matrixlength];
        int[] partialmsg;
        int[] mymessage = new int[longmsg.length];
        for (int i = 0; i < longmsg.length/ matrixlength; i++) {
            System.arraycopy(longmsg, i * matrixlength, multipliable, 0, matrixlength);
            try {
                partialmsg = this.enmultiply(multipliable);
                System.arraycopy(partialmsg,0 ,mymessage,i * matrixlength, matrixlength);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        int count = -1;
        for (int i = 0; i < longmsg.length; i++) {
            int currentval = mymessage[i];
            while (currentval!= 0) {
                currentval/= 94;
                count++;
            }
        }
        char[] pfm = new char[longmsg.length + count]; //properly_formatted_message
        int pfmi = 0;//pfm iterator
        for (int i = 0; i < longmsg.length; i++) {
            int currentval = mymessage[i];
            while (currentval!= 0) {
                pfm[pfmi] = (char) ((currentval % 94) + 32);
                currentval/= 94;
                pfmi++;
            }
            try {
                pfm[pfmi] = ' ';
            } catch (Exception _) {

            }
            pfmi++;
        }
        return pfm;
    }

    public char[] decrypt(char[] msg) {
        int wordcount = 1;
        for (int i = 0; i < msg.length; i++) {
            wordcount += (msg[i] == ' ')? 1:0;
        }
        int[] encryptedmsg = new int[wordcount];
        int emi = 0;//encrypted message incrementer
        int multiplier = 1;
        for (int i = 0; i < msg.length; i++) {
            if (msg[i] == ' ') {
                multiplier = 1;
                emi++;
            } else {
                encryptedmsg[emi] += (((int) msg[i]) - 32) * multiplier;
                multiplier *= 94;
            }
        }
        int matrixlength = this._inversevalues.length;
        int[] multipliable = new int[matrixlength];
        char[] partialmsg;
        char[] mymessage = new char[wordcount];
        for (int i = 0; i < encryptedmsg.length / matrixlength; i++) {
            System.arraycopy(encryptedmsg, i * matrixlength, multipliable, 0, matrixlength);
            try {
                partialmsg = this.demultiply(multipliable);
                System.arraycopy(partialmsg,0 ,mymessage,i * matrixlength, matrixlength);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return mymessage;
    }


}
