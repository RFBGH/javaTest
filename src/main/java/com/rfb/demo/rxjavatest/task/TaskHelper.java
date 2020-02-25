package com.rfb.demo.rxjavatest.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskHelper {

    final Executor mExcutor = new ThreadPoolExecutor(
            0,
            1,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new ThreadFactory() {
                SecurityManager s = System.getSecurityManager();
                final ThreadGroup group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();

                @Override
                public Thread newThread(final Runnable r) {

                    Thread t = new Thread(group, r, "adhoc-db-thread", 0);
                    if (t.isDaemon())
                        t.setDaemon(false);
                    if (t.getPriority() != Thread.NORM_PRIORITY)
                        t.setPriority(Thread.NORM_PRIORITY);
                    return t;
                }
            });


    private final List<ITask> mTasks = new ArrayList<ITask>();
    private AtomicBoolean isExcuting = new AtomicBoolean(false);

    public void addOrExcute(final ITask task){

        synchronized (mTasks){
            mTasks.add(task);
        }

        if(isExcuting.compareAndSet(false, true)){
            excute();
        }
    }

    private void excute(){

        final  List<ITask> temp;

        synchronized (mTasks){
            temp = new ArrayList<ITask>(mTasks);
            mTasks.clear();
            if(temp.isEmpty()){
                isExcuting.set(false);
                return;
            }
        }

        mExcutor.execute(new Runnable() {
            public void run() {

                for(ITask task:temp){
                    task.excute();
                }

                excute();
            }
        });
    }

}
