/*
Autor: charly y rogelio
 */
package Proyecto_U3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author asus
 */
public class Proyec_U3 {

    class nodo {
	String mujer ;
        String nombre;
        float saldo;
        nodo anterior;
        nodo sigue;
        String nip;
    }
    nodo inicio = null;
    nodo fin = null;
    nodo auxiliar = null;

    public void altas() throws IOException {
        Scanner leer = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodo nuevo = new nodo();
        System.out.println("Ingresa Nombre Del Cliente ");
        nuevo.nombre = br.readLine();
        System.out.println("Ingresa Tu Nip");
        nuevo.nip = leer.next();
        System.out.println("Ingresa La Cantidad a Depositar");
        nuevo.saldo = leer.nextFloat();
        nuevo.anterior = null;
        nuevo.sigue = null;
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {

            if (nuevo.nombre.compareTo(inicio.nombre) == 0) {
                System.out.println("Dato Repetido");
            } else {
                if (nuevo.nombre.compareTo(inicio.nombre) < 0) {
                    nuevo.sigue = inicio;
                    inicio.anterior = nuevo;
                    inicio = nuevo;
                } else {
                    if (nuevo.nombre.compareTo(fin.nombre) == 0) {
                        System.out.println("Dato Repetido");
                    } else {
                        if (nuevo.nombre.compareTo(fin.nombre) > 0) {
                            nuevo.anterior = fin;
                            fin.sigue = nuevo;
                            fin = nuevo;
                        } else {
                            auxiliar = inicio.sigue;
                            do {
                                if (nuevo.nombre.compareTo(auxiliar.nombre) == 0) {
                                    System.out.println("Dato Repetido");
                                    auxiliar = null;
                                } else {
                                    if (nuevo.nombre.compareTo(auxiliar.nombre) <= 0) {
                                        auxiliar.anterior.sigue = nuevo;
                                        nuevo.anterior = auxiliar.anterior;
                                        nuevo.sigue = auxiliar;
                                        auxiliar.anterior = nuevo;
                                        auxiliar = null;
                                    } else {
                                        auxiliar = auxiliar.sigue;
                                    }
                                }
                            } while (auxiliar != null);
                        }

                    }
                }
            }
        }
    }
public void eliminar() throws IOException{
    String borrar;
        if(inicio==null){
            System.out.println("No Hay Datos No puedes Borrar ");
        }else{
        Scanner leer=new Scanner(System.in);
        System.out.println("Ingresa El Nip a Eliminar ");
        borrar=leer.next();
        auxiliar=inicio;
        if(auxiliar==null){
            System.out.println("Lista Vacia");
        }
        else{
             if(inicio==fin){
                    System.out.println("Solo Tienes Un Clinete ");
                    System.out.println("Escribe continuar Para Eliminar ");
                    System.out.println("Salir Para Regresar Al Menu");
                    String com=leer.next();
                    switch(com){
                        case "continuar":
                            inicio=null;
                            fin=null;
                            System.out.println("Lista Vacia");
                            break;
                        default:
                            break;
                    }
                }
                else{
            if(borrar.compareTo(inicio.nip)==0){
                inicio=inicio.sigue;
                inicio.anterior=null;
                
            }
            else{
                if(borrar.compareTo(fin.nip)==0){
                    fin=fin.anterior;
                    inicio.sigue=null;
                }
                else{
                    auxiliar=auxiliar.sigue;
                    do{
                        if(borrar.compareTo(auxiliar.nip)==0){
                          auxiliar.anterior.sigue=auxiliar.sigue;
                          auxiliar=null;
                        }
                        else{
                            auxiliar=auxiliar.sigue;
                        }
                    }while(auxiliar!=null);
                    
                }
            
            }
        }
        }
        }
         
     }
      
    

    public void mostrar() {
        auxiliar = inicio;
        int n = 0;
        if (inicio == null) {
            System.out.println("No Hay Clientes En La Lista ");
        } else {
            do {
                n++;
                System.out.println("Cliente Numero " + n + "  " + auxiliar.nombre + " Saldo Ingresado " + auxiliar.saldo );
                auxiliar = auxiliar.sigue;
            } while (auxiliar != null);
        }
    }

