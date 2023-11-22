package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.hardware.Servo;
public class Lights {

    public final Servo lights;

    public Lights(Servo lights) {
        this.lights = lights;
    }

    public void startUpSetting(){
        //Pattern: Rainbow with Glitter
        lights.setPosition(-0.89);
    }

    public void initSetting(){
        //Pattern: Color Waves, Ocean Palette
        lights.setPosition(-0.41);
    }

    public void defaultSetting(){
        //Pattern: End to End Blend, Color 1 to Color 2
        lights.setPosition(0.45);
    }

    public void errorSetting(){
        //Pattern: Breath, Red
        lights.setPosition(-0.17);
    }

    public void funSetting(){
        //Pattern: Sinelon, Party Palette
        lights.setPosition(-0.77);
    }
}
