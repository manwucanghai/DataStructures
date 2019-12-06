package com.zws.datastruct.tree.huffman;

/**
 * @author zhengws
 * @date 2019-11-07 11:06
 */
public interface IHuffmanCode {


    /**
     * 对字节数组进行赫夫曼压缩编码
     * @param srcByte
     * @return
     */
    byte[] encode(byte[] srcByte);

    /**
     * 对赫夫曼加密后的字节码进行解码
     * @param encodeByte
     * @return
     */
    byte[] decode(byte[] encodeByte);

    /**
     * 文件压缩编码
     * @param srcFile 源文件路径
     * @param dstFile 压缩后文件路径
     */
    void fileEncode(String srcFile, String dstFile);

    /**
     * 文件解压编码
     * @param encodeFile 压缩文件
     * @param decodeFile 解压后文件
     */
    void fileDecode(String encodeFile, String decodeFile);

}
