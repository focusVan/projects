package focus.focusstepone.focustest.highquality;

import java.util.HashMap;
import java.util.Map;

/**
 * focus Create in 17:17 2018/8/22
 */
public class HashMapOutOfMemoryTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        final Runtime runtime = Runtime.getRuntime();
        // JVM终止前记录内存信息
        runtime.addShutdownHook(new Thread(){
            @Override
            public void run() {
                StringBuffer stringBuffer = new StringBuffer();
                long heapMaxSize = runtime.maxMemory() >> 20;
                stringBuffer.append("最大可用内存：" + heapMaxSize + "M\n");
                long total = runtime.totalMemory() >> 20;
                stringBuffer.append("堆内存大小：" + total + "M\n");
                long free = runtime.freeMemory() >> 20;
                stringBuffer.append("空内存：" + free + "M\n");
                System.out.println(stringBuffer);
            }
        });

        // 放入40万键值对
        for (int i = 0; i < 3932117; i++) {
            map.put("key" + i, "value" + i);
        }
    }
}
