#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>
#include <time.h>

// librerias del diablo
#include <windows.h>
#include <conio.h>
// sonido del diablo
#include <iostream>
#include <mmsystem.h>
#pragma comment( lib, "winmm" )
using namespace std;

#ifndef HEIGHT
#define HEIGHT 40
#endif

#ifndef WIDTH
#define WIDTH 40
#endif

#ifndef ESPACIO_DATOS
#define ESPACIO_DATOS 20
#endif

#ifndef RUTA_LETREROS
#define RUTA_LETREROS "letreros.txt"
#endif

#ifndef RUTA_PUNTAJE
#define RUTA_PUNTAJE "puntaje.dat"
#endif

/*
*   David Delgado Hernandez
*   150205
*   Laboratorio de programacion
*   Viernes 2:00-4:00
*/

//coordenadas
typedef struct
{
    int x;
    int y;
    int numero;
}coordenada;

// puntajes
typedef struct
{
    char nombre[30];
    // movimientos
    int puntaje;
    // 1 descendente, 2 horizontal, 3 caracol
    int modo;
}puntaje;

// funciones generales
void gotoxy(int,int);
void init(void);
bool validarDigitos(char *entrada);
bool validarCaracter(char *entrada);
void imprimirSeparador(void);
bool bienvenida(void);
void ingresarDatos(puntaje* jugador);
//funciones juego dibujo
void llenarMapa(char mapa[HEIGHT][WIDTH+1]);
void dibujarMapa(char mapa[HEIGHT][WIDTH+1]);
void colocarNumero(coordenada *cord,bool rewrite);
void loop(puntaje*);
void imprimirCoordenadas(void);
void imprimirDatos(puntaje jugador,coordenada cord);
void playSound(int sonido);
// funciones que generan informacion
int generarNumero(int []);
coordenada generarPosicion(int numero);
void generarVacio(void);
coordenada obtenerPosicionVacia(void);
void validarPosicionFinal(puntaje jugador);
void imprimirDesdeArchivo(int Nmensaje);
// funciones de manipulacion de datos
coordenada validarCasilla(char);
void moverCasilla(coordenada *posicion);
void cheatCode(int modo,int arreglo[3][3]);
void agregaPuntacion(puntaje jugador);
void leerPuntaje(void);
void organizarPuntaje(puntaje registro[5]);
void Play(void);

// representacion matrizial de casillas llenas
int posiciones[3][3];
// lista con los arreglos

int main()
{
    init();
    if (bienvenida())
        Play();
    return 0;
}

void init(){

    system("color 1D ");
    system("MODE CON cols=120 lines=80");
    srand(time(NULL));

    // inicializa los parametros de 4
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; i < 3; ++i)
            posiciones[i][j] = 0;
    }
}

void Play(){
        puntaje jugador;
        system("cls");
        //esperar 3 segundos
        ingresarDatos(&jugador);
        printf("jugador %s\n", jugador.nombre);

        do
        {
            printf("Ingrese el modo de juego\n");
            printf("1- descendente\n");
            printf("2- ascendente\n");
            printf("3- caracol\n");
            fflush(stdin);
            scanf("%d",&jugador.modo);
            if (jugador.modo == 1 || jugador.modo == 2 || jugador.modo == 3)
                break;
            else
                printf("\nValor erroneo\n");
        } while (true);
        system("cls");
        // entrar en el loop del juego
        loop(&jugador);
        validarPosicionFinal(jugador);

}

