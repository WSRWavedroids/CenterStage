package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Claw extends LinearOpMode {

    public Robot robot = null;

    public void runOpMode() {
        robot = new Robot(hardwareMap, telemetry, this);
    }

    public void closeClaw()
    {
        openAndCloseRightClaw(0.58); //Moves right claw left GOOD DONE
        openAndCloseLeftClaw(0.38); // //Moves left claw right
    }

    public void openClaw()
    {
        openAndCloseLeftClaw(0.5); //Moves left claw left
        openAndCloseRightClaw(0.5); // Moves right claw right GOOD DONE .3 was good
    }

    public void openAndCloseLeftClaw (double position){
        robot.leftClaw.setPosition(position);

        if (position == 0){
            telemetry.addData("Left Claw", "Closed");
        } else if (position >= 0.3){
            telemetry.addData("Left Claw", "Open");
        }

    }

    public void openAndCloseRightClaw (double position) {
        robot.rightClaw.setPosition(position);

        if (position == 0) {
            telemetry.addData("Right Claw", "Closed");
        } else if (position >= 0.3) {
            telemetry.addData("Right Claw", "Open");
        }
    }

}
