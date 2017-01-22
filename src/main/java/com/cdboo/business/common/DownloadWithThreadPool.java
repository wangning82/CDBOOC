package com.cdboo.business.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by houyi on 2017/1/19.
 */
public class DownloadWithThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(DownloadWithThreadPool.class);

    /**
     * 多线程下载
     *
     * @param urlLocation
     * @param filePath
     * @param poolLength
     * @throws IOException
     */
    public static void download(String urlLocation, String filePath, int poolLength) throws IOException {
        if (!new File(filePath).exists()) {
            Executor threadPool = Executors.newFixedThreadPool(poolLength);
            HttpURLConnection connection = getConnection(urlLocation);
            if (connection.getResponseCode() != 200) {
                logger.error("文件获取失败：" + urlLocation);
            } else {
                long len = connection.getContentLength();
                for (int i = 0; i < poolLength; i++) {
                    long start = i * len / poolLength;
                    long end = (i + 1) * len / poolLength - 1;
                    if (i == poolLength - 1) {
                        end = len;
                    }
                    DownloadWithRange download = new DownloadWithRange(urlLocation, filePath, start, end);
                    threadPool.execute(download);
                }
                connection.disconnect();
            }
        } else {
            logger.error("文件已存在：" + filePath);
        }
    }


    private static HttpURLConnection getConnection(String urlLocation) throws IOException {
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
