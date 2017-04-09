package com.xogrp.john.music.service;

import com.xogrp.john.music.service.MusicInfo;

interface MusicPlayServiceInterface {
    void play();
    void next();
    void pause();
    void stop();
    boolean isPlaying();
    void setMusicList(in List<MusicInfo> musicList);
}
