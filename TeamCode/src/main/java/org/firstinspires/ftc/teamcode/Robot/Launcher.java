package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Launcher extends LinearOpMode{

    public Robot robot = null;
    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    ElapsedTime timer = new ElapsedTime();//may be bad
    public void firePlane(long motorTime)
    {
        long beginning = System.currentTimeMillis();
        long end = beginning + motorTime;
        boolean planeAlreadyLaunched = false;
        while (end > System.currentTimeMillis() && planeAlreadyLaunched == false){

            robot.droneAndOdoPodL.setPower(1);
            planeAlreadyLaunched = true;

        }
        robot.droneAndOdoPodL.setPower(0);



        //timer

    }


}
