package org.firstinspires.ftc.teamcode.Autonomous.Blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousPLUS;
import org.firstinspires.ftc.teamcode.Autonomous.TensorFlow;


@Autonomous(group = "C TensorFlow", name = "Blue Far Score")
public class BlueFarScore extends AutonomousPLUS {

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
                    telemetry.addData("Its time to", "Gatekeep, Gasslight Girlboss");
                    telemetry.addData("Ur gonna do", "great");
                }
                telemetry.update();

                sleep(20);
            }
        }
        //Start and position yellow
        waitForStart();
        robot.claw.closeClaw();
        prepareNextAction(300);
        prepareNextAction(300);
        //Branches here
        StrafeFromOdometry(150,"Up",0,2);

        if(target == "Left Zone")
        {
            //This is certainly not tested
            robot.arm.rotateArmDown();
            telemetry.addData("Going to", "Right");
            //moveRobotLeft(1500, 2);//This value is off
            speed = .70;
            StrafeFromOdometry(1030,"Up",2,2);
            TurnFromOdometry(90,"Left",2);
            StrafeFromOdometry(555,"Up",2,2);
            prepareNextAction(400);
            robot.claw.openSecondaryClaw();
            robot.arm.rotateArmUp();
            prepareNextAction(700);
            speed = .60;
            StrafeFromOdometry(1000,"Down",2,2);
            prepareNextAction(500);
            StrafeFromOdometry(1200,"Right",2,2);
            robot.arm.rotateArmDown();
            StrafeFromOdometry(35,"Right",0,2);
            prepareNextAction(400);
            speed = 0.90;
            StrafeFromOdometry(3600,"Up", 2,2);
            //turnRobotRight(35, 2); // this might be dumb
            speed = .8;
            StrafeFromOdometry(1570,"Left",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up",0.55);
            prepareNextAction(200);
            robot.arm.rotateArmUp();
            prepareNextAction(500);
            StrafeFromOdometry(400,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(550,"Down",0,2);
            robot.arm.rotateArmDown();
            robot.lift.moveLift("Down", 0.55);
            StrafeFromOdometry(1400,"Right",2,2);
            StrafeFromOdometry(700,"Up",2,2);

        }

        else if(target == "Center")
        {
            telemetry.addData("Going to", "Center");
            robot.arm.rotateArmDown();
            StrafeFromOdometry(100,"Right",0,2);
            StrafeFromOdometry(1600,"Up",2,2);
            prepareNextAction(200);//new
            speed = .75;
            robot.lift.moveLift("Up", .55);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(850,"Down",2,2);
            StrafeFromOdometry(900,"Right",2,2);
            StrafeFromOdometry(1400,"Up",2,2);
            TurnFromOdometry(90,"Left",2);
            robot.lift.moveLift("Down",0.55);
            StrafeFromOdometry(100,"Right",0,2);
            speed = 0.85;
            StrafeFromOdometry(3600,"Up",2,2);
            StrafeFromOdometry(1250,"Left",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up",0.55);
            prepareNextAction(200);
            robot.arm.rotateArmUp();
            prepareNextAction(200);
            StrafeFromOdometry(800,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(150,"Down",2,2);
            robot.lift.moveLift("Down",0.55);
            robot.arm.rotateArmDown();
        }

        else if(target == "Right Zone")
        {
            telemetry.addData("Going to", "Right");
            StrafeFromOdometry(950,"Up",2,2);
            StrafeFromOdometry(450,"Right",2,2);
            prepareNextAction(200);//new
            robot.lift.moveLift("Up", .55);
            StrafeFromOdometry(450,"Up",2,2);
            robot.claw.openSecondaryClaw();
            prepareNextAction(400);
            StrafeFromOdometry(850,"Down",2,2);

        } else {
            robot.arm.rotateArmDown();
            telemetry.addData("Going to", "Right");
            //moveRobotLeft(1500, 2);//This value is off
            speed = .70;
            StrafeFromOdometry(1030,"Up",2,2);
            TurnFromOdometry(90,"Left",2);
            StrafeFromOdometry(555,"Up",2,2);
            prepareNextAction(400);
            robot.claw.openSecondaryClaw();
            robot.arm.rotateArmUp();
            prepareNextAction(700);
            speed = .60;
            StrafeFromOdometry(1000,"Down",2,2);
            prepareNextAction(500);
            StrafeFromOdometry(1200,"Right",2,2);
            robot.arm.rotateArmDown();
            prepareNextAction(400);
            speed = 0.90;
            StrafeFromOdometry(3600,"Up",5,2);
            speed = .8;
            StrafeFromOdometry(1570,"Left",2,2);
            prepareNextAction(200);
            robot.lift.moveLift("Up",0.55);
            prepareNextAction(200);
            robot.arm.rotateArmUp();
            prepareNextAction(500);
            StrafeFromOdometry(500,"Up",2,2);
            robot.claw.openClaw();
            StrafeFromOdometry(550,"Down",2,2);
            robot.arm.rotateArmDown();
            robot.lift.moveLift("Down", 0.55);
            StrafeFromOdometry(1400,"Right",2,2);
            StrafeFromOdometry(700,"Up",2,2);

        }

    }
}
