/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1project;

/**
 *
 * @author Mohib
 */
class ByteNode implements Comparable<ByteNode>{
    Byte data;
    int frequency;
    ByteNode left;
    ByteNode right;

    public ByteNode(Byte data, int frequency)
    {
        this.data = data;
        this.frequency = frequency;
    }
    @Override
    public int compareTo(ByteNode o)
    {
        return this.frequency - o.frequency;
    }
}