    public int verificacion(String nip) {
        int c = 0;
        Scanner leer = new Scanner(System.in);
        auxiliar = inicio;
        do {
            if (auxiliar.nip.equals(nip)) {
                //System.out.println("Nip correcto");
                c++;
                auxiliar = null;
            } else {

                auxiliar = auxiliar.sigue;
            }

        } while (auxiliar != null);

        return c;
    }
    int x = 0;

    public void consultas(String ni) {
        x=0;
        if (inicio == null) {
            System.out.println("lista Vacia");
        } else {
            auxiliar = inicio;
            do {
                x++;
                if (auxiliar.nip.equals(ni)) {
                    System.out.println("");
                    System.out.println("Numero de Cliente: " + x + " Nombre: "+auxiliar.nombre + " Saldo:  " + auxiliar.saldo);
                    auxiliar = null;
                } else {
                    auxiliar = auxiliar.sigue;
                }
            } while (auxiliar != null);
        }
    }

    public void depositos(String np, float mas) {
        if (inicio == null) {
            System.out.println("lista vacia");
        } else {
            auxiliar = inicio;
            do {
                if (auxiliar.nip.equals(np)) {
                    System.out.println("");
                    auxiliar.saldo = auxiliar.saldo + mas;
                    System.out.println("Deposito Exitoso");
                    System.out.println(" Cuenta Actualizada:  "+auxiliar.nombre+"  Saldo:  "+auxiliar.saldo);
                    auxiliar = null;
                } else {
                    auxiliar = auxiliar.sigue;
                }
            } while (auxiliar != null);
        }
    }

    public void depositos(float men, String nip) {
        if (inicio == null) {
            System.out.println("lista vacia");
        } else {
            auxiliar = inicio;
            do {
                if (auxiliar.nip.equals(nip)) {
                    System.out.println("");
                    if(auxiliar.saldo<men){
                        System.out.println("Saldo Insuficiente");
                        auxiliar=null;
                    }
                    else{
                    auxiliar.saldo = auxiliar.saldo - men;
                    System.out.println("Retiro Exitoso");
                    System.out.println(" Cuenta Actualizada:  "+auxiliar.nombre+"  Saldo:   "+auxiliar.saldo);
                    auxiliar = null;
                    }
                } else {
                    auxiliar = auxiliar.sigue;
                }
            } while (auxiliar != null);
        }
    }