void loop(puntaje* jugador){
    
    coordenada pos;
    char sigMov;
    jugador->puntaje = 0;
    // lista de numeros para no repetir
    int numeros[8];
    //  cuadricula
    char mapa[HEIGHT][WIDTH+1];

    for (int i = 0; i < 8; ++i)
        numeros[i] = 0;

    system("cls");
    llenarMapa(mapa);
    dibujarMapa(mapa);
    imprimirCoordenadas();
    // genera la casilla vacio de 1 a 9(nulo)
    generarVacio();

    for (int i = 0; i < 8; ++i)
    {
        // coordenadas reiniciar cada 3
        numeros[i] = generarNumero(numeros);

        pos = generarPosicion(numeros[i]);
        // colocar las posiciones (iniciales)
        colocarNumero(&pos,false);
    }

    coordenada newpos;
    while(true){
        imprimirDatos(*jugador,obtenerPosicionVacia());
        gotoxy(40 + 20,24);
        sigMov = getch();
        // capturar el siguiente movimiento
        newpos = validarCasilla(sigMov);
        if (newpos.numero == 100)
        {
            // detener el juego y revisar la posicion de las casillas
            break;
        }
        else if (newpos.numero == 1000)
        {
            cheatCode(jugador->modo,posiciones);
            for (int i = 0; i < 3; ++i)
            {
                for (int j = 0; j < 3; ++j)
                {
                    newpos.y = i+1;
                    newpos.x = j+1;
                    newpos.numero = posiciones[i][j];
                    colocarNumero(&newpos,true);

                }
            }
        }
        else if (newpos.numero != 10)
        {
            playSound(1);
            moverCasilla(&newpos);
            jugador->puntaje++;
        }
    }
}



bool bienvenida(void){
    playSound(2);

    char resp[3];
    char si[3] = "y";
    bool rtrn = false;
    imprimirSeparador();
    gotoxy(10,3);
    printf("Bienvenido al sistema de juego cuadro magico\n");
    gotoxy(10,5);
    printf("Objetivo:\n");
    gotoxy(10,6);
    printf("Debes de resolver el cubo ordenando numericamente\n");
    gotoxy(10,7);
    printf("de forma ascendente, descendente, y caracol\n");
    gotoxy(10,8);
    printf("Reglas del juego\n");
    gotoxy(10,9);
    printf("Solo puedes mover los cuadros cuando tengan\n");
    gotoxy(10,10);
    printf("un cuadro vacio adyacente\n");
    gotoxy(10,11);
    printf("Deseas ingresar al juego(y/n)\n");
    scanf("%s",&resp);
    gotoxy(10,14);
    if (strcmp(si,resp) != 0){
        printf("Hasta Pronto\n");
        system("exit");
    }
    else{
        printf("Bienvenido\n");
        rtrn = true;
    }
    gotoxy(10,16);
    return rtrn;
}

void ingresarDatos(puntaje* jugador){
    do
    {
        printf("Ingresa tu nombre\n");
        fflush(stdin);
        scanf("%[^\n]",jugador->nombre);

        if (validarCaracter(jugador->nombre))
        {
            break;
        }
        else{
            printf("Valor Erroneo\n");
        }
    } while (true);
}

// definicion de gotoxy
 #include <windows.h>
 void gotoxy(int x,int y){
      HANDLE hcon;
      hcon = GetStdHandle(STD_OUTPUT_HANDLE);
      COORD dwPos;
      dwPos.X = x;
      dwPos.Y= y;
      SetConsoleCursorPosition(hcon,dwPos);
 }
 // definicion para cambiar colores
 void setColors(int color){
    HANDLE hConsole;
    hConsole = GetStdHandle(STD_OUTPUT_HANDLE);
    SetConsoleTextAttribute(hConsole, color);
}
//Reproductor de sonidos
void playSound(int sonido){
    // reproduce sonidos de forma asincrona
    switch(sonido){
        case 1:
            PlaySound(TEXT("soundcasilla.wav"), NULL, SND_FILENAME | SND_ASYNC);
            break;
        case 2:
            PlaySound(TEXT("intro.wav"), NULL, SND_FILENAME | SND_ASYNC);
            break;
    }
}



 // funcion para validar digitos
bool validarDigitos(char *entrada){
    // verifica que todos sean numeros
    // 1 si esta bien
    // 0 si esta mal
    int i = 0;
    int rt = true;

    for ( i = 0; i < strlen(entrada); ++i)
    {
        if (!isdigit(entrada[i]))
        {

            rt = false;
            break;
        }
    }
    return rt;
}

 // funcion para validar caracteres
bool validarCaracter(char *entrada){
    // verifica que todos sean caracteres
    // 1 si esta bien
    // 0 si esta mal
    int i = 0;
    int rt = true;

    for ( i = 0; i < strlen(entrada); ++i)
    {
        if (!isalpha(entrada[i]) )
        {
            if (!isspace(entrada[i]))
            {
                // el punto tambien debe ser valido
                rt = false;
                break;
            }
        }
    }
    return rt;
}

