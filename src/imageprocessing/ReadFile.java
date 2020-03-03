import java.io.*;
import java.util.Formatter;

public class ReadFile {
    public static void main(String[] args) {
        try{
            FileInputStream myInputFile = new FileInputStream("image/yoda.tif");
            int value,j;
            j = 0;
//            String byteOrder = null;
//            int index = 0;
            while((value = myInputFile.read())!= -1){
                
                String hexFormat = String.format("%02X", value);
                System.out.printf("%5s",hexFormat);
                j++;
                if (j == 10) {
                    System.out.println("");
                    j = 0;
                }
               
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
//                String hexFormat = Integer.toHexString(value);
//                if (index == 0 && value == 73) {
//                    int value2 = myInputFile.read();
//                    if (value2 == 73) {
//                        byteOrder = "LSB";
//                        System.out.println("Byte Order: " + byteOrder);
//                    }
//                    index++;
//                } else if (index ==0 && value==0)
//                String hexFormat = String.format("%02X", value);
//                System.out.printf("%5s",hexFormat);
//                j++;
                
//                System.out.printf("%5s", value);
//			if(value % 5 == 0)
//			{
//				System.out.println();
//			}
//                index++;
//
            }
//            System.out.println(myInputFile.read());
//            System.out.println(myInputFile.read());
//            System.out.println(myInputFile.read());
//            System.out.println(myInputFile.read());
//            System.out.println(myInputFile.read());
//            System.out.println(myInputFile.read());

            myInputFile.close();
        }catch(IOException ex){
            System.out.println("File Error!");
        }
    }
}
