package ru.otus.l22;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class BlockingGC {
    private static int GC_COUNT = 1; // 1 G1 2 old GCs
    private final CountDownLatch doneSignal = new CountDownLatch(GC_COUNT);
    private List<Runnable> registration = new ArrayList<>();

    private BlockingGC() { installGCMonitoring(); }

        static void collect() {
            BlockingGC bgc = new BlockingGC();
            try {
                System.gc();
                bgc.doneSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                bgc.registration.forEach(Runnable::run);
            }
        }
        private void installGCMonitoring() {
            List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

            for (GarbageCollectorMXBean gcbean : gcbeans) {
                System.out.println("GC name: " + gcbean.getName());


                NotificationEmitter emitter = (NotificationEmitter) gcbean;
                NotificationListener listener = (notification, handback) -> {
                    if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                        if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                            GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                long duration = info.getGcInfo().getDuration();
                String gctype = info.getGcAction();

                System.out.println(gctype + ": - "
                        + info.getGcInfo().getId() + ", "
                        + info.getGcName()
                        + " (from " + info.getGcCause() + ") " + duration + " milliseconds");

            }

            GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
            if (info.getGcCause().equals("System.gc()")) {
                doneSignal.countDown();

            }

            System.out.println("Thread name: " + Thread.currentThread().getName());
        }
    };

                emitter.addNotificationListener(listener, null, null);

                registration.add(() -> {
                    try {
                        emitter.removeNotificationListener(listener);
                    } catch (ListenerNotFoundException e) {
                        e.printStackTrace();
                    }
                });


            }
        }
}





