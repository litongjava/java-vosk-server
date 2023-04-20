package com.litongjava.vosk.server.utils;

import java.io.File;

import lombok.extern.slf4j.Slf4j;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

@Slf4j
public class VideoUtils {
  /**
   * 视频转码
   * 
   * @param videoSource
   * @param videoTarget
   * @return true or false
   */
  public static boolean videoToVideo(String videoSource, String videoTarget) {
//      Date time = new Date();
//      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//      System.out.println(simpleDateFormat.format(time));

    long start = System.currentTimeMillis();

    File source = new File(videoSource);
    File target = new File(videoTarget);

    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("aac");
    audio.setBitRate(236000 / 2);
    audio.setChannels(2);
    audio.setSamplingRate(8000);

    VideoAttributes video = new VideoAttributes();
    video.setCodec("h264");
    video.setBitRate(1000000);
    video.setFrameRate(25);
    video.setQuality(4);
//      video.setSize(new VideoSize(720, 480));

    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setOutputFormat("mp4");
    attrs.setAudioAttributes(audio);
    attrs.setVideoAttributes(video);

    Encoder encoder = new Encoder();
    try {
      encoder.encode(new MultimediaObject(source), target, attrs);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(encoder.getUnhandledMessages());
      return false;
    } finally {
//          time = new Date();
//          System.out.println(simpleDateFormat.format(time));
      long end = System.currentTimeMillis();
      System.out.println("总耗时：" + (end - start) + "ms");
    }
  }

  /**
   * 视频文件转音频文件
   * 
   * @param videoPath
   * @param audioPath return true or false
   */
  public static boolean videoToAudio(String videoPath, String audioPath) {
    File fileMp4 = new File(videoPath);
    File fileMp3 = new File(audioPath);

    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("libmp3lame");
    audio.setBitRate(128000);
    audio.setChannels(2);
    audio.setSamplingRate(44100);

    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setOutputFormat("mp3");
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    MultimediaObject mediaObject = new MultimediaObject(fileMp4);
    try {
      encoder.encode(mediaObject, fileMp3, attrs);
      log.info("File MP4 convertito MP3");
      return true;
    } catch (Exception e) {
      log.error("File non convertito");
      log.error(e.getMessage());
      return false;
    }
  }

}
