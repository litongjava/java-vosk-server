package com.litongjava.vosk.server.utils;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WavUtils {

  public static String getFormat(byte[] data) {
    log.info("声音尺寸:{}", toInt(Arrays.copyOfRange(data, 4, 8)));
    log.info("音频格式:{}", toShort(Arrays.copyOfRange(data, 20, 22)));
    log.info("1 单声道 2 双声道: {}", toShort(Arrays.copyOfRange(data, 22, 24)));
    log.info("采样率:{}", toInt(Arrays.copyOfRange(data, 24, 28)));
    log.info("每秒波形的数据量：{}", toShort(Arrays.copyOfRange(data, 22, 24)));
    log.info("采样帧的大小：{}", toShort(Arrays.copyOfRange(data, 32, 34)));
    log.info("采样位数：{}", toShort(Arrays.copyOfRange(data, 34, 36)));
    return null;
  }

  public static int toInt(byte[] b) {
    return (((b[3] & 0xff) << 24) + ((b[2] & 0xff) << 16) + ((b[1] & 0xff) << 8) + ((b[0] & 0xff) << 0));
  }

  public static short toShort(byte[] b) {
    return (short) ((b[1] << 8) + (b[0] << 0));
  }

  public static int getSampleRate(byte[] data) {
    return toInt(Arrays.copyOfRange(data, 24, 28));
  }

  public static short getChannels(byte[] data) {
    return toShort(Arrays.copyOfRange(data, 22, 24));
  }
}
