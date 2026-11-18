package org.example.audiodetect.analysis;

import org.example.audiodetect.audio.AudioData;

public class spectrum {
    private static final int FFTsize =4096;
    private static final int HOPsize =2048;
    private static final double[] hannwindowsize = hannwindowmethod(FFTsize);

    public static double[] hannwindowmethod(int size){
        double[] hannwindow = new double[size];
        for(int i=0;i<size;i++){
            hannwindow[i]=0.5*(1-Math.cos(((2*Math.PI)*i)/(size-1)));
        }
        return hannwindow;
    }

    public static SpectrumData spectrumData(AudioData audioData){
        double[] samples = audioData.getSamples();
        if(samples.length < FFTsize){
            throw  new IllegalArgumentException("samples length less than FFTsize");
        }
        int framecount = ((samples.length-FFTsize)/(HOPsize))+1;
        for(int i=0;i<framecount;i++){
            int start = i*HOPsize;
            double[] buffer = new double[FFTsize];
            for(int j =0; j<FFTsize;j++){
                buffer[j] = samples[start+j]*hannwindowsize[j];
            }
        }
        System.out.println("Framecount: " + framecount);
        return null;
    }

}
