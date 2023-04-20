package com.litongjava.vosk.server.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import com.sun.media.sound.WaveFileReader;
import com.sun.media.sound.WaveFileWriter;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("restriction")
@Slf4j
public class AudioSystemUtils {

  public static String getFormat(byte[] data) throws IOException, UnsupportedAudioFileException {
    WaveFileReader reader = new WaveFileReader();
    try (AudioInputStream audioIn = reader.getAudioInputStream(new ByteArrayInputStream(data));) {

      AudioFormat srcFormat = audioIn.getFormat();
      //编码方式
      log.info("Encoding:{}",srcFormat.getEncoding());
      //样本频率
      log.info("SimpleRate:{}",srcFormat.getSampleRate());
      //每个样本位数
      log.info("SampleSizeInBits:{}",srcFormat.getSampleSizeInBits());
      //通道数
      log.info("Channels:{}",srcFormat.getChannels());
      //每个样本(帧)字节数
      log.info("FrameSize:{}",srcFormat.getFrameSize());
      //样本频率(振频率)
      log.info("FrameRate:{}",srcFormat.getFrameRate());
      //是否为大端排序
      log.info("IsBigEndian:{}",srcFormat.isBigEndian());
      return null;
    }
    
  }
  
  public static void reSamplingAndSave(byte[] data, String path) throws IOException, UnsupportedAudioFileException {
    WaveFileReader reader = new WaveFileReader();
    AudioInputStream audioIn = reader.getAudioInputStream(new ByteArrayInputStream(data));

    AudioFormat srcFormat = audioIn.getFormat();
    int targetSampleRate = 16000;

    AudioFormat dstFormat = new AudioFormat(srcFormat.getEncoding(), targetSampleRate, srcFormat.getSampleSizeInBits(),
        srcFormat.getChannels(), srcFormat.getFrameSize(), srcFormat.getFrameRate(), srcFormat.isBigEndian());

    AudioInputStream convertedIn = AudioSystem.getAudioInputStream(dstFormat, audioIn);
    File file = new File(path);
    WaveFileWriter writer = new WaveFileWriter();
    writer.write(convertedIn, AudioFileFormat.Type.WAVE, file);
  }

  /**
   * 重置声音流的SampleRate
   * @param bytes 声音流
   * @throws IOException 
   * @throws UnsupportedAudioFileException 
   */
  public static ByteArrayOutputStream resetSampleRate(byte[] data) throws UnsupportedAudioFileException, IOException {
    WaveFileReader reader = new WaveFileReader();
    AudioInputStream audioIn = reader.getAudioInputStream(new ByteArrayInputStream(data));
    
    AudioFormat srcFormat = audioIn.getFormat();
    int targetSampleRate = 16000;
    
    AudioFormat dstFormat = new AudioFormat(srcFormat.getEncoding(), targetSampleRate, srcFormat.getSampleSizeInBits(),
        srcFormat.getChannels(), srcFormat.getFrameSize(), srcFormat.getFrameRate(), srcFormat.isBigEndian());
    
    AudioInputStream convertedStream = AudioSystem.getAudioInputStream(dstFormat, audioIn);
    
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    WaveFileWriter writer = new WaveFileWriter();
    writer.write(convertedStream, AudioFileFormat.Type.WAVE, byteArrayOutputStream);
    
    return byteArrayOutputStream;
  }

}
