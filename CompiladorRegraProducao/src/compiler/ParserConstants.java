package compiler;

public interface ParserConstants
{
    int START_SYMBOL = 18;

    int FIRST_NON_TERMINAL    = 18;
    int FIRST_SEMANTIC_ACTION = 30;

    int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1,  5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1,  6, -1,  6, -1,  7, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1,  2, -1,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1,  4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1,  8, -1,  9, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, 13, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, 15, -1, 15, -1, -1, -1, -1, -1, 14, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 10, 11, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 }
    };

    int[][] PRODUCTIONS = 
    {
        { 31, 10,  3, 32, 17, 21,  6,  3, 33 },
        { 23, 22 },
        { 21 },
        {  0 },
        {  4, 34,  3, 35,  8, 19, 11, 36, 26, 27 },
        { 25, 20 },
        { 24, 19 },
        {  0 },
        {  7, 37 },
        {  9, 38 },
        { 15 },
        { 16 },
        {  3, 39, 28,  3, 40 },
        {  3, 41, 15,  3, 42 },
        { 12,  2, 43, 14 },
        {  0 }
    };

    String[] PARSER_ERROR =
    {
        "",
        "Era esperado fim de programa",
        "Era esperado Inteiro",
        "Era esperado identificador",
        "Era esperado REGRA",
        "Era esperado ALIAS",
        "Era esperado fim",
        "Era esperado e",
        "Era esperado se",
        "Era esperado ou",
        "Era esperado programa",
        "Era esperado entao",
        "Era esperado CNF",
        "Era esperado COMO",
        "Era esperado \"%\"",
        "Era esperado \"=\"",
        "Era esperado \"<>\"",
        "Era esperado \";\"",
        "<PROGRAMA> inválido",
        "<EXPRESSAO> inválido",
        "<EXPRESSAO2> inválido",
        "<LISTADEREGRAS> inválido",
        "<LISTADEREGRAS2> inválido",
        "<REGRA> inválido",
        "<COND> inválido",
        "<VALOR> inválido",
        "<RESULTADO> inválido",
        "<CNF> inválido",
        "<condicional> inválido",
        "<COMO> inválido"
    };
}
