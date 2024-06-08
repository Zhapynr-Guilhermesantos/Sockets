import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Processo2 {
    public static void main(String[] args) throws IOException, InterruptedException {
    int power = 0;
    
    @SuppressWarnings("unused")
    int ocupado = 0;
  while(true){
    while(power == 0){
      method();
      Thread.sleep(1000);
      if(method() == false){
       power = 1;
      }
    while(power == 1){
        System.out.println("Admnistrador está aberto!");
        ServerSocket serverSocket = new ServerSocket(4000);
        Socket socket = serverSocket.accept();
        System.out.println("Processo se conectou...");
        ocupado = 1;
    try {
        InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
        PrintStream saida = new PrintStream(socket.getOutputStream());
        BufferedReader reader = new BufferedReader(inputReader);
        saida.println("x");
        String x;
        x = reader.readLine();

        System.out.println("Processo: " + x);
        saida.println("Recebido");    
            serverSocket.close();
            
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        ocupado = 0; 
       serverSocket.close();

    } catch (SocketException e) {
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        ocupado = 0; 
       serverSocket.close();
     }
   }
  }
  }
        
    }

    public static boolean method() throws IOException, InterruptedException{
      
      try {
        Socket socket = new Socket("localhost", 4000); 
          InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
           BufferedReader reader = new BufferedReader(inputReader);
           String x = reader.readLine();
           System.out.println(x);

          Scanner scan = new Scanner(System.in);
          ProcessoThread clientThread = new ProcessoThread(socket);
          clientThread.start();

            PrintStream saida = new PrintStream(socket.getOutputStream());
            String teclado = scan.next();
            saida.println(teclado);
        
         
          

      } catch (ConnectException a) {
        Thread.sleep(2000);
        return false;
      }catch (Exception e) {
        method();
      }
      return true;
    }
}