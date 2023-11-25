package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.Servo;
public class Lights {

    public final RevBlinkinLedDriver lights;

    public Lights(RevBlinkinLedDriver lights) {
        this.lights = lights;
    }

    public void startUpSetting(){
        //Pattern: Rainbow with Glitter
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.RAINBOW_WITH_GLITTER);
    }

    public void initSetting(){
        //Pattern: Color Waves, Ocean Palette
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE);
    }

    public void defaultSetting(){
        //Pattern: End to End Blend, Color 1 to Color 2
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_END_TO_END_BLEND_1_TO_2);
    }

    public void errorSetting(){
        //Pattern: Breath, Red
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_RED);
    }

    public void funSetting(){
        //Pattern: Sinelon, Party Palette
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.SINELON_PARTY_PALETTE);
    }

    public void closedClaw(){
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CONFETTI);
    }
}
