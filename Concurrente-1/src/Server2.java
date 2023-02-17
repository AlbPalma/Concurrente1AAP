import java.io.DataInputStream;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Server2 {
   
	public static void main(String[] args) throws IOException {
        // Crea el socket del servidor en el puerto 5000
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Servidor iniciado en el puerto 5000");

        while (true) {
            // Acepta la conexión entrante del cliente
            Socket socket = serverSocket.accept();
            System.out.println("Conexión aceptada desde " + socket.getInetAddress().getHostAddress());

            // Obtiene los flujos de entrada y salida del socket
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // Lee la operación enviada por el cliente
            int operacion = inputStream.readInt();
            
            switch(operacion) {//Switch grande.
            case 1:{
            	
            	operacion = inputStream.readInt();//Seleccionar el calculo.
            	switch (operacion) {//Swithc calculadora.
                 case 1:
                     // Recibe dos números del cliente
                     int num1 = inputStream.readInt();
                     int num2 = inputStream.readInt();

                     // Realiza la suma de los números y envía el resultado al cliente
                     int suma = num1 + num2;
                     outputStream.writeInt(suma);
                     break;
                 case 2:
                     // Recibe dos números del cliente
                     num1 = inputStream.readInt();
                     num2 = inputStream.readInt();

                     // Realiza la resta de los números y envía el resultado al cliente
                     int resta = num1 - num2;
                     outputStream.writeInt(resta);
                     break;
                 case 3:
                     // Recibe dos números del cliente
                     num1 = inputStream.readInt();
                     num2 = inputStream.readInt();

                     // Realiza la multiplicación de los números y envía el resultado al cliente
                     int producto = num1 * num2;
                     outputStream.writeInt(producto);
                     break;
                 case 4:
                     // Recibe dos números del cliente
                     num1 = inputStream.readInt();
                     num2 = inputStream.readInt();

                     // Realiza la división de los números y envía el resultado al cliente
                     int division = num1 / num2;
                     outputStream.writeInt(division);
                     break;
                 default:
                     // Envía un mensaje al cliente indicando que la operación no es válida
                     outputStream.writeUTF("Operación no válida");
                     break;
             }
            	
            }break;
            case 2:{
            	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                System.out.println("Fecha y Hora actual: " + dateFormat.format(date));
            	String fecha = "Fecha y hora actuales: "+dateFormat.format(date);
            	outputStream.writeUTF(fecha);
            }break;
            
            
            
            default:{
            	
            }
            
            }//Final Switch grande.
            //outputStream.flush(); 
        }//Fin while
    }
}