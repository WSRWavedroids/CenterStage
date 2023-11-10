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
        prepareNextAction(300);
        sleepTime = 120;
        moveLift("Up", .55);//
        prepareNextAction(300);
        moveRobotLeft(150, 2);
        moveRobotForward(1150, 2); //900 was soo close
        robot.openClaw();
        moveRobotBackward(300, 2);
        moveRobotLeft(900, 2);
        moveRobotForward(1600, 2);
        sleepTime = 120;
        moveLift("Down", .55);//
        turnRobotRight(1070, 100);
        moveRobotForward(2700, 2);
        //robot.openClaw();
        //moveRobotBackward(150, 2);
        //moveRobotRight(3600, 2);
        // code goes here


    }
}
