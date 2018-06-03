package org.JPycRunner.objects;

// opcodes defines in https://github.com/python/cpython/blob/2.7/Include/opcode.h
/* Instruction opcodes for compiled code */

//#define STOP_CODE	0
//        #define POP_TOP		1
//        #define ROT_TWO		2
//        #define ROT_THREE	3
//        #define DUP_TOP		4
//        #define ROT_FOUR	5
//        #define NOP		9
//
//        #define UNARY_POSITIVE	10
//        #define UNARY_NEGATIVE	11
//        #define UNARY_NOT	12
//        #define UNARY_CONVERT	13
//
//        #define UNARY_INVERT	15
//
//        #define BINARY_POWER	19
//
//        #define BINARY_MULTIPLY	20
//        #define BINARY_DIVIDE	21
//        #define BINARY_MODULO	22
//        #define BINARY_ADD	23
//        #define BINARY_SUBTRACT	24
//        #define BINARY_SUBSCR	25
//        #define BINARY_FLOOR_DIVIDE 26
//        #define BINARY_TRUE_DIVIDE 27
//        #define INPLACE_FLOOR_DIVIDE 28
//        #define INPLACE_TRUE_DIVIDE 29
//
//        #define SLICE		30
//        /* Also uses 31-33 */
//        #define SLICE_1		31
//        #define SLICE_2		32
//        #define SLICE_3		33
//
//        #define STORE_SLICE	40
//        /* Also uses 41-43 */
//        #define STORE_SLICE_1	41
//        #define STORE_SLICE_2	42
//        #define STORE_SLICE_3	43
//
//        #define DELETE_SLICE	50
//        /* Also uses 51-53 */
//        #define DELETE_SLICE_1	51
//        #define DELETE_SLICE_2	52
//        #define DELETE_SLICE_3	53
//
//        #define STORE_MAP	54
//        #define INPLACE_ADD	55
//        #define INPLACE_SUBTRACT	56
//        #define INPLACE_MULTIPLY	57
//        #define INPLACE_DIVIDE	58
//        #define INPLACE_MODULO	59
//        #define STORE_SUBSCR	60
//        #define DELETE_SUBSCR	61
//
//        #define BINARY_LSHIFT	62
//        #define BINARY_RSHIFT	63
//        #define BINARY_AND	64
//        #define BINARY_XOR	65
//        #define BINARY_OR	66
//        #define INPLACE_POWER	67
//        #define GET_ITER	68
//
//        #define PRINT_EXPR	70
//        #define PRINT_ITEM	71
//        #define PRINT_NEWLINE	72
//        #define PRINT_ITEM_TO   73
//        #define PRINT_NEWLINE_TO 74
//        #define INPLACE_LSHIFT	75
//        #define INPLACE_RSHIFT	76
//        #define INPLACE_AND	77
//        #define INPLACE_XOR	78
//        #define INPLACE_OR	79
//        #define BREAK_LOOP	80
//        #define WITH_CLEANUP    81
//        #define LOAD_LOCALS	82
//        #define RETURN_VALUE	83
//        #define IMPORT_STAR	84
//        #define EXEC_STMT	85
//        #define YIELD_VALUE	86
//        #define POP_BLOCK	87
//        #define END_FINALLY	88
//        #define BUILD_CLASS	89
//
//        #define HAVE_ARGUMENT	90	/* Opcodes from here have an argument: */
//
//        #define STORE_NAME	90	/* Index in name list */
//        #define DELETE_NAME	91	/* "" */
//        #define UNPACK_SEQUENCE	92	/* Number of sequence items */
//        #define FOR_ITER	93
//        #define LIST_APPEND	94
//
//        #define STORE_ATTR	95	/* Index in name list */
//        #define DELETE_ATTR	96	/* "" */
//        #define STORE_GLOBAL	97	/* "" */
//        #define DELETE_GLOBAL	98	/* "" */
//        #define DUP_TOPX	99	/* number of items to duplicate */
//        #define LOAD_CONST	100	/* Index in const list */
//        #define LOAD_NAME	101	/* Index in name list */
//        #define BUILD_TUPLE	102	/* Number of tuple items */
//        #define BUILD_LIST	103	/* Number of list items */
//        #define BUILD_SET	104     /* Number of set items */
//        #define BUILD_MAP	105	/* Always zero for now */
//        #define LOAD_ATTR	106	/* Index in name list */
//        #define COMPARE_OP	107	/* Comparison operator */
//        #define IMPORT_NAME	108	/* Index in name list */
//        #define IMPORT_FROM	109	/* Index in name list */
//        #define JUMP_FORWARD	110	/* Number of bytes to skip */
//
//        #define JUMP_IF_FALSE_OR_POP 111 /* Target byte offset from beginning
//                                    of code */
//        #define JUMP_IF_TRUE_OR_POP 112	/* "" */
//        #define JUMP_ABSOLUTE	113	/* "" */
//        #define POP_JUMP_IF_FALSE 114	/* "" */
//        #define POP_JUMP_IF_TRUE 115	/* "" */
//
//        #define LOAD_GLOBAL	116	/* Index in name list */
//
//        #define CONTINUE_LOOP	119	/* Start of loop (absolute) */
//        #define SETUP_LOOP	120	/* Target address (relative) */
//        #define SETUP_EXCEPT	121	/* "" */
//        #define SETUP_FINALLY	122	/* "" */
//
//        #define LOAD_FAST	124	/* Local variable number */
//        #define STORE_FAST	125	/* Local variable number */
//        #define DELETE_FAST	126	/* Local variable number */
//
//        #define RAISE_VARARGS	130	/* Number of raise arguments (1, 2 or 3) */
//        /* CALL_FUNCTION_XXX opcodes defined below depend on this definition */
//        #define CALL_FUNCTION	131	/* #args + (#kwargs<<8) */
//        #define MAKE_FUNCTION	132	/* #defaults */
//        #define BUILD_SLICE 	133	/* Number of items */
//
//        #define MAKE_CLOSURE    134     /* #free vars */
//        #define LOAD_CLOSURE    135     /* Load free variable from closure */
//        #define LOAD_DEREF      136     /* Load and dereference from closure cell */
//        #define STORE_DEREF     137     /* Store into cell */
//
///* The next 3 opcodes must be contiguous and satisfy
//   (CALL_FUNCTION_VAR - CALL_FUNCTION) & 3 == 1  */
//        #define CALL_FUNCTION_VAR          140	/* #args + (#kwargs<<8) */
//        #define CALL_FUNCTION_KW           141	/* #args + (#kwargs<<8) */
//        #define CALL_FUNCTION_VAR_KW       142	/* #args + (#kwargs<<8) */
//
//        #define SETUP_WITH 143
//
//        /* Support for opargs more than 16 bits long */
//        #define EXTENDED_ARG  145
//
//        #define SET_ADD         146
//        #define MAP_ADD         147
//
//
//enum cmp_op {PyCmp_LT=Py_LT, PyCmp_LE=Py_LE, PyCmp_EQ=Py_EQ, PyCmp_NE=Py_NE, PyCmp_GT=Py_GT, PyCmp_GE=Py_GE,
//    PyCmp_IN, PyCmp_NOT_IN, PyCmp_IS, PyCmp_IS_NOT, PyCmp_EXC_MATCH, PyCmp_BAD};
//
//#define HAS_ARG(op) ((op) >= HAVE_ARGUMENT)

