# ZiqMusicPlayer
模仿网易云的项目， kotlin 与java 混合， 主要运用后台服务播放。集成了 腾讯bugly 热修复<br>
![image](https://github.com/ziq358/ZiqMusicPlayer/blob/master/readMe/jieshao.gif)

## V1.0:
适合初步了解音乐播放器的基本东西.<br>
https://github.com/ziq358/ZiqMusicPlayer/tree/music_v_1.0<br>
v1.0 主要是熟悉音乐播放器的基本功能，了解后台服务<br>
主要参考了：<br>
remusic 项目：https://github.com/aa112901/remusic<br>
并把其中自己想要了解的东西挑出来，把关键的地方抠出来，更加完善的逻辑并没有实现。<br>
关键的地方：<br>
* service + MediaPlayer 实现后台播放
* AIDL进程间通信
* service + notification + startForeground（。。。）提高级别
* 锁屏界面显示
* 6.0权限管理
<br>
具体的切换到v1.0分支的readme 文件查看


