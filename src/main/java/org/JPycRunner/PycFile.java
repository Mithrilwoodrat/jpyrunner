package org.JPycRunner;

import org.JPycRunner.objects.PyCodeObject;
import org.JPycRunner.objects.PyObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
//    https://github.com/python/cpython/blob/2.7/Python/marshal.c w_object 543
//    else if (PyCode_Check(v)) {
//        PyCodeObject *co = (PyCodeObject *)v;
//        w_byte(TYPE_CODE, p);
//        w_long(co->co_argcount, p);
//        w_long(co->co_nlocals, p);
//        w_long(co->co_stacksize, p);
//        w_long(co->co_flags, p);
//        w_object(co->co_code, p);
//        w_object(co->co_consts, p);
//        w_object(co->co_names, p);
//        w_object(co->co_varnames, p);
//        w_object(co->co_freevars, p);
//        w_object(co->co_cellvars, p);
//        w_object(co->co_filename, p);
//        w_object(co->co_name, p);
//        w_long(co->co_firstlineno, p);
//        w_object(co->co_lnotab, p);
//    }
    public PyCodeObject parse() {
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
                System.out.println("Invalid CodeObject!");
            }

            int co_argcount = getInt();
            System.out.printf("co_argcount: %d\n", co_argcount);

            //int co_kwonlyargcount = getInt();
            //System.out.printf("co_kwonlyargcount: %d\n", co_kwonlyargcount);

            int co_nlocals = getInt();
            System.out.printf("co_nlocals: %d\n", co_nlocals);
            int co_stacksize = getInt();
            System.out.printf("co_stacksize: %d\n", co_stacksize);
            int co_flags = getInt();
            System.out.printf("co_flags: %d\n", co_flags);

            //int co_firstlineno = getInt();
            //System.out.printf("co_firstlineno: %d\n", co_firstlineno);
            type = getType();

            if (type != TYPE_STRING) {
                System.out.println("Invalid CodeObject!");
            }
            int length = getInt();
            byte[] co_code = loadNbytes(length);
            System.out.printf("co_code: %s\n", toHex(co_code));

            type = getType();

            if (type != TYPE_TUPLE) {
                System.out.println("Invalid CodeObject!");
            }

            String[] co_consts = getStringTuple();

            type = getType();

            if (type != TYPE_TUPLE) {
                System.out.println("Invalid CodeObject!");
            }
            String[] co_names = getStringTuple();

            type = getType();

            if (type != TYPE_TUPLE) {
                System.out.println("Invalid CodeObject!");
            }
            String[] co_varsnames = getStringTuple();

            type = getType();

            if (type != TYPE_TUPLE) {
                System.out.println("Invalid CodeObject!");
            }
            String[] co_freevars = getStringTuple();

            type = getType();

            if (type != TYPE_TUPLE) {
                System.out.println("Invalid CodeObject!");
            }
            String[] co_cellvars = getStringTuple();

            type = getType();

            if (type != TYPE_STRING) {
                System.out.println("Invalid CodeObject!");
            }

            String co_filename = getString();

            type = getType();
            String co_name = getString();
            System.out.printf("co_name: %s\n", co_name);

            int co_firstlineno = getInt();
            System.out.printf("co_firstlineno: %d\n", co_firstlineno);

            type = getType();
            String co_lnotab = getString();

            System.out.printf("co_lnotab: %s\n", co_lnotab);

            PyCodeObject code = new PyCodeObject(co_argcount, co_nlocals, co_stacksize,
                    co_flags, co_code, co_consts,
                    co_names, co_varsnames, co_freevars,
                    co_cellvars, co_filename, co_name,
                    co_firstlineno, co_lnotab);
            return code;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadNbytes(int n) {
        byte[] result = new byte[n];
        if ((cursor + n) < length) {
            for (int i=0;i<n;i+=1) {
                result[i] = content[cursor+i];
            }
            //result =  Arrays.copyOfRange(content, cursor, cursor + n);
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

    private int getInt() {
        return toInt(loadNbytes(4));
    }

    private byte getType() {
        byte type =  loadNbytes(1)[0];
        System.out.printf("type: %c\n", type);
        return type;
    }

//    static void
//    w_pstring(const char *s, Py_ssize_t n, WFILE *p)
//    {
//        W_SIZE(n, p);
//        w_string(s, n, p);
//    }

//    static void
//    w_string(const char *s, Py_ssize_t n, WFILE *p)
//    {
//        if (p->fp != NULL) {
//            fwrite(s, 1, n, p->fp);
//        }
//        else {
//            while (--n >= 0) {
//                w_byte(*s, p);
//                s++;
//            }
//        }
//    }
    private  String getString() {
        int length = getInt();
        return new String(loadNbytes(length));
    }

    //    https://github.com/python/cpython/blob/2.7/Python/marshal.c w_object 369
    //    else if (PyTuple_CheckExact(v)) {
    //        w_byte(TYPE_TUPLE, p);
    //        n = PyTuple_Size(v);
    //        W_SIZE(n, p);
    //        for (i = 0; i < n; i++) {
    //            w_object(PyTuple_GET_ITEM(v, i), p);
    //        }
    //    }

    private String[] getStringTuple() {
        int length = getInt();
        System.out.printf("Tuple Length: %d\n", length);
        String[] result = new String[length];
        System.out.printf("Strings: ");
        for (int i=0; i < length; i++) {
            byte type = getType();
            // TODO implements PyObject getObject() method
            if (type == TYPE_STRING) {
                result[i] = getString();
                System.out.printf("%s\n", result[i]);
            } else if (type == TYPE_INTERNED) {
                result[i] = getString();
                System.out.printf("%s\n", result[i]);
            }
            else if (type == TYPE_NONE) {
                continue;
            }
        }
        System.out.printf("\n");
        return result;
    }
}