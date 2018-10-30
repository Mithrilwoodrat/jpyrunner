package org.JPycRunner.objects;
import org.JPycRunner.objects.OpCode;
/* Bytecode object */

//typedef struct _object {
//        Py_ssize_t ob_refcnt;
//        struct _typeobject *ob_type;
//        } PyObject;

//typedef struct {
//        PyObject_HEAD
//        int co_argcount;            /* #arguments, except *args */
//        int co_kwonlyargcount;      /* #keyword only arguments */
//        int co_nlocals;             /* #local variables */
//        int co_stacksize;           /* #entries needed for evaluation stack */
//        int co_flags;               /* CO_..., see below */
//        int co_firstlineno;         /* first source line number */
//        PyObject *co_code;          /* instruction opcodes */
//        PyObject *co_consts;        /* list (constants used) */
//        PyObject *co_names;         /* list of strings (names used) */
//        PyObject *co_varnames;      /* tuple of strings (local variable names) */
//        PyObject *co_freevars;      /* tuple of strings (free variable names) */
//        PyObject *co_cellvars;      /* tuple of strings (cell variable names) */
//    /* The rest aren't used in either hash or comparisons, except for co_name,
//       used in both. This is done to preserve the name and line number
//       for tracebacks and debuggers; otherwise, constant de-duplication
//       would collapse identical functions/lambdas defined on different lines.
//    */
//        Py_ssize_t *co_cell2arg;    /* Maps cell vars which are arguments. */
//        PyObject *co_filename;      /* unicode (where it was loaded from) */
//        PyObject *co_name;          /* unicode (name, for reference) */
//        PyObject *co_lnotab;        /* string (encoding addr<->lineno mapping) See
//                                   Objects/lnotab_notes.txt for details. */
//        void *co_zombieframe;       /* for optimization only (see frameobject.c) */
//        PyObject *co_weakreflist;   /* to support weakrefs to code objects */
//    /* Scratch space for extra data relating to the code object.
//       people to go through the proper APIs. */
//        void *co_extra;
//        } PyCodeObject;

public class PyCodeObject extends  PyObject{
    private static boolean debug = true;


    public int co_argcount;
    // public int co_kwonlyargcount;
    public int co_nlocals;
    public int co_stacksize;
    public int co_flags;

    public byte[] co_code;

    public String co_consts[];
    public String co_names[];
    public String co_varnames[];
    public String co_freevars[];
    public String co_cellvars[];

    // public long co_cell2arg;
    public String co_filename;
    public String co_name;
    public int co_firstlineno = -1;
    public String co_lnotab;

    public PyCodeObject(int co_argcount, int co_nlocals, int co_stacksize,
                        int co_flags, byte[] co_code, String[] co_consts,
                        String[] co_names, String[] co_varnames, String[] co_freevars,
                        String[] co_cellvars, String co_filename, String co_name,
                        int co_firstlineno, String co_lnotab) {
        this.co_argcount = co_argcount;
        this.co_nlocals = co_nlocals;
        this.co_stacksize = co_stacksize;
        this.co_flags = co_flags;
        this.co_code = co_code;
        this.co_consts = co_consts;
        this.co_names = co_names;
        this.co_varnames = co_varnames;
        this.co_freevars = co_freevars;
        this.co_cellvars = co_cellvars;
        this.co_filename = co_filename;
        this.co_name = co_name;
        this.co_firstlineno = co_firstlineno;
        this.co_lnotab = co_lnotab;
    }

    private static char getUnsigned(byte[] x, int i) {
        byte b = x[i];
        if (b < 0) {
            return (char) (b + 256);
        } else {
            return (char) b;
        }
    }

    private  String GetOparg(int opcode, int oparg) {
        if (opcode == OpCode.LOAD_CONST)
        {
            return co_consts[oparg];
        }
        else if (opcode == OpCode.LOAD_NAME || opcode == OpCode.STORE_NAME) {
            return co_names[oparg];
        }
        return null;
    }

    //ref: PyByteCode interpret
    public void call() {
        int opcode;    /* Current opcode */
        int oparg = 0; /* Current opcode argument, if any */
    }

    public void dis() {
        int next_instr = 0;
        int opcode;    /* Current opcode */
        int oparg = 0; /* Current opcode argument, if any */
        for (;next_instr < co_code.length ; ) {
            opcode = getUnsigned(co_code, next_instr);
            if (OpCode.has_args(opcode)) {
                next_instr += 2;
                oparg = (getUnsigned(co_code, next_instr) << 8) + getUnsigned(co_code, next_instr - 1);
                System.out.printf("opcode:%s , oparg: %d (%s)\n", OpCode.GetCode(opcode), oparg, GetOparg(opcode, oparg));
            }
            else{
                System.out.printf("opcode:%s\n", OpCode.GetCode(opcode));
            }
            next_instr += 1;
        }
    }
}