package com.testquiet;

import androidx.annotation.NonNull;

import android.Manifest;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import java.io.IOException;
import org.quietmodem.Quiet.*;

@ReactModule(name = TestQuietModule.NAME)
public class TestQuietModule extends ReactContextBaseJavaModule {
  public static final String NAME = "TestQuiet";

  private final ReactApplicationContext reactContext;

  private static FrameTransmitter transmitter = null;

  public TestQuietModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public final void start () {
    FrameTransmitterConfig transmitterConfig = null;
    final boolean hasPermission = ContextCompat.checkSelfPermission(this.reactContext, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
    if (!hasPermission) {
      System.out.println( "audio record is not permitted here!" );
      return;
    }

    try {
      this.transmitter = new FrameTransmitter(
        new FrameTransmitterConfig(
        this.reactContext,
        "audible-7k-channel-0")
      );
      this.transmitterConfig = ;
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ModemException e) {
      e.printStackTrace();
    }
  }

  @ReactMethod
  public final void send(final String pMessage) {
    try {
      if (this.transmitter != null) {
        this.transmitter.send(pMessage.getBytes());
      }
    }  catch (final Exception pException) {
      pException.printStackTrace();
    }
  }

  @ReactMethod
  public final void stop() {
    this.transmitter = null;
  }
}
