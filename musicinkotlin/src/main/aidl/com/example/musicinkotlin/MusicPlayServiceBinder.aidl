// MusicPlayServiceBinder.aidl
package com.example.musicinkotlin;

//MusicInfo.aidl 的路径 要与  MusicInfo实现类的 路径要一致
import com.example.musicinkotlin.service.musicService.MusicInfo;

interface MusicPlayServiceBinder {
    void play();
    void next();
    int getCurrentPosition();
    void pause();
    void stop();
    boolean isPlaying();
    MusicInfo getCurrentMusicInfo();
    void setMusicList(in List<MusicInfo> musicList);
}