void imprimirSeparador(){
    int i;
    printf("\n");
    for (i = 0; i < 80; ++i)
    {
        printf("#");
    }
    printf("\n");
}

void llenarMapa(char mapa [HEIGHT][WIDTH+1]){
    // llena el marco inmutable(cuadricula)

    int i,j;
    int contH = 1;
    int divH = HEIGHT / 3;
    int contV = 1;
    int divV = WIDTH / 3;

    for ( i = 0; i <HEIGHT; ++i)
    {
        for (j = 0; j < WIDTH; ++j)
        {
            if (contV == 3)
                contV = 1;
            if (i == 0 || i == HEIGHT -1 || i == (contH * divH))
                mapa[i][j] = '#';//dibuja las columnas
            else if (j == 0 || j == WIDTH -1 || j == (contV * divV))
                mapa[i][j] = '#';// dibuja las filas
            else
                mapa[i][j] = ' ';
            if (j ==(contV*divV))
                contV++;
        }
        if (i == (contH*divH) )
            contH++;
        // llenar los espacios
        mapa[i][WIDTH] = '*';
    }
}

void dibujarMapa(char mapa[HEIGHT][WIDTH+1]){
    int i,j;
    for ( i = 0; i < HEIGHT; ++i)
    {
        for ( j = 0; j < WIDTH+1; ++j)
        {
            // reemplza * por espacios
            if (mapa[i][j] == '*')
                printf("\n");
            else{
                if (mapa[i][j] == '#')
                    setColors(0);
                else if (mapa[i][j] == ' ')
                    setColors(68);
                printf("%c",mapa[i][j]);
            }
        }
    }
    setColors(28);
}

int generarNumero(int numeros[]){
    int numero;
    bool flag = false;

    while(true){
        numero = rand() %8 +1;
        for (int i = 0; i < 8; ++i)
        {
            if (numero == numeros[i])
            {
                flag = false;
                break;
            }
            else
                flag = true;
        }
        if (flag)
            break;
    }
    return numero;
}


coordenada generarPosicion(int numero){
    // regresa una coordenada libre
    coordenada posicion;
    bool flag = false;

    while(true){
        // detener el ciclo cuando se encuentre una posicion libre
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
            {
                // 0 es una posicion vacia
                if (posiciones[i][j] == 0)
                {
                    posicion.y = i+1;
                    posicion.x = j+1;
                    flag = true;
                    posiciones[i][j] = numero;
                    break;
                }
            }
            if (flag)
                break;
        }
        if (flag)
            break;
    }
    posicion.numero = numero;
    return posicion;
}

void generarVacio(void){
    // genera la posicion en la que  se encuetra la casilla vacia
    int x = (rand() % 3) + 1;
    int y = (rand() % 3) + 1;
    posiciones[y][x] = 9;
}

void colocarNumero(coordenada *cord,bool rewrite){

    // colocar el numero en la posicion indicada
    int posY;
    int posX;
    posY = (cord->y * (HEIGHT / 3)) - ((HEIGHT/3)/2);
    posX = cord->x * (WIDTH / 3) - ((WIDTH/3)/2);
    gotoxy(posX,posY);
    if (rewrite)
    {
        gotoxy(posX,posY);
        if (cord->numero == 0 || cord->numero == 9)
            printf(" ");
        else
            printf("%d\n", cord->numero);
    }
    else if (cord->numero != 9 &&  cord->numero != 0)
    {
        // dibujar las posiciones
        for (int i = 0; i < 9; ++i)
        {
            gotoxy(posX,posY);
            printf("%d\n", i);
            if (i == cord->numero)
                break;
            Sleep(150);
        }
    }
}

void imprimirCoordenadas(){
    for (int i = 1; i <= 3; ++i)
    {
        gotoxy(40+3,(i * (HEIGHT / 3)) - ((HEIGHT/3)/2));
        printf("%d\n", i);
    }
    char letraCord  = '@';
    for (int i = 1; i <= 3; ++i)
    {
        gotoxy(i * (WIDTH / 3) - ((WIDTH/3)/2),40+3);
        printf("%c\n", letraCord+i);
    }
}

coordenada obtenerPosicionVacia(void){
    coordenada vacio;
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            if (posiciones[i][j] == 0 || posiciones [i][j] == 9)
            {
                vacio.x = i+1;
                vacio.y = j+1;
                vacio. numero = 0;
            }
        }
    }
    return vacio;
}

