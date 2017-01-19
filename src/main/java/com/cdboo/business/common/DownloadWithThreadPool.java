package com.cdboo.business.common;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by houyi on 2017/1/19.
 */
public class DownloadWithThreadPool {

    /**
     * 多线程下载
     * @param urlLocation
     * @param filePath
     * @param poolLength
     * @throws IOException
     */
    public static void download(String urlLocation, String filePath, int poolLength) throws IOException {
        Executor threadPool = Executors.newFixedThreadPool(poolLength);
        HttpURLConnection connection = getConnection(urlLocation);

        long len = connection.getContentLength();
        for (int i = 0; i < poolLength; i++) {
            long start = i * len / poolLength;
            long end = (i + 1) * len / poolLength - 1;
            if (i == poolLength - 1) {
                end = len;
            }
            DownloadWithRange download = new DownloadWithRange(connection, filePath, start, end);
            threadPool.execute(download);
        }
        connection.disconnect();
    }

    private static HttpURLConnection getConnection(String urlLocation) throws IOException {
        URL url = null;
        if (urlLocation != null) {
            url = new URL(urlLocation);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");

        return conn;
    }
}
