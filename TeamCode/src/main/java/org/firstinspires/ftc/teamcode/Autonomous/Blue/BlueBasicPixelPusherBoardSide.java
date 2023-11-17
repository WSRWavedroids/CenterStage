package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;


@Autonomous(group = "Stage", name = "Blue Pixel Pusher Board Side")
    public class BlueBasicPixelPusherBoardSide extends AutonomousPLUS {

    public Robot robot = null;
    public Lift lift = new Lift();
    public Hook hook = new Hook();
    public Claw claw = new Claw();
    public Drivetrain DT = new Drivetrain();
        @Override
        public void runOpMode() {

            super.runOpMode();

            //Do this to pass inspection.
            waitForStart();
            claw.closeClaw();
            prepareNextAction(300);
            sleepTime = 120;
            lift.moveLift("Up", .55);//
            prepareNextAction(300);
            StrafeFromOdometry(0,150,2);
            //moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            StrafeFromOdometry(-1800,0,2);
            //moveRobotLeft(1800, 2);//Flipped
            StrafeFromOdometry(1650,0,2);
            //moveRobotRight(1650, 2); // was 100
            StrafeFromOdometry(0,1080,2);
            //moveRobotForward(1080, 2); //900 was soo close
            claw.openClaw();
            StrafeFromOdometry(0,-900,2);
            //moveRobotBackward(900, 2);
            StrafeFromOdometry(-1780,0,2);
            //moveRobotLeft(1780, 2);
            StrafeFromOdometry(0,150,2);
            //moveRobotRight(150, 2);

            // code goes here


        }
    }