void imprimirDatos(puntaje jugador,coordenada vacio){
    gotoxy(40 + ESPACIO_DATOS,10);
    printf("Jugador:\n");
    gotoxy(40 + ESPACIO_DATOS,12);
    printf("%s\n", jugador.nombre);
    gotoxy(40 + ESPACIO_DATOS,14);
    printf("Movimientos\n");
    gotoxy(40 + ESPACIO_DATOS,16);
    printf("%d\n", jugador.puntaje);

    //imprimir la seleccion de tipo de juego
    // 1 descendente, 2 horizontal, 3 caracol
    gotoxy(40 + ESPACIO_DATOS,18);
    printf("Modo de juego:\n");
    gotoxy(40 + ESPACIO_DATOS,20);
    switch(jugador.modo){
        case 1:
            printf("Descendente\n");
            break;
        case 2:
            printf("Horizontal\n");
            break;
        case 3:
            printf("Caracol\n");
            break;
    }
    gotoxy(40 + ESPACIO_DATOS,20);
    printf("Comandos:\n");
    gotoxy(40 + ESPACIO_DATOS,22);
    printf("Origen\n");
    // aqui va el scanf (24)
    gotoxy(40 + ESPACIO_DATOS,26);
    printf("Destino\n");
    // aqui  van las coordenadas a donde se puede mover
    gotoxy(40 + ESPACIO_DATOS,28);
    char Y = '@';
    printf("%d%c\n",vacio.x,Y+(vacio.y));
    gotoxy(10,45);
    printf("Al terminar presione ESC\n");
}

// cambiar la posicion de una casilla
coordenada validarCasilla(char sigMov){
    // validar los datos convertir a entero y revisar si esta dentro del rango
    // retorna 10 si es invalido
    // retorna 100 si es ESC
    coordenada newpos = {0,0,0};
    coordenada vacio = obtenerPosicionVacia();

    switch(sigMov){
        case 77:
            //arriba
            newpos.y = vacio.y -1;
            newpos.x = vacio.x;
            break;
        case 75:
            // abajo
            newpos.y = vacio.y+1;
            newpos.x = vacio.x;
            break;
        case 80:
            //izquierda
            newpos.y = vacio.y;
            newpos.x = vacio.x-1;
            break;
        case 72:
            // derecha
            newpos.y = vacio.y;
            newpos.x = vacio.x+1;
            break;
        case 27:
            // ESC
            newpos.numero = 100;
            break;
        case 82:
            // INSERT cheat code
            printf("Insert");
            newpos.numero = 1000;
            break;
        default:
            // ninguna
            newpos.numero = 10;
    }
    // revisar que las coordenadas existan dentro de los limites del marco
    if (newpos.numero != 100 && newpos.numero != 1000)
    {
        if (newpos.x-1 < 0 || newpos.x -1 > 2 )
            newpos.numero = 10;
        if (newpos.y-1 < 0 || newpos.y -1 > 2)
            newpos.numero = 10;
        printf("%d %d\n", newpos.x,newpos.y);
    }
    // 10 es el valor para movimiento erroneo
    if (newpos.numero != 10 && newpos.numero != 100 && newpos.numero != 1000)
        newpos.numero = posiciones[newpos.x-1][newpos.y-1];
    return newpos;
}


void moverCasilla(coordenada *posicion){
    coordenada vacio = obtenerPosicionVacia();

    posiciones[vacio.x-1][vacio.y-1] = posicion->numero;
    posiciones[posicion->x-1][posicion->y-1] = 0;

    coordenada pos;

    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            pos.y = i+1;
            pos.x = j +1;
            pos.numero = posiciones[i][j];
            colocarNumero(&pos,true);
        }
    }
}

void cheatCode(int modo,int arreglo[3][3]){
    // ordena el array pasado como argumento
    // cambia las posiciones para ganar la partida de acuerdo al tipo de juegp
    int valor;
    if (modo == 1)
        valor = 1;
    else
        valor = 8;

    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            arreglo[i][j] = valor;
            if (modo == 1 )
                valor++;
            if(modo == 2)
                valor--;
            if (modo == 3)
            {
                if (i == 0)
                    arreglo[i][j] = j+1;
                else if (i == 2)
                    arreglo[i][j] = 7-j;
                else{
                    arreglo[i][0] = 8;
                    arreglo[i][2] = 4;
                    arreglo[i][1] = 0;
                }
            }
            if (valor == 9)
                valor = 0;
        }
    }
}

