package com.cdboo.business.common.download;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class GeneralDownloadInfo implements IDownloadInfo {

    private RemoteLocalPair pair;
    private String curName;
    private File curFlagFile;
    private FileCheckPoints chp = null;
    private Logger logger = LoggerFactory.getLogger(GeneralDownloadInfo.class);

    public GeneralDownloadInfo(RemoteLocalPair pair) {
        this.pair = pair;
        curName = pair.localName;
        curFlagFile = new File(pair.getLocalFullPath() + ".cdboo");
    }

    @Override
    public FileCheckPoints getCurCheckPoints() {
        return chp;
    }

    @Override
    public RemoteLocalPair getPair() {
        return this.pair;
    }

    @Override
    public void initDownload() {

    }

    @Override
    public int getSplitNum() {
        return this.pair.splitNum;
    }

    @Override
    public boolean isNeedDownload(FileCheckPoints serverInitChp) {
        chp = readInfo();
        if (chp.timestamp == serverInitChp.timestamp && chp.totalSize == serverInitChp.totalSize && chp.getSplit() == serverInitChp.getSplit()) {
            // 不需要下载
            boolean isneed = false;
            for (int i = 0; i < chp.getStartPos().length; i++) {
                if (chp.getStartPos()[i] < chp.getEndPos()[i]) {
                    isneed = true;
                    logger.info("restore from checkpoint");
                    break;
                }
            }
            return isneed;
        } else {
            chp.copy(serverInitChp);
            return true;
        }
    }

    @Override
    public synchronized boolean writeInfo(FileCheckPoints chkp) {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(curFlagFile));
            dos.writeLong(chkp.timestamp);
            dos.writeLong(chkp.totalSize);
            int split = chp.getSplit();
            dos.writeInt(split);
            for (int i = 0; i < chp.getSplit(); i++) {
                dos.writeLong(chp.getStartPos()[i]);
                dos.writeLong(chp.getEndPos()[i]);
            }
        } catch (FileNotFoundException e) {
            logger.debug(curName, e);
        } catch (IOException e) {
            logger.debug(curName, e);
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    logger.debug(curName, e);
                }
            }
        }
        return true;
    }

    /**
     * <b>function:</b>读取写入点的位置信息 如果不存在，那么
     */
    @Override
    public FileCheckPoints readInfo() {
        if (curFlagFile.exists()) {
            DataInputStream dis = null;
            try {
                dis = new DataInputStream(new FileInputStream(curFlagFile));
                long curTimeStamp = dis.readLong();
                long curTotalSize = dis.readLong();
                int curSplit = dis.readInt();
                FileCheckPoints chkP = new FileCheckPoints();
                chkP.timestamp = curTimeStamp;
                chkP.totalSize = curTotalSize;
                long[] sp = new long[curSplit];
                long[] ep = new long[curSplit];
                for (int i = 0; i < curSplit; i++) {
                    sp[i] = dis.readLong();
                    ep[i] = dis.readLong();
                }
                chkP.setStartPos(sp);
                chkP.setEndPos(ep);
                return chkP;
            } catch (FileNotFoundException e) {
                logger.debug(curName, e);
            } catch (IOException e) {
                logger.debug(curName, e);
            } finally {
                if (dis != null) {
                    try {
                        dis.close();
                    } catch (IOException e) {
                        logger.debug(curName, e);
                    }
                }
            }
        } else {
            return new FileCheckPoints();
        }
        return new FileCheckPoints();
    }

    @Override
    public void downloadDone(FileCheckPoints chkp) {
        writeInfo(chkp);
    }

    @Override
    public void deleteFlagFile() {
        if(curFlagFile.exists()){
            curFlagFile.delete();
        }
    }

}
