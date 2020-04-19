/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ImgData {
    public static void main(String[] args) {
        try{
            FileInputStream myInputFile = new FileInputStream("image/yoda.tif");
            int value,one=0,two=0;
//            j = 0;

            String hex = "";
            int i = 1;
            int m = 0;
            System.out.println("---------------------------Header Info---------------------------------");
            
            System.out.print("Byte order    : ");
            while((i<=2)&&((value = myInputFile.read())!= -1)){
                hex = Integer.toHexString(value);
                System.out.print(hex);
                i++;
            }
            System.out.println("");
            
            System.out.print("Version       : ");
            while((i<=4)&&((value = myInputFile.read())!= -1)){
                hex = Integer.toHexString(value);
                if(value == 0){
                    hex = "";
                }
                System.out.print(hex);
                i++;
            }
            System.out.println("");
            
            System.out.print("Offset        : ");
            while((i<=8)&&((value = myInputFile.read())!= -1)){
                hex = Integer.toHexString(value);
                if(value == 0){
                    hex = "";
                }
                System.out.print(hex);
                i++;
            }
            
            one = myInputFile.read();
            two = myInputFile.read();
            m = getM(one,two); 
//            System.out.print(m);
            
        System.out.println("");
        System.out.println("----------------------------Data Entry---------------------------------");
        System.out.println("Tag                                   Type          Count     Value");
        System.out.println("-----------------------------------------------------------------------");
        
        ArrayList<String> num = new ArrayList<String>(); // Create an ArrayList object
        num.add("0");
            for(int array=1;array<=(12*m);array++){
                value = myInputFile.read();
                String data = String.format("%02X", value);
                num.add(data);
            }
         
        int tag = 0;
        int type = 0;
        int length = 0;
        int dvalue = 0;
        int StripOff = 0;
        
        String name = "",ntype = "";
            for(int j=0;j<m;j++){
                tag = getTag(num.get(1+(12*j)),num.get(2+(12*j)));
                type = getType(num.get(3+(12*j)),num.get(4+(12*j)));
                length = getLength(num.get(5+(12*j)),num.get(6+(12*j)),num.get(7+(12*j)),num.get(8+(12*j)));
                dvalue = getValue(num.get(9+(12*j)),num.get(10+(12*j)),num.get(11+(12*j)),num.get(12+(12*j)));
                String decimal_tag = String.format("%02X", tag);
                if(type == 1 ){
                    ntype = "byte";
                }else if(type == 2){
                    ntype = "ASCII";
                }else if(type == 3){
                    ntype = "short";
                }else if(type == 4){
                    ntype = "long";
                }else if(type == 5){
                    ntype = "rational";
                }
                
                if(tag==254){
                    name = "NewSubfileType";
                    System.out.println(decimal_tag + "(" +name + ")                    " + type + "(" + ntype + ")" + "          " + 1 + "       " + dvalue);
                }else if(tag==256){
                    name = "Image width";
                    System.out.println(decimal_tag + "(" +name + ")                      " + type + "(" + ntype + ")" + "         " + 1 + "       " + dvalue);
                }else if(tag==257){
                    name = "Image height";
                    System.out.println(decimal_tag + "(" +name + ")                     " + type + "(" + ntype + ")" + "         " + 1 + "       " + dvalue);
                }else if(tag==258){
                    name = "Bits per sample";
                    System.out.println(decimal_tag + "(" +name + ")                  " + type + "(" + ntype + ")" + "         " + 1 + "       " + dvalue);
                }else if(tag==259){
                    name = "Compression";
                    System.out.println(decimal_tag + "(" +name + ")                      " + type + "(" + ntype + ")" + "         " + 1 + "       " + dvalue);
                }else if(tag==262){
                    name = "Photometric interpretation";
                    System.out.println(decimal_tag + "(" +name + ")       " + type + "(" + ntype + ")" + "         " + 1 + "       " + dvalue);
                }else if(tag==273){
                    name = "Strip offsets";
                    StripOff = dvalue;
                    System.out.println(decimal_tag + "(" +name + ")                    " + type + "(" + ntype + ")" + "          " + 1 + "       " + dvalue);
                }else if(tag==277){
                    name = "Samples per pixel";
                    System.out.println(decimal_tag + "(" +name + ")                " + type + "(" + ntype + ")" + "         " + 1 + "       " + dvalue);
                }else if(tag==278){
                    name = "Rows per strip";
                    System.out.println(decimal_tag + "(" +name + ")                   " + type + "(" + ntype + ")" + "          " + 1 + "       " + dvalue);
                }else if(tag==279){
                    name = "Strip byte counts";
                    System.out.println(decimal_tag + "(" +name + ")                " + type + "(" + ntype + ")" + "          " + 1 + "       " + dvalue);
                }else if(tag==282){
                    name = "X resolution";
                    System.out.println(decimal_tag + "(" +name + ")                     " + type + "(" + ntype + ")" + "      " + 1 + "       " + dvalue);
                }else if(tag==283){
                    name = "Y resolution";
                    System.out.println(decimal_tag + "(" +name + ")                     " + type + "(" + ntype + ")" + "      " + 1 + "       " + dvalue);
                }
            }
            
            System.out.println("-----------------------------Image  Data-----------------------------");
            int x = (m*12)+1;
            int sixteen = 16;

            while((value = myInputFile.read())!=-1){
                if(x>(StripOff-10)){
                   if(sixteen == 1){
                       System.out.println();
                       sixteen = 16;
                   }
                   hex = String.format("%02X", value);
                   System.out.print(hex + " "); 
                   sixteen--;
                }
                x++;
            }
            
//            while((value = myInputFile.read())!= -1){
//                
//                String hexFormat = String.format("%02X", value);
//                System.out.printf("%5s",hexFormat);
//                j++;
//                if (j == 10) {
//                    System.out.println("");
//                    j = 0;
//                }
//            }
            myInputFile.close();
        }catch(IOException ex){
            System.out.println("File Error!");
        }
    }
    
    public static int getM(int i, int m){
        int tag = m + i;
        return tag;
    }
    public static int getTag(String i, String m){
        String tag = m + i;
        int value = Integer.parseInt(tag,16);
        String hex = String.format("%02X", value);
        return value;
    }
    public static int getType(String i, String m){
        String tag = m + i;
        int value = Integer.parseInt(tag,16);
        String hex = String.format("%02X", value);
        return value;
    }
    public static int getLength(String i, String m, String j, String k){
        String tag = k + j + m + i;
        int value = Integer.parseInt(tag,16);
        String hex = String.format("%02X", value);
        return value;
    }
    public static int getValue(String i, String m, String j, String k){
        String tag = k + j + m + i;
        int value = Integer.parseInt(tag,16);
        String hex = String.format("%02X", value);
        return value;
    }
}





