package httpapplication.nicechina.com.httpapplication.socket;

/**
 * 1、生成SOCKET
 * 2、发送数据
 * 3、处理错误机制
 * 4、错误接收机制
 * 5、数据接收机制
 */

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.koushikdutta.async.AsyncServer;
import com.koushikdutta.async.AsyncSocket;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.Util;
import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.ConnectCallback;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.callback.WritableCallback;

public class NSTCPSocketForLong implements ConnectCallback {
    public static final int m_errorSocket = 0x01;
    private Handler mErrorHandler = null;
    private Handler mDataHandlr = null;
    //socket
    private AsyncSocket socket = null;
    private static NSTCPSocketForLong sendSocketData;

    private NSTCPSocketForLong() {
        AsyncServer.getDefault().connectSocket("127.0.0.1", 8103, this);
    }

    public static NSTCPSocketForLong getInstance() {
        return SingletonHolder.instance();
    }

    @Override
    public void onConnectCompleted(Exception ex, AsyncSocket socket) {
        if (ex != null) {
            this.processError(m_errorSocket,ex.getMessage());
            return;
        }
        socket.setDataCallback(new DataCallback() {
            @Override
            public void onDataAvailable(DataEmitter emitter, ByteBufferList bb) {
                if(null != mDataHandlr){
                    Message msg = new Message();
                    msg.obj = new String(bb.getAllByteArray());
                    mDataHandlr.sendMessage(msg);
                }
                else {
                    Log.d("Socket", "接收到：" + new String(bb.getAllByteArray()));
                }
            }
        });
        socket.setClosedCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex != null) {
                    Log.d("Socket", "setClosedCallback出错");
                    return;
                }
                Log.d("Socket", "setClosedCallback");
            }
        });
        socket.setEndCallback(new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex != null) {
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

        this.socket = socket;
        System.out.println("Socket连接成功");
    }

    //初始化错误接收机制
    public void initErrorHandler(Handler handler){
        mErrorHandler = handler;
    }
    private void processError(int errorCode, String errorInfo){
        if(null != mErrorHandler) {
            Message msg = new Message();
            msg.what = errorCode;
            msg.obj = errorInfo;
            mErrorHandler.sendMessage(msg);
        }
        else{
            System.out.println("错误码：" + errorCode + "," + "错误信息：" + errorInfo);
        }
    }

    //初始化数据处理机制
    public void initDataHandler(Handler handler){
        mDataHandlr = handler;
    }
    //清除数据处理机制
    public void clearDataHandler(){
        mDataHandlr = null;
    }

    public void sendData(final String data){
        if (socket == null) {
            processError(m_errorSocket,"网络异常");
            return;
        }
        Util.writeAll(socket, data.getBytes(), new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex != null) {
                    processError(m_errorSocket,"数据发送失败");
                    return;
                }
                System.out.println("发送数据：" + data);
            }
        });
    }
    /**
     * 发送Socket心跳命令
     */
    public void sendHeartbeat() {
        if (socket == null) {
            Log.d("socket","null");
            return;
        }
        Util.writeAll(socket, "{\"header\":{\"cmd\":\"1002\"},\"body\":{\"result\":\"\"}}".getBytes(), new CompletedCallback() {
            @Override
            public void onCompleted(Exception ex) {
                if (ex != null) {
                    Log.d("SendSocket", "writeAll出错");
                    return;
                }
                Log.d("SendSocket", "发送了：{\"header\":{\"cmd\":\"1002\"},\"body\":{\"result\":\"\"}}");
            }
        });
    }

    public void clearNSTCPSocketForLong() {
        sendSocketData = null;
    }

    private static class SingletonHolder {
        public static NSTCPSocketForLong instance() {
            if (sendSocketData == null) {
                sendSocketData = new NSTCPSocketForLong();
            }
            return sendSocketData;
        }
    }
}

