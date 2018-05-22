package ServerII;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerListener extends Thread {
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(7400);
            // 循环的监听
            while (true) {
                Socket socket = serverSocket.accept();// 阻塞
                MyServerSocket.addtext("Someone has connected...");
                MyServerSocket.addtext("ip is:"+ socket.getInetAddress().toString().replace("/",""));
                ChatSocket cs = new ChatSocket(socket);
                cs.start();
                //把socket加入ChatManager
                ChatManager.getChatManager().add(cs);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}