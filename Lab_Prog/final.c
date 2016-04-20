#include <string.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <locale.h>

/*
*   David Delgado Hernandez
*   150205
*   Laboratorio de programacion
*   Viernes 2:00-4:00
*/
// definicion de gotoxy cambiar luego por algo mejor
 #include <windows.h>
 void gotoxy(int x,int y){
      HANDLE hcon;
      hcon = GetStdHandle(STD_OUTPUT_HANDLE);
      COORD dwPos;
      dwPos.X = x;
      dwPos.Y= y;
      SetConsoleCursorPosition(hcon,dwPos);
 }

// estas variables son cadenas para facilitar su validacion
    char telefono[8];
    char peso[5];
    char edad[5];
    char estatura[5];
    char nombre[60];
    int genero;

// funcion para validar digitos
int validarDigitos(char entrada[]){
    // verifica que todos sean numeros
    // 0 si esta bien
    // 1 si esta mal
    int i = 0;
    int rt = 0;

    for ( i = 0; i < strlen(entrada); ++i)
    {
        if (!isdigit(entrada[i]))
        {
            if (entrada[i] != '.' )
            {
                // el punto tambien debe ser valido
                rt =1;
                break;
            }
        }
    }
    return rt;
}

// N.enfermedad,N.mensaje,L.mensaje
char mensajes[7][9][60];
// Array Dieta
// Calorias,desayuno, colación, comida, colación, cena,Act.fisica
char dieta[7][60];

void llenarMensaje(char str[],int pila,int idx){
    strcpy(mensajes[pila][idx],str);
}

void llenarDieta(char str[],int idx){
    // en el indice idx lo llena con una cadena de longitud <= 60
    strcpy(dieta[idx],str);
}

