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