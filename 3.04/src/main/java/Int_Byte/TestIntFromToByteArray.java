package Int_Byte;

public class TestIntFromToByteArray {
    public static void main(String[] args) {
        int x = 0b00000011_01010001_11010110_10001001;
        //               0b00000011_01010001_11010110_10001001
        //0xFF = 00000000_00000000_000000000_11111111;
        //System.out.println(Integer.toBinaryString(x & 0xFF));

        byte[] a = new byte[4];
        a[3] = (byte) (x & 0xFF);
        /*System.out.println(a[3]);
        System.out.println(Integer.toBinaryString(a[3]));*/

        a[2] = (byte) ((x >> 8) & 0xFF);
        /*System.out.println(a[2]);
        System.out.println(Integer.toBinaryString(a[2]));*/

        a[1] = (byte) ((x >> 16) & 0xFF);
       /* System.out.println(a[1]);
        System.out.println(Integer.toBinaryString(a[1]));*/

        a[0] = (byte) ((x >> 24) & 0xFF);
        /*System.out.println(a[0]);
        System.out.println(Integer.toBinaryString(a[0]));*/

        int i = (a[3] & 0xFF) | ((a[2] & 0xFF) << 8) | ((a[1] & 0xFF)) << 16 | ((a[0] & 0xFF) << 24);
        System.out.println(Integer.toBinaryString(i));

        a = IntFromToByteArray.int2byteArray(123345);
        System.out.println(a);
        i = IntFromToByteArray.intFromByteArray(a);
        System.out.println(i);
    }
}
