package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;

@Autonomous(group = "Stage", name = "Red Far pixel pusher")
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
        moveRobotForward(1195, 2); //1175 was soo close
        robot.openClaw();
        moveRobotBackward(300, 2);
        moveRobotLeft(800, 2);
        moveRobotForward(1350, 2);//1200 was too short
        sleepTime = 120;
        moveLift("Down", .55);//
        turnRobotRight(100, 100);
        moveRobotForward(4500, 2);
        //robot.openClaw();
        //moveRobotBackward(150, 2);
        //moveRobotRight(3600, 2);
        // code goes here


    }
}
