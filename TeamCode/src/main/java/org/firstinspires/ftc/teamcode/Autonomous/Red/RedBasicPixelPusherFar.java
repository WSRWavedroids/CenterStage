package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

@Autonomous(group = "B Legacy", name = "Red Far pixel pusher")
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
        moveRobotForward(1205, 2); //1195 was soo close
        robot.openClaw();
        moveRobotBackward(300, 2);
        moveRobotLeft(750, 2);
        moveRobotForward(1340, 2);//1200 was too short
        sleepTime = 120;
        moveLift("Down", .55);//
        turnRobotRight(1070, 100);
        speed = 1;
        moveRobotForward(4000, 2);
        speed = 0.5;
        moveRobotForward(300, 2);
        //robot.openClaw();
        //moveRobotBackward(150, 2);
        //moveRobotRight(3600, 2);
        // code goes here


    }
}
