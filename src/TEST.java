import java.io.*;
import java.net.Socket;

public class TEST {

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("119.23.110.11", 8500);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream(), "utf-8"));
            reader = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
            BufferedWriter finalWriter = writer;
            BufferedReader finalReader = reader;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            String sendData = "A";
                            finalWriter.write(sendData + "\n");//必须加上换行
                            finalWriter.flush();
                            //if (finalReader != null) {
                            System.out.println(finalReader.readLine());
                            //}
                        } catch (IOException e) {
                            System.out.println("Error!");
                            e.printStackTrace();
                        }
                    }

                }
            }).start();
        }
    }
