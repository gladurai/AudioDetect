package org.example.audiodetect.analysis;

public class SpectrumData {
    double[][] magnitudes;
    float samplerate;
    int FFTsize;
    int HOPsize;

    SpectrumData(double[][] indouble, float insamplerate, int infftsize, int inhopsize){
        magnitudes = indouble;
        samplerate = insamplerate;
        FFTsize = infftsize;
        HOPsize = inhopsize;
    }

    public double[][] getMagnitudes() {
        return magnitudes;
    }
    public float getSamplerate() {
        return samplerate;
    }
    public int getFFTsize() {
        return FFTsize;
    }
    public int getHOPsize() {
        return HOPsize;
    }
}
