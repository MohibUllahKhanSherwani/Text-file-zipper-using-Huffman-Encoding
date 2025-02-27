# 📄 Text File Zipper using Huffman Encoding

This project is a **Text File Compressor and Decompressor** built using **Huffman Encoding** in Java. It was developed as part of the **Data Structures and Algorithms (DSA) Semester Project**.

---

## 📌 Overview
This program compresses and decompresses **only text files** using Huffman Encoding. Huffman Encoding is a lossless data compression algorithm that assigns shorter binary codes to frequently occurring characters, reducing file size without losing any data.

⚠ **Important:** This program works **only with text files (`.txt`)**. Other file types are **not supported**.

---

## 📸 Screenshots

### Compression Process:
<p align="center">
    <img src="https://github.com/user-attachments/assets/5f772c0a-6202-4cd8-aaa1-73f92fde234c" width="500">
</p>

### File Selection:
<p align="center">
    <img src="https://github.com/user-attachments/assets/748015ea-27f2-4596-af6a-07ee0d65318e" width="500">
</p>

---

## 🚀 Features
✔ **Compresses text files using Huffman Encoding**  
✔ **Decompresses files back to original text**  
✔ **Efficient for large text files (MBs in size)**  
✔ **Ensures that only text files are processed**  
✔ **Reduces file size significantly**  

---

## 🛠 How It Works
1. **File Selection:** Choose a `.txt` file for compression.
2. **Compression:** The program builds a Huffman Tree based on character frequency and encodes the text using optimized binary sequences.
3. **Decompression:** The encoded file is decoded back to its original form using the Huffman Tree.

---

## 📌 Requirements
- Java Development Kit (JDK) 8 or later
- Basic understanding of Java & File Handling

---

## 🔧 Usage Instructions

1. **Run the Java program** and select a **text file (`.txt` only)** to compress.
2. Observe the compression process and check the file size reduction.
3. Decompress the file to verify the restored content.

---

## 📢 Recommendations
- Preferably use a **longer text file (MBs in size)** for better compression efficiency.
- Always verify that the decompressed file is identical to the original.
- Do **not** attempt to compress non-text files (e.g., images, PDFs) as this program is **strictly for text files**.

---

## ❗ Limitations
- Works **only with `.txt` files**.
- Does **not** support other file types like images, PDFs, or binary files.
- Compression ratio depends on text redundancy; highly unique content may not compress well.

---

## 🤯 Understanding Huffman Encoding  
Huffman Encoding is a **complex and advanced topic** in data compression. If you are struggling to understand how this algorithm works, feel free to reach out to me. I’d be happy to explain the logic and implementation in detail!

📧 **Contact me at:** mohibkhansherwani@gmail.com  

---

## 📜 License
This project is licensed under the **MIT License**.

---

🌟 **Enjoy compressing your text files efficiently!** 🚀
