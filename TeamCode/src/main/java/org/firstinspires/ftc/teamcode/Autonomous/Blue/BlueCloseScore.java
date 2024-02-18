package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Close Score")
public class BlueCloseScore extends AutonomousPLUS {

    public TensorFlow TF = new TensorFlow();
    public String currentPosition;
    public String target;

    public void runOpMode() {

        super.runOpMode();
        if (opModeInInit()) {
            //Needs TF reference
            TF.initTfod(robot.hardwareMap);
            while (opModeInInit()) {
                currentPosition = TF.position(TF.tfod);
                telemetry.addData(target, "Its here");

                if (currentPosition != null) {
                    target = currentPosition;
                }
                telemetry.update();
                sleep(20);
            }
        }
        //Start and position yellow
        waitForStart();
        telemetry.addData(currentPosition,"here now");
        robot.claw.closeClaw();
        robot.claw.closeSecondaryClaw();
        prepareNextAction(300);
        //Branches here
        StrafeFromOdometry(150,"Up",0,2);
        telemetry.addData(currentPosition,"still here");
        speed = .75;

        if(target == "Left Zone")
        {
            telemetry.addData("Going to", "Left");
            robot.arm.rotateArmUp();
            StrafeFromOdometry(700,"Left",2,1);
            StrafeFromOdometry(1100,"Up",2,1);
            prepareNextAction(150);
            robot.claw.openSecondaryClaw();
            prepareNextAction(150);
            StrafeFromOdometry(600,"Down",2,1);
            StrafeFromOdometry(700,"Left",2,1);
            speed = .5;
            TurnFromOdometry(90,"Left",2);
            StrafeFromOdometry(350,"Right",2,1);
            speed = .75;
            StrafeFromOdometry(685,"Up",0,2);
            prepareNextAction(150);
            robot.claw.openClaw();
            prepareNextAction(150);
            StrafeFromOdometry(450,"Down",2,1);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1050,"Left",1,2);
            StrafeFromOdometry(650,"Up",1,1);
        }


        else if(target == "Center")
        {
            telemetry.addData("Going to", "Center");
            robot.arm.rotateArmDown();
            StrafeFromOdometry(100,"Left",0,2);
            StrafeFromOdometry(1600,"Up",2,2);
            prepareNextAction(200);//new
            speed = .75;
            robot.lift.moveLift("Up", .55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(750,"Down",2,2);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmUp();
            TurnFromOdometry(90, "Left",1);
            StrafeFromOdometry(1425,"Up",5,1);
            // moveRobotRight(150, 1);
            speed = .5;
            StrafeFromOdometry(200,"Up",0,1);
            robot.claw.openClaw();
            prepareNextAction(50);
            StrafeFromOdometry(450,"Down",1,1);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(1150,"Left",1,1);
            StrafeFromOdometry(500,"Up",1,1);
        }

        else if(target == "Right Zone")
        {
            telemetry.addData("Going to", "Right ");
            speed = 0.5;
            StrafeFromOdometry(100,"Left",1,1);
            StrafeFromOdometry(1000,"Up",1,1);
            TurnFromOdometry(90,"Right",1);
            StrafeFromOdometry(100,"Left",0,1);
            StrafeFromOdometry(565,"Up",1,1);
            prepareNextAction(200);
            robot.lift.moveLift("Up", .55);
            prepareNextAction(200);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            speed = .75;
            StrafeFromOdometry(1450,"Down",1,1);
            prepareNextAction(200);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            TurnFromOdometry(180,"Left",1);
            StrafeFromOdometry(785,"Up",2,1);
            StrafeFromOdometry(200,"Right",0,1);
            prepareNextAction(100);
            robot.claw.openClaw();
            prepareNextAction(100);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(450,"Down",1,1);
            StrafeFromOdometry(1500,"Left",2,1);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(750,"Up",1,1);

        } else {
            telemetry.addData("Going to", "Right ");
            speed = 0.5;
            StrafeFromOdometry(100,"Left",1,1);
            StrafeFromOdometry(1000,"Up",1,1);
            TurnFromOdometry(90,"Right",1);
            StrafeFromOdometry(100,"Left",0,1);
            StrafeFromOdometry(565,"Up",1,1);
            prepareNextAction(200);
            robot.lift.moveLift("Up", .55);
            prepareNextAction(200);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            speed = .75;
            StrafeFromOdometry(1450,"Down",1,1);
            prepareNextAction(200);
            robot.lift.moveLift("Down", .55);
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            TurnFromOdometry(180,"Left",1);
            StrafeFromOdometry(785,"Up",2,1);
            StrafeFromOdometry(200,"Right",0,1);
            prepareNextAction(100);
            robot.claw.openClaw();
            prepareNextAction(100);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(450,"Down",1,1);
            StrafeFromOdometry(1500,"Left",2,1);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(750,"Up",1,1);

        }


    }
}
