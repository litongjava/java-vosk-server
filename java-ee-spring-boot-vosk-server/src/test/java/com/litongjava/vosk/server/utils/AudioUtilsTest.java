package com.litongjava.vosk.server.utils;


import org.junit.Test;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;

public class AudioUtilsTest {

  @Test
  public void getSupportEncodeFormat() {
    Encoder encoder = new Encoder();
    String[] audioEncoders = null;
    try {
      audioEncoders = encoder.getAudioEncoders();
    } catch (EncoderException e) {
      e.printStackTrace();
    }
    for (String string : audioEncoders) {
      System.out.println(string);
    }
  }

  @Test
  public void getSupportDecodeFroamt() {
    Encoder encoder = new Encoder();
    String[] audioDecoders = null;
    try {
      audioDecoders = encoder.getAudioDecoders();
    } catch (EncoderException e) {
      e.printStackTrace();
    }
    for (String string : audioDecoders) {
      System.out.println(string);
    }

  }

  @Test
  public void test() {
    
  }
  
  public static void main(String[] args) {
    String srcPath = "H:\\video\\学习通课程视频\\不负卿春-大学生职业生涯规划\\1.1 梦想与目标—你不知道的发动机.mp3";
    String dstPath = "H:\\video\\学习通课程视频\\不负卿春-大学生职业生涯规划\\1.1 梦想与目标—你不知道的发动机.wav";
    AudioUtils.convertToWav(srcPath, dstPath);
  }

}