// cat opcode.h |grep "#define " | awk -F" " '{printf("static final int %s = %s;\n", $2, $3)}'

// ref : https://sourcegraph.com/github.com/jboss-javassist/javassist/-/blob/src/main/javassist/bytecode/Opcode.java?utm_source=share#L3:32

import java.util.HashMap;
import java.util.Map;

public class OpCode {
    public static final int ROT_TWO = 2;
    public static final int ROT_THREE = 3;
    public static final int DUP_TOP = 4;
    public static final int ROT_FOUR = 5;
    public static final int NOP = 9;
    public static final int UNARY_POSITIVE = 10;
    public static final int UNARY_NEGATIVE = 11;
    public static final int UNARY_NOT = 12;
    public static final int UNARY_CONVERT = 13;
    public static final int UNARY_INVERT = 15;
    public static final int BINARY_POWER = 19;
    public static final int BINARY_MULTIPLY = 20;
    public static final int BINARY_DIVIDE = 21;
    public static final int BINARY_MODULO = 22;
    public static final int BINARY_ADD = 23;
    public static final int BINARY_SUBTRACT = 24;
    public static final int BINARY_SUBSCR = 25;
    public static final int BINARY_FLOOR_DIVIDE = 26;
    public static final int BINARY_TRUE_DIVIDE = 27;
    public static final int INPLACE_FLOOR_DIVIDE = 28;
    public static final int INPLACE_TRUE_DIVIDE = 29;
    public static final int SLICE = 30;
    public static final int SLICE_1 = 31;
    public static final int SLICE_2 = 32;
    public static final int SLICE_3 = 33;
    public static final int STORE_SLICE = 40;
    public static final int STORE_SLICE_1 = 41;
    public static final int STORE_SLICE_2 = 42;
    public static final int STORE_SLICE_3 = 43;
    public static final int DELETE_SLICE = 50;
    public static final int DELETE_SLICE_1 = 51;
    public static final int DELETE_SLICE_2 = 52;
    public static final int DELETE_SLICE_3 = 53;
    public static final int STORE_MAP = 54;
    public static final int INPLACE_ADD = 55;
    public static final int INPLACE_SUBTRACT = 56;
    public static final int INPLACE_MULTIPLY = 57;
    public static final int INPLACE_DIVIDE = 58;
    public static final int INPLACE_MODULO = 59;
    public static final int STORE_SUBSCR = 60;
    public static final int DELETE_SUBSCR = 61;
    public static final int BINARY_LSHIFT = 62;
    public static final int BINARY_RSHIFT = 63;
    public static final int BINARY_AND = 64;
    public static final int BINARY_XOR = 65;
    public static final int BINARY_OR = 66;
    public static final int INPLACE_POWER = 67;
    public static final int GET_ITER = 68;
    public static final int PRINT_EXPR = 70;
    public static final int PRINT_ITEM = 71;
    public static final int PRINT_NEWLINE = 72;
    public static final int PRINT_ITEM_TO = 73;
    public static final int PRINT_NEWLINE_TO = 74;
    public static final int INPLACE_LSHIFT = 75;
    public static final int INPLACE_RSHIFT = 76;
    public static final int INPLACE_AND = 77;
    public static final int INPLACE_XOR = 78;
    public static final int INPLACE_OR = 79;
    public static final int BREAK_LOOP = 80;
    public static final int WITH_CLEANUP = 81;
    public static final int LOAD_LOCALS = 82;
    public static final int RETURN_VALUE = 83;
    public static final int IMPORT_STAR = 84;
    public static final int EXEC_STMT = 85;
    public static final int YIELD_VALUE = 86;
    public static final int POP_BLOCK = 87;
    public static final int END_FINALLY = 88;
    public static final int BUILD_CLASS = 89;
    private  static final int HAVE_ARGUMENT = 90;
    public static final int STORE_NAME = 90;
    public static final int DELETE_NAME = 91;
    public static final int UNPACK_SEQUENCE = 92;
    public static final int FOR_ITER = 93;
    public static final int LIST_APPEND = 94;
    public static final int STORE_ATTR = 95;
    public static final int DELETE_ATTR = 96;
    public static final int STORE_GLOBAL = 97;
    public static final int DELETE_GLOBAL = 98;
    public static final int DUP_TOPX = 99;
    public static final int LOAD_CONST = 100;
    public static final int LOAD_NAME = 101;
    public static final int BUILD_TUPLE = 102;
    public static final int BUILD_LIST = 103;
    public static final int BUILD_SET = 104;
    public static final int BUILD_MAP = 105;
    public static final int LOAD_ATTR = 106;
    public static final int COMPARE_OP = 107;
    public static final int IMPORT_NAME = 108;
    public static final int IMPORT_FROM = 109;
    public static final int JUMP_FORWARD = 110;
    public static final int JUMP_IF_FALSE_OR_POP = 111;
    public static final int JUMP_IF_TRUE_OR_POP = 112;
    public static final int JUMP_ABSOLUTE = 113;
    public static final int POP_JUMP_IF_FALSE = 114;
    public static final int POP_JUMP_IF_TRUE = 115;
    public static final int LOAD_GLOBAL = 116;
    public static final int CONTINUE_LOOP = 119;
    public static final int SETUP_LOOP = 120;
    public static final int SETUP_EXCEPT = 121;
    public static final int SETUP_FINALLY = 122;
    public static final int LOAD_FAST = 124;
    public static final int STORE_FAST = 125;
    public static final int DELETE_FAST = 126;
    public static final int RAISE_VARARGS = 130;
    public static final int CALL_FUNCTION = 131;
    public static final int MAKE_FUNCTION = 132;
    public static final int BUILD_SLICE = 133;
    public static final int MAKE_CLOSURE = 134;
    public static final int LOAD_CLOSURE = 135;
    public static final int LOAD_DEREF = 136;
    public static final int STORE_DEREF = 137;
    public static final int CALL_FUNCTION_VAR = 140;
    public static final int CALL_FUNCTION_KW = 141;
    public static final int CALL_FUNCTION_VAR_KW = 142;
    public static final int SETUP_WITH = 143;
    public static final int EXTENDED_ARG = 145;
    public static final int SET_ADD = 146;
    public static final int MAP_ADD = 147;

