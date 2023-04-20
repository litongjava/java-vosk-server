package com.litongjava.vosk.server.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class AudioSystemUtilsTest {

  @Test
  public void test() throws IOException, UnsupportedAudioFileException {
    String filePath = "E:\\my_audio\\ShandongCuisine.m4a";
    File file = new File(filePath);
    if (!file.exists()) {
      return;
    }
    byte[] data = FileUtils.readFileToByteArray(file);
    AudioSystemUtils.getFormat(data);
  }

}
