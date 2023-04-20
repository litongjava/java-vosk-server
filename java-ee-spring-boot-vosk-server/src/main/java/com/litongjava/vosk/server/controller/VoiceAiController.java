package com.litongjava.vosk.server.controller;

import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.litongjava.vosk.server.utils.VoskUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("admin")
@Slf4j
public class VoiceAiController {


  /**
   * 取出文数据流
   * 
   * @param file
   * @return
   * @throws IOException
   * @throws UnsupportedAudioFileException
   */
  @PostMapping("/getWord")
  public String getWord(MultipartFile file) throws IOException, UnsupportedAudioFileException {
    log.info("接收文件完成");
    byte[] bytes = file.getBytes();
    log.info("转为字节流完成");
    String text = VoskUtils.recognize(bytes);
    return text;
  }
}
