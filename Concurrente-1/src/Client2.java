import java.io.DataInputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        // Crea el socket del cliente conectándose al servidor en el puerto 5000
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Conexión establecida con el servidor");

        // Obtiene los flujos de entrada y salida del socket
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        
        
        
        while (true) {
        	System.out.println("Seleccione una operación:");
            System.out.println("1. Calculadora.");
            System.out.println("2.Dime la hora y el día.");
            System.out.println("3.Guardar texto.");
            System.out.println("4.Cargar texto.");
        	int operacion = scanner.nextInt();
            outputStream.writeInt(operacion);
        	
        	if (operacion == 0) {
                break;
            }
        	
        	switch(operacion) {//Swithc grande
            
            
            case 1:{
            	System.out.println("Seleccione una tarea:");
                System.out.println("1. Suma");
                System.out.println("2. Resta");
                System.out.println("3. Multiplicación");
                System.out.println("4. División");
                

                
                

                // Envía la operación seleccionada al servidor
                System.out.print("Operación: ");
                operacion = scanner.nextInt();
                outputStream.writeInt(operacion);

                

                switch (operacion) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        System.out.print("Introduce el primer número: ");
                        int num1 = scanner.nextInt();

                        System.out.print("Introduce el segundo número: ");
                        int num2 = scanner.nextInt();

                        // Envía los números al servidor
                        outputStream.writeInt(num1);
                        outputStream.writeInt(num2);

                        // Recibe el resultado del servidor
                        int resultado = inputStream.readInt();

                        System.out.println("Resultado: " + resultado);
                        break;
                    default:
                        // Recibe el mensaje del servidor indicando que la operación no es válida
                        String mensaje = inputStream.readUTF();
                        System.out.println(mensaje);
                        break;
                }
            	
            }break;
            
            case 2:{
            	String mensaje = String.valueOf(inputStream.readUTF());
            	System.out.println(mensaje);
            	//outputStream.flush();
            }break;
            
           
            default:{
            	String mensaje = inputStream.readUTF();
                System.out.println(mensaje);
            }
            }//Swithc grande
        	
        	
       
       
        }//Fin while.

        // Cierra el socket del cliente
        socket.close();
    }
}