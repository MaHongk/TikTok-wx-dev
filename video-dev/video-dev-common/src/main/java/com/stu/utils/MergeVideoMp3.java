package com.stu.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {
    private String ffmpegEXE;

    public MergeVideoMp3(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath, String mp3InputPath,
                          Double seconds,String videoOutputPath) throws Exception{
        //ffmpeg.exe -i asd.mp4 -i arrow.mp3 -t 2 -y sss.mp4
        List<String> command = new ArrayList<String>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

        command.add("-y");
        command.add(videoOutputPath);

//        for (String c : command) {
//            System.out.println(c);
//        }
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
        MergeVideoMp3 ffmpeg = new MergeVideoMp3("F:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("F:\\stu_videos_dev\\asd.mp4", "F:\\stu_videos_dev\\arrow.mp3",2.0,"F:\\stu_videos_dev\\ssss.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
