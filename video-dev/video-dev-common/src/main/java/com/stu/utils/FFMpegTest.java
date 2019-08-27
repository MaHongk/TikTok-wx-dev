package com.stu.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FFMpegTest {
    private String ffmpegEXE;

    public FFMpegTest(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath, String videoOutputPath) throws Exception{
        //ffmpeg -i input.mp4 output.avi

        List<String> command = new ArrayList<String>();
        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        command.add(videoOutputPath);

        for (String c : command) {
            System.out.println(c);
        }
        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();
        InputStream errorStream=process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String line= "";
        while((line=br.readLine())!=null){

        }
        if(br!=null){
            br.close();
        }
        if(inputStreamReader!=null){
            inputStreamReader.close();
        }
        if(errorStream!=null){
            errorStream.close();
        }
    }

    public static void main(String[] args) {
        FFMpegTest ffmpeg = new FFMpegTest("F:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("F:\\stu_videos_dev\\asd.mp4", "F:\\stu_videos_dev\\ada.avi");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
