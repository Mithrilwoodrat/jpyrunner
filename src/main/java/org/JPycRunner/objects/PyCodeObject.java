package org.JPycRunner.objects;

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
//       Type is a void* to keep the format private in codeobject.c to force
//       people to go through the proper APIs. */
//        void *co_extra;
//        } PyCodeObject;

public class PyCodeObject extends  PyObject{
    public int co_argcount;
    public int co_kwonlyargcount;
    public int co_nlocals;
    public int co_stacksize;
    public int co_flags;
    public int co_firstlineno = -1;

    public byte[] co_code;

    public String co_consts[];
    public String co_names[];
    public String co_varnames[];
    public String co_freevars[];
    public String co_cellvars[];

    public long co_cell2arg;
    public String co_filename;
    public String co_name;
    public int co_lnotab;

    public PyCodeObject(int co_argcount, int co_kwonlyargcount, int co_nlocals, int co_stacksize, int co_flags,
                        int co_firstlineno, byte[] co_code, String[] co_consts, String[] co_names, String[] co_varnames,
                        String[] co_freevars, String[] co_cellvars, long co_cell2arg,
                        String co_filename, String co_name, int co_lnotab) {
        this.co_argcount = co_argcount;
        this.co_kwonlyargcount = co_kwonlyargcount;
        this.co_nlocals = co_nlocals;
        this.co_stacksize = co_stacksize;
        this.co_flags = co_flags;
        this.co_firstlineno = co_firstlineno;
        this.co_code = co_code;
        this.co_consts = co_consts;
        this.co_names = co_names;
        this.co_varnames = co_varnames;
        this.co_freevars = co_freevars;
        this.co_cellvars = co_cellvars;
        this.co_cell2arg = co_cell2arg;
        this.co_filename = co_filename;
        this.co_name = co_name;
        this.co_lnotab = co_lnotab;
    }
}