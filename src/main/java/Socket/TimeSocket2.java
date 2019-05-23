package Socket;


import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/clock")
public class TimeSocket2 {

    private Session session;
    private Timer timer = new Timer();

    @OnOpen
    public void onOpen(
            Session session) throws IOException, EncodeException {

        this.session = session;
        timer.schedule(new sendTime(session), 0, 1000);
    }


    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {

        timer.cancel();
    }

    @OnMessage
    public void onMessage(Session session, String message){
        timer.cancel();
        timer = new Timer();
        timer.schedule(new sendTime(session), 0, Integer.parseInt(message));
    }
    @OnError
    public void onError(Session session, Throwable throwable) {
        timer.cancel();
    }
}
class sendTime extends TimerTask {
    private Session session;
    public sendTime(Session sess){
        this.session = sess;
    }
    public void run() {
        synchronized (session){
            try {
                session.getBasicRemote().
                        sendText(java.time.LocalTime.now().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}