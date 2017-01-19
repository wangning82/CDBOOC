package com.cdboo.business.common;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;

/**
 * Created by houyi on 2017/1/19.
 */
public class DownloadWithRange implements Runnable {

    private HttpURLConnection connection;

    private String filePath;

    private long start;

    private long end;

    public DownloadWithRange(HttpURLConnection connection, String filePath, long start, long end) {
        this.connection = connection;
        this.filePath = filePath;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            connection.setRequestProperty("Range", "bytes=" + start + "-" + end);

            File file = new File(filePath);
            RandomAccessFile out = null;
            if (file != null) {
                out = new RandomAccessFile(file, "rwd");
            }
            out.seek(start);
            InputStream in = connection.getInputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
