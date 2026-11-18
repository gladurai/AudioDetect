package org.example.audiodetect;

import org.example.audiodetect.analysis.SpectrumData;
import org.example.audiodetect.analysis.spectrum;
import org.example.audiodetect.audio.AudioData;
import org.example.audiodetect.audio.waveLoad;

import java.io.File;

public class Main {

    public static void main ( String [] args ) throws Exception {

        File testfile = new File("test-audio/mixcheck.wav");
        waveLoad waveLoad = new waveLoad();
        AudioData test = waveLoad.load(testfile);
        System.out.println(test.getSampleRate());
        int samplecount = test.getSamples().length;
        System.out.println("");
        System.out.println(samplecount);
        float samplerate = test.getSampleRate();
        float duration = samplecount/ samplerate;
        System.out.println(duration);
        System.out.println("");

        System.out.println("");
        spectrum.spectrumData(test);

    }
}
