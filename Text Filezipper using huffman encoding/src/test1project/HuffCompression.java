/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1project;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mohib
 */
public class HuffCompression {
    private static StringBuilder sb = new StringBuilder();
    private static Map<Byte, String> huffmap = new HashMap<>(); // The mapping of characters to characters bit

    public static void compress(String src, String dst) {
        try {
            FileInputStream in = new FileInputStream(src);
            byte[] b = new byte[in.available()];
            in.read(b);
            byte[] huffmanBytes = createZip(b);
            OutputStream out = new FileOutputStream(dst);
            ObjectOutputStream objectOut = new ObjectOutputStream(out);
            
            // To view mapping and the bytes
            //System.out.println(huffmap);
//            for (byte x : huffmanBytes)
//            {
//                System.out.print(x);
//            }

            // Write the compressed bytes to the dst file
            objectOut.writeObject(huffmanBytes);
            
            // Write the mapping to the dst file
            objectOut.writeObject(huffmap);
            in.close();
            objectOut.close();
            out.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private static byte[] createZip(byte[] bytes) {
        
        // Get all the nodes in a minimum priority queue(Priority decided on frequency of occurance of characters)
        MinPriorityQueue<ByteNode> nodes = getByteNodes(bytes);
        
        // Create tree from the minimum priority queue and return root of the heap
        ByteNode root = createHuffmanTree(nodes);
        
        // Create a hashmap representing original bytes in type byte and compressed bytes in type String
        Map<Byte, String> huffmanCodes = getHuffCodes(root);
        byte[] huffmanCodeBytes = zipBytesWithCodes(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static MinPriorityQueue<ByteNode> getByteNodes(byte[] bytes) {
        MinPriorityQueue<ByteNode> nodes = new MinPriorityQueue<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : bytes)
        {
            Integer value = map.get(b);
            
            // Add bytes (representing each character) and their frequency to a hashmap
            if (value == null)
                map.put(b, 1);
            else
                map.put(b, value + 1);
        }
        // entrySet() returns a set representation of the hashmap, it is same as the map but 
        // it is used for traversing a hashmap
        for (Map.Entry<Byte, Integer> entry : map.entrySet())
            nodes.add(new ByteNode(entry.getKey(), entry.getValue()));
        return nodes;
    }

    private static ByteNode createHuffmanTree(MinPriorityQueue<ByteNode> nodes) {
        while (nodes.len() > 1) {
            // Get two nodes with minimum most frequency, add their frequency and create at a parent node
            // with the added frequency, do this until length is greater than 1
            // this will leave one last node in the queue which will be the root node
            ByteNode left = nodes.poll();
            ByteNode right = nodes.poll();
            ByteNode parent = new ByteNode(null, left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            nodes.add(parent);
        }
        // return root node
        return nodes.poll();
    }

    private static Map<Byte, String> getHuffCodes(ByteNode root) {
        if (root == null) return null;
        
        // Append "1" if traversing to right and "0" if left to get compressed codes
        getHuffCodes(root.left, "0", sb);
        getHuffCodes(root.right, "1", sb);
        
        // Return the mapping of bytes to compressed bytes (which are in string form)
        return huffmap;
    }

    private static void getHuffCodes(ByteNode node, String code, StringBuilder sb1) {
        StringBuilder sb2 = new StringBuilder(sb1);
        sb2.append(code);
        if (node != null)
        {
            if (node.data == null) // Reached a node where there is no character but it is not null
               
            {
                getHuffCodes(node.left, "0", sb2);
                getHuffCodes(node.right, "1", sb2);
            } 
            else
                huffmap.put(node.data, sb2.toString());
        }
    }

    private static byte[] zipBytesWithCodes(byte[] bytes, Map<Byte, String> huffCodes) {
        StringBuilder strBuilder = new StringBuilder();
        // For each byte in the byte array, the corresponding code is appended to the string builder
        for (byte b : bytes)
            strBuilder.append(huffCodes.get(b));

        int length=(strBuilder.length()+7)/8;
        byte[] huffCodeBytes = new byte[length];
        int index = 0;
        for (int i = 0; i < strBuilder.length(); i += 8)
        {
            // Iterate in 8-bit chunks over the string builder
            String strByte;
            if (i + 8 > strBuilder.length())
                strByte = strBuilder.substring(i);
            else strByte = strBuilder.substring(i, i + 8);
            
            // Convert the strByte to and integer in base 2 (decimal) and then finally cast it to a byte
            huffCodeBytes[index] = (byte) Integer.parseInt(strByte, 2); 
            index++;
        }
        return huffCodeBytes;
    }

    public static void decompress(String src, String dst) {
        try {
            FileInputStream in = new FileInputStream(src);
            ObjectInputStream objectInStream = new ObjectInputStream(in);
            
            // Get the byte[] array from the compressed file
            byte[] huffmanBytes = (byte[]) objectInStream.readObject();
            
            // Get the hashmap from the compressed 
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) objectInStream.readObject();

            byte[] bytes = decomp(huffmanCodes, huffmanBytes);
            OutputStream out = new FileOutputStream(dst);
            out.write(bytes);
            in.close();
            objectInStream.close();
            out.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    public static byte[] decomp(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
    StringBuilder sb1 = new StringBuilder();
    for (int i = 0; i < huffmanBytes.length; i++) {
        byte b = huffmanBytes[i];
        sb1.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
    }
    Map<String, Byte> map = new HashMap<>();
    
     //Reverse the Map "HuffmanCodes" so we can look up what represents what
    for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet())
    {
        map.put(entry.getValue(), entry.getKey());
    }
    
    // Create list to store decoded bytes
    List<Byte> list = new ArrayList<>();
    
    // Store bits until the form a valid huffmancode
    StringBuilder temp = new StringBuilder();
    for (int i = 0; i < sb1.length(); i++)
    {
        // Traverse character by character in the String and append it to temp String
        temp.append(sb1.charAt(i));
        
        // If the temp String is a valid huffman key i.e contains a value for that string
        // then add it to the decoded byte arraylist and then set length of temp to 0 for next iteration
        if (map.containsKey(temp.toString())) 
        {
            list.add(map.get(temp.toString()));
            temp.setLength(0);
        }
    }
    
    byte[] b = new byte[list.size()];
    for (int i = 0; i < b.length; i++)
        b[i] = list.get(i);
    return b;
    }

    // Function used convert bytes to int but now simply String.format is used to do that
//    private static String convertbyteInBit(boolean flag, byte b) {
//        int byte0 = b;
//        if (flag) byte0 |= 256;
//        String str0 = Integer.toBinaryString(byte0);
//        if (flag || byte0 < 0)
//            return str0.substring(str0.length() - 8);
//        else return str0;
//    }
}

