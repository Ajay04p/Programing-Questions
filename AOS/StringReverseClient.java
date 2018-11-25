import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client
 */
public class StringReverseClient {
    /**
     * This method creates a client socket with servers' IP and port and connects to the servers' socket.
     * @param ipAddress: Servers' IP address
     * @param port : Servers' port at which it is listening
     */
    public void connectToServer(String ipAddress, Integer port){
        Socket client = null;
        PrintWriter outputToServer;
        try{
            //creating client socket
            client = new Socket(ipAddress, port);
            //creating input readers and output writers
            BufferedReader readFromServer = new BufferedReader((new InputStreamReader(client.getInputStream())));
            outputToServer = new PrintWriter(client.getOutputStream(), true);
            //creating input reader for the user
            Scanner sc = new Scanner(System.in);

            //initial response from the server
            String response = readFromServer.readLine();
            System.out.println(response);
            String input = "";
            //this will run till the user inputs 's' as the string.
            while(!"s".equalsIgnoreCase(input)) {

                input = sc.nextLine();
                if("s".equalsIgnoreCase(input)){
                    break;
                }
                //sending request for string reversal
                outputToServer.println(input);

                //recieving response from the server as the reversed string
                response = readFromServer.readLine();

                //displaying the response to the user console
                System.out.println(response);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(null != client && !client.isClosed()){
                try{
                    client.close();
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }


    }

    public static void main(String[] args) {
        StringReverseClient s = new StringReverseClient();
        String host = "127.0.0.1";
        int p = 6577;
        s.connectToServer(host,p);

    }
}
