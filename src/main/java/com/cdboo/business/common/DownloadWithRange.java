package com.cdboo.business.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by houyi on 2017/1/19.
 */
public class DownloadWithRange implements Runnable {

    private String urlLocation;

    private String filePath;

    private long start;

    private long end;

    public DownloadWithRange(String urlLocation, String filePath, long start, long end) {
        this.urlLocation = urlLocation;
        this.filePath = filePath;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connection = getConnection(urlLocation);
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
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpURLConnection getConnection(String urlLocation) throws IOException {
        URL url = null;
        if (urlLocation != null) {
            url = new URL(urlLocation.replaceAll(" ", "%20"));
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(1000);
        conn.setRequestMethod("GET");

        return conn;
    }

}