    public void pago_servicios(float men, String nip) {
        float aux = 0;
        if (inicio == null) {
            System.out.println("lista Vacia");
        } else {
            auxiliar = inicio;
            do {
                if (auxiliar.nip.equals(nip)) {
                    System.out.println("");
                    aux = men + 9;
                    if(auxiliar.saldo<aux){
                        auxiliar=null;
                        System.out.println("Saldo Insuficiente");
                    }
                    else{
                    auxiliar.saldo = auxiliar.saldo - aux;
                    System.out.println("Pago Exitoso ");
                    System.out.println(" Cuenta Actualizada:  "+auxiliar.nombre+"  Saldo: "+auxiliar.saldo);
                    auxiliar = null;}
                } else {
                    auxiliar = auxiliar.sigue;
                    //System.out.println("Transaccion invalida");
                }
            } while (auxiliar != null);
        }
    }
public void con(String ni) {
    Scanner leer = new Scanner(System.in);
        x=0;
        if (inicio == null) {
            System.out.println("lista Vacia");
        } else {
            auxiliar = inicio;
            do {
                x++;
                if (auxiliar.nip.equals(ni)) {
                    do{
                    System.out.println("Ingresa Tu Nuevo Nip");
                    String newnip=leer.next();
                    System.out.println("Confirma Tu Nuevo Nip");
                    String newnip1=leer.next();
                    if(newnip.equals(newnip1)){
                    auxiliar.nip=newnip;
                        System.out.println("Operacion Correcta");
                        auxiliar=null;
                    }else
                    {
                        System.out.println("Operacion Invalida");
                    }
                }while(auxiliar!=null);
                    auxiliar = null;
                } else {
                    auxiliar = auxiliar.sigue;
                }
            } while (auxiliar != null);
        }
    }
    public static void main(String[] args) throws IOException {
        Proyec_U3 p = new Proyec_U3();
        int pago = 0, cliente = 0, operacionbalquidia=0;
        String nip;
        int x = 0;
        int op = 0, op_1 = 0;
        String admi;
        Scanner leer = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("");
            System.out.println("*****Bancarnal*****");
            System.out.println("***Administrador****1");
            System.out.println("***Cliente**********2");
            System.out.println("***Salir************3");
            op_1 = leer.nextInt();
            switch (op_1) {
                case 1:
                    System.out.println("Ingresa El Pasword");
                    admi = leer.next();
                    if (admi.equals("karlos") || admi.equals("roge")) {
                        do {
                            System.out.println("");
                            System.out.println("");
                            System.out.println("---Bancarnal---");
                            System.out.println("---Ingresar Cliente--- 1");
                            System.out.println("---Eliminar Cliente--- 2");
                            System.out.println("---Mostrar Lista------ 3");
                            System.out.println("---Salir-------------- 4");
                            op = leer.nextInt();
                            switch (op) {
                                case 1:
                                    p.altas();
                                    break;
                                case 2:
                                    p.eliminar();
                                    break;
                                case 3:
                                    p.mostrar();
                                    break;
                                default:
                                    break;
                            }
                        } while (op != 4);
                    } else {
                        System.out.println("Pasword Incorrecto");
                    }
                    break;
                case 2:
                    if(p.inicio==null){
                        System.out.println("Lista Vacia No Hay Clientes En La Lista");
                        break;
                    }
                    else{
                    do {
                        System.out.println("");
                        System.out.println("");
                        System.out.println("---BANCARNAL---");
                        System.out.println("---Consulta de Saldo---- 1");
                        System.out.println("---Depositos------------ 2");
                        System.out.println("---Retiro De Efectivo--- 3");
                        System.out.println("---Pagos De Servicios--- 4");
                        System.out.println("---Atencion a Clientes---5");
                        System.out.println("---Salir-----------------6");
                        cliente = leer.nextInt();
                        int z = 0;
                        switch (cliente) {
                            case 1:
                                int c = 0;
                                do {
                                    c++;
                                    System.out.println("Ingresa El Nip");
                                    nip = leer.next();
                                    p.verificacion(nip);
                                    if (p.verificacion(nip) == 1) {
                                        System.out.println("Nip Correcto");
                                        break;
                                    } else {
                                        System.out.println("Nip Incorrecto");
                                    }
                                    if (c == 3) {
                                        System.out.println("Ingresaste De Manera Erronea 3 Veces Tu Nip Intentalo Mas Tarde");
                                    }
                                } while (c != 3);
                                p.consultas(nip);
                                break;
                            case 2:
                               int  m = 0;
                                do {
                                    m++;
                                    System.out.println("Ingresa El Nip");
                                    nip = leer.next();
                                    p.verificacion(nip);
                                    if (p.verificacion(nip) == 1) {
                                        System.out.println("Nip Correcto");
                                        break;
                                    } else {
                                        System.out.println("Nip Incorrecto");
                                    }
                                    if (m== 3) {
                                        System.out.println("Ingresaste De Manera Erronea 3 Veces Tu Nip Intentalo Mas Tarde");
                                    }
                                } while (m != 3);
                                if(m==3){}
                                else{
                                System.out.println("Ingrese La Cantidad ");
                                float mas = leer.nextFloat();
                                p.depositos(nip, mas);
                               // break;
                                }
                            break;
                            case 3:
                                c = 0;
                                do {
                                    c++;
                                    System.out.println("Ingresa El Nip De Tu Cuenta ");
                                    System.out.println("");
                                    nip = leer.next();
                                    p.verificacion(nip);
                                    if (p.verificacion(nip) == 1) {
                                        System.out.println("Nip Correcto");
                                        break;
                                    } else {
                                        System.out.println("Nip Incorrecto");
                                    }
                                    if (c == 3) {
                                        System.out.println("Ingresaste De Manera Erronea 3 Veces Tu Nip Intentalo Mas Tarde");
                                    }
                                } while (c != 3);
                                if(c==3){}
                                else{
                                System.out.println("Ingrese La Cantidad ");
                                float menos = leer.nextFloat();
                                p.depositos(menos, nip);
                                }
                                break;
                            case 4:
                                do {
                                    System.out.println("");
                                    System.out.println("");
                                    System.out.println("Pagos De Servicios Bancarnal");
                                    System.out.println("");
                                    System.out.println("Le Recordamos De La  Comicion De 9 Pesos Por Este Servicio");
                                    System.out.println("Agua -----1");
                                    System.out.println("Luz ------2");
                                    System.out.println("Telefono--3");
                                    System.out.println("Salir-----4");
                                    System.out.println("");
                                    pago = leer.nextInt();
                                    switch (pago) {
                                        case 1:
                                            c = 0;
                                            do {
                                                c++;
                                                System.out.println("Ingresa El Nip De Tu Cuenta ");
                                                System.out.println("");
                                                nip = leer.next();
                                                p.verificacion(nip);
                                                if (p.verificacion(nip) == 1) {
                                                    System.out.println("Nip Correcto");
                                                    break;
                                                } else {
                                                    System.out.println("Nip Incorrecto");
                                                }
                                                if (c == 3) {
                                                    System.out.println("Ingresaste De Manera Erronea 3 Veces Tu Nip Intentalo Mas Tarde");
                                                }
                                            } while (c != 3);
                                            if(c==3){}
                                            else{
                                            System.out.println("Ingrese La Cantidad a Depositar ");
                                            float pago_A = leer.nextFloat();
                                            p.pago_servicios(pago_A, nip);
                                            }
                                            break;
                                        case 2:
                                            c = 0;
                                            do {
                                                c++;
                                                System.out.println("Ingresa El Nip De Tu Cuenta ");
                                                System.out.println("");
                                                nip = leer.next();
                                                p.verificacion(nip);
                                                if (p.verificacion(nip) == 1) {
                                                    System.out.println("Nip Correcto");
                                                    break;
                                                } else {
                                                    System.out.println("Nip Incorrecto");
                                                }
                                                if (c == 3) {
                                                    System.out.println("Ingresaste De Manera Erronea 3 Veces Tu Nip Intentalo Mas Tarde");
                                                }
                                            } while (c != 3);
                                            if(c==3){}
                                            else{
                                            System.out.println("Ingrese La Cantidad a Depositar ");
                                            float pagoa_A = leer.nextFloat();
                                            p.pago_servicios(pagoa_A, nip);
                                            }
                                            break;
                                        case 3:
                                            c = 0;
                                            do {
                                                c++;
                                                System.out.println("Ingresa El Nip De Tu Cuenta ");
                                                System.out.println("");
                                                nip = leer.next();
                                                p.verificacion(nip);
                                                if (p.verificacion(nip) == 1) {
                                                    System.out.println("Nip Correcto");
                                                    break;
                                                } else {
                                                    System.out.println("Nip Incorrecto");
                                                }
                                                if (c == 3) {
                                                    System.out.println("Ingresaste De Manera Erronea 3 Veces Tu Nip Intentalo Mas Tarde");
                                                }
                                            } while (c != 3);
                                            if(c==3){}
                                            else{
                                            System.out.println("Ingrese La Cantidad a Depositar ");
                                            float ago_A = leer.nextFloat();
                                            p.pago_servicios(ago_A, nip);
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                } while (pago != 4);
                                break;
                            case 5:
                                int ser=0;
                                System.out.println("");
                                System.out.println("");
                                System.out.println("Atencion Clientes Bancarnal");
                                System.out.println("Afiliado ----1");
                                System.out.println("No Afiliado--2");
                                ser=leer.nextInt();
                                switch(ser){
                                    case 1:
                                        System.out.println("Cambiar Contraseña --- 1");
                                        System.out.println("Salir -----------------2");
                                        int a=leer.nextInt();
                                        switch(a){
                                            case 1:
                                                System.out.println("Ingresa Tu Contraseña");
                                                String l=leer.next();
                                                p.con(l);
                                                break;
                                            case 2:
                                                break;
                                            default:
                                                break;
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Quieres Ser Parte De Bancarnal");
                                        System.out.println("Continuar ---1");
                                        System.out.println("Salir--------2");
                                        int f=leer.nextInt();
                                        switch(f){
                                            case 1:
                                                p.altas();
                                                break;
                                            case 2:
                                                break;
                                            default: 
                                                break;
                                        }
                                }
                                break;
                            case 6:
                                break;
                            default:
                                break;
                        }
                    } while (cliente != 6);
                    break;
                    }
                 case 3:
                  System.exit(0);
                   break;
            }
        } while (op != 3);
    }

}
