package com.zws.datastruct.tree.huffman;

/**
 * @author zhengws
 * @date 2019-11-07 11:33
 */
public class HuffmanCodeTest {
    public static void main(String[] args) throws Exception{
        HuffmanCode code = new HuffmanCode();
//        String s = "i like java, do you like java ?";
//        byte[] srcBytes = s.getBytes();
//        byte[] bytes = code.encode(srcBytes);
//        System.out.println("原始字节长度: " + srcBytes.length);
//        System.out.println("压缩后字节长度: " + bytes.length);
//        code.decode(bytes);

        code.fileEncode("/Users/zhengws/Desktop/1.png", "/Users/zhengws/Desktop/1.zip");
//        code.fileDecode("/Users/zhengws/Desktop/1.zip", "/Users/zhengws/Desktop/2.jpg");
        //{32=1111, 97=0, 118=10, 106=110, 10=1110}
    }
}
