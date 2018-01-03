package org.JPycRunner;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// pyc file 2.7
// * magic number 4 bytes
// * date 4 bytes
// * PyCodeObject

// https://nedbatchelder.com/blog/200804/the_structure_of_pyc_files.html
// https://github.com/python/cpython/blob/master/Python/marshal.c
// https://github.com/python/cpython/blob/master/Python/import.c
// https://github.com/python/cpython/blob/2.7/Python/marshal.c

//#define TYPE_NULL               '0'
//        #define TYPE_NONE               'N'
//        #define TYPE_FALSE              'F'
//        #define TYPE_TRUE               'T'
//        #define TYPE_STOPITER           'S'
//        #define TYPE_ELLIPSIS           '.'
//        #define TYPE_INT                'i'
//        #define TYPE_INT64              'I'
//        #define TYPE_FLOAT              'f'
//        #define TYPE_BINARY_FLOAT       'g'
//        #define TYPE_COMPLEX            'x'
//        #define TYPE_BINARY_COMPLEX     'y'
//        #define TYPE_LONG               'l'
//        #define TYPE_STRING             's'
//        #define TYPE_INTERNED           't'
//        #define TYPE_STRINGREF          'R'
//        #define TYPE_TUPLE              '('
//        #define TYPE_LIST               '['
//        #define TYPE_DICT               '{'
//        #define TYPE_CODE               'c'
//        #define TYPE_UNICODE            'u'
//        #define TYPE_UNKNOWN            '?'
//        #define TYPE_SET                '<'
//        #define TYPE_FROZENSET          '>'
// cat defines| awk -F" " '{printf "private byte %s = %s;\n",$3,$4}'



public class PycFile {

    private byte TYPE_NONE = 'N';
    private byte TYPE_FALSE = 'F';
    private byte TYPE_TRUE = 'T';
    private byte TYPE_STOPITER = 'S';
    private byte TYPE_ELLIPSIS = '.';
    private byte TYPE_INT = 'i';
    private byte TYPE_INT64 = 'I';
    private byte TYPE_FLOAT = 'f';
    private byte TYPE_BINARY_FLOAT = 'g';
    private byte TYPE_COMPLEX = 'x';
    private byte TYPE_BINARY_COMPLEX = 'y';
    private byte TYPE_LONG = 'l';
    private byte TYPE_STRING = 's';
    private byte TYPE_INTERNED = 't';
    private byte TYPE_STRINGREF = 'R';
    private byte TYPE_TUPLE = '(';
    private byte TYPE_LIST = '[';
    private byte TYPE_DICT = '{';
    private byte TYPE_CODE = 'c';
    private byte TYPE_UNICODE = 'u';
    private byte TYPE_UNKNOWN = '?';
    private byte TYPE_SET = '<';
    private byte TYPE_FROZENSET = '>';

    private int cursor;
    private byte[] content;
    private int length;
    private String magic; // /r/n 62211 0A0D F303
    private String dateFormatted;
    public PycFile(byte [] content) {
        this.content = content;
        length = content.length;
    }

    public void parse() {
        try {
            magic = toHex(loadNbytes(4));
            System.out.println(magic);
            long epoch_time = toInt(loadNbytes(4));
            System.out.println(epoch_time);
            Date date = new Date(epoch_time*1000);
            DateFormat formatter = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
            String dateFormatted = formatter.format(date);
            System.out.println(dateFormatted);
            byte type = getType();
            if (type != TYPE_CODE) {
                System.out.println("Not CodeObject!");
            }
            long co_argcount = toInt(loadNbytes(4));
            System.out.printf("co_argcount: %d\n", co_argcount);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private byte[] loadNbytes(int n) {
        byte[] result = new byte[0];
        if ((cursor + n) < length) {
            result =  Arrays.copyOfRange(content, cursor, cursor + n);
            cursor = cursor + n;
        }
        return result;
    }

    private String toHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(String.format("%02X ", b));
            result.append(" "); // delimiter
        }
        return result.toString();
    }

    private String toAscii(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(String.format("%s ", b));
            //result.append(" "); // delimiter
        }
        return result.toString();

    }

    private int toInt(byte[] bytes) {
        int result = (((bytes[0] & 0xff) |
                ((bytes[1] & 0xff) << 8) |
                ((bytes[2] & 0xff) << 16) |
                ((bytes[3] & 0xff) << 24)));
        return result;
    }

    private byte getType() {
        byte type =  loadNbytes(1)[0];
        System.out.printf("type: %c\n", type);
        return type;
    }
}