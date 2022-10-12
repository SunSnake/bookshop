package com.gorgeous.bookshop.task;

import com.gorgeous.bookshop.constant.PowerJSON;
import com.gorgeous.bookshop.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

public class AddBookTask extends Thread {

    private static Logger log = LoggerFactory.getLogger(AddBookTask.class);

    private ApplicationContext context;
    private LinkedBlockingQueue<PowerJSON> bookQueue;

    public AddBookTask(ApplicationContext context) {
        this.context = context;
        this.bookQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        BookService bookService = (BookService) context.getBean("BookService");
        bookService.setBookQueue(bookQueue);

        // 每5秒消费一次队列
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                int size = bookQueue.size();
                if (size == 0) {
                    return;
                }

                List<PowerJSON> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    PowerJSON book = bookQueue.poll();
                    list.add(book);
                }
                int num = bookService.addBook(list);
                log.info("book插入{}条数据", num);
            }
        },0,5000);
    }

}
