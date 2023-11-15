package org.firstinspires.ftc.teamcode.Autonomous.Red;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Drivetrain;
import org.firstinspires.ftc.teamcode.Robot.Hook;
import org.firstinspires.ftc.teamcode.Robot.Lift;
import org.firstinspires.ftc.teamcode.Robot.Robot;

@Autonomous(group = "Stage", name = "Red Far pixel pusher")
public class RedBasicPixelPusherFar extends AutonomousPLUS {

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
        moveRobotLeft(150, 2);
        moveRobotForward(1195, 2); //1175 was soo close
        claw.openClaw();
        moveRobotBackward(300, 2);
        moveRobotLeft(800, 2);
        moveRobotForward(1350, 2);//1200 was too short
        sleepTime = 120;
        lift.moveLift("Down", .55);//
        turnRobotRight(100, 100);
        moveRobotForward(4500, 2);
        //robot.openClaw();
        //moveRobotBackward(150, 2);
        //moveRobotRight(3600, 2);
        // code goes here


    }
}