void validarPosicionFinal(puntaje jugador){
    // al finalizar el juego revisa dependiendo del modo de juego si la jugada
    // fue exitosa o no
    // 1 descendente, 2 horizontal, 3 caracol
    int orden[9],x = 0;
    bool win = true;
    system("cls");
    int correcto[3][3];

    cheatCode(jugador.modo,correcto);

    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
        {
            if (posiciones[i][j] != correcto[i][j] || win == false)
            {
                win = false;
                break;
            }
        }
    }
    setColors(177);
    if (win)
    {
        imprimirDesdeArchivo(1);
        agregaPuntacion(jugador);
    }
    else
        imprimirDesdeArchivo(2);

    printf("Cantidad de movimientos: %d\n",jugador.puntaje );
    for (int i = 0; i < 3; ++i)
    {
        for (int j = 0; j < 3; ++j)
            printf(" %d ",posiciones[i][j] );

        printf("\n");
    }
    leerPuntaje();
    printf("Deseas volver a jugar (y/n)\n");
    if (getch() == 'y')
    {
        system("cls");
        init();
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 3; ++j)
                posiciones[i][j] = 0;
        }
        Play();
    }
}

void imprimirDesdeArchivo(int Nmensaje){
    FILE *archivo;
    archivo = fopen(RUTA_LETREROS,"r");
    char buffer[30];
    
    if (archivo == NULL)
        printf("Error\n");

    // colocar el indice en el lugar apropiada dependiendo del mensaje
    if (Nmensaje == 1){
        setColors(139);
        fseek(archivo,0L,0);
    }
    else if(Nmensaje == 2){
        setColors(156);
        fseek(archivo,800L,0);
    }

    for(int i=0;i < 30;i++){
        // imprimir linea por linea
        if (fgets(buffer,30,archivo) != NULL)
            printf("%s",buffer);    
        
        strcpy(buffer,"");

    }
    setColors(157);
    printf("\n");
    fclose(archivo);
}

void agregaPuntacion(puntaje jugador){
    FILE *archivo;
    archivo = fopen(RUTA_PUNTAJE,"a");
    if (archivo != NULL)
    {
        fwrite(&jugador,sizeof(puntaje),1,archivo);
        fclose(archivo);
    }

}
void leerPuntaje(void){
    setColors(155);
    puntaje registro[5];
    organizarPuntaje(registro);
    printf("Puntajes mas altos\n");

    for (int i = 0; i < 5; ++i)
        printf("%s\t%d\n", registro[i].nombre,registro[i].puntaje);

    setColors(157);
}

void organizarPuntaje(puntaje registro[5]){
    // solo devuelve los 5 primeros lugares
    FILE *archivo;
    puntaje temp;
    archivo = fopen(RUTA_PUNTAJE,"r");
    puntaje *puntos;
    puntos = (puntaje*)malloc(10 * sizeof(puntaje));
    int cont = 0;

    for (int i = 0; i < 10; ++i)
        puntos[i].puntaje = 0;

    if (archivo != NULL)
    {
        while(!feof(archivo)){
            cont++;
            fread(&puntos[cont-1],sizeof(puntaje),1,archivo);
            if (cont % 10 == 0)
            {
                // genera un error cuando el registro se redimensiona por segunda ocasion
                puntos = (puntaje*)realloc(puntos,cont+10 * sizeof(puntaje));
                for (int i = cont; i <= cont+10 ; ++i)
                    puntos[i-1].puntaje = 0;
            }
        }

    }
    // ahora que tenemos todos los registros los organizamos
    // ignorar los ceros
    for (int i = 0; i < cont; ++i)
    {
        for (int j = 0; j < cont; ++j)
        {
            if (puntos[j].puntaje > puntos[i].puntaje)
            {
                temp = puntos[i];
                puntos[i] = puntos[j];
                puntos[j] = temp;
            }
        }
    }
    for (int i = 0; i < 5; ++i)
        registro[i] = puntos[i];
    free(puntos);
}
