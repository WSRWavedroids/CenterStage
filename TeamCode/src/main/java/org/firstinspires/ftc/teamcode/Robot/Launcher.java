package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Launcher {

    public final DcMotor droneAndOdoPodL;

    public Robot robot = null;
    public HardwareMap hardwareMap;
    public Telemetry telemetry;
    boolean planeAlreadyLaunched = false;

    public Launcher(DcMotor droneAndOdoPodL) {
        this.droneAndOdoPodL = droneAndOdoPodL;
    }

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry);
    }

    public void firePlane(long motorTime)
    {
        droneAndOdoPodL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        long beginning = System.currentTimeMillis();
        long end = beginning + motorTime;

        while (end > System.currentTimeMillis() && !planeAlreadyLaunched){

            droneAndOdoPodL.setPower(2);

        }
        planeAlreadyLaunched = true;
        droneAndOdoPodL.setPower(0);

    }


}
