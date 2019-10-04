package com.hepsiburada.test.driver;

import java.awt.*;
import java.io.*;
import java.nio.*;
import org.monte.media.*;
import org.monte.media.FormatKeys.*;
import org.monte.media.math.*;
import org.monte.screenrecorder.*;
import com.thoughtworks.gauge.ExecutionContext;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;
import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.FormatKeys.MIME_AVI;

public class VideoRecorder {

    private static ScreenRecorder screenRecorder;

    static void startRecording() throws Exception {

        File targetFolder = new File(System.getProperty("user.dir") + "/lib/screenshot/ssRecording/");

        // set the graphics configuration
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        // initialize the screen recorder:
        // - default graphics configuration
        // - full screen recording
        // - record in MOV format <------
        // - 15 frames per second
        // - black mouse pointer
        // - yes audio <------
        // - save capture to predefined location

        screenRecorder = new ScreenRecorder(gc,
            gc.getBounds(),
            new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, FormatKeys.MIME_QUICKTIME),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,VideoFormatKeys.ENCODING_QUICKTIME_ANIMATION,
                        CompressorNameKey,ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey,24, FrameRateKey, Rational.valueOf(15),
                        QualityKey,1.0f, KeyFrameIntervalKey, 15 * 60),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
            new Format(MediaTypeKey, MediaType.AUDIO,EncodingKey, ENCODING_QUICKTIME_TWOS_PCM,FrameRateKey,
            new Rational(48000, 1),SampleSizeInBitsKey, 16,ChannelsKey, 2, SampleRateKey,
            new Rational(48000, 1),SignedKey, true, ByteOrderKey, ByteOrder.BIG_ENDIAN),
            new File(String.valueOf(targetFolder)));


    /*
        // initialize the screen recorder:
        // - default graphics configuration
        // - full screen recording
        // - record in AVI format <------
        // - 15 frames per second
        // - black mouse pointer
        // - no audio <------
        // - save capture to predefined location


                screenRecorder = new ScreenRecorder(gc,
                gc.getBounds(),
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                null,
                new File(String.valueOf(targetFolder)));
    */

        screenRecorder.start();
    }

    public void stopRecording() throws Exception {
        this.screenRecorder.stop();
    }
}
