import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.net.Socket;
import java.net.SocketException;


public class ProcessoThread extends Thread{

    private Socket socket;
    
    public ProcessoThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        try {
        InputStreamReader inputReader = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(inputReader);
        String x;
        while((x = reader.readLine()) != null){
            System.out.println("Client: " + x);
        }
       
        }
        catch (SocketException b) {
            System.out.println("hi" + b);
            
         }catch (Exception A) {
            System.out.println("Lol" + A);
         } 
    }

    public int pain(int value){
        return value;
    }
    public boolean blessing(){
        return true;
    }
}
