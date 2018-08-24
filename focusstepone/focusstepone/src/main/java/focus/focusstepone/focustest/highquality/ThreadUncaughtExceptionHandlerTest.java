package focus.focusstepone.focustest.highquality;

/**
 * focus Create in 10:43 2018/8/24
 */
public class ThreadUncaughtExceptionHandlerTest {
    public static void main(String[] args) {
        TcpServer tcpServer = new TcpServer();
    }
}

class TcpServer implements Runnable {
    //创建后即运行
    public TcpServer() {
        Thread thread = new Thread(this);
        thread.setUncaughtExceptionHandler(new TcpServerExceptionHandler());
        thread.start();
    }
    @Override
    public void run() {
        //正常业务，运行3秒
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("normal: " + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //抛出异常
        throw new RuntimeException();
    }

    //异常处理器
    private static class TcpServerExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable e) {
            //记录线程异常信息
            System.out.println("thread: " + thread.getName() + "error, restart");
            e.printStackTrace();
            //重启线程,保证业务不中端
            new TcpServer();
        }
    }
}
