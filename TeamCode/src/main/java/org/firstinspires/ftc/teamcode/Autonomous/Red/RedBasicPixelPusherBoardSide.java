package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(group = "Stage", name = "Red Pixel Pusher Board Side")
    public class RedBasicPixelPusherBoardSide extends AutonomousPLUS {
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
            moveRobotForward(150, 2);

            //robot.rotateRightArm(0.2);

            moveRobotRight(1800, 2);
            moveRobotLeft(1650, 2); // was 100
            moveRobotForward(1080, 2); //900 was soo close
            claw.openClaw();
            moveRobotBackward(900, 2);
            moveRobotRight(1780, 2);
            moveRobotLeft(150, 2);

            // code goes here


        }
    }
