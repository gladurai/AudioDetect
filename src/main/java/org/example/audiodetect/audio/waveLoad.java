package org.example.audiodetect.audio;

import javax.sound.sampled.AudioFormat;
// Describes the bytes as data
// Audio input stream will give you the bytes,
// This will describe them, 16-bit, 2 channels, little-endian, 44100Hz etc.

import javax.sound.sampled.AudioInputStream;
//Read the wav header, and gives back a stream to sample from,
//carries information about those bytes.

import javax.sound.sampled.AudioSystem;
// Knows how to recognise audio file types
// and can also access microphone, speakers, inputs/outputs.

import java.io.File;
// allows you to point at a wav file

public class waveLoad {
}