void llenarTodosMensajes(){
    // lo mejor seria usar archivos en esta parte pero si nos dejan

    // llenar la matriz de mensajes
    // diabetes
    llenarMensaje("Ingrese el nivel de diabetes si padece\n",0,0);
    llenarMensaje("1- Diabetes tipo 1\n",0,1);
    llenarMensaje("2- Diabetes tipo 2\n",0,2);
    llenarMensaje("3- Algun familiar padece diabetes\n",0,3);
    llenarMensaje("0- Ninguno\n",0,4);
    llenarMensaje("El paciente padece Diabetes tipo 1\n",0,5);
    llenarMensaje("El paciente padece Diabetes tipo 2\n",0,6);
    llenarMensaje("Algun familiar padece diabetes \n",0,7);
    llenarMensaje("Sin problemas de diabetes\n",0,8);

    // trigliceridos
    llenarMensaje("Ingrese el nivel de trigliceridos si padece\n",1,0);
    llenarMensaje("1- Limítrofe alto: 150 a 199 mg/dL\n",1,1);
    llenarMensaje("2- Alto: 200 a 499 mg/dL\n",1,2);
    llenarMensaje("3- Muy alto: 500 mg/dL o superior\n",1,3);
    llenarMensaje("0- Ninguno\n",1,4);
    llenarMensaje("El paciente padece trigliceridos Limítrofe\n",1,5);
    llenarMensaje("El paciente padece trigliceridos Alto\n",1,6);
    llenarMensaje("EL paciente tiene trigliceridos Muy Alto \n",1,7);
    llenarMensaje("Sin problemas de trigliceridos\n",1,8);

    // alzhaimer
    llenarMensaje("Ingrese el nivel de Alzhaimer si padece\n",2,0);
    llenarMensaje("1- Etapa temprana (leve)\n",2,1);
    llenarMensaje("2- Etapa moderada (intermedia)\n",2,2);
    llenarMensaje("3- Etapa avanzada (severa)\n",2,3);
    llenarMensaje("0- Ninguno\n",2,4);
    llenarMensaje("El paciente padece Alzhaimer en etapa temprana (leve)\n",2,5);
    llenarMensaje("El paciente padece Alzhaimer en etapa moderada (intermedia)\n",2,6);
    llenarMensaje("EL paciente tiene Alzhaimer en etapa avanzada (severa) \n",2,7);
    llenarMensaje("Sin problemas de Alzhaimer\n",2,8);

    //anemia
    llenarMensaje("Ingrese el tipo de anemia si padece\n",3,0);
    llenarMensaje("1- por deficiencia de B12\n",3,1);
    llenarMensaje("2- por deficiencia de folato\n",3,2);
    llenarMensaje("3- ferropénica\n",3,3);
    llenarMensaje("0- Ninguno\n",3,4);
    llenarMensaje("El paciente padece anemia por deficiencia de B12\n",3,5);
    llenarMensaje("El paciente padece anemia por deficiencia de folato\n",3,6);
    llenarMensaje("EL paciente tiene anemia ferropénica \n",3,7);
    llenarMensaje("Sin problemas de anemia\n",3,8);

    // colesterol
    llenarMensaje("Ingrese el nivel de colesterol\n",4,0);
    llenarMensaje("1- Normal: menos de 200 mg/dl\n",4,1);
    llenarMensaje("2- Normal-alto: entre 200 y 240 mg/dl.\n",4,2);
    llenarMensaje("3- Alto: por encima de 240 mg/dl\n",4,3);
    llenarMensaje("0- Ninguno\n",4,4);
    llenarMensaje("El paciente tiene colesterol normal\n",4,5);
    llenarMensaje("El paciente tiene colesterol normal-alto\n",4,6);
    llenarMensaje("El paciente tiene colesterol alto\n",4,7);
    llenarMensaje("El paciente no tiene registro de colesterol\n",4,8);

    // paro cardiaco
    llenarMensaje("Ingrese numero de paros cardiacos\n",5,0);
    llenarMensaje("1- 1 paro cardiaco\n",5,1);
    llenarMensaje("2- 2 paros cardiacos\n",5,2);
    llenarMensaje("3- 3 paros cardiacos\n",5,3);
    llenarMensaje("0- Ninguno\n",5,4);
    llenarMensaje("El paciente tuvo 1 paro cardiaco\n",5,5);
    llenarMensaje("El paciente tuvo 2 paros cardiacos\n",5,6);
    llenarMensaje("El paciente tuvo 3 paros cardiacos\n",5,7);
    llenarMensaje("El paciente no tuvo paros cardiacos\n",5,8);

    // Alergias
    llenarMensaje("Ingrese el tipo de alergias (si padece)\n",6,0);
    llenarMensaje("1- Productos del maiz\n",6,1);
    llenarMensaje("2- Productos Lacteos\n",6,2);
    llenarMensaje("3- Granos de trigo o productos que contienen gluten\n",6,3);
    llenarMensaje("0- Ninguno\n",6,4);
    llenarMensaje("El paciente padece alergia a los productos de maiz\n",6,5);
    llenarMensaje("El paciente padece alergia a los productos lacteos\n",6,6);
    llenarMensaje("El paciente padece alergia a los productos con gluten\n",6,7);
    llenarMensaje("El paciente no padece alergias\n",6,8);
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

int evaluarEnfermedad(int numEnfermedad){
    // Evalua los padecimientos devueve el nivel que padece
    int flag;
    int nivel = 9;

        do
        {
            flag = 1;
            printf(mensajes[numEnfermedad][0]);
            printf(mensajes[numEnfermedad][1]);
            printf(mensajes[numEnfermedad][2]);
            printf(mensajes[numEnfermedad][3]);
            printf(mensajes[numEnfermedad][4]);

            fflush(stdin);
            scanf("%d",&nivel);

            if (nivel < 0 || nivel > 3)
            {
                flag = 0;
            }

        } while (flag != 1);
        system("cls");

    if (nivel == 0)
    {
        nivel = 8;
    }
    else{
        nivel +=4;
    }

    return nivel;
}

void bienvenida(){
    char resp[3];
    char si[3] = "y";

    // Mensaje de bienvenida
    gotoxy(10,3);
    printf("Bienvenido al sistema de asignacion de dietas\n");
    gotoxy(10,4);
    printf("\t---David Delgado Hernández---\n");
    gotoxy(10,5);
    printf("\n\n\n");

    gotoxy(10,9);
    printf("Este programa le ayudara a asignar una dieta en base a\n");
    gotoxy(10,10);
    printf("las caracteristicas y deficiencias en los pacientes\n");
    gotoxy(10,11);
    printf("se le ofrecera una dieta para 5 comidas\n");
    gotoxy(10,12);
    printf("Desea continuar?(y/n)\n");
    scanf("%s",&resp);

    if (strcmp(si,resp) == 0)
    {

        printf("\n\nIngresa el nombre del paciente\n");
        fflush(stdin);
        // el nombre puede contener espacios
        scanf("%[^\n]",&nombre);
        system("cls");
        while(1){

            printf("Ingrese su telefono(7 digitos)\n");
            fflush(stdin);
            // El numero de telefono debe tener 7 digitos
            scanf("%s",&telefono);
            if (strlen(telefono) != 7 || validarDigitos(telefono) == 1)
            {
                printf("El numero debe tener 7 digitos\n");
            }
            else{
                break;
            }
        }
        system("cls");
        do{
            fflush(stdin);
            printf("Ingrese su peso\n");
            scanf("%s",&peso);
            if (validarDigitos(peso) == 1)
            {
                printf("Valor erroneo\n");
            }
            else{
                break;
            }
        }while(1);
        system("cls");

        do
        {
            printf("Ingrese su edad\n");
            fflush(stdin);
            scanf("%s",&edad);

            if (validarDigitos(edad) == 1)
            {
                printf("Valor erroneo\n");
            }
            else{
                break;
            }

        } while (1);
        system("cls");
        do
        {
            printf("Ingrese su estatura(en metros)\n");
            fflush(stdin);
            scanf("%s",&estatura);
            if ( validarDigitos(estatura)== 1)
            {
                printf("Valor Erroneo\n");
            }
            else{
                break;
            }
        } while (1);
        system("cls");
        do
        {
            printf("Ingrese su genero\n");
            printf("1- masculino\n");
            printf("2- femenino\n");
            fflush(stdin);
            scanf("%d",&genero);
            if (genero == 2 || genero == 1)
            {
                break;
            }


        } while (1);
        system("cls");
        return;
    }
}
// funcion para calcular las calorias
/*
*    hombre
*    peso * 10 + 625*altura - 5*edad + 5
*
*   mujeres
*   peso * 10 + 625*altura - 5*edad + 161
*/
char* calcularCalorias(){
    int calorias;
    char strcalorias[60];

    calorias = (atof(peso) * 10) + (atof(estatura)*625) - (atoi(edad) * 5);

    if (genero == 1)
    {
        calorias += 5;
    }
    else{
        calorias += 161;
    }

    // convertir cadena a entero
    sprintf(strcalorias,"Calorias necesarias: %d\n",calorias);
    return strcalorias;
}


// Funcion para evaluar las condiciones para las dietas

int evaluarDieta(int nivelSub[]){
    // los arrarys se pasan por valor no por referencia asi que hago una copia
    // para no afectar a los datos
    int i;
    int result;
    int nivel[7];
    /* orden niveles
    *   nivel[0] diabetes
    *   nivel[1] trigliceridos
    *   nivel[2] alzhaimer
    *   nivel[3] anemia
    *   nivel[4] colesterol
    *   nivel[5] paros cardiacos
    *   nivel[6] alergias
    */
    for ( i = 0; i < 7; ++i)
    {
        // dado que anteriormente sumamos 4 a los niveles para poder usuarlos
        // con los mensajes ahora restamos esos 4
        nivel[i] = nivelSub[i] - 4;
    }
    result = 1;
    // TODO : las que no entren dentro de este rango deben ser 0

    // dietas A)
    // Diabetes (opción 1), colesterol (opción 1), triglicéridos (opcion1),
    // anemia (opción 1),Alzheimer (opción 1)
    if (nivel[0] == 1 && nivel[1] == 1 && nivel[2] == 1 && nivel[3] == 1 && nivel[4] == 1 )
    {
        // llenar la dieta
        // Array Dieta
        // Calorias,desayuno, colación, comida, colación, cena,Act.fisica
        llenarDieta(calcularCalorias(),0);
        llenarDieta("Desayuno: café con leche semidesnatada y bocadillito mini\n",1);
        llenarDieta("Colacion: macedonia de fruta.\n",2);
        llenarDieta("Comida: Verduras salteadas con almendras\n",3);
        llenarDieta("Colacion: mandarinas\n",4);
        llenarDieta("Cena: puré de calabacín con merluza a la plancha \n",5);
        llenarDieta("Actividad Fisica: Correr por la mañana \n",6);
    }

    // dietas B)
    // Diabetes (opción 1), colesterol (opción 2), triglicéridos (opcion3),
    // anemia (opción 3), paro cardiaco (opción 1).

    else if (nivel[0] == 1 && nivel[4] == 2 && nivel[1] == 3 && nivel[3] == 3 && nivel[5] == 1 )
    {
        llenarDieta(calcularCalorias(),0);
        llenarDieta("Desayuno: té con leche con 2 kiwis\n",1);
        llenarDieta("Colacion: bocadillito con yogur líquido desnatado\n",2);
        llenarDieta("Comida: Ensalada con lentejas, habas o guisantes\n",3);
        llenarDieta("Colacion: mango\n",4);
        llenarDieta("Cena: ensalada completa\n",5);
        llenarDieta("Actividad Fisica: Natacion\n",6);
    }
    // dietas C)
    // Diabetes (opción 2), colesterol (opción 3),triglicéridos (opcion2),
    // anemia (opción 3), Alzheimer (opción 2), Alergias (opción 1).

    else if (nivel[0] == 2 && nivel[4] == 3 && nivel[1] == 2 && nivel[3] == 3 && nivel[2] == 2
        && nivel[6] == 1 )
    {
        llenarDieta(calcularCalorias(),0);
        llenarDieta("Desayuno: café con leche con sándwich de tomate y queso fresco\n",1);
        llenarDieta("Colacion: yogur natural desnatado edulcorado con nueces\n",2);
        llenarDieta("Comida: Garbanzos con acelgas o espinacas y huevo duro\n",3);
        llenarDieta("Colacion: melón\n",4);
        llenarDieta("Cena: Verdurita al vapor o lékué con pollo\n",5);
        llenarDieta("Actividad Fisica: Pilates\n",6);
    }
    // dietas D)
    // triglicéridos (opcion1), anemia (opción 3), alergias (opción 3).
    else if (nivel[1] == 1 && nivel[3] == 3 && nivel[2] == 1 && nivel[6] == 3  )
    {
        llenarDieta(calcularCalorias(),0);
        llenarDieta("Desayuno: leche con cereales de desayuno.\n",1);
        llenarDieta("Colacion: manzana con yogur desnatado edulcorado.\n",2);
        llenarDieta("Comida: Ensalada con patatas y atún, caballa o sardinas en aceite\n",3);
        llenarDieta("Colacion: plátano\n",4);
        llenarDieta("Cena: Ensalada de tomate y ventresca de atún\n",5);
        llenarDieta("Actividad Fisica: Jugar al aire libre\n",6);
    }
    // dietas E)
    // colesterol (opción 3), Alzheimer (opción 3). Alergias (opción 2).
    else if (nivel[4] == 3 && nivel[2] == 3 && nivel[6] == 2 )
    {
        llenarDieta(calcularCalorias(),0);
        llenarDieta("Desayuno: Tortitas de arroz con mermelada ecológica\n",1);
        llenarDieta("Colacion: Yogur con frutos secos y miel\n",2);
        llenarDieta("Comida: Carne de conejo guisado con cebolla\n",3);
        llenarDieta("Colacion: Fruta seca dulce: ciruelas pasas, dátiles...\n",4);
        llenarDieta("Cena: Brécol con patatas al vapor\n",5);
        llenarDieta("Actividad Fisica: Spinning\n",6);
    }
    else{
        for ( i = 0; i < 6; ++i)
        {
            // vaciar el array
            llenarDieta("",i);
        }
        llenarDieta("No existe dieta para este paciente\n",6);
        result = 0;
    }
    return result;
}

void main(){
    setlocale(LC_ALL, "spanish");
    int i;
    int niveles[7];
    int result;
    char resp[3];
    char si[3] = "y";

    system("color 3F");
    // damos la bienvenida y capturamos los datos
    volveraEvaluar:
    bienvenida();
    // llenar la matriz de mensajes
    llenarTodosMensajes();
    //evaluar diabetes
    niveles[0]= evaluarEnfermedad(0);
    // evaluar trigliceridos
    niveles[1]= evaluarEnfermedad(1);
    // evaluar alzahaimer
    niveles[2]= evaluarEnfermedad(2);
    // evaluar anemia
    niveles[3]= evaluarEnfermedad(3);
    // evaluar colesterol
    niveles[4]= evaluarEnfermedad(4);
    // paro cardiaco
    niveles[5]= evaluarEnfermedad(5);
    //alergias
    niveles[6]= evaluarEnfermedad(6);

    // Con los niveles obtendremos las dietas
    // devuelve 0 si no existe dieta
    result = evaluarDieta(niveles);

    // imprimir los datos del paciente
    printf("Nombre: %s\n",nombre);
    printf("Telefono: %s\n", telefono);
    printf("Edad: %s\n", edad);
    printf("Peso: %s kg\n", peso);
    printf("Estatura: %s m\n", estatura);
    if (genero == 1)
    {
        printf("Genero: Masculino\n");
    }
    else{
        printf("Genero: Femenino\n");
    }

    // imprimir las enfermedades
    imprimirSeparador();
    for ( i = 0; i < 7; ++i)
    {
        gotoxy(5,i+8);
        printf("%s", mensajes[i][niveles[i]]);
    }
    imprimirSeparador();

    // imprimir las dietas
    // si no exite imprimir advertencia guardada en ultimo lugar del array
    if (result == 1)
    {
        for (i = 0; i < 7; ++i)
        {
            gotoxy(5,i+17);
            printf("%s", dieta[i]);
        }
    }
    else{
        printf("%s\n", dieta[6]);
        printf("Desea volver a realizar la evaluacion(y/n)\n");
        fflush(stdout);
        scanf("%s",&resp);

        if (strcmp(si,resp) == 0){
            system("cls");
            goto volveraEvaluar;
        }
    }
    imprimirSeparador();
}
