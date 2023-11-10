package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(group = "Stage", name = "Far pixel pusher")
public class RedBasicPixelPusherFar extends AutonomousPLUS {
    @Override
    public void runOpMode() {

        super.runOpMode();

        //Do this to pass inspection.
        waitForStart();
        robot.closeClaw();
        prepareNextAction(100);
        sleepTime = 250;
        moveLift("Up", .55);//
        prepareNextAction(300);
        moveRobotLeft(150, 2);
        moveRobotForward(1150, 2); //900 was soo close
        robot.openClaw();
        moveRobotBackward(300, 2);
        moveRobotLeft(450, 2);
        moveRobotForward(950, 2);
        turnRobotRight(1070, 100);
        moveRobotForward(2700, 2);
        //robot.openClaw();
        //moveRobotBackward(150, 2);
        //moveRobotRight(3600, 2);
        // code goes here


    }
}