    public static boolean has_args(int op) {
        return (op >= HAVE_ARGUMENT);
    }

    public static String GetCode(int opcode) {
        return  CodeMap.get(opcode);
    }

    private static final HashMap<Integer, String> CodeMap = CreateCodeMap();
    private static HashMap<Integer, String> CreateCodeMap()
    {
        HashMap<Integer, String> codemap = new HashMap<Integer, String>();
        codemap.put(0,"STOP_CODE");
        codemap.put(1,"POP_TOP");
        codemap.put(2,"ROT_TWO");
        codemap.put(3,"ROT_THREE");
        codemap.put(4,"DUP_TOP");
        codemap.put(5,"ROT_FOUR");
        codemap.put(9,"NOP");
        codemap.put(10,"UNARY_POSITIVE");
        codemap.put(11,"UNARY_NEGATIVE");
        codemap.put(12,"UNARY_NOT");
        codemap.put(13,"UNARY_CONVERT");
        codemap.put(15,"UNARY_INVERT");
        codemap.put(19,"BINARY_POWER");
        codemap.put(20,"BINARY_MULTIPLY");
        codemap.put(21,"BINARY_DIVIDE");
        codemap.put(22,"BINARY_MODULO");
        codemap.put(23,"BINARY_ADD");
        codemap.put(24,"BINARY_SUBTRACT");
        codemap.put(25,"BINARY_SUBSCR");
        codemap.put(26,"BINARY_FLOOR_DIVIDE");
        codemap.put(27,"BINARY_TRUE_DIVIDE");
        codemap.put(28,"INPLACE_FLOOR_DIVIDE");
        codemap.put(29,"INPLACE_TRUE_DIVIDE");
        codemap.put(30,"SLICE");
        codemap.put(31,"SLICE_1");
        codemap.put(32,"SLICE_2");
        codemap.put(33,"SLICE_3");
        codemap.put(40,"STORE_SLICE");
        codemap.put(41,"STORE_SLICE_1");
        codemap.put(42,"STORE_SLICE_2");
        codemap.put(43,"STORE_SLICE_3");
        codemap.put(50,"DELETE_SLICE");
        codemap.put(51,"DELETE_SLICE_1");
        codemap.put(52,"DELETE_SLICE_2");
        codemap.put(53,"DELETE_SLICE_3");
        codemap.put(54,"STORE_MAP");
        codemap.put(55,"INPLACE_ADD");
        codemap.put(56,"INPLACE_SUBTRACT");
        codemap.put(57,"INPLACE_MULTIPLY");
        codemap.put(58,"INPLACE_DIVIDE");
        codemap.put(59,"INPLACE_MODULO");
        codemap.put(60,"STORE_SUBSCR");
        codemap.put(61,"DELETE_SUBSCR");
        codemap.put(62,"BINARY_LSHIFT");
        codemap.put(63,"BINARY_RSHIFT");
        codemap.put(64,"BINARY_AND");
        codemap.put(65,"BINARY_XOR");
        codemap.put(66,"BINARY_OR");
        codemap.put(67,"INPLACE_POWER");
        codemap.put(68,"GET_ITER");
        codemap.put(70,"PRINT_EXPR");
        codemap.put(71,"PRINT_ITEM");
        codemap.put(72,"PRINT_NEWLINE");
        codemap.put(73,"PRINT_ITEM_TO");
        codemap.put(74,"PRINT_NEWLINE_TO");
        codemap.put(75,"INPLACE_LSHIFT");
        codemap.put(76,"INPLACE_RSHIFT");
        codemap.put(77,"INPLACE_AND");
        codemap.put(78,"INPLACE_XOR");
        codemap.put(79,"INPLACE_OR");
        codemap.put(80,"BREAK_LOOP");
        codemap.put(81,"WITH_CLEANUP");
        codemap.put(82,"LOAD_LOCALS");
        codemap.put(83,"RETURN_VALUE");
        codemap.put(84,"IMPORT_STAR");
        codemap.put(85,"EXEC_STMT");
        codemap.put(86,"YIELD_VALUE");
        codemap.put(87,"POP_BLOCK");
        codemap.put(88,"END_FINALLY");
        codemap.put(89,"BUILD_CLASS");
        codemap.put(90,"HAVE_ARGUMENT");
        codemap.put(90,"STORE_NAME");
        codemap.put(91,"DELETE_NAME");
        codemap.put(92,"UNPACK_SEQUENCE");
        codemap.put(93,"FOR_ITER");
        codemap.put(94,"LIST_APPEND");
        codemap.put(95,"STORE_ATTR");
        codemap.put(96,"DELETE_ATTR");
        codemap.put(97,"STORE_GLOBAL");
        codemap.put(98,"DELETE_GLOBAL");
        codemap.put(99,"DUP_TOPX");
        codemap.put(100,"LOAD_CONST");
        codemap.put(101,"LOAD_NAME");
        codemap.put(102,"BUILD_TUPLE");
        codemap.put(103,"BUILD_LIST");
        codemap.put(104,"BUILD_SET");
        codemap.put(105,"BUILD_MAP");
        codemap.put(106,"LOAD_ATTR");
        codemap.put(107,"COMPARE_OP");
        codemap.put(108,"IMPORT_NAME");
        codemap.put(109,"IMPORT_FROM");
        codemap.put(110,"JUMP_FORWARD");
        codemap.put(111,"JUMP_IF_FALSE_OR_POP");
        codemap.put(112,"JUMP_IF_TRUE_OR_POP");
        codemap.put(113,"JUMP_ABSOLUTE");
        codemap.put(114,"POP_JUMP_IF_FALSE");
        codemap.put(115,"POP_JUMP_IF_TRUE");
        codemap.put(116,"LOAD_GLOBAL");
        codemap.put(119,"CONTINUE_LOOP");
        codemap.put(120,"SETUP_LOOP");
        codemap.put(121,"SETUP_EXCEPT");
        codemap.put(122,"SETUP_FINALLY");
        codemap.put(124,"LOAD_FAST");
        codemap.put(125,"STORE_FAST");
        codemap.put(126,"DELETE_FAST");
        codemap.put(130,"RAISE_VARARGS");
        codemap.put(131,"CALL_FUNCTION");
        codemap.put(132,"MAKE_FUNCTION");
        codemap.put(133,"BUILD_SLICE");
        codemap.put(134,"MAKE_CLOSURE");
        codemap.put(135,"LOAD_CLOSURE");
        codemap.put(136,"LOAD_DEREF");
        codemap.put(137,"STORE_DEREF");
        codemap.put(140,"CALL_FUNCTION_VAR");
        codemap.put(141,"CALL_FUNCTION_KW");
        codemap.put(142,"CALL_FUNCTION_VAR_KW");
        codemap.put(143,"SETUP_WITH");
        codemap.put(145,"EXTENDED_ARG");
        codemap.put(146,"SET_ADD");
        codemap.put(147,"MAP_ADD");
        return codemap;
    }
    // from jython/src/org/python/core/OpCode.java
    public static final int PyCmp_LT        =  0;
    public static final int PyCmp_LE        =  1;
    public static final int PyCmp_EQ        =  2;
    public static final int PyCmp_NE        =  3;
    public static final int PyCmp_GT        =  4;
    public static final int PyCmp_GE        =  5;
    public static final int PyCmp_IN        =  6;
    public static final int PyCmp_NOT_IN    =  7;
    public static final int PyCmp_IS        =  8;
    public static final int PyCmp_IS_NOT    =  9;
    public static final int PyCmp_EXC_MATCH = 10;
    public static final int PyCmp_BAD       = 11;
}