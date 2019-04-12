import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class encryption {
private Socket socket = null;
private InputStream inStream = null;
private OutputStream outStream = null;
static StringBuffer sb11=new StringBuffer();
public encryption() {
}
public void createSocket() {
try {
socket = new Socket("localHost", 3339);
System.out.println("Connected");
inStream = socket.getInputStream();
outStream = socket.getOutputStream();
createReadThread();
createWriteThread();
} catch (UnknownHostException u) {
u.printStackTrace();
} catch (IOException io) {
io.printStackTrace();
}
}
public void createReadThread() {
Thread readThread = new Thread() {
public void run() {
while (socket.isConnected()) {
try {
byte[] readBuffer = new byte[200];
int num = inStream.read(readBuffer);
if (num > 0) {
byte[] arrayBytes = new byte[num];
          String zz11[]=null;
          String abcd11;
          String xyz11=null;
          String xyz22=null;

System.arraycopy(readBuffer, 0, arrayBytes, 0, num);
String recvedMessage = new String(arrayBytes, "UTF-8");

 for(char ch11 : recvedMessage.toCharArray())
         {
            if(((int)ch11>=65 && (int)ch11<=90) || ((int)ch11>=97 && (int)ch11<=122)) 
                 xyz11=xyz11+((char)((int)ch11+100));
            else if((int)ch11>=48 && (int)ch11<=57)
                     xyz11=xyz11+((char)((int)ch11-25)); 
            else
                     xyz11=xyz11+(ch11);
         }
          xyz11=xyz11.substring(5,(xyz11.length())); 
          System.out.println(xyz11); 
          //int aa11=sb11.indexOf(xyz11);
         // System.out.println(aa11);
/*
          if(aa11>=0)
          {         
           //zz11=sb11.substring(aa11,(aa11+20)).split(","); 
          for(char ch11 : sb11.substring(aa11,sb11.length()).toCharArray())
         {
            if(((int)ch11>=165 && (int)ch11<=190) || ((int)ch11>=197 && (int)ch11<=222)) 
                 xyz22=xyz22+((char)((int)ch11-100));
            else if((int)ch11>=(48-25) && (int)ch11<=(57-25))
                     xyz22=xyz22+((char)((int)ch11+25)); 
            else
                     xyz22=xyz22+(ch11);
          }
          xyz22=xyz22.substring(3,(xyz22.length()));
          System.out.println(xyz22);
          } 

*/
System.out.println("Received message :" + recvedMessage);
}/* else {
// notify();
}*/
;
//System.arraycopy();
}catch (SocketException se){
System.exit(0);
} catch (IOException i) {
i.printStackTrace();
}
}
}
};
readThread.setPriority(Thread.MAX_PRIORITY);
readThread.start();
}
public void createWriteThread() {
Thread writeThread = new Thread() {
public void run() {
while (socket.isConnected()) {
try {
BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
sleep(100);
String typedMessage = inputReader.readLine();

 for(char ch11 : typedMessage.toCharArray())
     {
       if(((int)ch11>=65 && (int)ch11<=90) || ((int)ch11>=97 && (int)ch11<=122)) 
        sb11.append((char)((int)ch11+100));
       else if((int)ch11>=48 && (int)ch11<=57)
            sb11.append((char)((int)ch11-25)); 
         else
            sb11.append(ch11);
     }
     //System.out.println(abcd);
     System.out.println(sb11);

if ( typedMessage!= null && typedMessage.length() > 0) {
synchronized (socket) {
outStream.write(typedMessage.getBytes("UTF-8"));
sleep(100);
}
}
;
//System.arraycopy();
} catch (IOException i) {
i.printStackTrace();
} catch (InterruptedException ie) {
ie.printStackTrace();
}
}
}
};
writeThread.setPriority(Thread.MAX_PRIORITY);
writeThread.start();
}
public static void main(String[] args) throws Exception {
encryption myChatClient = new encryption();
myChatClient.createSocket();
/*myChatClient.createReadThread();
myChatClient.createWriteThread();*/
}
}