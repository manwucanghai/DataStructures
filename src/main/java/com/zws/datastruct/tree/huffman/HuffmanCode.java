package com.zws.datastruct.tree.huffman;

import java.io.*;
import java.util.*;

/**
 * @author zhengws
 * @date 2019-11-07 11:11
 */
public class HuffmanCode implements IHuffmanCode {

    private Map<Byte, String> codeMap = new HashMap<>();

    private Map<String, Byte> decodeMap = new HashMap<>();

    private class Node implements Comparable<Node> {
        Byte data;
        int weight;
        Node left;
        Node right;

        public Node(Byte data, int weight) {
            this.data = data;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }


    @Override
    public byte[] encode(byte[] srcByte) {
        //1. 获取字符及个数映射关系
        Map<Byte, Node> map = getByteCountMap(srcByte);
        //2. 根据字符个数关系，串讲赫夫曼树
        Node root = createHuffManTree(map);
        //3.根据赫夫曼树获取赫夫曼编码
        createHuffManCode(root, "", new StringBuilder());
        //4.通过赫夫曼编码，将原始字节数组转成赫夫曼字节数组
        return genrateHuffManByte(srcByte);
    }


    @Override
    public byte[] decode(byte[] encodeByte) {
        //1.将赫夫曼字节数组转换成，二进制编码
        String binStr = transformBinStr(encodeByte);
        //2.根据decodeMap还原原始字节数组
        return decodeByte(binStr);
    }


    @Override
    public void fileEncode(String srcFile, String dstFile) {
        InputStream in = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            in = new FileInputStream(srcFile);
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            byte[] encode = encode(bytes);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(encode);
            oos.writeObject(decodeMap);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void fileDecode(String encodeFile, String decodeFile) {
        InputStream in = null;
        ObjectInputStream oin = null;
        OutputStream os = null;
        try {
            in = new FileInputStream(encodeFile);
            oin = new ObjectInputStream(in);
            byte[] bytes = (byte[]) oin.readObject();
            decodeMap = (Map<String, Byte>) oin.readObject();
            byte[] decode = decode(bytes);
            os = new FileOutputStream(decodeFile);
            os.write(decode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (in != null) {
                    in.close();
                }
                if (oin != null){
                    oin.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将二进制字符串转换成原始字节数组.
     *
     * @param binStr
     * @return
     */
    private byte[] decodeByte(String binStr) {
        List<Byte> list = new ArrayList<>();
        int count;
        for (int i = 0; i < binStr.length(); i += count) {
            count = 0;
            Byte result = null;
            while (result == null && (i + count) < binStr.length()) {
                count++;
                result = decodeMap.get(binStr.substring(i, i + count));
            }
            list.add(result);
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 将字节数组转换为二进制字符串
     *
     * @param encodeByte
     * @return
     */
    private String transformBinStr(byte[] encodeByte) {
        StringBuilder sb = new StringBuilder();
        int length = encodeByte.length;
        int b;
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                b = encodeByte[i];
            } else {
                b = encodeByte[i] | 256;
            }
            String s = Integer.toBinaryString(b);
            if (s.length() >= 8) {
                sb.append(s.substring(s.length() - 8));
            } else {
                sb.append(s);
            }
        }
        return sb.toString();
    }

    /**
     * 根据原字节数组，获取字节与字节出现个数的映射关系.
     *
     * @param srcByte
     * @return
     */
    private Map<Byte, Node> getByteCountMap(byte[] srcByte) {
        Map<Byte, Node> map = new HashMap<>();
        for (byte b : srcByte) {
            Node node = map.get(b);
            if (node == null) {
                map.put(b, new Node(b, 1));
            } else {
                node.weight += 1;
            }
        }
        return map;
    }

    /**
     * 生成赫夫曼字节数组
     *
     * @param srcByte
     * @return
     */
    private byte[] genrateHuffManByte(byte[] srcByte) {
        StringBuilder sb = new StringBuilder();
        for (byte b : srcByte) {
            sb.append(codeMap.get(b));
        }
        int lenght = sb.length();
        int index = 0;
        int size = (lenght + 7) / 8;
        byte[] bytes = new byte[size];
        while (index < size) {
            if ((index + 1) * 8 < lenght) {
                /**
                 * 注意：如果采用Byte.valuef("二进制", 2),则有可能导致值越界的情况。
                 */
                bytes[index] = (byte) Integer.parseInt(sb.substring(index * 8, (index + 1) * 8), 2);
            } else {
                bytes[index] = (byte) Integer.parseInt(sb.substring(index * 8), 2);
            }
            index++;
        }
        return bytes;
    }

    /**
     * 创建赫夫曼编码
     *
     * @param node
     */
    private void createHuffManCode(Node node, String type, StringBuilder preSb) {
        if (node == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(preSb);
        sb.append(type);
        if (node.left != null) {
            createHuffManCode(node.left, "0", sb);
        }
        if (node.right != null) {
            createHuffManCode(node.right, "1", sb);
        }
        //当前节点为叶子节点
        if (node.data != null) {
            codeMap.put(node.data, sb.toString());
            //提供后续解密使用.
            decodeMap.put(sb.toString(), node.data);
        }
    }

    /**
     * 创建赫夫曼树
     *
     * @param map
     */
    private Node createHuffManTree(Map<Byte, Node> map) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Byte, Node> entry : map.entrySet()) {
            queue.add(entry.getValue());
        }
        while (queue.size() > 1) {
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            Node parent = new Node(null, node1.weight + node2.weight);
            parent.left = node1;
            parent.right = node2;
            queue.add(parent);
        }
        return queue.poll();
    }
}
