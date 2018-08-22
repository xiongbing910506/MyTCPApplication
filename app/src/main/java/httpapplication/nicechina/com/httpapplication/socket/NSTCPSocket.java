package httpapplication.nicechina.com.httpapplication.socket;

import android.util.Log;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.ConnectCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.WritableCallback;

public class NSTCPSocket {

    /**
     *  Socket短连接
     *  向服务器发送一次命令
     *  @param order
     * */
    public void shortConnectionSendOrder(final String order){
        AsyncServer.getDefault().connectSocket("10.0.2.2", 2404, new ConnectCallback() {
            @Override
            public void onConnectCompleted(Exception ex, AsyncSocket socket) {

                if (ex!=null) {
                    Log.d("Socket","连接出错");
                    return;
                }

                socket.setDataCallback(new DataCallback() {
                    @Override
                    public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                        Log.d("Socket", "接收到：" + new String(bb.getAllByteArray()));
                    }
                });

                socket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex!=null) {
                            Log.d("Socket", "setClosedCallback出错");
                            return;
                        }
                        Log.d("Socket", "setClosedCallback");
                    }
                });

                socket.setEndCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex!=null) {
                            Log.d("Socket", "setEndCallback出错");
                            return;
                        }
                        Log.d("Socket", "setEndCallback");
                    }
                });

                socket.setWriteableCallback(new WritableCallback() {
                    @Override
                    public void onWriteable() {
                        Log.d("Socket", "onWriteable");
                    }
                });

                Util.writeAll(socket, order.getBytes(), new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex!=null) {
                            Log.d("Socket", "writeAll出错");
                            return;
                        }
                        Log.d("Socket", "发送了："+order);
                    }
                });
            }});
    }
}
