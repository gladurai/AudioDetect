package org.example.audiodetect;

import org.example.audiodetect.analysis.SpectrumData;
import org.example.audiodetect.analysis.spectrum;
import org.example.audiodetect.audio.AudioData;
import org.example.audiodetect.audio.waveLoad;

import java.io.File;

public class Main {

    public static void main ( String [] args ) throws Exception {

        File testfile = new File("test-audio/440hzsine.wav");
        waveLoad waveLoad = new waveLoad();
        AudioData test = waveLoad.load(testfile);
        System.out.println(test.getSampleRate());
        int samplecount = test.getSamples().length;
        System.out.println("");
        System.out.println(samplecount);
        float samplerate = test.getSampleRate();
        float duration = samplecount / samplerate;
        System.out.println(duration);
        System.out.println("");

        System.out.println("");
        SpectrumData object = spectrum.spectrumData(test);
        double[][] magnitudes = object.getMagnitudes();
        for (int i = 400; i < 450; i++)
        {
            System.out.println(magnitudes[1][i]);
        }

        // --- 440 Hz test: find the loudest bin in one frame ---
        int frame = magnitudes.length / 2;          // a frame from the middle of the file
        double[] row = magnitudes[frame];           // all bins for that frame

        int loudestBin = 0;
        double loudestMagnitude = 0.0;

        for (int bin = 0; bin < row.length; bin++) {
            if (row[bin] > loudestMagnitude) {
                loudestMagnitude = row[bin];
                loudestBin = bin;
            }
        }

        double frequency = loudestBin * samplerate / 4096.0;

        System.out.println("Frames: " + magnitudes.length);
        System.out.println("Checking frame: " + frame);
        System.out.println("Loudest bin: " + loudestBin);
        System.out.println("Magnitude: " + loudestMagnitude);
        System.out.println("Frequency: " + frequency + " Hz");

        // neighbours, to see the peak's shape
        for (int bin = loudestBin - 2; bin <= loudestBin + 2; bin++) {
            System.out.println("  bin " + bin + ": " + row[bin]);
        }
    }
}
