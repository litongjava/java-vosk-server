package com.litongjava.vosk.server.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class VoskUtilsTest {

  @Before
  public void before() {
    VoskUtils.loadModel();
  }

  @Test
  void test() throws IOException, UnsupportedAudioFileException {
    // Copy Full Name to Clipboard
    // String filePath = "E:\\my_audio\\ShandongCuisine.m4a";
    String filePath = "E:\\my_audio\\ShandongCuisine.wav";

    File file = new File(filePath);
    if (!file.exists()) {
      return;
    }
    byte[] data = FileUtils.readFileToByteArray(file);
    System.out.println("dataSize:" + data.length);
    String recognize = VoskUtils.recognize(data);
    System.out.println(recognize);
  }
  public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
    VoskUtils.loadModel();
    // Copy Full Name to Clipboard
    // String filePath = "E:\\my_audio\\ShandongCuisine.m4a";
    //String filePath = "E:\\my_audio\\Recording_20211124132438.wav";
    String filePath = "H:\\video\\学习通课程视频\\不负卿春-大学生职业生涯规划\\1.1 梦想与目标—你不知道的发动机.wav";

    File file = new File(filePath);
    if (!file.exists()) {
      return;
    }
    byte[] data = FileUtils.readFileToByteArray(file);
    System.out.println("dataSize:" + data.length);
    String recognize = VoskUtils.recognize(data);
    System.out.println(recognize);
  }

}
