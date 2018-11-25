import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server
 */
public class StringReverseServer {
    /**
     * This method creates the server socket and spawns a thread upon request from client.
     * This method will continue to wait for other clients once the thread for the current client has been created successfully.
     * @param port : the port at which the server sockets need to be created
     */
    public void createServerSocket(Integer port){
        try{
            //creating server socket
            ServerSocket server = new ServerSocket(port);
            //client id for keeping track of clients
            int client = 0;

            while(true){
                if(client == 0)
                    System.out.println("Waiting for Client...");
                else
                    System.out.println("Waiting for another Client...");

                //waiting for the client for connection
                Socket socket =  server.accept();

                System.out.println("Client Connected with ID :" + client);
                //creating thread upon client request for connection
                ServerThread serverThread = new ServerThread();
                serverThread.setSocket(socket);
                serverThread.setClient(client++);
                Thread t = new Thread(serverThread);
                t.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args)throws Exception {
        StringReverseServer s =  new StringReverseServer();
        s.createServerSocket(6577);
    }
}
