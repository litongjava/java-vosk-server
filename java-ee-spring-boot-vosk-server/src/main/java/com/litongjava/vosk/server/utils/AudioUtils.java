package com.litongjava.vosk.server.utils;

import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

public class AudioUtils {
  /**
   * m4r音频格式转换为mp3，audioPath可更换为要转换的音频格式
   * 
   * @param audioPath
   * @param mp3Path
   */
  public static void m4rToMp3(String audioPath, String mp3Path) {
    File source = new File(audioPath);
    File target = new File(mp3Path);
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("libmp3lame");
    audio.setBitRate(128000);
    audio.setChannels(2);
    audio.setSamplingRate(44100);

    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setOutputFormat("mp3");
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    try {
      encoder.encode(new MultimediaObject(source), target, attrs);
    } catch (EncoderException e) {
      e.printStackTrace();
    }
  }

  public static void convertToWav(String srcPath, String dstPath) {
    File source = new File(srcPath);
    File target = new File(dstPath);
    Encoder encoder = new Encoder();
    EncodingAttributes attrs = getEncodingAttributesOfWav();
    try {
      encoder.encode(new MultimediaObject(source), target, attrs);
    } catch (EncoderException e) {
      e.printStackTrace();
    }
  }

  
  /**
   * -ac 1 -ar 16000 16k.wav
   * @return
   */
  private static EncodingAttributes getEncodingAttributesOfWav() {
    AudioAttributes audioAttributes = new AudioAttributes();
    //audio.setCodec("1");
    
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setOutputFormat("wav");
    attrs.setAudioAttributes(audioAttributes);
    return attrs;
  }

  /**
   * ffmpeg -i test.wav -f mp3 -acodec libmp3lame -y wav2mp3.mp3
   * @return
   */
  private static EncodingAttributes getEncodingAttributesOfMp3() {
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("libmp3lame");

    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setOutputFormat("mp3");
    attrs.setAudioAttributes(audio);
    return attrs;
  }

}
