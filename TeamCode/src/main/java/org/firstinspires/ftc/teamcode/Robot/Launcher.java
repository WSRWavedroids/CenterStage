package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Launcher extends LinearOpMode{

    public Robot robot = null;
    boolean planeAlreadyLaunched = false;
    @Override
    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void firePlane(long motorTime)
    {
        long beginning = System.currentTimeMillis();
        long end = beginning + motorTime;

        while (end > System.currentTimeMillis() && !planeAlreadyLaunched){

            robot.droneAndOdoPodL.setPower(2);

        }
        planeAlreadyLaunched = true;
        robot.droneAndOdoPodL.setPower(0);

    }


}
