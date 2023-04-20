package com.litongjava.vosk.server.utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.util.StringUtils;
import org.vosk.LibVosk;
import org.vosk.LogLevel;
import org.vosk.Model;
import org.vosk.Recognizer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VoskUtils {
  // 模型文件存放路径
  private static String MODEL_PATH = null;
  private static Model model = null;

  public synchronized static void loadModel() {
    if (model == null) {
      //String path="D:\\vosk-model\\vosk-model-cn-kaldi-cvte-2\\graph";
      //String path="D:\\vosk-model\\vosk-model-cn-0.1";
      //String path="D:\\vosk-model\\vosk-model-cn-kaldi-cvte-2\\am";
      //String path="D:\\vosk-model\\vosk-model-cn-kaldi-cvte-2";
      //String path="D:\\vosk-model\\vosk-model-cn-kaldi-cvte-2\\conf";
      String path="D:\\vosk-model\\vosk-model-en-us-0.22";
      File file = new File(path);
      MODEL_PATH = file.getAbsolutePath();
      
      log.info("开始加载模型:{}",MODEL_PATH);
      LibVosk.setLogLevel(LogLevel.WARNINGS);
      try {
        model = new Model(MODEL_PATH);
      } catch (Exception e) {
        log.error("加载模型失败:{}", MODEL_PATH);
      }
      // model.close();
      log.info("加载模型完成");
    }

  }

  public static String recognize(byte[] data) throws UnsupportedAudioFileException, IOException {

    int sampleRate = WavUtils.getSampleRate(data);
    log.info("sampleRate:{}", sampleRate);
    short track = WavUtils.getChannels(data);
    log.info("track:{}", track);

    long startRecogTimestramp = System.currentTimeMillis();
    log.info("开始识别{}", startRecogTimestramp);

    try ( // 采样率为音频采样率的声道倍数
        Recognizer recognizer = new Recognizer(model, sampleRate * track);) {
      recognizer.acceptWaveForm(data, data.length);
      String result = recognizer.getFinalResult();
      long endRecogTimestramp = System.currentTimeMillis();
      log.info("识别结束:{}", endRecogTimestramp);
      log.info("识别完:{}", (endRecogTimestramp - startRecogTimestramp) + "ms");
      log.info("识别结果：{}", result);

      if (StringUtils.hasLength(result)) {
        JSONObject jsonObject = JSON.parseObject(result);
        return jsonObject.getString("text").replace(" ", "");
      }
    }
    return null;
  }

}
