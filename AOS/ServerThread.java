import java.io.*;
import java.net.Socket;
/**
 * Thread Class
 */
public class ServerThread implements Runnable{

    Socket socket;
    Integer client;

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Reverses the string
     * @param input : A string that needs to reveresed
     * @return reversed string
     */
    public String reverseString(String input){
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }

    /**
     * this method is called whenever start() method is called over this class' object.
     */
    @Override
    public void run() {
        PrintWriter outputToClient = null;
        try {
            //creating input reader and output writer.
            outputToClient = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader readFromClient = new BufferedReader((new InputStreamReader(this.socket.getInputStream())));
            outputToClient.println("Welcome Client "+ this.getClient()+", Please provide string to be reversed " );

            //receiving request from the client
            String request = "";
            while(!"s".equalsIgnoreCase(request)) {
                request = readFromClient.readLine();
                System.out.println("Received request from Client " + this.getClient() + " to reverse the string: '"+request+"'");
                //calling reverse method
                String response;
                if(null != request)
                    response = this.reverseString(request);
                else
                    response = "Null String recived";
                //sending the response to client
                outputToClient.println("Reverse is: '"+ response + "' #press 's' to stop or input another string to continue..");
                System.out.println("Processing Complete for Client " + this.getClient());
            }

        }catch(IOException ioe){
            System.out.println("ERROR : "+ ioe.getMessage() + " for Client "+getClient());
        }finally {
            if(null != this.socket && !this.socket.isClosed()){
                try{
                    this.socket.close();
                }
                catch(Exception e){
                    System.out.println(e.getMessage() + getClient());
                }
            }
            if(null != outputToClient){
                    outputToClient.close();
            }
        }
    }
}
