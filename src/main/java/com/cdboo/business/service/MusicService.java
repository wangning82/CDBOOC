package com.cdboo.business.service;

import com.cdboo.business.common.Constants;
import com.cdboo.business.entity.PlanModel;
import com.cdboo.business.entity.RestChannel;
import com.cdboo.business.entity.RestMusic;
import com.cdboo.business.repository.MusicRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by houyi on 2016/12/30 0030.
 */
@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private PlanService planService;

    private RestMusic findMusicById(Long musicId) {
        return musicRepository.findOne(musicId);
    }

    public RestMusic save(RestMusic music) {
        return musicRepository.save(music);
    }

    public void deleteAll() {
        musicRepository.deleteAll();
    }

    /**
     * 更新收藏状态
     *
     * @param musicId
     * @param favorite
     */
    public void updateFavorite(Long musicId, String favorite) {
        RestMusic music = findMusicById(musicId);
        music.setFavorite(favorite);
        musicRepository.flush();
    }

    /**
     * 查询收藏音乐
     *
     * @return
     */
    public List<RestMusic> findFavoriteList() {
        List<RestMusic> result = musicRepository.findByFavorite(Constants.FAVORITE_YES);
        for (RestMusic music : result) {
            Iterable<RestChannel> channels = channelService.findChannelByMusic(music);
            if (channels.iterator().hasNext()) {
                music.setVoice(channels.iterator().next().getVoice());
            }
        }
        return result;
    }

    /**
     * 查询频道歌单(去除子频道重复的歌曲)
     *
     * @param channelId
     * @return
     */
    public List<RestMusic> findMusicByChannel(Long channelId) {
        List<RestMusic> result = new ArrayList();
        RestChannel restChannel = channelService.findChannelById(channelId);
        if (Constants.CHANNEL_TYPE_GROUP.equals(restChannel.getChannelType())) {
            for (RestChannel channel : restChannel.getChildChannelList()) {
                for (RestMusic music : channel.getMusicList()) {
                    music.setVoice(channel.getVoice());
                    if (!result.contains(music)) {
                        result.add(music);
                    }
                }
            }
        } else {
            result = restChannel.getMusicList();
        }
        return result;
    }

    /**
     * 查询当前播放列表
     *
     * @return
     */
    public List<RestMusic> findPlanMusic() {
        List<RestMusic> result = new ArrayList();
        // 根据优先级，节日 > 主题 > 风格
        findMusicByStyle(result, Constants.MUSIC_FESTIVAL);
        findMusicByStyle(result, Constants.MUSIC_THEME);
        findMusicByStyle(result, Constants.MUSIC_MANNER);
        return result;
    }

    /**
     * 查询播放列表
     *
     * @param result
     * @param style
     */
    private void findMusicByStyle(List<RestMusic> result, String style) {
        Iterable<PlanModel> festivalList = planService.findPlanByStyle(style);
        for (PlanModel planModel : festivalList) {
            for (RestMusic restMusic : planModel.getChannel().getMusicList()) {
                restMusic.setVoice(planModel.getChannel().getVoice());
                if (!result.contains(restMusic)) {
                    result.add(restMusic);
                }
            }
        }
    }

    /**
     * 查询插播列表(每秒检索)
     *
     * @return
     */
    public RestMusic findSpotMusic() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        String currentTime = sdf.format(new Date());

        List<RestMusic> result = new ArrayList<>();

        Predicate predicate = planService.getPredicateByStyle(Constants.MUSIC_SPOT).and(planService.getPredicateByDate());
        Iterable<PlanModel> list = planService.findAll(predicate);
        // 判断插播的间隔时间和次数
        for (PlanModel planModel : list) {
            int cycle = 0;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sdf.parse(planModel.getTimestep().getStarttime()));
            while (cycle <= Integer.parseInt(planModel.getCycleTimes()) - 1) {
                calendar.add(Calendar.MINUTE, cycle * Integer.parseInt(planModel.getIntervalTime()));
                if (currentTime.equals(sdf.format(calendar.getTime()))) {
                    result = planModel.getChannel().getMusicList();
                    break;
                }
                cycle++;
            }
        }
        if (result.size() != 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

}
