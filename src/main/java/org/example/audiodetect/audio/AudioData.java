package org.example.audiodetect.audio;

 public class AudioData {

    double[] samples;
    float samplerate;

    AudioData(double[] insamples, float insamplerate){
        samples = insamples;
        samplerate = insamplerate;
    }

    public double[] getSamples(){
        return samples;
    }
    public float getSampleRate(){
        return samplerate;
    }
}