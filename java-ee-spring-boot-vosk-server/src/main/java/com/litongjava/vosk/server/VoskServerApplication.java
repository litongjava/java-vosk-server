package com.litongjava.vosk.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.litongjava.vosk.server.utils.VoskUtils;

@SpringBootApplication
public class VoskServerApplication {
  public static void main(String[] args) {
    SpringApplication.run(VoskServerApplication.class, args);
    VoskUtils.loadModel();
  }
}
