package org.example.audiodetect.audio;

import javax.sound.sampled.*;
// Describes the bytes as data
// Audio input stream will give you the bytes,
// This will describe them, 16-bit, 2 channels, little-endian, 44100Hz etc.

//Read the wav header, and gives back a stream to sample from,
//carries information about those bytes.

// Knows how to recognise audio file types
// and can also access microphone, speakers, inputs/outputs.

import java.io.File;
// allows you to point at a wav file
import java.io.IOException;

public class waveLoad {
public AudioData load (File file)throws UnsupportedAudioFileException, IOException {
    try (AudioInputStream stream = AudioSystem.getAudioInputStream(file)) {
       AudioFormat format = stream.getFormat();
       float sampleRate = format.getSampleRate();
       int channels = format.getChannels();
       int sampleSizeInBits = format.getSampleSizeInBits();
       boolean isBigEndian =  format.isBigEndian();

       if (sampleSizeInBits != 16){
           throw new UnsupportedAudioFileException("Only 16-bit wav supported.");
       }

       byte[] bytes = stream.readAllBytes();

       System.out.println("Sample Rate: " + sampleRate);
       System.out.println("Channels: " + channels);
       System.out.println("Sample Size In Bits: " + sampleSizeInBits);
       System.out.println("IsBigEndian: " + isBigEndian);

       if (channels != 1 && channels != 2){
           throw new UnsupportedAudioFileException("Wrong channels specified.");
       }

       int bytesperframe = 2*channels;
       int framecount = bytes.length /  bytesperframe;
       double[] samples = new double[framecount];

        for (int frame = 0; frame< framecount; frame++){
            int framestart = frame*bytesperframe;
            if (bytesperframe == 2){
                samples[frame] = decodeSample(bytes, framestart, isBigEndian)/32768.0;
            } else {

                int leftsample =  decodeSample(bytes, framestart, isBigEndian);
                int rightsample =  decodeSample(bytes, framestart+2, isBigEndian);
                samples[frame] = ((leftsample+rightsample)/2.0)/32768.0;
            }
        }
        return new AudioData(samples,sampleRate);
    }
}
    private static int decodeSample(byte[] data, int position, boolean bigEndian) {

    byte first = data[position];
    byte second = data[position+1];

    byte low;
    byte high;
    if (bigEndian) {
        high = first;
        low = second;
    }else {
        low = first;
        high = second;
    }

    int lowUnsignedByte = low & 0xFF;
    int highshift = high << 8;

    int lowandhigh = lowUnsignedByte | highshift;

    return lowandhigh;
    }

}


