package compiler;

public interface Constants extends ScannerConstants, ParserConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_Inteiro = 2;
    int t_identificador = 3;
    int t_REGRA = 4;
    int t_ALIAS = 5;
    int t_fim = 6;
    int t_e = 7;
    int t_se = 8;
    int t_ou = 9;
    int t_programa = 10;
    int t_entao = 11;
    int t_CNF = 12;
    int t_COMO = 13;
    int t_TOKEN_14 = 14; //"%"
    int t_TOKEN_15 = 15; //"="
    int t_TOKEN_16 = 16; //"<>"
    int t_TOKEN_17 = 17; //";"

}
